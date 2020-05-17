package rpis81.kobzareva.oop.model;

import java.time.LocalDate;

public interface Account {
    long getNumber();
    Tariff getTariff();
    void setTariff(Tariff tariff);
    LocalDate getRegistrationDate();
}
