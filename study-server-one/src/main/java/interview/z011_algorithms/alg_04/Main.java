package interview.z011_algorithms.alg_04;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。  没解答出来
 * @author zhutong
 * @date 2020/4/3 16:12
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println(longestPalindrome("aaaa"));
    }
    public static String longestPalindrome(String s) {
        
        char[] ss = s.toCharArray();
        if(ss.length == 0){
            return "";
        }
        if(ss.length == 1){
            return s;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int begIndex = 0;
        int endIndex = 0;
        int maxLength = 0;
        for (int i = 0; i < ss.length; i++) {
            int j =1;
            if(2 > maxLength){
                if(i+1 < ss.length && ss[i] == ss[i+1]){
                    begIndex = i;
                    endIndex = i+1;
                    maxLength = 2;
                }
            }
            while ((i -j >= 0) && (i+j < ss.length)  && (ss[i -j ] == ss[i+j])){
                if(2*j + 1 > maxLength){
                    begIndex = i -j ;
                    endIndex = i+j;
                    maxLength = 2*j + 1;
                }
                j++;
    
            }
            
        }
        for (int i = begIndex; i <= endIndex; i++) {
            stringBuilder.append(ss[i]);
        }
        if(endIndex+1 < ss.length){
    
            boolean flag = true;
            for (int i = 0; i < stringBuilder.toString().toCharArray().length; i++) {
                if(ss[endIndex+1] != stringBuilder.toString().toCharArray()[i]){
                    flag = false;
                }
            };
            if(flag){
                stringBuilder.append(ss[endIndex+1]);
            }
            
        }
        return stringBuilder.toString();
    }
    
    
    public static boolean check(int beg,int end, char[] ss){
        
        if(beg > 0 ){
            return false;
        }
        if(end >= ss.length){
            return false;
        }
        if(ss[beg] == ss[end]){
            return true;
        }else {
            return false;
        }
    }
}
