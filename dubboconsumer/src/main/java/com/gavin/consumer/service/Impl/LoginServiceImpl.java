package com.gavin.consumer.service.Impl;

import com.alibaba.fastjson.JSON;
import com.gavin.consumer.service.LoginService;
import com.gavin.consumer.tool.JwtUtil;
import com.gavin.enums.ProcessCodeEnum;
import com.gavin.pojo.LoginUser;
import com.gavin.pojo.ResponseResult;
import com.gavin.redis.JedisTool;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * springsecutriy 单点登录认证的核心处理逻辑
 *
 */
@Service
@Log

public class LoginServiceImpl implements LoginService {

    //认证管理中心
    @Autowired
    private AuthenticationManager authenticationManager;

    //Redis缓存对象:持有该对象，可以对redis进行操作
    @Autowired
    JedisTool jedisTool;


    /**
     * 处理认证逻辑
     *
     * @param userDetails 是前端的请求入参
     * @return
     */
    @Override
    public ResponseResult<Map<String, String>> login(UserDetails userDetails) throws  Exception {
        LoginUser loginUser = (LoginUser) userDetails;
        String userName = loginUser.getSysUser().getUserName();
        String password = loginUser.getSysUser().getPassword();
        //根据认证流程=>>loginUser是前端的请求入参，需要封装成为usernamePasswordAuthenticationToken：依然是一个bean
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        //根据认证流程=>>进行认证(密码对比)的核心逻辑:调用authenticate方法进行认证


        Authentication authenticate = null;

        try {
            authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (AuthenticationException e) {
            log.info("用户登录异常:{}"+e);
            throw new RuntimeException("用户名或密码错误，请联系管理员！");
        }

        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误，请联系管理员！");
        }
        LoginUser authUser = (LoginUser) authenticate.getPrincipal();
        String userId = authUser.getSysUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        jedisTool.setKey(jwt ,JSON.toJSONString(authUser));
        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("token", jwt);

        return new ResponseResult<>(ProcessCodeEnum.SUCCESS,resultMap);

    }

    /**
     * 登出功能
     * 通过上下文SecurityContextHolder取得当前用户信息，而不需要通过传参
     * @return
     */
    @Override
    public ResponseResult logout() {

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getSysUser().getId();
        jedisTool.deleteObject(id);
        return new ResponseResult(ProcessCodeEnum.SUCCESS,"登出成功！");
    }


    public static void main(String[] args) {

        String passwoed = "123";

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String psw1 = bCryptPasswordEncoder.encode(passwoed);
        String psw2 = bCryptPasswordEncoder.encode(passwoed);
        System.out.println("psw1:"+psw1+"||"+"psw2:"+psw2);
        System.out.println(bCryptPasswordEncoder.matches(passwoed,psw2));


    }






}
