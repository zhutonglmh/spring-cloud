package studyserverone.base.link;

/**
 * @author zhutong
 * @date 2019/7/29 14:17
 */
public class SinglyLinkedList<T> {
    
    private Node<T> node;
    
    public SinglyLinkedList(){
       
    }
    
    public void add(T value){
        if(node != null){
            node.addEnd(value);
        }else {
            node = new Node<>();
            node.setValue(value);
        }
    }
    

    
    public void remove(T value){
        if(this.node != null){
            if(value.equals(this.node.getValue())){
                this.node = this.node.getNextNode();
            }
            this.node.remove(value);
        }
    }
    
    public int size(){
        int i = 0;
        Node node2 = this.node;
        while (node2 != null){
            node2 = node2.getNextNode();
            i++;
        }
        return i;
    }
    
    /**
     * 双指针 追着玩 大梦一场三千载 
     * @param k
     * @return
     */
    public Node<T> getNodeDesc(int k){
        
        Node<T> node = this.node;
        
       Node<Integer> p1 = new Node<>();
       Node<Integer> p2 = new Node<>();
       
       while (node != null){
           node = node.getNextNode();
           p1.setValue(p1.getValue() + 1);
           if(p1.getValue() >=  k){
               p2.setValue(p2.getValue() + 1);
           }
       }
       
       return null;
       
    }
    
    public void asc(){
        Node<T> temp = this.node;
        while (temp != null){
            System.out.println(temp.getValue());
            temp = temp.getNextNode();
        }
    }
    
    public void desc(){
        desc(this.node);
    }
    public void desc(Node<T> node){
        
        if(node != null){
            if(node.hasNextNode()){
                desc(node.getNextNode());
            }
            System.out.println(node.getValue());  
        }
       
    }
    
    public void overReturn(){
        Node<T> node1 = null;
        if(this.node != null){
            node1 = node.overReturn(null);
        }
        this.node = node1;
    }
    
    public void overReturn(Node<T> node){
        Node<T> node1 = null;
        if(this.node != null){
            node1 = node.overReturn(null);
        }
        this.node = node1;
    }
}
