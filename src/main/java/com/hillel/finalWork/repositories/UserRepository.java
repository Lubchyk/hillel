package com.hillel.finalWork.repositories;

import com.hillel.finalWork.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByName(String name);

    int deleteById(int id);

    List<User> findAll();

}
