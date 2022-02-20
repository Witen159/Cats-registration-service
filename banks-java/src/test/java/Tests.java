import banks.classes.CentralBank;
import banks.classes.account.AccountTemplate;
import banks.classes.bank.Bank;
import banks.classes.bank.PercentAmount;
import banks.classes.client.Client;
import banks.classes.client.ClientBuilder;
import banks.classes.client.ClientDirector;
import banks.classes.observer.notification.CommissionNotification;
import banks.classes.observer.notification.CreditLimitNotification;
import banks.classes.observer.notification.OperationLimitNotification;
import banks.classes.transaction.AbstractTransaction;
import banks.tools.BankException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tests {
    private CentralBank _centralBank;
    private ClientDirector _clientDirector;
    private ClientBuilder _clientBuilder;
    private LocalDateTime _startTime;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup()
    {
        _startTime = LocalDateTime.of(2022, 1, 1, 1, 1);
        _centralBank = CentralBank.getInstance(_startTime);
        _clientDirector = new ClientDirector();
        _clientBuilder = new ClientBuilder();
        _clientDirector.setBuilder(_clientBuilder);
    }

    @Test
    public void operationLimitAndTransactionCancelTest() throws BankException {
        _clientDirector.buildFullClient("Ivan", "Ivanov", 12345 ,"alaska");
        Client ivan = _clientBuilder.getClient();
        _clientDirector.buildClientWithAddress("Denis", "Denisov", "alaska");
        Client denis = _clientBuilder.getClient();

        var a = new ArrayList<Integer>();
        a.add(50000);
        a.add(100000);
        var b = new ArrayList<Double>();
        b.add(1.0);
        b.add(2.0);
        b.add(3.0);

        var percentAmount = new PercentAmount(a, b);

        Bank bank = _centralBank.registerNewBank("Тинькофф", 10000, 10000, percentAmount, 3, 1000);
        bank.registerNewClient(ivan);
        bank.registerNewClient(denis);

        AccountTemplate ivanDebit = bank.addDebitAccount(ivan, 75000);
        AccountTemplate denisDebit = bank.addDebitAccount(denis, 150000);

        AbstractTransaction transaction = bank.transfer(ivanDebit, denisDebit, 30000);
        Assert.assertEquals(denisDebit.getMoney(), 180000, 0);
        Assert.assertEquals(ivanDebit.getMoney(), 45000, 0);

        _centralBank.cancelOperation(transaction);
        Assert.assertEquals(denisDebit.getMoney(), 150000, 0);
        Assert.assertEquals(ivanDebit.getMoney(), 75000, 0);

        thrown.expect(BankException.class);
        bank.withdrawal(denisDebit, 30000);
        thrown.expect(BankException.class);
        bank.transfer(denisDebit, ivanDebit, 30000);
    }

    @Test
    public void runTimeTest() throws BankException {
        _clientDirector.buildFullClient("Ivan", "Ivanov", 12345 ,"alaska");
        Client client = _clientBuilder.getClient();

        var a = new ArrayList<Integer>();
        a.add(50000);
        a.add(100000);
        var b = new ArrayList<Double>();
        b.add(1.0);
        b.add(2.0);
        b.add(3.0);

        var percentAmount = new PercentAmount(a, b);
        Bank bank = _centralBank.registerNewBank("Тинькофф", 10000, 10000, percentAmount, 3, 1000);
        bank.registerNewClient(client);

        AccountTemplate debit = bank.addDebitAccount(client, 40000);
        AccountTemplate deposit = bank.addDepositAccount(client, 40000, _centralBank.getCurrentTime().plusDays(40));
        AccountTemplate credit = bank.addCreditAccount(client, 40000);

        // В конце месяца на дебетовый и депозитный упали проценты
        _centralBank.newDate(_centralBank.getCurrentTime().plusDays(31));
        Assert.assertEquals(debit.getMoney(), 40101.92, 0);
        Assert.assertEquals(deposit.getMoney(), 40033.97, 0);

        // Депозитный счет закрылся, оставшийся остаток на процент зачислился на счет. Дебетовый не изменился
        _centralBank.newDate(_centralBank.getCurrentTime().plusDays(10));
        Assert.assertEquals(debit.getMoney(), 40101.92, 0);
        Assert.assertEquals(deposit.getMoney(), 40044.94, 0);

        // Прошел еще месяц. Депозитный счет не изменился (закрыт), дебетовый обновился
        _centralBank.newDate(_centralBank.getCurrentTime().plusDays(20));
        Assert.assertEquals(debit.getMoney(), 40194.21, 0);
        Assert.assertEquals(deposit.getMoney(), 40044.94, 0);

        // Кредитный счет в плюсе, комиссии не было
        Assert.assertEquals(credit.getMoney(), 40000, 0);

        // Кредитный счет ушел в минус, снималась комиссия
        bank.withdrawal(credit, 41000);
        _centralBank.newDate(_centralBank.getCurrentTime().plusDays(5));
        Assert.assertEquals(credit.getMoney(), -6000, 0);

        // Баланс на кредитном счете упал ниже кредитного лимита
        thrown.expect(BankException.class);
        _centralBank.newDate(_centralBank.getCurrentTime().plusDays(5));
    }

    @Test
    public void notificationTest() throws BankException {
        _clientDirector.buildFullClient("Ivan", "Ivanov", 12345 ,"alaska");
        Client client = _clientBuilder.getClient();

        var a = new ArrayList<Integer>();
        a.add(50000);
        a.add(100000);
        var b = new ArrayList<Double>();
        b.add(1.0);
        b.add(2.0);
        b.add(3.0);

        var percentAmount = new PercentAmount(a, b);
        Bank bank = _centralBank.registerNewBank("Тинькофф", 10000, 10000, percentAmount, 3, 1000);
        bank.registerNewClient(client);

        bank.addObserver(client);
        bank.getBankParametersChanger().changeCommission(1500);
        bank.getBankParametersChanger().changeOperationLimit(11000);
        bank.getBankParametersChanger().changeCreditNegativeLimit(12000);

        Assert.assertTrue(client.getNotifications().get(0) instanceof CommissionNotification);
        Assert.assertTrue(client.getNotifications().get(1) instanceof OperationLimitNotification);
        Assert.assertTrue(client.getNotifications().get(2) instanceof CreditLimitNotification);
    }
}
