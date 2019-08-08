package studyserverone.base.link;

/**
 * @author zhutong
 * @date 2019/7/29 15:01
 */
public class Main {
    
    public static void main(String[] args) {
        SinglyLinkedList<String> stringSinglyLinkedList = new SinglyLinkedList<>();
        stringSinglyLinkedList.add("1");
        stringSinglyLinkedList.add("2");
        stringSinglyLinkedList.add("3");
        stringSinglyLinkedList.add("4");
        stringSinglyLinkedList.add("5");
        stringSinglyLinkedList.add("6");
        stringSinglyLinkedList.add("7");
//        System.out.println(stringSinglyLinkedList.size());
//        stringSinglyLinkedList.asc();
//        stringSinglyLinkedList.remove("5");
//        stringSinglyLinkedList.asc();
//        stringSinglyLinkedList.desc();
//        stringSinglyLinkedList.overReturn();
//        stringSinglyLinkedList.asc();
        System.out.println(stringSinglyLinkedList.getNodeDesc(3).getValue());
        
    }
}
