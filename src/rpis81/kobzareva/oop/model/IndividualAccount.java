package rpis81.kobzareva.oop.model;

import java.time.LocalDate;
import java.util.Objects;

public class IndividualAccount extends AbstractAccount{
    private Person person;
    private final LocalDate hyi = LocalDate.now();

    public IndividualAccount(Person person, long number) {
        super(number,new IndividualsTariff(), LocalDate.now());
        this.person = Objects.requireNonNull(person,"Значение person не должно быть null");
        Tariff tariff = new IndividualsTariff();
        tariff.add(new Service());
        setTariff(tariff);
    }

    public IndividualAccount(Tariff tariff, Person person, long number, LocalDate registrationDate) {
        super(number, tariff,registrationDate);
        this.person = Objects.requireNonNull(person,"Значение person не должно быть null");
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = Objects.requireNonNull(person,"Значение person не должно быть null");
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("Individual account:\n");
        builder.append("holder: "+ getPerson().getName()+"\n");
        builder.append(super.toString());
        return builder.toString();
    }

    @Override
    public int hashCode(){
        return 97* super.hashCode()*Objects.hashCode(getPerson());
    }

    @Override
    public boolean equals(Object obj){
        IndividualAccount other = (IndividualAccount) obj;
        return Objects.equals(person,other.person) && super.equals(obj);
    }
}
