package io.x12fd16b.week7.sat.assignment03.dao.mapper;

import io.x12fd16b.week7.sat.assignment03.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户 Mapper
 *
 * @author David Liu
 */
@Mapper
public interface UserMapper {

    /**
     * 列出所有 user
     *
     * @return 获取所有用户
     */
    List<User> listAll();

    /**
     * 创建用户
     *
     * @param user 用户
     * @return 插入记录行数
     */
    int createUser(@Param("user") User user);

}
