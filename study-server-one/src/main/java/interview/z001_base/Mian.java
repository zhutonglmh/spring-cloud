package interview.z001_base;

/**
 * @author zhutong
 * @date 2020/1/14 16:29
 */
public class Mian {
    
    public static void main(String[] args) {
    
        System.out.println(method("bafd"));
    }
    
    public static char method(String s){
        
        char[] a1= s.toCharArray();
        
        int[] ss = new int[200];
    
        for (int i = 0; i < a1.length; i++) {
            char temp = a1[i];
            
            int result = ss[temp];
            if(result == 0){
                ss[temp] = 1;
            }
            if(result == 1){
                ss[temp] = 2;
            }
        }
    
        for (int i = 0; i < a1.length; i++) {
            char temp = a1[i];
            if(ss[temp] == 1){
                return temp;
            }
        }
        return 'z';
    }
}
