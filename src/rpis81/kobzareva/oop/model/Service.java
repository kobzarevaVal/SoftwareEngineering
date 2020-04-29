package rpis81.kobzareva.oop.model;

public final class Service {
    final private double cost;
    final private String name;
    final private ServiceTypes type;
    public Service(){
        this.name = "интернет 100 мб\\сек";
        this.cost = 300;
        this.type = ServiceTypes.INTERNET;
    }
    public Service(String name, double cost, ServiceTypes type){
        this.cost = cost;
        this.name = name;
        this.type = type;
    }
    public String getName(){ return name; }
    public double getCost(){return cost;};
    public ServiceTypes getType(){ return type; }

}
