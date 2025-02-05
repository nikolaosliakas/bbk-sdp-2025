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
        System.out.println("Original List: "+ myList);
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
         * version of reduce() for ParallelStreams
         *
         * <pre>{@code
         * <U> U reduce(
         *          U identityVal,
         *          BiFunction<U, ? super T,U> accumulator,
         *          BinaryOperator<U> combiner
         *          )}</pre>
         *
         *  Note from API:
         *
         * Many reductions using this form can be represented more simply by
         * an explicit combination of map and reduce operations.
         * The accumulator function acts as a fused mapper and accumulator, which
         * can sometimes be more efficient than separate mapping and reduction,
         * such as when knowing the previously reduced value allows you to avoid
         * some computation.
         *
         * Some parallelStreams need different formulas for combiner and accumulator
         * */

        double productOfSqrRoots = myList.parallelStream().reduce(
                1.0,
                (a, b) -> a * Math.sqrt(b),
                (a, b) -> a * b
        );
        System.out.println("Product of square roots: "+ productOfSqrRoots);

    }
}
class StreamDemo4{
    /**
     * <pre>@code{
     *  <R> Stream <R> map(Function < ? super T, ? extends R> mapFunc)
     * }</pre>
     * R - return type
     * T - Type of elements of invoking stream
     * mapFunc is instance of 'Function'.
     *
     * Rules:
     *      - Stateless
     *      - Non-interfering
     * Since new stream returned map() is intermediate method.
     * */
    public static void main(String[] ars) {

        ArrayList<Double> myList = new ArrayList<>();

        myList.add(7.0);
        myList.add(18.0);
        myList.add(10.0);
        myList.add(24.0);
        myList.add(17.0);
        myList.add(5.0);

        // Map the square root of the elements in myList to a new stream
        Stream<Double> sqrtRootStrm = myList.stream().map((a) -> Math.sqrt(a));
        Stream<Double> sqrtRootStrm2 = myList.stream().map(Math::sqrt);

        //Product of square root
        double productOfSqrRoots = sqrtRootStrm.reduce(1.0, (a,b) -> a* b);
        double productOfSqrRoots2 = sqrtRootStrm2.reduce(1.0, (a,b) -> a*b);

        System.out.println("Product of square roots is " + productOfSqrRoots);
        System.out.println("Product of square roots is " + productOfSqrRoots2);


    }

}
/// map() to create new stream containing only selected fields from original.
class NamePhoneEmail {
    String name;
    String phonenum;
    String email;

    NamePhoneEmail(String n, String p, String e){
        name = n;
        phonenum = p;
        email = e;
    }

    /// Niko addition - not from textbook
    public void printOut(){
        System.out.println(name + " " + phonenum + " " + email );
    }
}

class NamePhone{
    String name;
    String phonenum;


    NamePhone(String n, String p){
        name = n;
        phonenum = p;
    }
    /// Niko addition - not from textbook
    public void printOut(){
        System.out.println(name + " " + phonenum );
    }
}

class StreamDemo5{
    public static void main(String[] args){
        ArrayList<NamePhoneEmail> myList = new ArrayList<>();
        myList.add(new NamePhoneEmail("Larry", "555-5555", "larry@herbschildt.com"));
        myList.add(new NamePhoneEmail("Mary", "333-3333", "mary@shenzhen.com"));
        myList.add(new NamePhoneEmail("Jade", "222-9999", "jade@copernicus.com"));

        System.out.println("Original values are: ");
        myList.forEach(NamePhoneEmail::printOut);
        System.out.println();

        // Map just name and phone number

        Stream<NamePhone> nameAndPhone = myList.stream().map(
                (a) -> new NamePhone(a.name, a.phonenum)
        );

        System.out.println("List of names and phonenumbers: ");
        nameAndPhone.forEach(NamePhone::printOut);
    }
}
/** Collecting
 *       Obtaining a collection from a stream
 *   collect()
 * <pre>
 *  <R,A>  R collect(
 *                      Collector<? super T, A, R> collectorFunc
 *                  )
 * </pre>
 *
 * R = result type
 * T = element type of invoking stream
 * A = accumulated type
 *
 * collectorFunc - specifies __how__ collection process works
 *
 * Method is terminal.
 */

class StreamDemo7 {
    public static void main(String[] args){
        ArrayList<NamePhoneEmail> myList = new ArrayList<>();
        myList.add(new NamePhoneEmail("Larry", "555-5555", "larry@herbschildt.com"));
        myList.add(new NamePhoneEmail("Mary", "333-3333", "mary@shenzhen.com"));
        myList.add(new NamePhoneEmail("Jade", "222-9999", "jade@copernicus.com"));

        Stream<NamePhone> nameAndPhone = myList.stream().map(
                (a) -> new NamePhone(a.name, a.phonenum)
        );
        /// collect( Collectors.toList )
        ///  TODO - raise in class, why does the IDE suggest something that changes the output from mutable reduction
                ///  return of .collect() with .toList() which returns an unmodifiable collection
        List<NamePhone> npList = nameAndPhone.collect(Collectors.toList());
//        List<NamePhone> npListDirect = nameAndPhone.toList(); //
        System.out.println("Names and phone numbers in List:");
        for(NamePhone e : npList)
            e.printOut();

        // New mapping of a names and phones - NB intermediate operation
        nameAndPhone = myList.stream().map((a) -> new NamePhone(a.name, a.phonenum));
        /// collect( Collectors.toSet )
        Set<NamePhone> npSet = nameAndPhone.collect(Collectors.toSet());
        System.out.println("\n Names and phone numbers in Set:");
        for(NamePhone e : npSet)
            e.printOut();

        ///  collect for LinkedLists
        nameAndPhone = myList.stream().map((a) -> new NamePhone(a.name, a.phonenum));
        LinkedList<NamePhone> npLList = nameAndPhone.collect(
                /// Supplier<R> target - only method Supplier.get() returns a reference to a mutable storage obj
                () -> new LinkedList<>(),
                /// BiConsumer<R, ? super T> accumulator - void BiConsumer.accept(T obj, U obj2)
                            /// accumulator is obj is target collection and obj2 is the element to place in that target collection
                (list, element) -> list.add(element),
                /// BiConsumer<R, R> combiner
                            /// specifies two collections that will be combined
                (listA, listB) -> listA.addAll(listB)
        );
//        // Does the same with method references
//        LinkedList<NamePhone> npLList = nameAndPhone.collect(
//                LinkedList::new, // AKA Supplier<R> target
//                LinkedList::add,
//                LinkedList::addAll
//        );

    }
}

/// Iterators with streams
class StreamDemo8{

    public static void main(String[] args) {
        ArrayList<String> myList = new ArrayList<>();
        myList.add("Alpha");
        myList.add("Beta");
        myList.add("Gamma");
        myList.add("Delta");
        myList.add("Phi");
        myList.add("Omega");

        Stream<String> myStream = myList.stream();
        Iterator<String> itr = myStream.iterator();

        myStream = myList.stream();
        Spliterator<String> splitItr = myStream.spliterator();
        while(itr.hasNext())
            System.out.println(itr.next());
        /// Consolidates the next() and hasNext() functions by 'try'-ing on the next element but encapulating with
        ///  return to false if there are no more methods to try
        while(splitItr.tryAdvance((n)-> System.out.println("Greek letter: " + n)));

        /// Using Spliterator forEachRemaining()
        ///  no loop needed to be provided.
        splitItr.forEachRemaining((n) -> System.out.println(n));
//        splitItr.forEachRemaining(System.out::println);
    }
}




