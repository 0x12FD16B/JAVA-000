package io.x12fd16b.week7.sat.assignment02.service;

import io.x12fd16b.week7.sat.assignment02.Application;
import io.x12fd16b.week7.sat.assignment02.dao.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
//@Transactional
//@Rollback
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testListUsers() {
        for (int i = 0; i < 4; i++) {
            userService.listUsers();
        }
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(1L);
        user.setAccount("qwer");
        userService.createUser(user);
    }
}