package rpis81.kobzareva.oop;
import rpis81.kobzareva.oop.model.IndividualsTariff;
import rpis81.kobzareva.oop.model.Service;

public class Test {
    public static void main (String[] args) {
       // System.out.println("Я сделяль!");
        lab1Test();
    }

    public static void lab1Test(){
        // Создаем 3 сервиса: 2 интернета и один мобильный
        Service XXLInternet = new Service();
        Service XLInternet = new Service("Интернет 75мб/с", 200);
        Service SMSMobile = new Service("500 SMS", 50);
        IndividualsTariff individualsTariff = new IndividualsTariff();
        individualsTariff.add(XLInternet);
        individualsTariff.add(XXLInternet);
        individualsTariff.add(SMSMobile);
        System.out.println("Получить сервис по индексу");
        System.out.println(individualsTariff.get(1).getName());
        System.out.println("Получить сервис по имени");
        System.out.println(individualsTariff.get("500 SMS").getName());
        System.out.println("Есть ли услуга с заданным названием");
        System.out.println(individualsTariff.hasService("Интернет 75мб/с"));
        //System.out.println("Изменить ссылку по номеру");
        //System.out.println(individualsTariff.set(1,SMSMobile).getName());
       // System.out.println("Удалить по номеру");
       // System.out.println(individualsTariff.remove(0).getName());
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
    }

}
