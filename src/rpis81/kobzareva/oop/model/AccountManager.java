package rpis81.kobzareva.oop.model;

public class AccountManager {
    private Account[] accounts;
    private int size;
    private final static Account DEFAULT_ACCOUNT = new Account();
    final static IndividualsTariff DEFAULT_TARIFF = new IndividualsTariff();
    // public AccountManager(){}

    // принимающий массив счетов. В этом конструкторе происходит копирование элементов в
    //новый массив, и ссылка на него записывается в атрибут.
    public AccountManager(Account[] accountArray){
        this.accounts = accountArray;
        this.size = accountArray.length;
    }

    // принимающий один параметр – число счетов, инициализирующий массив соответствующим числом элементов
    public AccountManager(int size){
        this.accounts =new Account[size];
        this.size=size;
    }

    // 1) добавляющий счет в первое свободное место в массиве
    public boolean add(Account account){
        for (int i = 0; i< accounts.length; i++){
            if (accounts[i]==null) {
                accounts[i]=account;
                size++;
                return true;
            }
        }
        return false;
    }

    // 2) добавляющий счет в заданное место в массиве
    public boolean add(int index, Account account) {
        doubleUp();
        Account[] newArray = new Account[accounts.length+1];
        // свиг вправо от места вставки
        for (int i = 0; i < getAccounts().length; i++)
        {
            if (i < index)
            {
                newArray[i] = getAccounts()[i];
            }
            else if (i > index)
            {
                newArray[i + 1] = getAccounts()[i];
            }
        }
        newArray[index] = account;
        accounts = newArray;
        return false;
    }

    // 3) возвращающий ссылку на экземпляр класса Account по его номеру в массиве
    public Account getAccount(int index){
        return accounts[index];
    }

    // 4) изменяющий ссылку на экземпляр класса Account по его номеру в массиве
    public Account setAccount(int number, Account account){
        Account pastAccount = new Account();
        for (int i = 0; i< accounts.length; i++){
            if (accounts[i].getNumber()==number) {
                pastAccount = accounts[i];
                accounts[i] = account;
                return pastAccount;
            }
        }
        return DEFAULT_ACCOUNT;
    }

    //5) удаляющий элемент из массива по его номеру
    public Account remove(int index) {
        Account deletedAccount = accounts[index];
        Account[] newArray = new Account[getAccounts().length - 1];
        for (int i = 0; i < getAccounts().length; i++)
        {
            if (i < index)
            {
                newArray[i] = getAccounts()[i];
            }
            else if (i > index)
            {
                newArray[i - 1] = getAccounts()[i];
            }
        }
        accounts = newArray;
        return deletedAccount;
    }
    //6) возвращающий число счетов
    public int getCountOfAccount(){
        int countOfAccount = 0;
        for (int i = 0; i< accounts.length; i++){
            if (accounts[i]!=null) countOfAccount++;
        }
        return countOfAccount;
    }

    //7) возвращающий массив счетов
    public Account[] getAccounts(){
        Account[] newServices = new Account[getCountOfAccount()];
        int counter = 0;
        for (Account account : accounts){
            if (account!=null) {
                newServices[counter] = account;
                counter++;
            }
        }
        return newServices;
    }

    // 8) возвращающий ссылку на экземпляр класса IndividualsTariff для счета с заданным номером
    public IndividualsTariff getTariff(long accountNumber){
        for (Account account: getAccounts()){
            if (account.getNumber()==accountNumber) return account.getIndividualsTariff();
        }
        return DEFAULT_TARIFF;
    }

    // 9) изменяющий ссылку на экземпляр класса IndividualsTariff для счета с заданным номером
    public  IndividualsTariff setTariff(int accountNumber,IndividualsTariff tariff){
        IndividualsTariff individualsTariff = getTariff(accountNumber);
        for (int i = 0; i< getAccounts().length; i++){
            if (getAccounts()[i].getNumber()==accountNumber) accounts[i].setIndividualsTariff(tariff);
        }
        return individualsTariff;
    }

    private void doubleUp() {
        if (accounts[accounts.length - 1] != null) {
            Account[] updatedRentedAccounts = new Account[size * 2];
            System.arraycopy(accounts, 0, updatedRentedAccounts, 0, accounts.length);
            accounts = updatedRentedAccounts;
        }
    }
}
