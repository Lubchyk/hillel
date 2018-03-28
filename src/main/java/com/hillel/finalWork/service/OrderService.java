package com.hillel.finalWork.service;

import com.hillel.finalWork.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hillel.finalWork.model.Orders;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Orders> findAll(){
      return orderRepository.findAll();
    }

    public Orders findOne(int id) {
        return orderRepository.findOne(id);
    }

    public Orders createOrder(Orders order) {
        return orderRepository.save(order);
    }

    public boolean update(Orders order) {
        return orderRepository.save(order) != null ? true : false;
    }

    public int delete(Orders order) {
        return orderRepository.deleteById(order.getId());
    }

    public boolean exists (int id) {
        return orderRepository.existsById(id);
    }

    public List<Object> findTop() {
        return orderRepository.findTop();
    }

}
