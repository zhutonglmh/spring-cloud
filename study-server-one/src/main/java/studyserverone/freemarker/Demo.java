package studyserverone.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.core.io.FileSystemResource;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhutong
 * @date 2019/8/16 13:29
 */
public class Demo {
    
 
    public static void main(String[] args) throws Exception{
    
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(new File("D:/新建文件夹"));
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("demo.ftl");
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("name","朱同");
        File fileName = new File("D:/新建文件夹/demo2.xml");
        FileOutputStream out = new FileOutputStream(fileName);
        OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
        template.process(stringMap,osw);
        FileSystemResource systemResource = new FileSystemResource(fileName);
        boolean delete = fileName.delete();
    }
}
