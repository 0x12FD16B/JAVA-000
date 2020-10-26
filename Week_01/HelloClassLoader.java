import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> klass = new HelloClassLoader().findClass("Hello");
        Object o = klass.newInstance();
        Method method = klass.getMethod("hello");
        method.invoke(o);
    }

    @Override
    protected Class<?> findClass(String name) {
        try (InputStream resourceStream = this.getClass().getResourceAsStream("Hello.xlass")) {
            try {
                byte[] bytes = new byte[resourceStream.available()];
                resourceStream.read(bytes);
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = (byte) (255 - bytes[i]);
                }
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}