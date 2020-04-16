package rpis81.kobzareva.oop.model;

public class IndividualAccount implements Account{
    private Tariff tariff;
    private Person person;
    private long number;
    // исправлено
    //todo: такой конструктор не требовался в задании

    public IndividualAccount(Person person, long number) {
        this.person = person;
        this.number = number;
    }

    public IndividualAccount(Tariff tariff, Person person, long number) {
        this.tariff = tariff;
        this.person = person;
        this.number = number;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
