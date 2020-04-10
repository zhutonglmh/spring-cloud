package interview.z011_algorithms.alg_02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhutong
 * @date 2020/1/15 15:00
 */
public class Main {
    
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l2.next = l3;
        l1.next = l2;
    
        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);
        l5.next = l6;
        l4.next = l5;
        ListNode s = addTwoNumbersFast(l1,l4);
        while (s != null){
            System.out.println(s.val);
            s = s.next;
        }
    }
    
    
    public static ListNode addTwoNumbersFast(ListNode l1, ListNode l2){
    
        ListNode root = new ListNode(0);
        ListNode result = root;
        int array = 0;
        while(l1 != null || l2 != null || array != 0){
            
            int resultInt = (null ==  l1 ? 0 : l1.val) + (null == l2 ? 0 : l2.val) + array;
            array = resultInt / 10;
            int val = resultInt % 10;
            ListNode listNode = new ListNode(0);
            listNode.val = val;
            root.next = listNode;
            root = listNode;
            l1 = null != l1 ?  l1.next : null;
            l2 = null != l2 ?  l2.next : null;
        }
        return result.next;
    }
    
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        
        
        while(l1 != null){
            list1.add(l1.val);
            l1 = l1.next;
        }
        
        while(l2 != null){
            list2.add(l2.val);
            l2 = l2.next;
        }
        
        ListNode resultNode = new ListNode(0);
                
        List<ListNode> listNodes = new ArrayList<>();
        ListNode resultNodeFinal = resultNode;
        int size = list1.size() > list2.size() ? list1.size() : list2.size();
        for(int i = 0; i < size;i++){
            
            Integer add1 = i >= list2.size() ? 0 : list2.get(i);
            Integer add2 = map.getOrDefault(i, 0);
            Integer add3 = i >= list1.size() ? 0 : list1.get(i);
            int result = add3 + add1 + add2;
            
            if(result >= 10){
                int val = result - 10;
                map.put(i + 1,1);
                resultNode.val = val;
            }else{
                resultNode.val = result;
            }
            
            if(i != size -1){
                ListNode resultNodeNext = new ListNode(1);
                resultNode.next = resultNodeNext;
                resultNode = resultNodeNext;
            }else{
                if(result >= 10){
                    ListNode resultNodeNext = new ListNode(1);
                    resultNodeNext.val = 1;
                    resultNode.next = resultNodeNext;
                    resultNode = resultNodeNext;
                }
            }
        }
        return resultNodeFinal;
    }

    
    
 
}
