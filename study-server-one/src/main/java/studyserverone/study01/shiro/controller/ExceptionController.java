package studyserverone.study01.shiro.controller;

import org.apache.shiro.authc.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import studyserverone.study01.shiro.conf.ResultMap;

/**
 * @author zhutong
 * @date 2019/4/16 15:40
 */
@RestControllerAdvice
public class ExceptionController {
    
    private final ResultMap resultMap;
    
    @Autowired
    public ExceptionController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }
    
    /** 捕捉 CustomRealm 抛出的异常*/
    @ExceptionHandler(AccountException.class)
    public ResultMap handleShiroException(Exception ex) {
        return resultMap.fail().message(ex.getMessage());
    }
    
}
