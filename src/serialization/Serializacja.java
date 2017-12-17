package serialization;

import java.io.Serializable;
import java.lang.reflect.Field;

public class Serializacja {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Osoba o = new Osoba();
        Osoba oo = new Osoba();
        Class clazz = o.getClass();
        //nakładając klasę Class na Osobę, możemy przełamać prywatność pól klasy Osoba
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            System.out.println(field.getName());
            field.setAccessible(true);
            System.out.println("field.get(o): " + field.get(o));
        }
        System.out.println(o.toString());
        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        field.set(o, "Imie");
        field.set(oo, "Imie2");
        System.out.println(o.toString());
        System.out.println(oo.toString());

        System.out.println(toJson(o));

    }

    public static String toJson(Osoba o ) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = o.getClass();
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(o, "Jan Kowalski");
        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);
        age.set(o, 30);

        return "{'name': " + name.get(o) + ", 'age': " + age.get(o) +"}";
    }

}

class Osoba implements Serializable{
    private String name;
    private int age;

    public String toString(){
        return "Osoba: " + name + " " + age;
    }
}

class Address{
    private String street;
    private int houseNumber;

    @Override
    public String toString() {
        return "Address:" + street + ", houseNumber: " + houseNumber + ".";
    }
}