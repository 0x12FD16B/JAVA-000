package io.x12fd16b.week7.sat.assignment03.dao;

import io.x12fd16b.week7.sat.assignment03.Application;
import io.x12fd16b.week7.sat.assignment03.dao.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void listUsers() {
        for (int i = 0; i < 4; i++) {
            List<User> users = userDao.listUsers();
            log.info("用户查询结果: {}", users);
        }
    }

    @Test
    public void createUser() {
        User user = new User();
        user.setId(1L);
        user.setAccount("hhhhh");
        userDao.createUser(user);
    }
}