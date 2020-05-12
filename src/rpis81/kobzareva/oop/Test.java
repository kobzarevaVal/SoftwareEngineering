package rpis81.kobzareva.oop;
import rpis81.kobzareva.oop.model.*;

import java.util.Arrays;

public class Test {
    public static void main (String[] args) {
       // System.out.println("Я сделяль!");
        lab4test();
       /* Service XXLInternet = new Service();
        Service XLInternet = new Service("Интернет 75мб/с", 200);
        Service sMSMobile = new Service("500 SMS", 50);
        IndividualsTariff individualsTariff = new IndividualsTariff();
        individualsTariff.add(XXLInternet);
        individualsTariff.add(XLInternet);
        individualsTariff.add(sMSMobile);
        System.out.println("добавить в конкретное место");
        System.out.println(individualsTariff.add(4,sMSMobile));
        System.out.println("Массив услуг");
        // тесты плохого нерабочего метода
        for (Service service:individualsTariff.GetServices()){
           // System.out.println(service.getName());

        }*/

    }
    public static void lab3test(){
        // Создаем 5 сервиса: 3 интернета и 2 мобильных
        Service service0 = new Service();
        Service service1 = new Service("service1", 100, ServiceTypes.INTERNET);
        Service service2 = new Service("service2", 200, ServiceTypes.INTERNET);
        Service service3 = new Service("service3", 301, ServiceTypes.MAIL);
        Service service4 = new Service("service4", 400, ServiceTypes.PHONE);

        // Создаем 3 тарифа для разных типов пользователей с разным составом сервисов
        // индивидуальный
        Tariff individualsTariff = new IndividualsTariff();
        individualsTariff.add(service0);
        individualsTariff.add(service4);
        individualsTariff.add(service1);
        System.out.println("Сервисы с типом PHONE");
        for (int i = 0;i<individualsTariff.getServices(ServiceTypes.PHONE).length;i++){
            System.out.println(individualsTariff.getServices(ServiceTypes.PHONE)[i].getName());
        }
        // юридический
        EntityTariff entityTariff = new EntityTariff();
        entityTariff.add(service2);
        entityTariff.add(service3);
        entityTariff.add(service4);
        System.out.println("Сервисы с типом INTERNET");
        for (int i = 0;i<entityTariff.getServices(ServiceTypes.INTERNET).length;i++){
            System.out.println(entityTariff.getServices(ServiceTypes.INTERNET)[i].getType());
            System.out.println(entityTariff.getServices(ServiceTypes.INTERNET).length);
        }
        // тариф без мобильного сервиса
        Tariff entityTariffWithoutPHONE = new EntityTariff();
        entityTariff.add(service0);
        entityTariff.add(service1);
        entityTariff.add(service2);
        Account accountIndividual = new IndividualAccount(individualsTariff, new Person("individualName0","individualName0"), 0);
        Account account1Entity = new EntityAccount(1, "firstEntity", entityTariff);
        Account account2Entity = new EntityAccount(2, "secondEntity", entityTariff);
        Account accountWithoutPHONE = new EntityAccount(333, "accountWithoutPHONE", entityTariffWithoutPHONE);

        // Создаем аккаунт менеджера
        AccountManager accountManager = new AccountManager(3);
        accountManager.add(accountIndividual);
        accountManager.add(account1Entity);
        accountManager.add(account2Entity);
        accountManager.add(accountWithoutPHONE);
         System.out.println("Выводим все номера счетов с мобильным сервисом в тарифе");
         for (Account account: accountManager.getAccounts(ServiceTypes.PHONE)){
             System.out.println(account.getNumber());
         }
        // System.out.println(Me.getAccounts(ServiceTypes.PHONE)[0].getNumber());
        // System.out.println(Me.getAccounts(ServiceTypes.PHONE)[1].getNumber());

        System.out.println("Выводим все номера счетов физических лиц");
        for (Account account: accountManager.getIndividualAccounts()){
            System.out.println(account.getNumber());
        }
        //System.out.println(accountManager.getIndividualAccounts()[0].getNumber());

        System.out.println("Выводим все номера счетов юридических лиц");
        for (Account account: accountManager.getEntityAccounts()){
            System.out.println(account.getNumber());
        }

    }

    public static void lab4test(){
        Service service0 = new Service();

        Person person = new Person("name","surname");

        Tariff individualTariff = new IndividualsTariff();
        individualTariff.add(service0);
        individualTariff.add(service0);
        individualTariff.add(service0);

        Tariff entityTariff = new EntityTariff();
        entityTariff.add(service0);
        entityTariff.add(service0);
        entityTariff.add(service0);

        IndividualAccount individualAccount = new IndividualAccount(individualTariff,person,0);
        EntityAccount entityAccount = new EntityAccount("name",0);

        AccountManager accountManager = new AccountManager(2);
        accountManager.add(individualAccount);
        accountManager.add(entityAccount);

        // проверка toString
        System.out.println(service0.toString());
        System.out.println(person.toString());
        System.out.println("for individualTariff \n" + individualTariff.toString());
        System.out.println("for entityTariff \n" + entityTariff.toString());
        System.out.println("   " + individualAccount.toString());
        System.out.println("   " + entityAccount.toString());
        System.out.println(accountManager.toString());
        // проверка HashCode
        System.out.println(service0.hashCode());
        System.out.println(person.hashCode());
        System.out.println(individualTariff.hashCode());
        System.out.println(entityTariff.hashCode());
        System.out.println(entityAccount.hashCode());
    }
}
