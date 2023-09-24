package com.gavin.consumer.framework.filter;

import com.alibaba.fastjson.JSON;
import com.gavin.consumer.tool.JwtUtil;
import com.gavin.pojo.LoginUser;
import com.gavin.redis.JedisTool;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 所有需要认证请求的过滤器：
 * 获取请求头的token，然后通过redis获取用户对象，然后封装Authentication对象存入SecurityContextHolder
 * 如果获取不到则登录过期
 *
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    JedisTool jedisTool;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");

        if (!StringUtils.hasText(token)) {
            //放行？？？？？？？？
            filterChain.doFilter(request, response);
            return;
        }

        String userid = null;
        try {
            Claims claims = JwtUtil.praseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            log.error("非法登录token:{}"+token);
            throw new RuntimeException("非法登录token");
        }

        String userDetail = jedisTool.getKey(userid);
        LoginUser loginUser = (LoginUser) JSON.parse(userDetail);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录，请重新登录");
        }

        UsernamePasswordAuthenticationToken authenticationToken  = new UsernamePasswordAuthenticationToken(loginUser, null, null);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);

    }

}
