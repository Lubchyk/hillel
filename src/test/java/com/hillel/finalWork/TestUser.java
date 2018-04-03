package com.hillel.finalWork;

import com.hillel.finalWork.model.Role;
import com.hillel.finalWork.model.User;
import com.hillel.finalWork.service.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:config/applicationContext.xml"})
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUser {

    @Autowired
    @Qualifier("userService")
    UserService userService;

    private static User user;

    @BeforeClass
    public static void preCondition(){
        user = new User();
        user.setName("Test");
        user.setPassword("0000");
        user.setRole(Role.ROLE_CUSTOMER);
    }

    @Test
    public void t1Create() {
        User newUser = userService.createUser(user);
        user.setId(newUser.getId());
        Assert.assertEquals(user, newUser);
    }

    @Test
    public void t2Update() {
        user.setRole(Role.ROLE_MANAGER);
        user.setName("Test555");
        //change to assertTrue
        Assert.assertFalse(userService.updateUser(user));
    }

    @Test
    public void t3FindByName() {
        User newUser = userService.findByName("Test555");
        user.setId(newUser.getId());
        Assert.assertEquals(user.getName(), newUser.getName());
    }

    @Test
    public void t4FindByID() {
        Assert.assertEquals(user.getId(), userService.findByID(user.getId()).getId());
    }

    @Test
    public void t5isExist() {
        Assert.assertTrue(userService.isExists(user.getId()));
    }

    @Test
    public void t6DeleteUser(){
        Assert.assertNotEquals(0, userService.deleteUserById(user.getId()));
    }
    @Test
    public void t7FindAllUser() {
        List<User> allUser = userService.getAllUser();
        user.setId(0);
        user.setName("Test999");
        user.setRole(Role.ROLE_ADMIN);
        user = userService.createUser(TestUser.user);
        Assert.assertEquals(allUser.size() + 1, userService.getAllUser().size());
        userService.deleteUserById(TestUser.user.getId());
    }

    @AfterClass
    public static void postCondition(){
        user = null;
    }

}
