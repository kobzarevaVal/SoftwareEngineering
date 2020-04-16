package rpis81.kobzareva.oop;
import rpis81.kobzareva.oop.model.*;

import java.util.Arrays;

public class Test {
    public static void main (String[] args) {
       // System.out.println("Я сделяль!");
       // lab1Test();
        lab2test();
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

    public static void lab1Test(){
        // Создаем 3 сервиса: 2 интернета и один мобильный
        Service XXLInternet = new Service();
        Service XLInternet = new Service("Интернет 75мб/с", 200);
        Service sMSMobile = new Service("500 SMS", 50);
        IndividualsTariff individualsTariff = new IndividualsTariff();
        individualsTariff.add(XXLInternet);
        individualsTariff.add(XLInternet);
        individualsTariff.add(sMSMobile);
        System.out.println("добавить в конкретное место");
        System.out.println(individualsTariff.add(4,sMSMobile));
        System.out.println("Получить сервис по индексу");
        System.out.println(individualsTariff.get(1).getName());
        System.out.println("Получить сервис по имени");
        System.out.println(individualsTariff.get("500 SMS").getName());
        System.out.println("Есть ли услуга с заданным названием");
        System.out.println(individualsTariff.hasService("Интернет 75мб/с"));
        //System.out.println("Изменить ссылку по номеру");
        //System.out.println(individualsTariff.set(1,sMSMobile).getName());
        System.out.println("Удалить по номеру");
        System.out.println(individualsTariff.remove(0).getName());
       // System.out.println("Удалить по имени");
       // System.out.println(individualsTariff.remove("интернет 100 мб\\сек").getName());
        System.out.println("число услуг");
        System.out.println(individualsTariff.getSize());
        System.out.println("Массив услуг");
        for (Service service:individualsTariff.getServices()){
            System.out.println(service.getName());
        }
        System.out.println("Сортировка по цене");
        for (Service service:individualsTariff.sortedServicesByCost()){
            System.out.println(service.getCost());
        }
        System.out.println("Общая стоимость");
        System.out.println(individualsTariff.cost());
        System.out.println("===============================================");
        // создаем персоны и аккаунты с ними
        Person person0 = new Person("Name0","Surname0");
        Person person1 = new Person("Name1","Surname1");
        Person person2 = new Person("Name2","Surname2");
        Account account0 = new IndividualAccount(person0,0);
        Account account1 = new IndividualAccount(person1,1);
        Account account2 = new IndividualAccount(person2,2);
        AccountManager accountManager = new AccountManager(2);
        // добавляем аккаунты в массив
        accountManager.add(account0);
        accountManager.add(account1);
        accountManager.add(account2);
        System.out.println("Получить ссылку по номеру");
        System.out.println(accountManager.getAccount(1).getNumber());
        //System.out.println("Изменить ссылку по номеру");
        //System.out.println(accountManager.setAccount(0,account2));
       // System.out.println("Удалить по номеру");
       // System.out.println(accountManager.remove(0).getNumber());
        System.out.println("число счетов");
        System.out.println(accountManager.getCountOfAccount());
        System.out.println("массив счетов");
        for (Account account:accountManager.getAccounts()){
            System.out.println(account.getNumber());
        }
        System.out.println("получить тариф по номеру");
        // задаем тариф
        account0.setTariff(individualsTariff);
        for (Service service:accountManager.getTariff(0).getServices()){
            System.out.println(service.getName());
        }
    }
    public static void lab2test(){
        EntityTariff entityTariff = new EntityTariff();
        Service service0 = new Service();
        Service service1 = new Service("name1",100);
        Service service2 = new Service("name2",200);

        System.out.println("Добавление элемента "+entityTariff.add(service0));
        System.out.println("Добавление элемента "+entityTariff.add(service1));
        System.out.println("Добавление элемента в конкретное место "+entityTariff.add(1,service2));
        System.out.println("Получить элемент по номеру: "+entityTariff.get(1).getName());
        //  System.out.println("Удалить элемент по номеру: "+ entityTariff.remove(1).getName());
        //  System.out.println("Получить элемент по номеру: "+entityTariff.get(1).getName());
        //  System.out.println("Заменить элемент по номеру: "+ entityTariff.set(1,service0).getName());
        //  System.out.println("Получить элемент по номеру: "+entityTariff.get(1).getName());
        System.out.println("Получить элемент по имени: "+entityTariff.get("интернет 100 мб\\сек").getName());
        //  System.out.println("Есть ли элемент с данным именем: "+ entityTariff.hasService("name4"));
        //  System.out.println("Есть ли элемент с данным именем: "+ entityTariff.hasService("name1"));
        //  System.out.println("Удалить элемент по имени: "+ entityTariff.remove("name1").getName());
        //  System.out.println("Получить элемент по номеру: "+entityTariff.get(1).getName());
        System.out.println("Массив сервисов:");
        for (Service service: entityTariff.getServices()){
            System.out.println(service.getName());
        }
        System.out.println("Количество элементов: "+ entityTariff.getSize());
        System.out.println("Сортировка по стоимости");
        for (Service service: entityTariff.sortedServicesByCost()){
            System.out.println(service.getCost());
        }
        System.out.println("Общая стоимость: "+ entityTariff.cost());
    }

}
