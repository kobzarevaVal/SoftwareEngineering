package rpis81.kobzareva.oop.model;

import java.util.Arrays;
import java.util.Objects;

//todo: комментарии из IndividualsTariff применимы сюда
public class AccountManager {
    private Account[] accounts;
    private int size;
    // private final static Account DEFAULT_ACCOUNT = new Account();
    final static IndividualsTariff DEFAULT_TARIFF = new IndividualsTariff();

    // принимающий массив счетов. В этом конструкторе происходит копирование элементов в
    //новый массив, и ссылка на него записывается в атрибут.
    public AccountManager(Account[] accountArray) {
        this.accounts = accountArray;
        //  this.size = accountArray.length;
    }

    // принимающий один параметр – число счетов, инициализирующий массив соответствующим числом элементов
    public AccountManager(int size) {
        this.accounts = new Account[size];
        this.size = size;
    }

    public boolean add(Account account){
        doubleUp();
        for (int i = 0; i<accounts.length;i++){
            if (accounts[i]==null) {
                accounts[i] = account;
                size++;
                return true;
            }
        }
        return false;
    }
    // 2) добавляет на конкретное место в массиве
    public boolean add(int index, Account account) {
        doubleUp();
        // свиг вправо от места вставки
        if (accounts.length >= index){
            System.arraycopy(accounts, index, accounts, index + 1, accounts.length - index - 1);
        }
        return add(account);
    }
    private void doubleUp() {
        if (accounts[accounts.length - 1] != null) {
            Account[] updatedRentedAccounts = new Account[size * 2];
            System.arraycopy(accounts, 0, updatedRentedAccounts, 0, accounts.length);
            accounts = updatedRentedAccounts;
        }
    }

    // 3) возвращающий ссылку на экземпляр класса Account по его номеру в массиве
    public Account getAccount(int index) {
        return accounts[index];
    }

    // 4) изменяющий ссылку на экземпляр класса Account по его номеру в массиве
    public Account setAccount(int number, Account account) {
        Account lostAccount;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getNumber() == number) {
                lostAccount = accounts[i];
                accounts[i] = account;
                return lostAccount;
            }
        }
        return null;
    }

    // удалить по номеру
    public Account remove(int index) {
        Account lostAccount = accounts[index];
        accounts[index] = null;
        size--;
        accounts = getAccounts();
        return lostAccount;
    }

    //6) возвращающий число счетов
    public int getCountOfAccount() {
        return getAccounts().length;
    }

    //7) возвращающий массив счетов
    public  Account[] getAccounts(){
        accounts = Arrays.stream(accounts).filter(Objects::nonNull).toArray(Account[]::new);
        return accounts;
    }

    // 8) возвращающий ссылку на экземпляр класса IndividualsTariff для счета с заданным номером
    public IndividualsTariff getTariff(long accountNumber) {
        for(int i=0;i<size;i++){
            if (isEquals(i,accountNumber)) return getAccounts()[i].getIndividualsTariff();
        }

        return DEFAULT_TARIFF;
    }
    private boolean isEquals(int index, long accountNumber){
        return getAccounts()[index].getNumber()==accountNumber;
    }

    // 9) изменяющий ссылку на экземпляр класса IndividualsTariff для счета с заданным номером
    public IndividualsTariff setTariff(int accountNumber, IndividualsTariff tariff) {
        IndividualsTariff individualsTariff = getTariff(accountNumber);
        for (int i = 0; i < getAccounts().length; i++) {
            if (isEquals(i,accountNumber)) accounts[i].setIndividualsTariff(tariff);
        }
        return individualsTariff;
    }
}
