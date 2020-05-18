package rpis81.kobzareva.oop;
import rpis81.kobzareva.oop.model.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

public class Test {
    public static void main (String[] args) {
       // System.out.println("Я сделяль!");
        lab5test();
    }

    private int daysInTheMonth(Service service){
        return Period.between(service.getActivationDate(),service.getActivationDate().plusMonths(1)).getDays();
    }

    private int countOfActivatedDays(Service service){
        return Period.between(service.getActivationDate(),LocalDate.now()).getDays();
    }

    public static void lab4test() {
        Service service0 = new Service();

        Person person = new Person("name", "surname");

        Tariff individualTariff = new IndividualsTariff();
        individualTariff.add(service0);
        individualTariff.add(service0);
        individualTariff.add(service0);

        Tariff entityTariff = new EntityTariff();
        entityTariff.add(service0);
        entityTariff.add(service0);
        entityTariff.add(service0);

        IndividualAccount individualAccount = new IndividualAccount(individualTariff, person, 999999999999988L,LocalDate.now());
        EntityAccount entityAccount = new EntityAccount("name", 999999999999998L);

        AccountManager accountManager = new AccountManager(2);
        try {
        accountManager.add(individualAccount);
        accountManager.add(entityAccount);
        }
        catch (Exception e){

        }
        // проверка toString
        System.out.println(service0.toString());
        System.out.println(person.toString());
        System.out.println("for individualTariff \n" + individualTariff.toString());
        System.out.println("   " + individualAccount.toString());
        System.out.println(accountManager.toString());
        // проверка HashCode
        System.out.println(service0.hashCode());
        System.out.println(person.hashCode());
        System.out.println(individualTariff.hashCode());
        System.out.println(individualAccount.hashCode());
    }

    public static void lab5test(){
        Service service0 = new Service();
        System.out.println("с датой активации "+service0.toString());
        System.out.println("дата активации service0: "+service0.getActivationDate());
        System.out.println(service0.hashCode());

        EntityAccount entityAccount = new EntityAccount("name",999999999999998L);
        AccountManager accountManager = new AccountManager(3);

        try {
            accountManager.add(entityAccount);
            accountManager.add(entityAccount);
          //  Service service = new Service(null,5,ServiceTypes.ADDITIONAL_SERVICE, LocalDate.now());
          //  Person person = new Person(null,"surname");
          //  EntityAccount entityAccount = new EntityAccount(null,999999999999998L);
          //  EntityTariff entityTariff = new EntityTariff();
           // entityTariff.get(null);
         //   entityAccount.setTariff(null);
            accountManager.add(20,entityAccount);


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        EntityTariff entityTariff = new EntityTariff();
        entityTariff.add(service0);
        entityTariff.add(service0);
        entityTariff.add(service0);
        System.out.println("размер "+entityTariff.getSize());
        entityTariff.add(10,service0);
    }
}
