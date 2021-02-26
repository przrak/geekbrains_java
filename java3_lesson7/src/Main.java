import main.AfterSuite;
import main.BeforeSuite;
import main.Test;
import test.TestTask;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {
    public static void main(String[] args)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException,
            InvocationTargetException, ClassNotFoundException {
        /*
        Создать класс, который может выполнять «тесты», в качестве тестов выступают классы с
        наборами методов с аннотациями @main.Test. Для этого у него должен быть статический метод
        start(), которому в качестве параметра передается или объект типа Class, или имя класса.
         Из «класса-теста» вначале должен быть запущен метод с аннотацией @main.BeforeSuite, если
         такой имеется, далее запущены методы с аннотациями @main.Test, а по завершению всех
         тестов –
          метод с аннотацией @main.AfterSuite. К каждому тесту необходимо также добавить приоритеты
          (int числа от 1 до 10), в соответствии с которыми будет выбираться порядок их
          выполнения, если приоритет одинаковый, то порядок не имеет значения. Методы с
          аннотациями @main.BeforeSuite и @main.AfterSuite должны присутствовать в единственном
          экземпляре, иначе необходимо бросить RuntimeException при запуске «тестирования».
         */
        start(TestTask.class);
//        start("test.TestTask");
    }

    public static <T> void start(final Class<T> clazz)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException,
            InstantiationException {
        Method[] methods = clazz.getDeclaredMethods();
        Constructor<T> constr = clazz.getConstructor();
        T instance = constr.newInstance();
        checkAnnotations(methods, instance);
    }

    public static void start(final String className)
            throws ClassNotFoundException, InvocationTargetException, IllegalAccessException,
            NoSuchMethodException, InstantiationException {
        Class<?> clazz = Class.forName(className);
        Method[] methods = clazz.getDeclaredMethods();
        Constructor<?> constr = clazz.getConstructor();
        Object instance = constr.newInstance();
        checkAnnotations(methods, instance);
    }

    private static <T> void checkAnnotations(
            final Method[] methods,
            final T obj)
            throws InvocationTargetException, IllegalAccessException {
        Method firstMethod = null;
        Method lastMethod = null;
        final Map<Integer, List<Method>> treeMap = new HashMap<>();
        List<Method> list = new ArrayList<>();
        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (firstMethod != null) {
                    throw new RuntimeException("BeforeSuite annotation can be only one in class");
                }
                firstMethod = m;
            }
            if (m.isAnnotationPresent(AfterSuite.class)) {
                if (lastMethod != null) {
                    throw new RuntimeException("AfterSuite annotation can be only one in class");
                }
                lastMethod = m;
            }
            if (m.isAnnotationPresent(Test.class)) {
                Test annotation = m.getAnnotation(Test.class);
                int priority = annotation.priority();
                if (priority > Test.MAX_PRIORITY) {
                    throw new RuntimeException("Priority more than MAX_PRIORITY(10)");
                }
                if (priority < Test.MIN_PRIORITY) {
                    throw new RuntimeException("Priority less than MIN_PRIORITY(0)");
                }
                List<Method> tmp;
                if (treeMap.containsKey(priority)) {
                    tmp = treeMap.get(priority);
                } else {
                    tmp = new ArrayList<>();
                }
                tmp.add(m);
                treeMap.put(priority, tmp);
            }
        }
        if (firstMethod != null) {
            firstMethod.invoke(obj);
        }
        for (List<Method> methodList : treeMap.values()) {
            for (Method method : methodList) {
                method.invoke(obj);
            }
        }
        if (lastMethod != null) {
            lastMethod.invoke(obj);
        }
    }
}
