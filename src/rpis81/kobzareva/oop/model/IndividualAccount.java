package rpis81.kobzareva.oop.model;

import java.util.Objects;

public class IndividualAccount extends AbstractAccount{
    private Person person;

    public IndividualAccount(Person person, long number) {
        super(number,new IndividualsTariff());
        this.person = person;
        Tariff tariff = new IndividualsTariff();
        tariff.add(new Service());
        setTariff(tariff);
    }

    public IndividualAccount(Tariff tariff, Person person, long number) {
        super(number, tariff);
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
