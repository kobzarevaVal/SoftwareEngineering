package rpis81.kobzareva.oop.model;

import java.time.LocalDate;
import java.util.Objects;

public class EntityAccount extends AbstractAccount {
    private String entityName;

    public EntityAccount(String entityName, long number) {
        super(number, new EntityTariff(), LocalDate.now());
        this.entityName  = Objects.requireNonNull(entityName,"Значение entityName не должно быть null");
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

    public EntityAccount (long number, String entityName, Tariff tariff, LocalDate registrationDate){
        super(number, tariff,registrationDate);
        this.entityName = Objects.requireNonNull(entityName,"Значение entityName не должно быть null");
    }

    public String getEntityName(){
        return entityName;
    }

    public void setEntityName(String entityName){
        Objects.requireNonNull(entityName,"Значение entityName не должно быть null");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Entity account: "+"\n");
        builder.append("Entity "+entityName+"\n");
        builder.append(super.toString());
        return builder.toString();
       // return String.format(super.toString(),builder);
    }

    @Override
    public int hashCode() {
        return 53 * super.hashCode();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof EntityAccount)) {
            return false;
        }
        EntityAccount other = (EntityAccount) obj;
        return entityName == other.entityName;
    }
}
