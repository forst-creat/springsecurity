package com.atguigu.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * ClassName: SecurityCustomerConfig
 * Description:自定义实现类设置
 * 1、创建配置类，设置使用哪个userDetailsService实现类
 * 2、编写实现类，返回User对象，User对象有用户名密码及操作权限
 *
 * @Author: liuyang
 * @Create: 2023/7/26 - 15:06
 */
@Configuration
public class SecurityCustomizeConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()   //自定义登录页面
                .loginPage("/login.html")  //登录页面设置
                .loginProcessingUrl("/user/login")  //登录访问路径
                .defaultSuccessUrl("/test/index").permitAll()  //登录成功跳转路径
                .and().authorizeRequests()
                .antMatchers("/","/user/login","/test/hello").permitAll()  //设置那些路径可以直接访问不需要认证
                .anyRequest().authenticated()
                .and().csrf().disable(); //关闭csrf防护
    }
}
