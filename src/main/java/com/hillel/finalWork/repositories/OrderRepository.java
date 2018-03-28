package com.hillel.finalWork.repositories;

import com.hillel.finalWork.model.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public interface OrderRepository extends CrudRepository<Orders, Integer> {

    @Query(value = "SELECT o.created, o.status, sum(p.price) as suma From Orders As o INNER JOIN Product AS p ON o.id = p.order_id GROUP BY p.order_id ORDER BY suma DESC LIMIT 3", nativeQuery = true)
    List<Object> findTop();

    boolean existsById(int id);

    List<Orders> findAll();

    int deleteById(int id);

}
