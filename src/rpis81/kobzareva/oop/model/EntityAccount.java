package rpis81.kobzareva.oop.model;

public class EntityAccount implements Account {
    private Tariff tariff;
    private String entityName;
    private long number;

    public EntityAccount(String entityName, long number) {
        this.entityName = entityName;
        this.number = number;
    }

    public EntityAccount(Tariff tariff, String entityName, long number) {
        this.tariff = tariff;
        this.entityName = entityName;
        this.number = number;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
    @Override
    public long getNumber() {
        return this.number;
    }

    @Override
    public Tariff getTariff() {
        return this.tariff;
    }

    @Override
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }
}
