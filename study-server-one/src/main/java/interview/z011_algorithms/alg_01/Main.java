package interview.z011_algorithms.alg_01;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhutong
 * @date 2020/1/15 14:20
 * 
 */
public class Main {
    
//    给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//    你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
//    示例:
//    给定 nums = [2, 7, 11, 15], target = 9
//    因为 nums[0] + nums[1] = 2 + 7 = 9
//    所以返回 [0, 1]
    
   
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
    
        int[] result = twoSum(nums,target);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
    
    public static int[] twoSum(int[] nums, int target) {
    
        int[] result = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
    
        for (int i = 0; i < nums.length; i++) {
            
            if(map.containsKey(nums[i])){
    
                result[0] = map.get(nums[i]);
                result[1] = i;
                return result;
            }
            map.put(target - nums[i],i);
        }
        return result;
    }
}


