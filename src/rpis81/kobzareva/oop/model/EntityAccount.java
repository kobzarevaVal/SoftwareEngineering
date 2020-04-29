package rpis81.kobzareva.oop.model;

public class EntityAccount extends AbstractAccount {
    private String entityName;

    public EntityAccount(String entityName, long number) {
        super(number, new EntityTariff());
        this.entityName = entityName;
        Tariff tariff = new EntityTariff();
        tariff.add(new Service());
        setTariff(tariff);
    }
// в третьей лабе нужо было удалить логику работы с номером счета и ссылкой на тариф, поэтому оставлю конструктор комментарием
   // public EntityAccount(String entityName, long number) {
   //     this.entityName = entityName;
   //     this.number = number;
   //     this.tariff.add(new Service());
   // }

    public EntityAccount (long number, String entityName, Tariff tariff){
        super(number, tariff);
        this.entityName = entityName;
    }

    public String getEntityName(){
        return entityName;
    }

    public void setEntityName(String entityName){
        this.entityName = entityName;
    }
}
