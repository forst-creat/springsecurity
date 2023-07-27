package com.atguigu.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Users
 * Description:
 *
 * @Author: liuyang
 * @Create: 2023/7/26 - 17:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private Integer id;
    private String username;
    private String password;
}
