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
        //退出
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/test/index").permitAll();
        //配置没有权限跳转自定义页面
        http.exceptionHandling().accessDeniedPage("/unauth.html");
        http.formLogin()   //自定义登录页面
                .loginPage("/login.html")  //登录页面设置
                .loginProcessingUrl("/user/login")  //登录访问路径
                .defaultSuccessUrl("/success.html").permitAll()  //登录成功跳转路径
                .and().authorizeRequests()
                .antMatchers("/", "/user/login", "/test/hello").permitAll()  //设置那些路径可以直接访问不需要认证
                //法1、hasAnyAuthority(),设置基于拥有某一种权限才能访问第一步在配置类第二步在继承UserDetailsService类中设置
//                .antMatchers("/test/index").hasAuthority("admin")
                //法2、hasAnyAuthority(),设置基于拥有多种权限才能访问第一步在配置类第二步在继承UserDetailsService类中设置
//                .antMatchers("/test/index").hasAnyAuthority("admin,manager")
                //法3、hasRole(),底层增加了ROLE_xxxx
                .antMatchers("/test/index").hasRole("sale")
                //法4、hasAnyRole()
//                .antMatchers("/test/index").hasAnyRole("sale,consumer")
                .anyRequest().authenticated()
                .and().csrf().disable(); //关闭csrf防护
    }
}
