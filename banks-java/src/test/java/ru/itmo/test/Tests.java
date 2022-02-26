package ru.itmo.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.itmo.banks.entity.CentralBank;
import ru.itmo.banks.entity.account.AbstractAccount;
import ru.itmo.banks.entity.bank.Bank;
import ru.itmo.banks.entity.bank.PercentAmount;
import ru.itmo.banks.entity.client.Client;
import ru.itmo.banks.entity.client.ClientBuilderImpl;
import ru.itmo.banks.entity.client.ClientDirector;
import ru.itmo.banks.entity.observer.notification.CommissionNotification;
import ru.itmo.banks.entity.observer.notification.CreditLimitNotification;
import ru.itmo.banks.entity.observer.notification.OperationLimitNotification;
import ru.itmo.banks.entity.transaction.AbstractTransaction;
import ru.itmo.banks.tools.BankException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tests {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private CentralBank centralBank;
    private ClientDirector clientDirector;
    private ClientBuilderImpl clientBuilder;
    private LocalDateTime startTime;

    @Before
    public void setup() {
        startTime = LocalDateTime.of(2022, 1, 1, 1, 1);
        centralBank = CentralBank.getInstance(startTime);
        clientDirector = new ClientDirector();
        clientBuilder = new ClientBuilderImpl();
        clientDirector.setBuilder(clientBuilder);
    }

    @Test
    public void operationLimitAndTransactionCancelTest() {
        clientDirector.buildFullClient("Ivan", "Ivanov", 12345, "alaska");
        Client ivan = clientBuilder.getClient();
        clientDirector.buildClientWithAddress("Denis", "Denisov", "alaska");
        Client denis = clientBuilder.getClient();

        var a = new ArrayList<Integer>();
        a.add(50000);
        a.add(100000);
        var b = new ArrayList<Double>();
        b.add(1.0);
        b.add(2.0);
        b.add(3.0);

        var percentAmount = new PercentAmount(a, b);

        Bank bank = centralBank.registerNewBank("Тинькофф", 10000, 10000, percentAmount, 3, 1000);
        bank.registerNewClient(ivan);
        bank.registerNewClient(denis);

        AbstractAccount ivanDebit = bank.addDebitAccount(ivan, 75000);
        AbstractAccount denisDebit = bank.addDebitAccount(denis, 150000);

        AbstractTransaction transaction = bank.transfer(ivanDebit, denisDebit, 30000);
        Assert.assertEquals(denisDebit.getMoney(), 180000, 0);
        Assert.assertEquals(ivanDebit.getMoney(), 45000, 0);

        centralBank.cancelOperation(transaction);
        Assert.assertEquals(denisDebit.getMoney(), 150000, 0);
        Assert.assertEquals(ivanDebit.getMoney(), 75000, 0);

        thrown.expect(BankException.class);
        bank.withdrawal(denisDebit, 30000);
        thrown.expect(BankException.class);
        bank.transfer(denisDebit, ivanDebit, 30000);
    }

    @Test
    public void runTimeTest() {
        clientDirector.buildFullClient("Ivan", "Ivanov", 12345, "alaska");
        Client client = clientBuilder.getClient();

        var a = new ArrayList<Integer>();
        a.add(50000);
        a.add(100000);
        var b = new ArrayList<Double>();
        b.add(1.0);
        b.add(2.0);
        b.add(3.0);

        var percentAmount = new PercentAmount(a, b);
        Bank bank = centralBank.registerNewBank("Тинькофф", 10000, 10000, percentAmount, 3, 1000);
        bank.registerNewClient(client);

        AbstractAccount debit = bank.addDebitAccount(client, 40000);
        AbstractAccount deposit = bank.addDepositAccount(client, 40000, centralBank.getCurrentTime().plusDays(40));
        AbstractAccount credit = bank.addCreditAccount(client, 40000);


        // В конце месяца на дебетовый и депозитный упали проценты
        centralBank.newDate(centralBank.getCurrentTime().plusDays(31));
        Assert.assertEquals(Math.round(debit.getMoney() * 100.0) / 100.0, 40101.92, 0);
        Assert.assertEquals(Math.round(deposit.getMoney() * 100.0) / 100.0, 40033.97, 0);

        // Депозитный счет закрылся, оставшийся остаток на процент зачислился на счет. Дебетовый не изменился
        centralBank.newDate(centralBank.getCurrentTime().plusDays(10));
        Assert.assertEquals(Math.round(debit.getMoney() * 100.0) / 100.0, 40101.92, 0);
        Assert.assertEquals(Math.round(deposit.getMoney() * 100.0) / 100.0, 40044.94, 0);

        // Прошел еще месяц. Депозитный счет не изменился (закрыт), дебетовый обновился
        centralBank.newDate(centralBank.getCurrentTime().plusDays(20));
        Assert.assertEquals(Math.round(debit.getMoney() * 100.0) / 100.0, 40194.21, 0);
        Assert.assertEquals(Math.round(deposit.getMoney() * 100.0) / 100.0, 40044.94, 0);

        // Кредитный счет в плюсе, комиссии не было
        Assert.assertEquals(credit.getMoney(), 40000, 0);

        // Кредитный счет ушел в минус, снималась комиссия
        bank.withdrawal(credit, 41000);
        centralBank.newDate(centralBank.getCurrentTime().plusDays(5));
        Assert.assertEquals(credit.getMoney(), -6000, 0);

        // Баланс на кредитном счете упал ниже кредитного лимита
        thrown.expect(BankException.class);
        centralBank.newDate(centralBank.getCurrentTime().plusDays(5));
    }

    @Test
    public void notificationTest() {
        clientDirector.buildFullClient("Ivan", "Ivanov", 12345, "alaska");
        Client client = clientBuilder.getClient();

        var a = new ArrayList<Integer>();
        a.add(50000);
        a.add(100000);
        var b = new ArrayList<Double>();
        b.add(1.0);
        b.add(2.0);
        b.add(3.0);

        var percentAmount = new PercentAmount(a, b);
        Bank bank = centralBank.registerNewBank("Тинькофф", 10000, 10000, percentAmount, 3, 1000);
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
