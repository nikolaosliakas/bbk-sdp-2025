package main;

public class GenericMethods {

    /*
    *
    * */

    public static void main(String[] args){
        Integer[] ints = {10,20, 30, 40, 50};
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Friday", "Thursday"};

        print(ints);
        print(daysOfWeek);
        System.out.println(countGreaterThan(ints, 10));
    }

    //public static <T> int countGreaterThan(T[] list, T elem){
    //      This allows for T to be able to compare to itself without bounding it to a specific Reference Type
    public static <T extends Comparable<T>> int countGreaterThan(T[] list, T elem){
        int count = 0;
        for(T element:list){
            if(element.compareTo(elem) > 0)
                count++;
        }
        return count;
    }
    /**
     * Generic Method
     * NB - if logic is used to compare two generic defined reference types
     *      you may need to use the Comparable Interface*/
    public static <E> void print(E[] list){

        for(E element: list){
            System.out.print(element+" ");
        }
        System.out.println();
    }
}
