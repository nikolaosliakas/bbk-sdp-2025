package main;

public class GenericClasses{

    public static void main(String[] arg){

        /*
        * Generic person with
        *   type parameter E
        *   type argument String
        * NB - with any variable declaration p1 doesn't hold a Person Object, just that it will contain
        *       a Person with a reference to an Employee object
        * */
        Person<Employee> e1 = new Person<>();
        Person<Contact> c1 = new Person<>();

        Employee emp1 = new Employee("John", "Slattery", "123456");
        Contact con1 = new Contact("Mrs", "Peggy", "Fisher", "717-555-2131");

        e1.setPerson(emp1);
        c1.setPerson(con1);

        System.out.println(e1.getPerson().toString());
        System.out.println(c1.getPerson().toString());

    }
}

