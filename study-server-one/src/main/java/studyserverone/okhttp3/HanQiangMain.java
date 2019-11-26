package studyserverone.okhttp3;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhutong
 * @date 2019/10/14 17:01
 */
public class HanQiangMain {
    
    public static void main(String[] args) throws Exception {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), "{\"sql\":\"SELECT a.id as id ,c.id as cate_id,c.cate_name as cate_name from scm_stock_goods_detail a\\r\\nleft join scm_goods b on a.goods_id = b.id\\r\\nleft join scm_goods_category c on b.cate_id = c.id\\r\\nwhere (a.cate_id is null) or (a.cate_name is null)\",\"basename\":\"online_cloud_scm\",\"source\":\"online_scm_select\"}");
        Request requestOk = new Request.Builder()
                                    .url("http://47.98.58.111:18080/api/v2/query")
                                    .post(body)
                                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzExMzg3MDcsIm5hbWUiOiLmnLHlkIwiLCJyb2xlIjoiZ3Vlc3QifQ.0YX0Xlwr-0L-HYGexocyMLtyI2jpRJ6F7M8XBWzlB68")
                                    .build();
        Response response;
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(16000, TimeUnit.SECONDS)//设置连接超时时间
                                      .readTimeout(16000, TimeUnit.SECONDS)//设置读取超时时间
                                      .build();
        response = client.newCall(requestOk).execute();
        String jsonString = response.body().string();
        if (response.isSuccessful()) {
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            List<Map<String, Object>> maps = (List<Map<String, Object>>) jsonObject.get("data");
        
            for (Map<String, Object> s : maps) {
                System.out.println("update scm_stock_goods_detail set cate_id = \""+s.get("cate_id")+"\",cate_name = \""+s.get("cate_name")+"\" where id = \""+s.get("id")+"\";\n");
            }
        }
    }
}
