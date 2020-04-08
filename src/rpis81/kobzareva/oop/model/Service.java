package rpis81.kobzareva.oop.model;

public class Service {
    private final String DEFAULT_NAME = "интернет 100 мб\\сек";
    private final double DEFAULT_COST = 300;
    private double cost;
    private String name;
    // исправлено
    //todo: у нас существуют магические константы
    public Service(){
        this.name = DEFAULT_NAME;
        this.cost = DEFAULT_COST;
    }
    public Service(String name, double cost){
        this.cost = cost;
        this.name = name;
    }
    public String getName(){ return name; }
    public void setName(String newName) {name=newName; }
    public double getCost(){return cost;};
    public void setCost(double newCost) {cost=newCost;}

}
