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
