package com.gavin.TestController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by 17428 on 2022/11/6.
 */

@Controller
@RequestMapping("config")
public class TestController {

    @Value("${useLocalCache}")
    private boolean useLocalCache;

    @Value("${k}")
    private String k;


    public void setUseLocalCache(boolean useLocalCache) {
        this.useLocalCache = useLocalCache;
    }

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }


    //拉取nacos配置验证
    @RequestMapping(value = "/getK", method = GET)
    @ResponseBody
    public String getK() {
        return k;
    }





}