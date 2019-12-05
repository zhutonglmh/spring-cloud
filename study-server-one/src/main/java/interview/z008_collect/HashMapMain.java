package interview.z008_collect;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhutong
 * @date 2019/12/5 10:02
 */
public class HashMapMain {
    
    public static void main(String[] args) {
    
                                                                                        
        String a = "zhutong";   //hash  -304651013   0001 0010 0010 1000 1001 1011 0000 0101  符号位置为负1 后取反
                                //                   1110 1101 1101 0111 0110 0100 1111 1011   补码 = 反码+1     负数的码 = 正数的反码 + 1
                                // 无符号右移16位    0000 0000 0000 0000 1110 1101 1101 0111    异或  参与运算的两数各对应的二进位相异或,当两对应的二进位相异时,结果为1
                                //按位异或           1110 1101 1101 0111 1000 1001 0010 1100        
                                // 初始容量-1        0000 0000 0000 0000 0000 0000 0000 1111
                                // 取模运算(&)       0000 0000 0000 0000 0000 0000 0000 1100   12              这也就是为什么是要2的幂次方  因为只有这时候取模才可以用 hash ^ (length - 1)                    
        String b = "limeihua"; //hash  1160645952    0100 0101 0010 1110 0000 1101 0100 0000
                                // 无符号右移16位    0000 0000 0000 0000 0100 0101 0010 1110 
                                //按位异或           0100 0101 0010 1110 0100 1000 0110 1110
        String c = "li1ei1ua";                         // 初始容量-1        0000 0000 0000 0000 0000 0000 0000 1111   
    
        String d = "lime11ua";                        // 取模运算(&)       0000 0000 0000 0000 0000 0000 0000 1110   14     
//        System.out.println(a.hashCode());
//        System.out.println(b.hashCode());
        Map demo =  new HashMap<String,String>(2); //初始容量16

        //扰动函数  >>> 无符号右移16 位   右移  正数补0 负数补1  左移  全补0  异或
        demo.put(a,null);//   12
        demo.put(b,null);//   14
        demo.put(c,null);//   14
        demo.put(d,null);//   14
        
        //00  02
    
        System.out.println((a.hashCode() ^ (a.hashCode()>>> 16)) & 32); //0
        System.out.println((b.hashCode() ^ (b.hashCode()>>> 16)) & 32); //2
    
        System.out.println((a.hashCode() ^ (a.hashCode()>>> 16)) & 63); //0
        System.out.println((b.hashCode() ^ (b.hashCode()>>> 16)) & 63); //2
        
       
    }//12  14 
}
