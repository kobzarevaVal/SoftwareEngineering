package rpis81.kobzareva.oop.model;

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
}
