package io.x12fd16b.week7.sat.assignment02.service;

import io.x12fd16b.week7.sat.assignment02.dao.UserDao;
import io.x12fd16b.week7.sat.assignment02.dao.entity.User;
import io.x12fd16b.week7.sat.assignment02.support.annotations.DataSourceSelector;
import io.x12fd16b.week7.sat.assignment02.support.enums.DynamicDataSourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User Service
 *
 * @author David Liu
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @DataSourceSelector(value = DynamicDataSourceEnum.SLAVE)
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    @DataSourceSelector(value = DynamicDataSourceEnum.MASTER)
    public void createUser(User user) {
        userDao.createUser(user);
    }
}
