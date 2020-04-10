package interview.z012_java8;


import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;

/**
 * @author zhutong
 * @date 2020/3/25 10:52
 */
public class Main {
    
    public static void main(String[] args) {
    
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.addAll(null);
    
        test(2,v -> Double.valueOf(2));
    
    }
    public static <T> double test( T d,ToDoubleFunction<T> supplier){
    
        System.out.println(supplier.applyAsDouble(d));
        return supplier.applyAsDouble(d);
    }
}
