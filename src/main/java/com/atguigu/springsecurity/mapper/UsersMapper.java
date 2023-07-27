package com.atguigu.springsecurity.mapper;
import com.atguigu.springsecurity.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UsersMapper
 * Description:
 *
 * @Author: liuyang
 * @Create: 2023/7/26 - 17:12
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {
}
