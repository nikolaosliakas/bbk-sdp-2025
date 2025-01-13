package main;

public class GenericInterface <T> implements GenericInterfaces<T>{
    public T myList;
    public void add(T t){
        myList = t;
    }

}

interface GenericInterfaces<T>{

    // NB - forces Develops to include and write code for a method that implements this interface.
    void add(T t);

}
