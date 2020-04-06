package rpis81.kobzareva.oop.model;

public class Person {
    private String name;
    private String surname;
    public Person(String name,String surname){
        this.name = name;
        this.surname = surname;
    }
    public String getName(){ return name; }
    public void setName(String newName) {name=newName; }
    public String getSurname(){ return surname; }
    public void setSurname(String newSurname) {surname=newSurname; }

}
