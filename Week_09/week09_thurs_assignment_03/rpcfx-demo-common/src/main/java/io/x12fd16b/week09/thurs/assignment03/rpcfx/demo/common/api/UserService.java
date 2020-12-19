package io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.api;

import io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.model.User;

/**
 * UserService
 *
 * @author David Liu
 */
public interface UserService {

    User findById(Integer id);
}
