package com.hillel.finalWork;

import com.hillel.finalWork.model.Orders;
import com.hillel.finalWork.model.Status;
import com.hillel.finalWork.service.OrderService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:config/applicationContext.xml"})
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOrders {

    @Autowired
    private OrderService orderService;

    private static Orders orders;

    @BeforeClass
    public static void preCondition(){
        orders = new Orders();
        orders.setStatus(Status.ABOLITION);
        orders.setCreated(Date.valueOf(LocalDate.now()));
    }

    @Test
    public void t1CreateOrder() {
        Orders newOrder = orderService.createOrder(orders);
        orders.setId(newOrder.getId());
        Assert.assertEquals(orders, newOrder);
    }
    @Test
    public void t2UpdateOrder() {
        orders.setStatus(Status.DONE);
        Assert.assertTrue(orderService.update(orders));
    }

    @Test
    public void t3FindById() {
        Assert.assertEquals(orders.getId(), orderService.findOne(orders.getId()).getId());
    }

    @Test
    public void t4IsExistOrder() {
        Assert.assertTrue(orderService.exists(orders.getId()));
    }

    @Test
    public void t5DeleteOrder() {
        Assert.assertNotEquals(0, orderService.delete(orders));
    }

    @Test
    public void t6FindAllOrders() {
        List<Orders> all = orderService.findAll();
        orders.setStatus(Status.NEW);
        orders.setId(0);
        orderService.createOrder(orders);
        Assert.assertEquals(all.size() + 1, orderService.findAll().size());
        orderService.delete(orders);
    }

    @AfterClass
    public static void postCondition(){
        orders = null;
    }
}
