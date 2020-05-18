package rpis81.kobzareva.oop.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class EntityTariff implements Tariff, Cloneable {
    private Node head;
    private Node tail;
    private int size;

    public EntityTariff(){
        this.tail = new Node(null,head,null);
        this.head = new Node(null,null,tail);
        this.size = 0;
    }
// исправлено, один конструктор вызван внутри другого
    public EntityTariff(Service[] services){
        this();
        for (int i = 0; i < services.length; i++) {
            add(services[i]);
            size++;
        }
    }

    @Override
    public boolean add(Service service) {
        if(Objects.isNull(service)) throw new NullPointerException("Значение service не должно быть null");
        return addNode(service);
    }

    @Override
    public boolean add(int index, Service service) {
        if(Objects.isNull(service)) throw new NullPointerException("Значение service не должно быть null");
        if (checkIndex(index)) throw new IndexOutOfBoundsException("Индекс вне границ диапазона");
        return addNode(index,service);
    }

    private boolean checkIndex(int index){
        return index < 0 || index >= size;
    }


    @Override
    public Service get(int index) {
        if (checkIndex(index)) throw new IndexOutOfBoundsException("Индекс вне границ диапазона");
        if (!hasService(getServices()[index].getName())) throw new NoSuchElementException("Услуги с данным именем не сущетсвует");
        // if (index<=0 || index>size) throw new IndexOutOfBoundsException("Индекс вне границ массива");
        return getNode(index).getValue();
    }

    @Override
    public Service get(String serviceName) {
        if (!hasService(serviceName)) throw new NoSuchElementException("Услуги с данным именем не сущетсвует");
        if(Objects.isNull(serviceName)) throw new NullPointerException("Значение serviceName не должно быть null");
        for (int i = 0; i < size; i++){
            if (isNameEquals(i,serviceName)) {
                return getNode(i).getValue();
            }
        }
        return new Service();
    }

    private boolean isNameEquals(int index, String serviceName) {
        return getNode(index).getValue().getName().equals(serviceName) && getNode(index).getValue().getName() != null;

    }
    @Override
    public boolean hasService(String serviceName) {
        if(Objects.isNull(serviceName)) throw new NullPointerException("Значение serviceName не должно быть null");
        for(Service service:getServices()){
            if(service.getName().equals(serviceName)) return true;
        }
        return false;
    }

    @Override
    public Service set(int index, Service service) {
        if (checkIndex(index)) throw new IndexOutOfBoundsException("Индекс вне границ диапазона");
        if(Objects.isNull(service)) throw new NullPointerException("Значение service не должно быть null");
        return setNode(index,service).getValue();
    }

    @Override
    public Service remove(int index) {
        if (checkIndex(index)) throw new IndexOutOfBoundsException("Индекс вне границ диапазона");
        if (!hasService(getServices()[index].getName())) throw new NoSuchElementException("Услуги с данным именем не сущетсвует");
        return removeNode(index).getValue();
    }

    @Override
    public Service remove(String serviceName) {
        if(Objects.isNull(serviceName)) throw new NullPointerException("Значение serviceName не должно быть null");
        if (!hasService(serviceName)) throw new NoSuchElementException("Услуги с данным именем не сущетсвует");

        for (int i = 0; i < size; i++){
            if (get(i).getName().equals(serviceName)) {
                return remove(i);
            }
        }
        return new Service();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Service[] getServices() {
        Service[] services = new Service[size+1];
        for(int i = 0; i < size+1; i++) {
            services[i] = get(i);
        }
        return filterServices(services);
    }
    private Service[] filterServices(Service[] services){
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

    @Override
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
    }

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


    @Override
    public Service[] getServices(ServiceTypes type) {
        if(Objects.isNull(type)) throw new NullPointerException("Значение type не должно быть null");
        Service[] newServiceArray = new Service[getCountOfTypeServices(type)];
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

    private int getCountOfTypeServices(ServiceTypes type){
        if(Objects.isNull(type)) throw new NullPointerException("Значение type не должно быть null");
        Service[] services = getServices();
        int result = 0;
        for (int i=0;i<getServices().length;i++){
            if (services[i].getType().equals(type)) {
                result++;
            }
        }
        return result;
    }

    public boolean removeService(Service service){
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
            if (getServices()[i].equals(service)) {
                return i;
            }
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

    // приватные методы все вроде, остальные нужно было самостоятельно реализовать, а приватные в них используются. Да и публичные методы
    // тут только те что из интерфейса

    // добавляющий узел в конец списка
    private boolean addNode(Service service){
        Node prev = tail;
        prev.setValue(service);
        tail = new Node(null,prev,null);
        prev.setNext(tail);
        size++;
        return true;
    }
    // добавляющий узел в заданную позицию в списке
    private boolean addNode (int index, Service service){
        Node node = getNode(index), previousNode = node.getPrevious();
        Node insertedNode = new Node(null);
        insertedNode.setNext(node);
        if(index == 0) {
            insertedNode.setPrevious(insertedNode);
            head = insertedNode;
            getNode(size).setNext(head);
            node.setPrevious(head);
        }
        else {
            previousNode.setNext(insertedNode);
            insertedNode.setPrevious(previousNode);
            node.setPrevious(insertedNode);
        }
        size++;
        getNode(index).setValue(service);
        return true;
    }
    // возвращающий ссылку на узел по его номеру в списке
    private Node getNode(int index){
        Node target = head.getNext();
        for(int i = 0;i<index;i++){
            target = (target.getNext() != null) ? target.getNext() : target;
        }
        return target;
    }
    // удаляющий узел по его номеру в списке
    private Node removeNode (int index){
        Node node = getNode(index), nextNode = node.getNext(), previousNode = node.getPrevious();
        previousNode.setNext(nextNode);
        nextNode.setPrevious(previousNode);
        size--;
        return node;
    }
    // изменяющий узел с заданным номером
    private Node setNode (int index, Service service){
        Node node = getNode(index);
        Node replacedService = node;
        node.setValue(service);
        return replacedService;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("Services:\n");
        for(Service service:getServices()){
            builder.append(service.toString());
        }
        return builder.toString();

    }

    @Override
    public int hashCode(){
        int result=71;
        for(Service service:getServices()){
          //  result*=service.hashCode(); // результат - 0
          //  result*=Objects.hashCode(service); // результат - 0, даже с проверкой service!=null
           if (Objects.hash(service)>0) result*= Objects.hash(service); // отрицательный результат
        }
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof EntityTariff)) {
            return false;
        }
        EntityTariff other = (EntityTariff) obj;
        return size==other.size;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public Iterator<Service> iterator() {
        return null;
    }
}