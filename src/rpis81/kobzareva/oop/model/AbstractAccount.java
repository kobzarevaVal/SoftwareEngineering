package rpis81.kobzareva.oop.model;

import java.time.LocalDate;
import java.util.Objects;

public abstract class AbstractAccount implements Account{
    private long number;
    private Tariff tariff;
    private LocalDate registrationDate;
    private final long MIN_NUMBER = 001000000000001L;
    private final long MAX_NUMBER = 999999999999999L;

    protected AbstractAccount (long number, Tariff tariff, LocalDate registrationDate){
        if (number< MIN_NUMBER || number>MAX_NUMBER) throw new IllegalAccountNumber("Номер вне границ диапазона");
        else this.number = number;
        this.tariff = Objects.requireNonNull(tariff,"Значение tariff не должно быть null");
        if (registrationDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("Некорректная дата");
        else this.registrationDate = registrationDate;
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
        this.tariff = Objects.requireNonNull(tariff,"Значение tariff не должно быть null");
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("Number: "+ number+"\n");
        builder.append("registrationDate: "+registrationDate+"\n");
        builder.append("Services:\n");
        for(Service service:getTariff().getServices()){
            builder.append(service.toString());
        }
        return builder.toString();
    }

    @Override
    public int hashCode(){
       return Objects.hash(number,getTariff().getSize(),registrationDate);
      //  return Long.hashCode(number)*Integer.hashCode(getTariff().getSize());
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
        return Objects.equals(number,other.number) &&
                Objects.equals(getTariff().getSize(), other.getTariff().getSize()) &&
                Objects.equals(registrationDate, other.registrationDate);
    }
}
