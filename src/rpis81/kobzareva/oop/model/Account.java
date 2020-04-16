package rpis81.kobzareva.oop.model;

public interface Account {
    long getNumber();
    Tariff getTariff();
    void setTariff(Tariff tariff);
}
