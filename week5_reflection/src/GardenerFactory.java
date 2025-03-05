import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Properties;

public class GardenerFactory {

    public static Tree createTree(String treeName) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Properties props = new Properties();
//        InputStream file = new FileInputStream("Tree.properties");
        InputStream file = new FileInputStream("/Users/nl/IdeaProjects/bbk-sdp-2025/week5_reflection/src/Tree.properties");
        props.load(file);
        String name = props.getProperty(treeName);
        file.close();
        return createObject(name);

    }

    @SuppressWarnings("unchecked")
    static <T> T createObject(String treeName) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> c = Class.forName(treeName);
        Constructor<?>[] conArray = c.getDeclaredConstructors();
//        return (T)c.newInstance() <--direct call is deprecated!
        Object[] instantiationArgs = {4, "Georgia"};
        return (T)c.getDeclaredConstructor(conArray[0].getParameterTypes()).newInstance(instantiationArgs);

    }
}
