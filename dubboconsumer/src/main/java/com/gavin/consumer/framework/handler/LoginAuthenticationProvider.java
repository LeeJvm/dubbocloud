package com.gavin.consumer.framework.handler;

import com.gavin.pojo.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


/**
 * Created by 17428 on 2023/9/17.
 */
@Component
@Slf4j
public class LoginAuthenticationProvider  implements AuthenticationProvider {

    @Autowired
    UserDetailsService userDetailsService;



    /**
     * 密码对比逻辑
     * 可以添加ip白名单的功能
     * 用户重复登录限制
     * @param authentication 页面传进来的用户名和密码
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LoginUser user = (LoginUser) authentication.getPrincipal();
        String username = user.getUsername();
        String password = user.getPassword();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        boolean match = this.match(password, userDetails.getPassword());
        if (match) {
            return ((Authentication) userDetails);
        }
        return  null;
    }


    private boolean match(String args1, String args2) {
        return args1.equals(args2);
    }




    @Override
    public boolean supports(Class<?> aClass) {

        return false;
    }
}
