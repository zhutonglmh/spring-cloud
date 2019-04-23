package studyserverone.study01.shiro.conf;

import lombok.Data;

/**
 * @author zhutong
 * @date 2019/4/16 15:30
 */
@Data
public class ResultMap {
    
    private String code;
    
    private String message;
    
    public ResultMap success(){
        this.code = "200";
        return this;
    }
    
    public ResultMap message(String message){
        this.message = message;
        return this;
    }
    
    public ResultMap fail(){
        this.code = "500";
        return this;
    }
    
}
