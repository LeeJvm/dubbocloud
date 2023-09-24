package com.gavin.consumer.service;

import com.gavin.pojo.LoginUser;
import com.gavin.pojo.ResponseResult;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

/**
 * Created by 17428 on 2023/9/3.
 */
public interface LoginService {

    /**
     * 处理认证逻辑
     *
     * @param loginUser
     * @return
     */
     ResponseResult<Map<String, String>> login(UserDetails  userDetails) throws Exception;

    /**
     * 登出
     * @return
     */
    ResponseResult logout();

}
