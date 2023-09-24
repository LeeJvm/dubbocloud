package com.gavin.consumer.rest.common;

import com.gavin.consumer.service.LoginService;
import com.gavin.enums.ProcessCodeEnum;
import com.gavin.pojo.LoginUser;
import com.gavin.pojo.ResponseResult;
import com.gavin.pojo.SentimentDTO;
import com.gavin.pojo.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 17428 on 2023/4/1.
 */


@RestController
@RequestMapping("/api")
@Api("标准演示接口")
@Slf4j
public class TestController {



    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    LoginService loginService;


    @Autowired
    PasswordEncoder passwordEncoder;



    @PostMapping("/test1")
    @ApiOperation(value = "test1接口",notes = "根据入参，设置更新参数；然后返回参数")
    public String test1(@ApiParam(name ="test1接口") @RequestBody SentimentDTO sentimentDTO) throws  Exception {



        sentimentDTO.setEndDate("111111");
        String sentimentDTOString = sentimentDTO.toString();
        return sentimentDTOString;



    }





    /**
     * 真正的登录接口：传入一个登录对象(包含用户名和密码)
     * 需要先经过filter解密，再进行认证逻辑
     * 认证逻辑：
     * 返回jwt token
     *
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/localLogin",method = RequestMethod.POST)
    public ResponseResult<Map<String, String>> localLogin(@RequestBody SysUser sysUser) throws  Exception {
        //入参校验
        if (StringUtils.isEmpty(sysUser.getUserName())||StringUtils.isEmpty(sysUser.getPassword())) {
            return new ResponseResult<>(ProcessCodeEnum.LOGIN_FAIL, null);
        }
        //用户名加密

        log.info("登录入参加密后：{}"+sysUser);
        UserDetails  userDetails = LoginUser.builder().sysUser(sysUser).build();
        if (userDetails == null) {
            return new ResponseResult<>(ProcessCodeEnum.LOGIN_FAIL, null);
        }

        //根据返回的useDetails，调用认证核心逻辑实现登录认证、密码对比
        //入参是前端的输入参数：用户名和密码
        ResponseResult<Map<String, String>> loginInfo = loginService.login(userDetails);

        log.info("登录返回结果：{}"+loginInfo);
        //返回token
        return  loginInfo;

    }

    /**
     * 登出接口
     *
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ApiOperation("退出登录")
    public ResponseResult logout(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        log.info("用户{}退出登录成功" + header);
        ResponseResult<Object> result = new ResponseResult<>(ProcessCodeEnum.SUCCESS, null);
        return result;
    }









    //$2a$10$UAmg6bBalltQ4fF6xNJYdOCfv0X5zH6xHhfwAIQhZQbS5oElRyVJi
    @RequestMapping(value = "/encode",method = RequestMethod.GET)
    public String encode(@RequestParam("psw") String psw) {

        String encode = passwordEncoder.encode(psw);
        log.info("密码加密后：{}"+encode);
        log.info("密码加密前：{}"+psw);
        return encode;
    }










}
