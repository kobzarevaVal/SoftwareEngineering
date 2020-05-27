package rpis81.kobzareva.oop.model;
import java.util.Collection;
                        // Lb 6                     //Lb 7
public interface Tariff extends Iterable<Service>/*, Collection<Service>*/ {
    boolean add(Service service);
    boolean add(int index, Service service);
    Service get(int index);
    Service get(String serviceName);
    boolean hasService(String serviceName);
    Service set(int index, Service service);
    Service remove(int index);
    Service remove(String serviceName);
    Service[] getServices();
    Service[] sortedServicesByCost();
    /* можно сделать дефолтным
    default List<Service> sortedServicesByCost(){
        return Arrays.stream(toArray())
                .sorted(Service::compareTo)
                .collect(Collectors.toCollection(ArrayList::new));
    }
     */
    double cost();
    int getSize();
    // default int size(){ return toArray().length; } // можно сделать дефолтным
    Service[] getServices(ServiceTypes type);
    String toString();
    int hashCode();
    boolean equals(Object obj);
    Object clone() throws CloneNotSupportedException;
    boolean removeService(Service service);
    int firstIndexOf(Service service);
    int lastIndexOf(Service service);
}
