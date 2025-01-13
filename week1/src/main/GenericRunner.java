package main;
import java.util.ArrayList;
import java.util.Date;
public class GenericRunner {

    public static void main(String[] args){
        // Raw parameter type
        // Comparable c = new Date();
        // System.out.println(c.compareTo("Monday"));
        // Generic allows for check at compile with invalid input
//         Comparable<Date> c2 = new Date();
        //System.out.println(c2.compareTo("String"));

//        ArrayList a = new ArrayList();
//        a.add(15);
//        a.add("15");
//        Integer b = (Integer)a.get(1);
        ArrayList<Integer> a = new ArrayList<Integer>(); // after Java7 no need to repeat as type set on left
        a.add(15);
//        a.add("15"); // compile fails here
        Integer b = a.get(0); // no need to cast like above to retrieve value

        //Generic Interface
        GenericInterface<String> list = new GenericInterface<>();
        list.add("Good afternoon");
    }
}

/*
* class name<T1, T2, ..., Tn>{/.../}
*
*
* Type Parameter names
*   E - used for Element in Collections
*   K - Key
*   N - Number
*   T - Type
*   V - Value
*
* Generic Method
*   - <E> outside the method signature communicates 'generic' to the input type within the method signature.
* public static <E> void print(E[] list){}
*
* Generic Interface
* public interface MyInterface <E>{}
* public interface MyKeyValInterface <K,V> {}
*
*ArrayList
*   before JAVA 5.0
*   public class ArrayList
*       extends AbstractList
*       implements List, RandomAccess, Cloneable, Serializable
*
*   after Java 5.0 with generics
*   public class ArrayList<E>
        extends AbstractList<E>
*       implements List<E>, RandomAccess, Cloneable, Serializable
*
*
* */

/*
* Type Erasure
*   - removes type information when generics are compiled
*   - allows for backward compatibility with pre-generic code
* Raw Type
*   - generic class without type arguments
*   - nongeneric class/interface types are not raw types
*       - NB
* List<Integer> list1 = new List<Integer>();
*
* List is a generic class, but in the below does not contain type hint like above.
* List rawList = new List();
*
* NB - Raw Type means you with Objects and not declared types (you will have to cast to retrieve)
* */


