package studyserverone.okhttp3;

import okhttp3.*;

import java.io.File;

/**
 * @author zhutong
 * @date 2019/8/20 16:13
 */
public class Okhttp3Utils {
    
    /**
     * xml格式post请求接口调用
     * @param url 接口地址
     * @param xmlStr xml格式请求参数体
     * @return
     */
    public static String postXml(){
        RequestBody body=RequestBody.create(MediaType.parse("application/json"),"{\"sql\":\"select * from scm_store limit 1\",\"basename\":\"online_cloud_scm\",\"source\":\"online_scm_select\"}");
        Request requestOk = new Request.Builder()
                                    .url("http://47.98.58.111:18080/api/v2/query")
                                    .post(body)
                                    .header("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NjkzMzYzMjMsIm5hbWUiOiLmnLHlkIwiLCJyb2xlIjoiZ3Vlc3QifQ.R-IecmrMFeS9DSniOG01UrmVjyh7Tht1IAXFNDj_lyE")
                                    .build();
        
        Response response;
        try {
            response = new OkHttpClient().newCall(requestOk).execute();
            String jsonString = response.body().string();
            if(response.isSuccessful()){
                return jsonString;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "";
    }
    
    /**
     * get请求接口，返回json
     * @param url 接口地址
     * @return
     */
    public static String getJson(String url){
// RequestBody body=RequestBody.create(MediaType.parse("application/json"),"");
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .get()
                                    .build();
        
        Response response;
        try {
            response = new OkHttpClient().newCall(requestOk).execute();
            String jsonString = response.body().string();
            if(response.isSuccessful()){
                return jsonString;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "";
    }
    
    /**
     * 发送文件
     * @param url 请求接口地址
     * @param uploadDir 参数上传目录
     * @param baseFileUrl 文件保存基准路径
     * @param relativeUrl 文件保存的相对路径
     * @return 接口返回值
     * 该方法前端以formData格式传入，包括文件和参数，可一起传入。
     */
    public String uploadFilePost(String url,String uploadDir,String baseFileUrl,String relativeUrl){
        
        File temporaryFile = new File(baseFileUrl+relativeUrl);
        if(!temporaryFile.exists()){
            return "";
        }
        RequestBody requestBody = new MultipartBody.Builder()
                                          .addFormDataPart("uploadDir", uploadDir) //参数一，可注释掉
                                          .addFormDataPart("fileUrl", relativeUrl) //参数二，可注释掉
                                          .addFormDataPart("file", temporaryFile.getName(), RequestBody.create(MediaType.parse("application/octet-stream"),temporaryFile)) //文件一
                                          .build();
        Request requestOk = new Request.Builder()
                                    .url(url)
                                    .post(requestBody)
                                    .build();
        
        Response response;
        try {
            response = new OkHttpClient().newCall(requestOk).execute();
            String jsonString = response.body().string();
//	temporaryFile.delete();
            if(response.isSuccessful()){
                return jsonString;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
