package rpis81.kobzareva.oop.model;

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
}
