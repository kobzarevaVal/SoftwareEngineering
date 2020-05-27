package rpis81.kobzareva.oop.model;
import java.lang.Cloneable;
import java.time.LocalDate;
import java.util.Objects;
import java.lang.Comparable;
                                                    // Lb 6
public final class Service implements Cloneable, Comparable<Service> {
    final private double cost;
    final private String name;
    final private ServiceTypes type;
    final private String  DEFAULT_NAME = "интернет 100 мб\\сек";
    final private double  DEFAULT_COST = 300;
    final private ServiceTypes  DEFAULT_TYPE = ServiceTypes.INTERNET;
    private LocalDate activationDate;
    private final LocalDate DEFAULT_DATE = LocalDate.now();

    public Service(){
        this.name = DEFAULT_NAME;
        this.cost = DEFAULT_COST;
        this.type = DEFAULT_TYPE;
        this.activationDate = DEFAULT_DATE;
    }
    public Service(String name, double cost, ServiceTypes type, LocalDate activationDate){
        this.activationDate = Objects.requireNonNull(activationDate,"Значение activationDate не должно быть null");
        if (activationDate.isAfter(LocalDate.now()) || cost<=0) throw new IllegalArgumentException("Некорректная дата или цена");
        this.cost = cost;
        this.name = Objects.requireNonNull(name,"Значение name не должно быть null");
        this.type = Objects.requireNonNull(type,"Значение type не должно быть null");
    }
    public String getName(){ return name; }
    public double getCost(){return cost;};
    public ServiceTypes getType(){ return type; }
    public LocalDate getActivationDate() {
        return activationDate;
    }

    @Override
    public String toString()
    {
       // return name+"\\"+cost+"p.";
        return String.format("%-40s%n",name+"\\"+cost+"p.\\"+activationDate);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(name)*Objects.hashCode(type)*Double.hashCode(cost)*Objects.hashCode(activationDate);
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
                Objects.equals(type, other.type) &&
                Objects.equals(activationDate,other.activationDate);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    // Lb 6
    @Override
    public int compareTo(Service o) {
        return (int)(this.cost - o.cost);
    }
}
