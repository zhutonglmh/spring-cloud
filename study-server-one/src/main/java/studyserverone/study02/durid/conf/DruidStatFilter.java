package studyserverone.study02.durid.conf;


import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @author 朱同
 */
@WebFilter(filterName="druidWebStatFilter" ,urlPatterns= "/*",
initParams={
@WebInitParam(name="exclusions",value=".js,.gif,.jpg,.bmp,.png,.css,.ico,/druid/")//忽略资源
}
)
public class DruidStatFilter extends WebStatFilter {
}

