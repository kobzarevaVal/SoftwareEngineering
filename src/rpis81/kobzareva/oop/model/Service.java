package rpis81.kobzareva.oop.model;

public final class Service {
    final private double cost;
    final private String name;
    final private ServiceTypes type;
    final private String  DEFAULT_NAME = "интернет 100 мб\\сек";
    final private double  DEFAULT_COST = 300;
    final private ServiceTypes  DEFAULT_TYPE = ServiceTypes.INTERNET;


    public Service(){
        this.name = DEFAULT_NAME;
        this.cost = DEFAULT_COST;
        this.type = DEFAULT_TYPE;
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
