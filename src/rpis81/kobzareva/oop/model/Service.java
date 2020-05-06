package rpis81.kobzareva.oop.model;
import java.lang.Cloneable;
import java.util.Objects;

public final class Service implements Cloneable {
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

    @Override
    public String toString(){
        return String.format("%-40s%n",name+"\\"+cost+"p.");
    }

    @Override
    public int hashCode(){
        return name.hashCode()*type.hashCode()*Double.hashCode(cost);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Service)) {
            return false;
        }
        Service other = (Service) obj;
        return Objects.equals(name,other.name) &&
                Objects.equals(cost, other.cost) &&
                Objects.equals(type, other.type);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
