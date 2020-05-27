package rpis81.kobzareva.oop.model;
import java.util.*;

//todo: комментарии из IndividualsTariff применимы сюда
                            // Lb 6
public class AccountManager  implements Iterable<Account> {
    private Account[] accounts;
    private int size;
    // private final static Account DEFAULT_ACCOUNT = new Account();
    final static IndividualsTariff DEFAULT_TARIFF = new IndividualsTariff();
    private final long MIN_NUMBER = 001000000000001L;
    private final long MAX_NUMBER = 999999999999999L;

    // принимающий массив счетов. В этом конструкторе происходит копирование элементов в
    //новый массив, и ссылка на него записывается в атрибут.
    public AccountManager(Account[] accountArray) {
        this.accounts = accountArray;
          this.size = accountArray.length;
    }

    // принимающий один параметр – число счетов, инициализирующий массив соответствующим числом элементов
    public AccountManager(int size) {
        this.accounts = new Account[size];
        this.size = size;
    }

    public boolean add(Account account) throws DublicateAccountNumberException{
        if(Objects.isNull(account)) throw new NullPointerException("Значение account не должно быть null");
        if (isAccountExists(account.getNumber())) throw new DublicateAccountNumberException("Аккаунт с таким номером уже существует");
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
    public boolean add(int index, Account account) throws DublicateAccountNumberException{
        if (checkIndex(index)) throw new IndexOutOfBoundsException("Индекс вне границ диапазона");
        if (index<=0 || index>size) throw new IndexOutOfBoundsException("Индекс вне границ массива");
        if(Objects.isNull(account)) throw new NullPointerException("Значение account не должно быть null");

        doubleUp();
        // свиг вправо от места вставки
        if (accounts.length >= index){
            System.arraycopy(accounts, index, accounts, index + 1, accounts.length - index - 1);
        }
        return add(account);
    }

    private boolean checkIndex(int index){
        return index < 0 || index >= size;
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
        if (checkIndex(index)) throw new IndexOutOfBoundsException("Индекс вне границ диапазона");
        return getAccounts()[index];
    }

    // 4) изменяющий ссылку на экземпляр класса Account по его номеру в массиве
    public Account setAccount(long number, Account account) throws DublicateAccountNumberException {
        if (isAccountExists(account.getNumber())) throw new DublicateAccountNumberException("Аккаунт с таким номером уже существует");
        if(Objects.isNull(account)) throw new NullPointerException("Значение account не должно быть null");
        if (!isInRange(number)) throw new IllegalAccountNumber("Номер вне границ диапазона");

        Account lostAccount;
        for (int i = 0; i < accounts.length; i++) {
            if (!isAccountExists(number))
            if (accounts[i].getNumber() == number) {
                lostAccount = accounts[i];
                accounts[i] = account;
                return lostAccount;
            }
        }
        return null;
    }

    public boolean isInRange(long number){
        return number< MIN_NUMBER || number>MAX_NUMBER;
    }

    private boolean isAccountExists(long number){
        for (Account account:getAccounts()){
            if(account.getNumber()==number) return true;
        }
        return false;
    }

    // удалить по номеру
    public Account remove(int index) {
        if (checkIndex(index)) throw new IndexOutOfBoundsException("Индекс вне границ диапазона");

        Account lostAccount = accounts[index];
        accounts[index] = null;
        size--;
        accounts = getAccounts();
        return lostAccount;
    }

    //6) возвращающий число счетов
    public int getCountOfAccount() {
        int size = 0;
        for (int i=0;i<accounts.length;i++){
            if (accounts[i]!=null) size++;
        }
        return size;
    }

    //7) возвращающий массив счетов
    // см. комментарий из IndividualsTariff
    public  Account[] getAccounts(){
        Account[] result = new Account[getCountOfAccount()];
        int index =0;
        for (int i=0;accounts.length>i;i++){
            if (accounts[i]!=null) {
                result[index] = accounts[i];
                index++;
            }
        }
        accounts = result;
        return accounts;
    }

    // 8) возвращающий ссылку на экземпляр класса IndividualsTariff для счета с заданным номером
    public Tariff getTariff(long accountNumber) {
        if (isInRange(accountNumber)) throw new IllegalAccountNumber("Номер вне границ диапазона");
        if (!isAccountExists(accountNumber)) throw new NoSuchElementException("Элемента с таким номером не существует");

        for(int i=0;i<size;i++){
            if (isEquals(i,accountNumber)) return getAccounts()[i].getTariff();
        }

        return DEFAULT_TARIFF;
    }
    private boolean isEquals(int index, long accountNumber) {
        if (checkIndex(index)) throw new IndexOutOfBoundsException("Индекс вне границ диапазона");

        //  if (index<=0 || index>size) throw new IndexOutOfBoundsException("Индекс вне границ массива");
        if (isInRange(accountNumber)) throw new IllegalAccountNumber("Номер вне границ диапазона");
        return getAccounts()[index].getNumber()==accountNumber;
    }

    // 9) изменяющий ссылку на экземпляр класса IndividualsTariff для счета с заданным номером
    public Tariff setTariff(long accountNumber, Tariff tariff) {
        if(Objects.isNull(tariff)) throw new NullPointerException("Значение tariff не должно быть null");
        if (isInRange(accountNumber)) throw new IllegalAccountNumber("Номер вне границ диапазона");
        if (!isAccountExists(accountNumber)) throw new NoSuchElementException("Элемента с таким номером не существует");

        Tariff individualsTariff = getTariff(accountNumber);
        for (int i = 0; i < getAccounts().length; i++) {
            if (isEquals(i,accountNumber)) accounts[i].setTariff(tariff);
        }
        return individualsTariff;
    }

    /*public Account[] getAccounts(ServiceTypes serviceType){
        if(Objects.isNull(serviceType)) throw new NullPointerException("Значение serviceType не должно быть null");

        Account[] getAccountsArray = new Account[size];
        int index = 0;
        for (Account account : getAccounts()){
            for (Service service: account.getTariff().getServices()){
                if (service.getType().equals(serviceType)){
                    getAccountsArray[index] = account;
                    index++;
                }
            }
        }
        Account[] shortArray = new Account[index];
        System.arraycopy(getAccountsArray, 0, shortArray, 0, index);
        return shortArray;
    }*/
    // Lb 7. возвращающий массив счетов, у которых подключена услуга заданного типа по интерфейсной ссылке Set<Account>
    public Set<Account> getAccounts(ServiceTypes serviceTypes){
        HashSet<Account> accountHashSet = new HashSet<>();
        Objects.requireNonNull(serviceTypes,"Значение serviceTypes не может быть Null");
        for(Account account :getAccounts()){
            for(Service service :(Service[]) account.getTariff().toArray()){
                if(service.getType()==serviceTypes){
                    accountHashSet.add(account);
                }else
                    throw new NoSuchElementException("Элемент не найден ");
            }
        }
        return accountHashSet;
    }

    // Lb 7. возвращающий массив счетов, у которых подключена услуга с заданным названием по интерфейсной ссылке Set<Account>
    public Set<Account> getAccounts(String serviceName){
        HashSet<Account> accountHashSet = new HashSet<>();
        for(Account account : getAccounts()){
            for(Service service : (Service[]) account.getTariff().toArray()){
                if(service.equals(serviceName)){
                    accountHashSet.add(account);
                }
            }
        }
        return accountHashSet;
    }

    /*public Account[] getIndividualAccounts(){
        Account[] getAccountsArray = new Account[getCountOfAccount()];
        int index = 0;
        for (Account account : accounts){
            if (account instanceof IndividualAccount){
                getAccountsArray[index] = account;
                index++;
            }
        }
        Account[] shortArray = new Account[index];
        System.arraycopy(getAccountsArray, 0, shortArray, 0, index);
        return shortArray;
    }*/

    // Lb 7. возвращающий массив счетов физ. лиц по интерфейсной ссылке List<Account>
    public List<Account> getIndividualAccounts(){
        ArrayList<Account> accountArrayList = new ArrayList<>() ;
        for(Account account : getAccounts()){
            if(account instanceof IndividualAccount){
                accountArrayList.add(account);
            }
        }
        return accountArrayList;
    }


   /* public Account[] getEntityAccounts(){
        Account[] getAccountsArray = new Account[getCountOfAccount()];
        int index = 0;
        for (Account account : accounts){
            if (account instanceof EntityAccount){
                getAccountsArray[index] = account;
                index++;
            }
        }
        Account[] shortArray = new Account[index];
        System.arraycopy(getAccountsArray, 0, shortArray, 0, index);
        return shortArray;
    }*/

    // Lb 7. возвращающий массив счетов юр. лиц по интерфейсной ссылке List<Account>
    public List<Account> getEntityAccounts(){
        LinkedList<Account> accountLinkedList = new LinkedList<>();
        for(Account account:getAccounts()){
            if(account instanceof EntityAccount){
                accountLinkedList.add(account);
            }else{
                throw  new NoSuchElementException("Элемент не найден");
            }
        }
        return accountLinkedList;
    }


   /* @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("accounts:\n");
        for(Account account: getAccounts()){
            builder.append(account.toString());
        }
        return builder.toString();
    }*/
    // Lb 6
     @Override
        public String toString() {
            Account[] accounts = getAccounts();
            StringBuilder stringBuilder = new StringBuilder();
            iterator().forEachRemaining(account -> stringBuilder.append(account.toString()).append("\n"));
            return stringBuilder.toString();
        }

    public boolean removeAccount(Account account){
        if(Objects.isNull(account)) throw new NullPointerException("Значение account не должно быть null");

        accounts[getIndex(account)] = null;
        accounts = getAccounts();
        for (Account currentAccount: getAccounts()){
            return !currentAccount.equals(account);
        }
        return false;

    }

    public int getIndex(Account account){
        if(Objects.isNull(account)) throw new NullPointerException("Значение account не должно быть null");

        int index=0;
        for(int i = 0; i < getAccounts().length; i++)
        {
            if (getAccounts()[i].equals(account)) return i;
        }
        return index;
    }
    //  возвращающий ссылку на счет по номеру счета
    public Account getAccount (long accountNumber){
        if (isInRange(accountNumber)) throw new IllegalAccountNumber("Номер вне границ диапазона");
        if (!isAccountExists(accountNumber)) throw new NoSuchElementException("Элемента с таким номером не существует");
        for (Account account: getAccounts()){
            if (accountNumber==account.getNumber()) return account;
        }
        return null;
    }
    // Lb 6. Методы интерфейса Iterable и класс ServiceIterator
    @Override
    public Iterator<Account> iterator() {
        AccountIterator iterator = new AccountIterator();
        return iterator();
    }


    private class AccountIterator implements java.util.Iterator<Account>{
        int index  = 0;

        @Override
        public boolean hasNext() {
            return index<getCountOfAccount();
        }

        @Override
        public Account next() {
            if (!hasNext()) throw new NoSuchElementException();
            return getAccounts()[index++];
        }
    }
}
