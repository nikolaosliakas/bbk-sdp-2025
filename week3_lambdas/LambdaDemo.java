import java.util.ArrayList;
import java.util.Collections;

public class LambdaDemo{
    public static void main(String[] args){
        MyNumber myNum; //declare interface reference

        /*Lambda is constant
        * When assigned to myNum, a class instance is constructed in which
        * the lambda expression implements getValue() method in MyNumber.
        * */
        // Lambda in assignment context
        myNum = () -> 123.45;

        System.out.println("A fixed value: " + myNum.getValue());

        // More complex
        myNum = () -> Math.random() * 100;
        // These call lambda expression in previous line
        System.out.println("A random value: " + myNum.getValue());
        System.out.println("Another random value: " + myNum.getValue());

        //The below fails
        // String cannot be converted to double.
//        myNum = () -> "123.25"
    }
}

class LambdaDemo2{
    public static void main(String[] args){
        //boolean test if number is even.
        NumericTest isEven = (n) -> (n % 2) == 0;

        if(isEven.test(10)) System.out.println("10 is even!");
        if(!isEven.test(9)) System.out.println("9 is not even!");

        //Testing non-negativity
        NumericTest isNonNegative = (n) -> n >= 0;
        if(isNonNegative.test(10)) System.out.println("10 is not negative!");
        if(!isNonNegative.test(-9)) System.out.println("9 is negative!");
    }
}

class LambdaDemo3{
    public static void main(String[] args){
        //boolean test if number is even.
        NumericTest2 isFactor = (n, d) -> (n % d) == 0;

        if(isFactor.test(10,2)) System.out.println("2 is a factor of 10!");
        if(!isFactor.test(10, 3)) System.out.println("3 is not a factor of 10!");


    }
}

class BlockLambdaDemo{
    public static void main(String[] args){
        //Takes int returns int
        NumericFunc factorial = (n) -> {
            int result = 1;

            for(int i=1; i <= n; i++){
                result = i * result;
            }
            return result;
        };

        System.out.println("The factorial of 3 is " + factorial.func(3));
        System.out.println("The factorial of 5 is " + factorial.func(5));


    }
}

class GenericFuncInterfaceDemo{
    public static void main(String[] args){
        SomeFunc<String> reverse = (str) -> {
            String result = "";
            int i;

            for( i = str.length() -1; i >=0; i--){
                result += str.charAt(i);
            }
            return result;
        };

        System.out.println("Lambda reversed is: " + reverse.func("Lambda"));
        System.out.println("Expression reversed is: " + reverse.func("Expression"));

        SomeFunc<Integer> factorial = (n) -> {
            int result = 1;

            for(int i=1; i <= n; i++){
                result = i * result;
            }
            return result;
        };
        System.out.println("The factorial of 3 is " + factorial.func(3));
        System.out.println("The factorial of 5 is " + factorial.func(5));
    }
}

/**
 * Using local variables available to lambda*/
interface MyFunc {
    int func(int n);
}
class VarCapture{
    public static void main(String[] args){
        //A local variable that can be captured.
        int num = 10;

        MyFunc myLambda = (n) -> {
            // legal as it doesn't modify num
            int v = num + n;

            // illegal as it attempts to modify num
            // Compilation: "Variable used in lambda expressions should be final or effectively final
//            num++
            return v;
        };
        // This line would remove effective finality from num and thus throw a compilation error in lambda
        //num = 9;
    }
}

interface StringFunc {
    String func(String n);
}
/**
 * Static Method references!
 * */
class MyStringOps{
    static String strReverse(String str){
        String result = "";
        int i;
        for(i = str.length()-1; i >= 0 ; i--){
            result += str.charAt(i);
        }
        return result;
    }
}
class MethodRefDemo{

    static String stringOp(StringFunc sf, String s){
        return sf.func(s);
    }

    public static void main(String[] args){
        String inStr = "Lambdas add power to Java";
        String outStr;

        // Here, a method reference to strReverse is passed to stringOp().
        outStr = stringOp(MyStringOps::strReverse, inStr);
        System.out.println("Original string: " + inStr);
        System.out.println("String reversed: " + outStr);
    }
}

/**
 * Instance method references
 * */
class MyStringOps2{
    String strReverse(String str){
        String result = "";
        int i;
        for(i = str.length()-1; i >= 0 ; i--){
            result += str.charAt(i);
        }
        return result;
    }
}
class MethodRefDemo2{
    // NB - you still need the static method to call into main.
    static String stringOp(StringFunc sf, String s){
        return sf.func(s);
    }

    public static void main(String[] args){
        String inStr = "Lambdas add power to Java";
        String outStr;

        MyStringOps2 strOps = new MyStringOps2();
        // Here, a method reference to strReverse is passed to stringOp().
        outStr = stringOp(strOps::strReverse, inStr);
        System.out.println("Original string: " + inStr);
        System.out.println("String reversed: " + outStr);
    }
}

//
class MyClass{
    private int val;
    MyClass (int v) {val = v;}
    int getVal(){return val;}
}
class UseMethodRef{
    static int compareMC(MyClass a , MyClass b){
        return a.getVal() - b.getVal();
    }
    public static void main(String[] args){
        ArrayList<MyClass> al = new ArrayList<>();

        al.add(new MyClass(1));
        al.add(new MyClass(2));
        al.add(new MyClass(3));
        al.add(new MyClass(40));
        al.add(new MyClass(5));
        al.add(new MyClass(16));
        al.add(new MyClass(15));
        al.add(new MyClass(3));

        MyClass maxValObj = Collections.max(al, UseMethodRef::compareMC);
        System.out.println("Maximum value is: " + maxValObj.getVal());
    }
}

