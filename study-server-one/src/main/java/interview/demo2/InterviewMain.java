package interview.demo2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhutong
 * @date 2019/10/30 21:27
 */
public class InterviewMain {
    
    
    public static void main(String[] args) {
    
        ff("zxcvbnm,./ ");
        
    }
    
    
    public static boolean ff(String string){
        
        char[] ss = string.toCharArray();
        for (char s : ss) {
            for (char c : ss) {
                if(s ==c){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean ff2(String string){
    
       
        char[] ss = string.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char s : ss) {
            set.add((Character)s);
        }
        return ss.length == set.size();
    }
    
    
}
