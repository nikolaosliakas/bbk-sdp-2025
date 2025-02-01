import java.util.*;
import java.util.stream.*;

public class StreamDemo {

    public static void main(String[] args){

        ArrayList<Integer> myList = new ArrayList<>();

        myList.add(7);
        myList.add(18);
        myList.add(10);
        myList.add(24);
        myList.add(17);
        myList.add(5);

        System.out.println("Original list: " + myList);

        // Obtain a Stream to the array list.
        Stream<Integer> myStream = myList.stream();

        // Obtain min and max
        Optional<Integer> minVal = myStream.min(Integer::compare);
        if (minVal.isPresent())
            System.out.println("Min value: " + minVal.get());

        // This needs to be repeated because previous call to min is a 'terminal'
        //      operation that consumed the stream.
        myStream = myList.stream();
        Optional<Integer> maxVal = myStream.max(Integer::compare);
        if (minVal.isPresent())
            System.out.println("Max value: " + maxVal.get());

        // Sort the stream
        Stream<Integer> sortedStream = myList.stream().sorted();
        // Sorted stream
        System.out.print("Sorted stream: ");
        sortedStream.forEach((n) -> System.out.print(n + " "));
        System.out.println();

        // Filter - NB filter is stateless

        Stream<Integer> oddVals = myList.stream().filter((n) -> (n % 2) == 1);
        System.out.print("Odd values: ");
        oddVals.forEach((n) -> System.out.print(n + " "));
        System.out.println();

        // Odd Values greater than 5

        Stream<Integer> oddGtFiveVals = myList.stream().filter((n) -> (n % 2) == 1)
                .filter((n) -> n > 5);
        System.out.print("Odd values greater than 5: ");
        oddGtFiveVals.forEach((n) -> System.out.print(n + " "));

    }

}

/**
 * Reduction Operations
 *  - special case reductions: min(), max() and count et c.
 *  general reductions use the reduce() method.
 *      - have three implementations
 *      Optional<T> reduce(BinaryOperator<T> accumulator)
 *      T reduce(T identityVal, BinaryOperator<T> accumulator)
 * T is the element type
 * BinaryOperator is a functional interface that requires both input parameters
 *      to be the same type as output param
 *      T apply(T val, T val2)
 * accumulator type(BinaryOperator) is a function that operates on two values and outputs a result.
 *
 * in first invocation of the BinaryOperator will use identityVal or the first element
 * */

/**
 * Rules for function used as accumulator operation
 *  1. Stateless
 *  2. Non-interfering
 *      - Data source not modified by operation
 *  3. Associative
 *      - arithmatic meaning
 *      - ex. (10 * 2) * 7 is equivalent to 10 * (2 * 7)
 * */

class StreamDemo2{

    public static void main(String[] args){
        ArrayList<Integer> myList = new ArrayList<>();

        myList.add(7);
        myList.add(18);
        myList.add(10);
        myList.add(24);
        myList.add(17);
        myList.add(5);

        // Obtain product of elements 2 ways
        // Explicitly writing the identity val or not

        Optional<Integer> productObj = myList.stream().reduce(
                (a, b) -> a * b
        );
        if(productObj.isPresent())
            System.out.println("Product as Optional: " + productObj.get());

        int product = myList.stream().reduce(1, (a,b) -> a * b);
        System.out.println("Product from stream with explicit identity val: " + product);

        // Real accumulation without referring to 'next' in lambda
        /*
        * If b is even, then a*b is returned. Otherwise, a is returned.
        * This works because a holds the current result identity is 1
        * */
        int evenProduct = myList.stream().reduce(1,
                (a,b) ->
                    {if(b%2==0)
                        return a*b;
                        else
                            return a;});
        System.out.println("Product from stream for even numbers: " + evenProduct);

        /*
        Niko example:
            product of everything even then multiply by 10.
            accumulator needs to be associative so it doesn't matter if the order is mixed.
        */
        int nikoProduct = myList.stream().reduce(10,
                (a,b) ->
                {if(b%2==0)
                    return a*b;
                else
                    return a;});
        System.out.println("Niko Example- product of even number * 10: " + nikoProduct);


        /**
         * ParallelStreams
         *  - Same rules as reduce: stateless, non-interfering and associative.
         *
         * */


    }
}



