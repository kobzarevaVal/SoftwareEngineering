package rpis81.kobzareva.oop.model;

public class IndividualsTariff {
    private Service[] services;
    private int size;
    private final Service DEFAULT_SERVICE = new Service();
    private final int countOfServices = 8;
    // сделано
    //todo: снова магические константы, которые должны выноситься в приватные поля

    // по-умолчанию, инициирующий массив из 8 элементов
    public IndividualsTariff(){
        services = new Service[countOfServices];
    }

    // принимающий целое число – емкость массива, инициирующий массив указанным числом элементов
    public IndividualsTariff(int index){
        services = new Service[index];
    }

    // принимающий массив услуг. В этом конструкторе происходит копирование элементов в
    //новый массив, и ссылка на него записывается в атрибут.
    public IndividualsTariff(Service[] services){
        this.services = services;
    }

    // 1) добавляющий услугу в первое свободное место в массиве - норм
    public boolean add(Service service){
        for (int i=0;i<services.length;i++){
            if (services[i]==null) {
                services[i]=service;
                size= getSize();
                return true;
            }
        }
        return false;
    }
    // сделано
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
        if(services[services.length - 1] != null) {
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
        for (int i = 0;i<getServices().length;i++){
            // сделано
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
        for (int i = 0;i<getServices().length;i++){
            // сделано
            //todo: и тут
            if (isEquals(i,serviceName)) return true;
        }
        return false;
    }
    // изменить ссылку по номеру
    public Service set(int index, Service service) {
        // сделано
        //todo: lost больше подходит, чем last
        Service lostService = services[index];
        services[index]=service;
        return lostService;
    }
    // удалить по номеру
    public Service remove(int index) {
        Service deletedService = services[index];
        Service[] newArray = new Service[getServices().length - 1];
        for (int i = 0; i < getServices().length; i++)
        {
            if (i < index)
            {
                newArray[i] = getServices()[i];
            }
            else if (i > index)
            {
                newArray[i - 1] = getServices()[i];
            }
        }
        services = newArray;
        return deletedService;
    }
    // удалить по инмени
    public Service remove(String serviceName) {
        int index = getIndex(serviceName);
        return remove(index);
    }
    private int getIndex(String name){
        int index=0;
        for(int i = 0; i < getServices().length; i++)
        {
            if (getServices()[i].getName().equals(name)) index = i;
        }
        return index;
    }
    // число услуг
    public int getSize() {
        int countOfServices = 0;
        for (Service service: services){
            if (service != null) countOfServices++;
        }
        return countOfServices;
    }

    //10) возвращающий массив услуг -norm
    public Service[] getServices() {
        //todo: почему не обычный for?
        //потому что нужно просто прбежаться по элементам массива и записать их в другой, но на всякий случай исправлю
        // исправлено
        //todo: вместо поэлементного копирования можно использовать System.arraycopy просто на позицию левее
        Service[] newArray = new Service[getSize()];
        for (int i = 0; i<services.length;i++) {
            if (services[i] != null) {
                System.arraycopy(services,0,newArray,0,getSize());
            }
        }
        services = newArray;
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
        double  totalCost = 50;
        //todo: почему не обычный for?
        // потому что тут не нужно менять элементы массива, достаточно просто пробежаться по элементам и сложить стоимость,
        // плюс не придется в цикле вызывать getServices, но исправлю
        for(int i = 0;i<getSize();i++) {
            totalCost += getServices()[i].getCost();
        }
        return totalCost;
    }

    //todo: выстраивайте методы в порядке использования!
}
