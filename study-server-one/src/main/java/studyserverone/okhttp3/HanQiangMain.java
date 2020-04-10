package studyserverone.okhttp3;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * @author zhutong
 * @date 2019/10/14 17:01
 */
public class HanQiangMain {
    
    public static void main(String[] args) throws Exception {
    
        System.out.println(BigDecimal.TEN.setScale(2,ROUND_HALF_UP).toPlainString());
       
    }
   
}
