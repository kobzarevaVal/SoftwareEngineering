package rpis81.kobzareva.oop.model;

public class Service {
    private double cost;
    private String name;
    public Service(){
        this.name = "интернет 100 мб\\сек";
        this.cost = 300;
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
