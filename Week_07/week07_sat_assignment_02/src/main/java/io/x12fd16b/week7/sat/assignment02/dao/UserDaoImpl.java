package io.x12fd16b.week7.sat.assignment02.dao;

import io.x12fd16b.week7.sat.assignment02.dao.entity.User;
import io.x12fd16b.week7.sat.assignment02.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User Dao
 *
 * @author David Liu
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public List<User> listUsers() {
        return userMapper.listAll();
    }

    @Override
    public void createUser(User user) {
        userMapper.createUser(user);
    }
}
