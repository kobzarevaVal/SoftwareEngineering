package rpis81.kobzareva.oop.model;
import java.lang.Cloneable;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class IndividualsTariff implements Tariff {

    private final static int DEFAULT_SIZE = 8;
    private int size;
    private Service[] services;
    private final Service DEFAULT_SERVICE = new Service();
    // по-умолчанию, инициирующий массив из 8 элементов
    public IndividualsTariff() {
        // исправлено
        //todo: снова магические константы, которые должны выноситься в приватные поля
        services = new Service[DEFAULT_SIZE];
    }

    // принимающий целое число – емкость массива, инициирующий массив указанным числом элементов
    public IndividualsTariff(int index) {
        if (checkIndex(index)) throw new IndexOutOfBoundsException("Индекс вне границ диапазона");

        services = new Service[index];
    }

    private boolean checkIndex(int index){
        return index < 0 || index >= size();
    }

    // принимающий массив услуг. В этом конструкторе происходит копирование элементов в
    //новый массив, и ссылка на него записывается в атрибут.
    public IndividualsTariff(Service[] services) {
        this.services = services;
    }

    // 1) добавляющий услугу в первое свободное место в массиве - норм
    public boolean add(Service service){
        if(Objects.isNull(service)) throw new NullPointerException("Значение service не должно быть null");
        doubleUp();
        for (int i = 0; i<services.length;i++){
            if (services[i]==null) {
                services[i] = service;
                size++;
                return true;
            }
        }
        return false;
    }
    // исправлено
    //todo: методом копирования массива System.arraycopy пользоваться можно

    // 2) добавляет на конкретное место в массиве
    public boolean add(int index, Service service) {
        if (checkIndex(index)) throw new IndexOutOfBoundsException("Индекс вне границ диапазона");
        if(Objects.isNull(service)) throw new NullPointerException("Значение service не должно быть null");
        doubleUp();
        // свиг вправо от места вставки
        if (services.length >= index){
            System.arraycopy(services, index, services, index + 1, services.length - index - 1);
        }
        return add(service);
    }

    private void doubleUp() {
        if (services[services.length - 1] != null) {
            Service[] updatedRentedServices = new Service[size * 2];
            System.arraycopy(services, 0, updatedRentedServices, 0, services.length);
            services = updatedRentedServices;
        }
    }

    // получить ссылку на экземпляр класса по индексу
    public Service get(int index) {
        if (checkIndex(index)) throw new IndexOutOfBoundsException("Индекс вне границ диапазона");
        if (!hasService(services[index].getName())) throw new NoSuchElementException("Услуги с данным именем не сущетсвует");
        return services[index];
    }

    // получить ссылку на экземпляр класса по имени услуги
    public Service get(String serviceName) {
        if (!hasService(serviceName)) throw new NoSuchElementException("Услуги с данным именем не сущетсвует");
        if(Objects.isNull(serviceName)) throw new NullPointerException("Значение serviceName не должно быть null");
        for (int i = 0; i < getServices().length; i++) {
            // исправлено
            //todo: вынести логику сравнения в отдельный приватный метод и вызывать
            if (isEquals(i,serviceName)) {
                return getServices()[i];
            }
        }
        return DEFAULT_SERVICE;
    }

    private boolean isEquals(int index, String serviceName){
        return getServices()[index].getName().equals(serviceName);
    }

    // есть ли услуга с заданным названием
    public boolean hasService(String serviceName) {
        if(Objects.isNull(serviceName)) throw new NullPointerException("Значение serviceName не должно быть null");
        for (int i = 0; i < getServices().length; i++) {
            // исправлено
            //todo: и тут
            if (isEquals(i,serviceName)) return true;
        }
        return false;
    }

    // изменить ссылку по номеру
    public Service set(int index, Service service) {
        if (checkIndex(index)) throw new IndexOutOfBoundsException("Индекс вне границ диапазона");

        if(Objects.isNull(service)) throw new NullPointerException("Значение service не должно быть null");

        Service lоstService = services[index];
        services[index] = service;
        // исправлено
        //todo: lost больше подходит, чем last
        return lоstService;
    }

    // удалить по номеру
    public Service remove(int index) {
        if (checkIndex(index)) throw new IndexOutOfBoundsException("Индекс вне границ диапазона");
        Service deletedService = services[index];
        services[index] = null;
        size--;
        services = getServices();
        return deletedService;
    }
    // удалить по инмени
    public Service remove(String serviceName) {
        if(Objects.isNull(serviceName)) throw new NullPointerException("Значение serviceName не должно быть null");
        if (!hasService(serviceName)) throw new NoSuchElementException("Услуги с данным именем не сущетсвует");
        Service tmp = new Service();
        for (Service service:getServices()){
            if (service.getName().equals(serviceName)) tmp = service;
        }
        int index = firstIndexOf(tmp);
        return remove(index);
    }



    // число услуг
   /* public int getSize() {
        return size;
    }*/

    //10) возвращающий массив услуг -norm
    /*Я знаю, что так нельзя скорее всего, но я не знаю как при помощи arraycopy скопирвать массив не на интервале
    (то есть отсюда до сюда) а с условием (то есть скопруй все кроме null) без перебора по элементам. Я пробовала использовать
     arraycopy со сдвигом налево но выпадал NullPointerException если подряд шло два или более null. Так что я нашла
    вот такой способ. На всякий случай оставляю ниже старый метод с поэлементным копированием. Пожалуйста, не заставляйте
    меня тратить еще несколько дней на реализацию через arraycopy, я сдаюсь. Оно работает, я не лезу*/

    // это плохой нерабочий метод, я просто оставлю это здесь
    public  Service[] GetServices(){
        Service[] result = new Service[size()];
        for (int i=0;i<services.length;i++){
            if(services[i]==null) {
                System.arraycopy(services, i+ 1, result, 0, services.length - i - 1);
            }
        }
        return result;
    }
    // это хороший рабочий метод, он используется везде
    public  Service[] getServices(){
        Service[] result = new Service[size()];
        int index =0;
        for (int i=0;services.length>i;i++){
            if (services[i]!=null) {
                result[index] = services[i];
                index++;
            }
        }
        services = result;
        return services;
    }

   /* @Override
    public Service[] sortedServicesByCost() {
        Service[] sortingServices = getServices();
        for(int i = sortingServices.length-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
                if( sortingServices[j].getCost() > sortingServices[j+1].getCost() ){
                    Service tmp = sortingServices[j];
                    sortingServices[j] = sortingServices[j+1];
                    sortingServices[j+1] = tmp;
                }
            }
        }
        return (sortingServices);
    }*/

    // Lb 6
   /* public Service[] sortedServicesByCost() {
        Service[] services = getServices();
        Arrays.sort(services,Service::compareTo);
        return services;
    }*/

   // Lb 7
    // sortedServicesByCost стал дефолтным в интерфейсе Tariff

    // стоимость тарифа
    @Override
    public double cost() {
        double cost = 50;
        LocalDate accountCreateDate;
        Period oneMonth;
        for( Service service: getServices()){
            if(!isMonth(service)){
                accountCreateDate = service.getActivationDate();
                oneMonth = Period.between(accountCreateDate,LocalDate.now());
                cost += (oneMonth.getDays() * service.getCost())/ LocalDate.now().getDayOfMonth();
            }else{
                cost +=service.getCost();
            }
        }
        return cost;
    }

    public boolean isMonth(Service service){
        LocalDate accountCreateDate = service.getActivationDate();
        Period oneMonth = Period.between(accountCreateDate,LocalDate.now());
        if(oneMonth.getMonths() >=1){
            return true;
        }
        return false;
    }

   /* @Override
    public Service[] getServices(ServiceTypes type) {
        if(Objects.isNull(type)) throw new NullPointerException("Значение type не должно быть null");
        Service[] newServiceArray = new Service[getCountOfServices(type)];
        Service[] services = getServices();
        int counter = 0;
        for (int i=0;i<getServices().length;i++){
            if (services[i].getType().equals(type)) {
                newServiceArray[counter] = services[i];
                counter++;
            }
        }
        return (newServiceArray);
    }*/

    // Lb 7.  возвращает коллекцию по интерфейсной ссылке Collection<Service>
        @Override
    public Collection<Service> getService(ServiceTypes type) {
        Objects.requireNonNull(type,"Значение type не должно быть Null");
        LinkedList<Service> serviceLinkedList = new LinkedList<>();
        for(Service service:(Service[]) toArray()){
            if(service.getType().equals(type)){
                serviceLinkedList.add(service);
            }
        }
        return  serviceLinkedList;
    }


    private int getCountOfServices(ServiceTypes type){
        if(Objects.isNull(type)) throw new NullPointerException("Значение type не должно быть null");
        int result = 0;
        for (int i=0;i<getServices().length;i++){
            if (services[i].getType().equals(type)) {
                result++;
            }
        }
        return result;
    }

   /* @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("Services:\n");
        for(Service service:getServices()){
            builder.append(service.toString());
        }
        return builder.toString();
    }*/
    // Lb 6
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("Services:\n");
        iterator().forEachRemaining(service1 -> builder.append(service1.toString()));
        return builder.toString();
    }

    public boolean removeService(Service service) {
        if(Objects.isNull(service)) throw new NullPointerException("Значение service не должно быть null");
        int size = size();
        remove(firstIndexOf(service));
        return size!=size();
    }

    public int firstIndexOf(Service service){
        if(Objects.isNull(service)) throw new NullPointerException("Значение service не должно быть null");
        int index=0;
        for(int i = 0; i < getServices().length; i++)
        {
            if (getServices()[i].equals(service)) return i;
        }
        return index;
    }

    public int lastIndexOf(Service service){
        if(Objects.isNull(service)) throw new NullPointerException("Значение service не должно быть null");
        int index=0;
        for(int i = getServices().length-1; i >=0 ; i--)
        {
            if (getServices()[i].equals(service)) return i;
        }
        return index;
    }

  /*  @Override
    public int hashCode(){
        int hashCode= 31;
        Service[] services = getServices();
        for(int i=0; i<services.length; i++){
            hashCode *=services.hashCode();
        }
        return hashCode;
    }*/
    // Lb 6
     @Override
        public int hashCode() {
            AtomicInteger atomicInteger = new AtomicInteger(31 *size());
            iterator().forEachRemaining(service -> atomicInteger.updateAndGet(value -> value ^ service.hashCode()));
            return atomicInteger.get();
        }

    @Override
    public boolean equals(Object obj){
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof IndividualsTariff)) {
            return false;
        }
        IndividualsTariff other = (IndividualsTariff) obj;
        return size == other.size;

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Lb 6. Методы интерфейса Iterable и класс ServiceIterator
    @Override
    public Iterator<Service> iterator() {
        return new ServiceIterator();
    }

    private class ServiceIterator implements Iterator<Service>{
        int index;
        @Override
        public boolean hasNext() {
            return index<size();
        }

        @Override
        public Service next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException();
            return getServices()[index++];
        }
    }
    // Lb 7. Методы интерфейса Collection
    @Override
    public boolean addAll(Collection<? extends Service> c) {
        c.forEach(this::add);
        return size() >=c.size();
    }

    @Override
    public void clear() {
        iterator().forEachRemaining(this::remove);
    }

    @Override
    public boolean contains(Object o) {
        Service service =(Service) o;
        return hasService(service.getName());
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean remove(Object o) {
        Service service =(Service) o;
        remove(service.getName());
        return contains(service);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int initSize = size();
        iterator().forEachRemaining(service -> {
            if(!c.contains(service)){remove(service);}
        });
        return initSize >size();
    }

    @Override // заменяет собой метод getSize
    public int size() {
        return size;
    }

    @Override // заменяет собой метод getServices()
    public Object[] toArray() {
        Service[]  services = new Service[size];
        for(int i= 0; i<size; i++){
            services[i] = get(i);
        }
        return services;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Service[] services = (Service[]) a;
        Arrays.sort(services,Comparator.comparing(Service::getCost));
        return (T[]) services;
    }
}
