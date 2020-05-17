package rpis81.kobzareva.oop.model;
import java.lang.Cloneable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class IndividualsTariff implements Tariff,Cloneable {
    private final static int DEFAULT_SIZE = 8;
    private int size;
    private Service[] services;
    private final Service DEFAULT_SERVICE = new Service();
    private String INDEX_OUT_OF_BOUND_MESSAGE = "Индекс вне границ массива";
    // по-умолчанию, инициирующий массив из 8 элементов
    public IndividualsTariff() {
        // исправлено
        //todo: снова магические константы, которые должны выноситься в приватные поля
        services = new Service[DEFAULT_SIZE];
    }

    // принимающий целое число – емкость массива, инициирующий массив указанным числом элементов
    public IndividualsTariff(int index) throws IndexOutOfBoundsException {
        try {
            Objects.checkIndex(index,size);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Индекс вне границ массива");
        }
        services = new Service[index];
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
    public boolean add(int index, Service service) throws IndexOutOfBoundsException {
        try {
            Objects.checkIndex(index,size);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Индекс вне границ массива");
        }
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
    public Service get(int index) throws IndexOutOfBoundsException {
        try {
            Objects.checkIndex(index,size);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Индекс вне границ массива");
        }
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
    public Service set(int index, Service service) throws IndexOutOfBoundsException {
        try {
            Objects.checkIndex(index,size);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Индекс вне границ массива");
        }
        if(Objects.isNull(service)) throw new NullPointerException("Значение service не должно быть null");

        Service lastService = services[index];
        services[index] = service;
        // исправлено
        //todo: lost больше подходит, чем last
        return lastService;
    }

    // удалить по номеру
    public Service remove(int index) throws IndexOutOfBoundsException {
        try {
            Objects.checkIndex(index,size);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Индекс вне границ массива");
        }
        Service deletedService = services[index];
        services[index] = null;
        size--;
        services = getServices();
        return deletedService;
    }
    // удалить по инмени
    public Service remove(String serviceName) {
        if (!hasService(serviceName)) throw new NoSuchElementException("Услуги с данным именем не сущетсвует");
        if(Objects.isNull(serviceName)) throw new NullPointerException("Значение serviceName не должно быть null");
        Service tmp = new Service();
        for (Service service:getServices()){
            if (service.getName().equals(serviceName)) tmp = service;
        }
        int index = firstIndexOf(tmp);
        return remove(index);
    }



    // число услуг
    public int getSize() {
        return size;
    }

    //10) возвращающий массив услуг -norm
    /*Я знаю, что так нельзя скорее всего, но я не знаю как при помощи arraycopy скопирвать массив не на интервале
    (то есть отсюда до сюда) а с условием (то есть скопруй все кроме null) без перебора по элементам. Я пробовала использовать
     arraycopy со сдвигом налево но выпадал NullPointerException если подряд шло два или более null. Так что я нашла
    вот такой способ. На всякий случай оставляю ниже старый метод с поэлементным копированием. Пожалуйста, не заставляйте
    меня тратить еще несколько дней на реализацию через arraycopy, я сдаюсь. Оно работает, я не лезу*/

    // это плохой нерабочий метод, я просто оставлю это здесь
    public  Service[] GetServices(){
        Service[] result = new Service[getSize()];
        for (int i=0;i<services.length;i++){
            if(services[i]==null) {
                System.arraycopy(services, i+ 1, result, 0, services.length - i - 1);
            }
        }
        return result;
    }
    // это хороший рабочий метод, он используется везде
    public  Service[] getServices(){
        Service[] result = new Service[getSize()];
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

    // сортировка
    public Service[] sortedServicesByCost() {
        Service[] sortingServices = getServices();
        for (int i = sortingServices.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (sortingServices[j].getCost() > sortingServices[j + 1].getCost()) {
                    Service tmp = sortingServices[j];
                    sortingServices[j] = sortingServices[j + 1];
                    sortingServices[j + 1] = tmp;
                }
            }
        }
        return (sortingServices);
    }

    // стоимость тарифа
    public double cost() {
        double totalCost = 50;
        // исправлено
        //todo: почему не обычный for?
        for (Service service: getServices()){
            if (countOfActivatedDays(service)<daysInTheMonth(service))
                totalCost+= countOfActivatedDays(service)*service.getCost()/daysInTheMonth(service);
            else totalCost += service.getCost();
        }
        return totalCost;
    }

    private int countOfActivatedDays(Service service){
        return Period.between(service.getActivationDate(),LocalDate.now()).getDays();
    }

    private int daysInTheMonth(Service service){
        return Period.between(service.getActivationDate(),service.getActivationDate().plusMonths(1)).getDays();
    }

    @Override
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

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("Services:\n");
        for(Service service:getServices()){
            builder.append(service.toString());

        }
        return builder.toString();

    }

    public boolean removeService(Service service) {
        if(Objects.isNull(service)) throw new NullPointerException("Значение service не должно быть null");
        int size = getSize();
        remove(firstIndexOf(service));
        return size!=getSize();
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

    @Override
    public int hashCode(){
        int result=31;
        for(Service service:getServices()){
          //  result*=service.hashCode();  // результат ноль
          //  result*=Objects.hashCode(service);  // результат ноль
           result*= (Objects.hash(service)); // я не знаю почему так, результат отрицательный в даже если такие проверки провести
                                                                                // if(Objects.hash(service)>0)  result*= Math.abs(Objects.hash(service));
        }
        return result;
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
}
