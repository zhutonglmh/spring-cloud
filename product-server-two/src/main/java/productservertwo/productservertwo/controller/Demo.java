package productservertwo.productservertwo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping("demo")
@RestController
public class Demo {

    @GetMapping("/demo")
    public String demo(){
        return "8763 ：朱同";
    }
    
    public static void main(String[] args) throws Exception{
        BigDecimal s  = BigDecimal.valueOf(33.33333363333333);
        BigDecimal ss = BigDecimal.valueOf(20.0000002);
        System.out.println(s.divide(ss,8,BigDecimal.ROUND_HALF_UP));
    
        BigDecimal s1  = BigDecimal.valueOf(3.744855662921811);
        BigDecimal ss1 = BigDecimal.valueOf(2.2469134);
        System.out.println(s1.divide(ss1,8,BigDecimal.ROUND_HALF_UP));
    }
    
  
    private void ss (Node node){
    
        if(null == node){
            return;
        }
        ss(node.left);
        ss(node.right);
        System.out.println(node.value);
    }
    
    class Node{
        private Node left;
        
        private Node right;
        
        private String value;
    }
}
