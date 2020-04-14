package rpis81.kobzareva.oop.model;

import java.util.Arrays;
import java.util.Objects;

public class IndividualsTariff {
    public final static int DEFAULT_SIZE = 8;
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
        services = new Service[index];
    }

    // принимающий массив услуг. В этом конструкторе происходит копирование элементов в
    //новый массив, и ссылка на него записывается в атрибут.
    public IndividualsTariff(Service[] services) {
        this.services = services;
    }

    // 1) добавляющий услугу в первое свободное место в массиве - норм
    public boolean add(Service service){
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
        return services[index];
    }

    // получить ссылку на экземпляр класса по имени услуги
    public Service get(String serviceName) {
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
        for (int i = 0; i < getServices().length; i++) {
            // исправлено
            //todo: и тут
            if (isEquals(i,serviceName)) return true;
        }
        return false;
    }

    // изменить ссылку по номеру
    public Service set(int index, Service service) {
        Service lastService = services[index];
        services[index] = service;
        // исправлено
        //todo: lost больше подходит, чем last
        return lastService;
    }

    // удалить по номеру
    public Service remove(int index) {
        Service deletedService = services[index];
        services[index] = null;
        size--;
        services = getServices();
        return deletedService;
    }
    // удалить по инмени
    public Service remove(String serviceName) {
        int index = getIndex(serviceName);
        return remove(index);
    }

    private int getIndex(String name) {
        int index = 0;
        for (int i = 0; i < getServices().length; i++) {
            if (getServices()[i].getName().equals(name)) index = i;
        }
        return index;
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
    public  Service[] getServices(){
        services = Arrays.stream(services).filter(Objects::nonNull).toArray(Service[]::new);
        return services;
    }

    public  Service[] GetServices(){
        Service[] newServices = new Service[getSize()];
        int counter = 0;
        for (int i=0;services.length>i;i++){
            if (services[i]!=null) {
                newServices[counter] = services[i];
                counter++;
            }
        }
        services=newServices;
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
        for (int i=0;i<size;i++) {
            totalCost += getServices()[i].getCost();
        }
        return totalCost;
    }

    // удваивание массива перенесено к месту вызова
    //todo: выстраивайте методы в порядке использования!
}
