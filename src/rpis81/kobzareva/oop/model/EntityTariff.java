package rpis81.kobzareva.oop.model;

public class EntityTariff implements Tariff {
    private Node head;
    private Node tail;
    private int size;
    transient Node last;
    public EntityTariff(){
        tail = new Node(null,head,null);
        head = new Node(null,null,tail);
        this.size = 0;
    }

    public EntityTariff(Service[] services){
        tail = new Node(null,head,null);
        head = new Node(null,null,tail);
        size = 0;
        for (int i = 0; i < services.length; i++) {
            add(services[i]);
            size++;
        }
    }

    @Override
    public boolean add(Service service) {
        return addNode(service);
    }

    @Override
    public boolean add(int index, Service service) {
        return addNode(index,service);
    }

    @Override
    public Service get(int index) {
        return getNode(index).getValue();
    }

    @Override
    public Service get(String serviceName) {
        for (int i = 0; i < size; i++){
            if (isNameEquals(i,serviceName)) {
                return getNode(i).getValue();
            }
        }
        return new Service();
    }
    private boolean isNameEquals(int index, String serviceName){
        return getNode(index).getValue().getName().equals(serviceName) && getNode(index).getValue().getName() != null;
    }
    @Override
    public boolean hasService(String serviceName) {
        for(Service service:getServices()){
            if(service.getName().equals(serviceName)) return true;
        }
        return false;
    }

    @Override
    public Service set(int index, Service service) {
        return setNode(index,service).getValue();
    }

    @Override
    public Service remove(int index) {
        return removeNode(index).getValue();
    }

    @Override
    public Service remove(String serviceName) {
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
    private  Service[] filterServices(Service[] services){
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
        for (Service service : getServices()){
            cost += service.getCost();
        }
        return cost;
    }

    // приватные методы
    private boolean addNode(Service service){
        Node prev = tail;
        prev.setValue(service);
        tail = new Node(null,prev,null);
        prev.setNext(tail);
        size++;
        return true;
    }
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
    private Node getNode(int index){
        Node target = head.getNext();
        for(int i = 0;i<index;i++){
            target = (target.getNext() != null) ? target.getNext() : target;
        }
        return target;
    }

    private Node removeNode (int index){
        Node node = getNode(index), nextNode = node.getNext(), previousNode = node.getPrevious();
        previousNode.setNext(nextNode);
        nextNode.setPrevious(previousNode);
        size--;
        return node;
    }

    private Node setNode (int index, Service service){
        Node node = getNode(index);
        Node replacedService = node;
        node.setValue(service);
        return replacedService;
    }
}
