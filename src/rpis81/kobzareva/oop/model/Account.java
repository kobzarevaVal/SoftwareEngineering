package rpis81.kobzareva.oop.model;

public class Account {
    private IndividualsTariff individualsTariff;
    private Person person;
    private int number;
    // исправлено
    //todo: такой конструктор не требовался в задании

    public Account(Person person, int number) {
        this.person = person;
        this.number = number;
    }

    public Account(IndividualsTariff individualsTariff, Person person, int number) {
        this.individualsTariff = individualsTariff;
        this.person = person;
        this.number = number;
    }

    public IndividualsTariff getIndividualsTariff() {
        return individualsTariff;
    }

    public void setIndividualsTariff(IndividualsTariff individualsTariff) {
        this.individualsTariff = individualsTariff;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
