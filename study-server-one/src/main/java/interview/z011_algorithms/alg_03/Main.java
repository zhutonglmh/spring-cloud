package interview.z011_algorithms.alg_03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhutong
 * @date 2020/1/15 17:59
 */
public class Main {
    
    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(lengthOfLongestSubstring("bbbbb"));
//        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("au"));
        
    }
    public static  int lengthOfLongestSubstring(String s) {
        
        char[] arr = s.toCharArray();
    
        if(arr.length == 0){
            return 0;
        }
        Map<Character,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int length = 0;
        for (int i = 0; i < arr.length; i++) {
            if(map.containsKey(arr[i])){
               
                length = i + 1 - map.get(arr[i]);
                list.add(length);
                map.put(arr[i],i+1);
            }else {
                map.put(arr[i],i+1);
                length++;
                list.add(length);
            }
        }
        int temp = 1;
        for (Integer integer : list) {
            temp = temp > integer?temp:integer;
        }
        return temp;
    }
}
