import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Gardener {

    public static void main(String[] args) throws ClassNotFoundException {
//        String className = getClassName("peach");
//        Class<?> c = Class.forName(className);
        try {
            Tree myTree = GardenerFactory.createTree("peach");
            System.out.println("hello");
        } catch (IOException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        System.out.println("myTree.");
    }

    /**
     * Read a file (txt, properties,xml)  at runtime
     * returns a string in `java.aws.Color` format for each class*/
    private static String getClassName(String fruit){
        return switch(fruit){
            case "peach" -> "PeachTree";
            case "lemon" -> "LemonTree";
            default -> throw new IllegalStateException("No trees with this fruit type: " + fruit);
        };
    }


}
