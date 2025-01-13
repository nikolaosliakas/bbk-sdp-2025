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
        Person<String> p1 = new Person<>();
    }
}

