package studyserverone.okhttp3;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zhutong
 * @date 2019/10/14 17:01
 */
public class HanQiangMain {
    
    public static void main(String[] args) throws Exception {
    
        Date date = DateUtil.parse("2019-11-29","yyyy-MM-dd");
        System.out.println( DateUtils.isSameDay(date, new Date()));
       
    }
   
}
