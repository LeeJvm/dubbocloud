package com.gavin.consumer.framework.handler;

import com.gavin.consumer.tool.JwtUtil;
import com.gavin.redis.JedisTool;
import io.jsonwebtoken.Claims;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 17428 on 2023/9/17.
 */

@Component
public class LogoutHandler implements LogoutSuccessHandler {


    @Autowired
    JedisTool jedisTool;


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {

        String token = request.getHeader("token");

        //清理用户缓存
        Claims claims = JwtUtil.praseJWT(token);
        String userid = claims.getSubject();

        if (!StringUtils.isEmpty(userid)){
            jedisTool.deleteObject(userid);

            //记录登出日志

            //更新用户状态：在线用户-->离线用户

        }

        //重定向到登录页的url




    }


}
