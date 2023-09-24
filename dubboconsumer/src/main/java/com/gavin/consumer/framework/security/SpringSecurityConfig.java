 package com.gavin.consumer.framework.security;

 import com.gavin.consumer.framework.filter.CorsFilter;
 import com.gavin.consumer.framework.filter.JwtAuthenticationTokenFilter;
 import com.gavin.consumer.framework.handler.LogoutHandler;
 import com.gavin.consumer.service.Impl.UserDetailsServiceImpl;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.security.authentication.AuthenticationManager;
 import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
 import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.builders.WebSecurity;
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 import org.springframework.security.config.http.SessionCreationPolicy;
 import org.springframework.security.core.userdetails.UserDetailsService;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 import org.springframework.security.web.authentication.logout.LogoutFilter;


 /**
  * Created by 17428 on 2023/7/12.
  */
 @Configuration
 //开启判断用户对某个控制层的方法是否具有访问权限的功能--->开启接口注解权限控制
 //@EnableGlobalMethodSecurity(prePostEnabled = true)
 //@EnableWebSecurity  //Spring Security用于启用Web安全的注解

 public class  SpringSecurityConfig extends WebSecurityConfigurerAdapter {

     @Autowired
     JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

     @Autowired
     UserDetailsService userDetailsService;

     @Autowired
     UserDetailsServiceImpl userDetailsServiceImpl;


     @Autowired
     LogoutHandler logoutHandler;

     @Autowired
     CorsFilter corsFilter;



     /**
      * 定义springsecurity加密方案
      * 只需要使用把BCryptPasswordEncoder对象注入Spring容器中，SpringSecurity就会使用该PasswordEncoder来进行密码校验
      *
      * @return
      */
     @Bean
     public PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();

     }



     /**
      * 三大核心类之一：管理中心
      * ProviderManager实现了AuthenticationManager接口
      *
      * @return
      * @throws Exception
      */
     @Bean(name = "authenticationManager")
     @Override
     public AuthenticationManager authenticationManagerBean() throws Exception {
          return super.authenticationManagerBean();

       /*  ProviderManager providerManager = new ProviderManager(new DaoAuthenticationProvider());
         return providerManager;*/
     }



     /**  装配：处理认证逻辑对象AuthenticationProvider（密码对比）
      *
      * @param  auth
      * @throws Exception
      *
      */
     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         //auth.userDetailsService(userDetailsServiceImpl);
         DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
         authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
         authenticationProvider.setPasswordEncoder(passwordEncoder());
         auth.authenticationProvider(authenticationProvider);
         //super.configure(auth);
     }




     /**
      * 设置前端的免权限认证的资源等： js，css， images等
      * 如果是前后端分离的话，就不需要特别设置
      * @param web
      * @throws Exception
      */
     @Override
     public void configure(WebSecurity web) throws Exception {
         super.configure(web);
     }


     /** 装配拦截器和过滤器
      *
      * 1，设置成功登录后跳转界面，登录失败后返回界面等，注销成功后跳转界面
      * 2，设置curl等安全策略
      * 3，设置接口url权限：赋权
      *
      * @param http
      * @throws Exception
      */
     @Override
     protected void configure(HttpSecurity http) throws Exception {
         //关闭csrf
         http.csrf().disable()
                 //不通过session获取securitycontext
                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 .and()
                 .authorizeRequests()
                 //允许登录接口访问
                 .antMatchers("/api/*").anonymous()
                 //其余请求都需要认证
                 .anyRequest().authenticated();
         //定义退出逻辑：在调用/logout接口时候，会执行此handler
         //http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutHandler);



         //添加过滤链 JWT filter
         http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
         //添加cors filter
         //http.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
         //http.addFilterBefore(corsFilter, LogoutFilter.class);



     }


 }
