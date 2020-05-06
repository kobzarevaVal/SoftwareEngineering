package rpis81.kobzareva.oop.model;

import java.util.Objects;

public abstract class AbstractAccount implements Account{
    private long number;
    private Tariff tariff;
    protected AbstractAccount (long number, Tariff tariff){
        this.number = number;
        this.tariff = tariff;
    }

    @Override
    public long getNumber() {
        return number;
    }

    @Override
    public Tariff getTariff() {
        return tariff;
    }

    @Override
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("Number: "+ number+"\n");
        builder.append("Services:\n");
        for(Service service:getTariff().getServices()){
            builder.append(service.toString());
        }
        return builder.toString();
    }

    @Override
    public int hashCode(){
        return Long.hashCode(number)*Integer.hashCode(getTariff().getSize());
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof AbstractAccount)) {
            return false;
        }
        AbstractAccount other = (AbstractAccount) obj;
        return Objects.equals(number,other.number) && Objects.equals(getTariff().getSize(), other.getTariff().getSize());
    }
}
