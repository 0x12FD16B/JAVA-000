package io.x12fd16b.week09.thurs.assignment03.rpcfx.server.service;

import io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.api.UserService;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.model.User;

/**
 * UserService
 *
 * @author David Liu
 */
public class UserServiceImpl implements UserService {
    @Override
    public User findById(Integer id) {
        return new User(1, "user");
    }
}
