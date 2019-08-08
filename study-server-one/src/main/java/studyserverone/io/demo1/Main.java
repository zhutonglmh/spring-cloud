package studyserverone.io.demo1;

import io.netty.util.internal.StringUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhutong
 * @date 2019/6/25 14:57
 */
public class Main {
    
    public static void main(String[] args) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String proDate = "2018/7/25";
    
        Date pro = format.parse(proDate.toString());
        System.out.println(format.format(pro));;
    }
}
