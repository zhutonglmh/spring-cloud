package studyserverone.base.link;

/**
 * @author zhutong
 * @date 2019/7/29 14:55
 */
public class Node<T> {
    
    
    private T value;
    private Node<T> nextNode;
    
    public void addEnd(T value) {
        if (this.hasNextNode()) {
            this.nextNode.addEnd(value);
        } else {
            Node<T> nextNode = new Node<>();
            nextNode.setValue(value);
            this.nextNode = nextNode;
        }
    }
    
    
    public boolean hasNextNode() {
        
        return this.nextNode != null;
    }
    
    public Node<T> getNextNode() {
        return this.nextNode;
    }
    
    public T getValue() {
        return value;
    }
    
    public void setValue(T value) {
        this.value = value;
    }
    
    
    public void remove(T value){
        if(this.hasNextNode()){
            if(this.nextNode.getValue().equals(value)){
                this.nextNode = this.nextNode.nextNode;
            }else {
                this.nextNode.remove(value);
            };
        }        
    };
    
    public Node<T> overReturn(Node<T> node){
        if(hasNextNode()){
            Node<T> v =this.getNextNode().overReturn(this);
            this.nextNode = node;
            return v;
        }else {
            this.nextNode = node;
            return this;
        }
    }
}

