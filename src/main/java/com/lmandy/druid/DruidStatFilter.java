package com.lmandy.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Druid拦截器，用于查看Druid监控
 * Created by lmandy on 2017/10/24.
 */
@WebFilter(
    filterName="druidWebStatFilter",
    urlPatterns="/*",
    initParams={
    // 忽略资源
    @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*,/static/*")
    }
)
public class DruidStatFilter extends WebStatFilter {
}
