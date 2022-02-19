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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Tests {
    private CentralBank _centralBank;
    private ClientDirector _clientDirector;
    private ClientBuilder _clientBuilder;
    private LocalDateTime _startTime;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void Setup()
    {
        _startTime = LocalDateTime.of(2022, 1, 1, 1, 1);
        _centralBank = CentralBank.GetInstance(_startTime);
        _clientDirector = new ClientDirector();
        _clientBuilder = new ClientBuilder();
        _clientDirector.SetBuilder(_clientBuilder);
    }

    @Test
    public void OperationLimitAndTransactionCancelTest() throws BankException {
        _clientDirector.BuildFullClient("Ivan", "Ivanov", 12345 ,"alaska");
        Client ivan = _clientBuilder.GetClient();
        _clientDirector.BuildClientWithAddress("Denis", "Denisov", "alaska");
        Client denis = _clientBuilder.GetClient();

        var a = new ArrayList<Integer>();
        a.add(50000);
        a.add(100000);
        var b = new ArrayList<Double>();
        b.add(1.0);
        b.add(2.0);
        b.add(3.0);

        var percentAmount = new PercentAmount(a, b);

        Bank bank = _centralBank.RegisterNewBank("Тинькофф", 10000, 10000, percentAmount, 3, 1000);
        bank.RegisterNewClient(ivan);
        bank.RegisterNewClient(denis);

        AccountTemplate ivanDebit = bank.AddDebitAccount(ivan, 75000);
        AccountTemplate denisDebit = bank.AddDebitAccount(denis, 150000);

        AbstractTransaction transaction = bank.Transfer(ivanDebit, denisDebit, 30000);
        Assert.assertEquals(denisDebit.GetMoney(), 180000, 0);
        Assert.assertEquals(ivanDebit.GetMoney(), 45000, 0);

        _centralBank.CancelOperation(transaction);
        Assert.assertEquals(denisDebit.GetMoney(), 150000, 0);
        Assert.assertEquals(ivanDebit.GetMoney(), 75000, 0);

        thrown.expect(BankException.class);
        bank.Withdrawal(denisDebit, 30000);
        thrown.expect(BankException.class);
        bank.Transfer(denisDebit, ivanDebit, 30000);
        // Assert.Throws<BankException>(() => bank.Withdrawal(denisDebit, 30000));
        // Assert.Throws<BankException>(() => bank.Transfer(denisDebit, ivanDebit, 30000));
    }

    @Test
    public void RunTimeTest() throws BankException {
        _clientDirector.BuildFullClient("Ivan", "Ivanov", 12345 ,"alaska");
        Client client = _clientBuilder.GetClient();

        var a = new ArrayList<Integer>();
        a.add(50000);
        a.add(100000);
        var b = new ArrayList<Double>();
        b.add(1.0);
        b.add(2.0);
        b.add(3.0);

        var percentAmount = new PercentAmount(a, b);
        Bank bank = _centralBank.RegisterNewBank("Тинькофф", 10000, 10000, percentAmount, 3, 1000);
        bank.RegisterNewClient(client);

        AccountTemplate debit = bank.AddDebitAccount(client, 40000);
        AccountTemplate deposit = bank.AddDepositAccount(client, 40000, _centralBank.GetCurrentTime().plusDays(40));
        AccountTemplate credit = bank.AddCreditAccount(client, 40000);

        // В конце месяца на дебетовый и депозитный упали проценты
        _centralBank.NewDate(_centralBank.GetCurrentTime().plusDays(31));
        Assert.assertEquals(debit.GetMoney(), 40101.92, 0);
        Assert.assertEquals(deposit.GetMoney(), 40033.97, 0);

        // Депозитный счет закрылся, оставшийся остаток на процент зачислился на счет. Дебетовый не изменился
        _centralBank.NewDate(_centralBank.GetCurrentTime().plusDays(10));
        Assert.assertEquals(debit.GetMoney(), 40101.92, 0);
        Assert.assertEquals(deposit.GetMoney(), 40044.94, 0);

        // Прошел еще месяц. Депозитный счет не изменился (закрыт), дебетовый обновился
        _centralBank.NewDate(_centralBank.GetCurrentTime().plusDays(20));
        Assert.assertEquals(debit.GetMoney(), 40194.21, 0);
        Assert.assertEquals(deposit.GetMoney(), 40044.94, 0);

        // Кредитный счет в плюсе, комиссии не было
        Assert.assertEquals(credit.GetMoney(), 40000, 0);

        // Кредитный счет ушел в минус, снималась комиссия
        bank.Withdrawal(credit, 41000);
        _centralBank.NewDate(_centralBank.GetCurrentTime().plusDays(5));
        Assert.assertEquals(credit.GetMoney(), -6000, 0);

        // Баланс на кредитном счете упал ниже кредитного лимита
        thrown.expect(BankException.class);
        _centralBank.NewDate(_centralBank.GetCurrentTime().plusDays(5));
        //Assert.ер<BankException>(() => _centralBank.NewDate(_centralBank.GetCurrentTime().plusDays(5)));
    }

    @Test
    public void NotificationTest() throws BankException {
        _clientDirector.BuildFullClient("Ivan", "Ivanov", 12345 ,"alaska");
        Client client = _clientBuilder.GetClient();

        var a = new ArrayList<Integer>();
        a.add(50000);
        a.add(100000);
        var b = new ArrayList<Double>();
        b.add(1.0);
        b.add(2.0);
        b.add(3.0);

        var percentAmount = new PercentAmount(a, b);
        Bank bank = _centralBank.RegisterNewBank("Тинькофф", 10000, 10000, percentAmount, 3, 1000);
        bank.RegisterNewClient(client);

        bank.AddObserver(client);
        bank.getBankParametersChanger().ChangeCommission(1500);
        bank.getBankParametersChanger().ChangeOperationLimit(11000);
        bank.getBankParametersChanger().ChangeCreditNegativeLimit(12000);

        Assert.assertTrue(client.GetNotifications().get(0) instanceof CommissionNotification);
        Assert.assertTrue(client.GetNotifications().get(1) instanceof OperationLimitNotification);
        Assert.assertTrue(client.GetNotifications().get(2) instanceof CreditLimitNotification);
    }
}
