package com.atguigu.springsecurity.service;

import cn.hutool.core.util.StrUtil;
import com.atguigu.springsecurity.entity.Users;
import com.atguigu.springsecurity.mapper.UsersMapper;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: MyUserDetailsService
 * Description:
 *
 * @Author: liuyang
 * @Create: 2023/7/26 - 15:14
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {  //返回Security中的User类，注意参数
        //根据用户名查询数据库
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank("username"),"username",username);
        Users users = usersMapper.selectOne(queryWrapper);
        if (users == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        //设置基于某一种/多种权限才能访问第二步
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_sale");
        return new User(users.getUsername(), new BCryptPasswordEncoder().encode(users.getPassword()), auths);
    }
}
