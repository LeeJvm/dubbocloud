package com.gavin.consumer.service.Impl;

import com.gavin.consumer.mapper.SysUserDao;
import com.gavin.pojo.LoginUser;
import com.gavin.pojo.SysUser;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * springsecurity登录认证的数据源，需要实现springsecurity的UserDetailsService接口
 *
 *
 */
@Service
@Log
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    SysUserDao sysUserDao;

    /**
     * 根据springsecurity的规范提供用户信息（数据源）
     * @param username 是前端传进来的username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = sysUserDao.selectByUsername(username);

        if (sysUser == null) {
            throw new UsernameNotFoundException("用户或密码错误，请联系管理员！");
        }
        log.info("查询返回用户信息:{}"+sysUser);

        LoginUser loginUser = new LoginUser(sysUser);

        return loginUser;
    }



}
