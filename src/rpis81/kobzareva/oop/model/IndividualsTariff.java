package rpis81.kobzareva.oop.model;

public class IndividualsTariff {
    private Service[] services;
    private int size;
    private final Service DEFAULT_SERVICE = new Service();
    // по-умолчанию, инициирующий массив из 8 элементов
    public IndividualsTariff(){
        services = new Service[8];
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
    // 2) добавляет на конкретное место в массиве
    public boolean add(int index, Service service) {
        doubleUp();
        Service[] newArray = new Service[services.length+1];
        // свиг вправо от места вставки
        for (int i = 0; i < getServices().length; i++)
        {
            if (i < index)
            {
                newArray[i] = getServices()[i];
            }
            else if (i > index)
            {
                newArray[i + 1] = getServices()[i];
            }
        }
        newArray[index] = service;
        services = newArray;
        return false;
    }

    // получить ссылку на экземпляр класса по индексу
    public Service get(int index) {
        return services[index];
    }
    // получить ссылку на экземпляр класса по имени услуги
    public Service get(String serviceName) {
        for (int i = 0;i<getServices().length;i++){
            if (getServices()[i].getName().equals(serviceName)) {
                return getServices()[i];
            }
        }
        return DEFAULT_SERVICE;
    }

    // есть ли услуга с заданным названием
    public boolean hasService(String serviceName) {
        for (int i = 0;i<getServices().length;i++){
            if (getServices()[i].getName().equals(serviceName)) return true;
        }
        return false;
    }
    // изменить ссылку по номеру
    public Service set(int index, Service service) {
        Service lastService = services[index];
        services[index]=service;
        return lastService;
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
        for (int i = 0;i<services.length;i++){
            if (services[i]!=null) countOfServices++;
        }
        return countOfServices;
    }

    //10) возвращающий массив услуг -norm
    public Service[] getServices(){
        Service[] newServices = new Service[getSize()];
        int counter = 0;
        for (Service service : services){
            if (service!=null) {
                newServices[counter] = service;
                counter++;
            }
        }
        return newServices;
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
        for(Service service : getServices()) {
            totalCost += service.getCost();
        }
        return totalCost;
    }
    // удваивание массива
    private void doubleUp() {
        if(services[services.length - 1] != null) {
            Service[] updatedRentedServices = new Service[size * 2];
            System.arraycopy(services, 0, updatedRentedServices, 0, services.length);
            services = updatedRentedServices;
        }
    }
}
