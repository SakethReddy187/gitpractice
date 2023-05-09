import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Unique {

    public static void main(String[] args){
        String input = "My job is software development.";
       LinkedHashMap<Character, Long> freqMap =  input.toLowerCase().chars().mapToObj(e-> (char)e)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,Collectors.counting()));
        //freqMap.entrySet().stream().forEach(e-> System.out.println(e));
        System.out.println(freqMap.entrySet().stream().filter(e-> e.getValue() > 1)
                .map(e->e.getKey()).findFirst().get());
    }
}
