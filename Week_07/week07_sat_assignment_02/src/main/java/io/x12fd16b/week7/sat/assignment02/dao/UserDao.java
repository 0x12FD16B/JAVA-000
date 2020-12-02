package io.x12fd16b.week7.sat.assignment02.dao;

import io.x12fd16b.week7.sat.assignment02.dao.entity.User;

import java.util.List;

/**
 * 用户 Dao
 *
 * @author David Liu
 */
public interface UserDao {

    /**
     * 获取所有用户
     *
     * @return 用户列表
     */
    List<User> listUsers();

    /**
     * 创建用户
     *
     * @param user 用户
     */
    void createUser(User user);
}
