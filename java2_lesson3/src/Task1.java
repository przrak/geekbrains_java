import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Task1 {
    public static void main(final String[] args) {
        final String[] arr = new String[]{
                "hello",
                "bye",
                "world",
                "message",
                "bye",
                "hello",
                "home",
                "red",
                "block",
                "cat",
                "dog",
                "bye",
                "tv",
                "hello"
        };
        final Set<String> set = Arrays.stream(arr)
                .collect(Collectors.toSet());
        System.out.println(set.toString());
        final Map<String, Long> map = Arrays.stream(arr)
                .collect(Collectors.groupingBy(str -> str, Collectors.counting()));
        System.out.println(map);
    }
}
