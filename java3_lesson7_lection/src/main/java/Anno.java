import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Retention(RetentionPolicy.SOURCE) // будет видна в исходниках, компилироваться не будет
//@Retention(RetentionPolicy.CLASS) // будет компилироваться, но доступна только внутри класса
@Retention(RetentionPolicy.RUNTIME) // будет доступна нормально во время выполнения
@Target(ElementType.METHOD)
public @interface Anno {
    String description() default "Without description";
    int time() default 0;
}
