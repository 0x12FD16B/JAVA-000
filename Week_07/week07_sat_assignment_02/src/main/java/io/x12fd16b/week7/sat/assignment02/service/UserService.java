package io.x12fd16b.week7.sat.assignment02.service;

import io.x12fd16b.week7.sat.assignment02.dao.entity.User;

import java.util.List;

/**
 * User Service
 *
 * @author David Liu
 */
public interface UserService {

    /**
     * 获取用户列表
     *
     * @return 用户列表信息
     */
    List<User> listUsers();

    /**
     * 创建用户
     *
     * @param user 用户信息
     */
    void createUser(User user);
}
