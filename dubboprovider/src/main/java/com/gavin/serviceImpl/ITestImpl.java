package com.gavin.serviceImpl;



import com.gavin.service.ITest;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * Created by 17428 on 2022/11/6.
 *
 * 注册接口
 */

@DubboService(version = "${provider.version}")
public class ITestImpl implements ITest {



    public String Hello() {
        return "helli";
    }


}
