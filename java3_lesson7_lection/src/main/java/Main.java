import java.lang.reflect.*;

public class Main {
    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class c1 = Cat.class;

        Class c2 = new Cat("A", 3, "R").getClass();

        Class c3 = Class.forName("Cat");

        Constructor<Cat> constr = c1.getConstructor(String.class,
                int.class, String.class);
        Cat cat = constr.newInstance("Barsik", 5, "red");
        System.out.println(cat);

        System.out.println(int.class);
        System.out.println(int[].class);
        System.out.println(int[][].class);

        Method[] methods = c1.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        Method[] methods1 = c1.getDeclaredMethods();
        for (Method method : methods1) {
            System.out.println(method);
        }

        Method mInfo = c1.getDeclaredMethod("info", int.class);
        mInfo.invoke(cat, 5);

        int mod = mInfo.getModifiers();
        System.out.println(mod);

        System.out.println(Modifier.toString(mod));

        Field[] fields = c1.getFields();
        for (Field f : fields) {
            System.out.println(f);
        }

        Field[] fields1 = c1.getDeclaredFields();
        for (Field f : fields1) {
            System.out.println(f);
        }

        Field field = c1.getDeclaredField("name");
        field.setAccessible(true);
        System.out.println(field.get(cat));

        field.set(cat, "purple");

        System.out.println(cat);

        //SecurityManager // загуглить

        Class mt = MyTest.class;
        Method[] methods2 = mt.getDeclaredMethods();
        for (Method m : methods2) {
            if (m.isAnnotationPresent(Anno.class)) {
                System.out.println(m.getAnnotation(Anno.class));
                m.setAccessible(true);
                m.invoke(null);
            }
        }
    }
}
