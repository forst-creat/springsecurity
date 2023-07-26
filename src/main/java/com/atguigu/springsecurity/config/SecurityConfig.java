package com.atguigu.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * ClassName: SecurityConfig
 * Description:
 *
 * @Author: liuyang
 * @Create: 2023/7/26 - 4:23
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {   //配置类形式，继承WebSecurityConfigurerAdapter，重写configure()

    @Autowired
    private PasswordEncoder passwordEncoder; //取出密码前需要对密码进行加密，创建密码解析器

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String password = passwordEncoder.encode("123"); //对密码进行加密
        auth.inMemoryAuthentication().withUser("lucy").password(password).roles("admin");
    }

    @Bean
    public PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }
}
