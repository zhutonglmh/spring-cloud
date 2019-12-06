package interview.z001_base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhutong
 * @date 2019/12/6 20:31
 */
public class Algorithm {
    
    //java 程序设计  实现最小值函数
    
    public static void main(String[] args) {
        int minInteger = min(new Integer[]{1, 2, 3});//result:1
        double minDouble = min(new Double[]{1.2, 2.2, -1d});//result:-1d
        System.out.println(1);
    }
    
    /**
     * 一个泛型的获取数组最小值的函数.并且这个方法只能接受Number的子类并且实现了Comparable接口。
     * <p>
     * <? super T>  模拟逆变 <? extends T> 模拟协变
     */
    private static <T extends Number & Comparable<? super T>> T min(T[] values) {
        if (null == values) return null;
        T min = values[0];
        for (int i = 1; i < values.length; i++) {
            T t = values[i];
            if (min.compareTo(t) > 0) {
                min = t;
            }
        }
        return min;
    }
    
    
}
