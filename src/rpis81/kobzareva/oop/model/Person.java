package rpis81.kobzareva.oop.model;

import java.util.Objects;

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

    @Override
    public String toString(){
        return String.format( name+" "+surname);
    }

    @Override
    public int hashCode(){
        return name.hashCode()*surname.hashCode();
        //return Objects.hash(name,surname);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Person)) {
            return false;
        }
        Person other = (Person) obj;
        return Objects.equals(name,other.name) &&
                Objects.equals(surname, other.surname);
    }
}
