package com.hillel.finalWork.service;

import com.hillel.finalWork.domain.UserData;
import com.hillel.finalWork.model.User;
import com.hillel.finalWork.repositories.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Component
@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Getter
    private UserData userData;


    public User findByID(int id) {
        return userRepository.findOne(id);
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        String encodePassWord = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodePassWord);
        return userRepository.save(user);
    }

    public boolean updateUser(User user) {
        return userRepository.save(user) != null ? true : false;
    }

    public boolean isExists(int id) {
        return userRepository.exists(id);

    }

    public int deleteUserById(int id) {
        return userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(@NotNull String name) throws UsernameNotFoundException {
        return createUserData(name);
    }

    private UserData createUserData(String name) {
        User user = findByName(name);
        try {
            userData = UserData.builder().username(user.getName())
                .password(user.getPassword())
                .authorities(Arrays.asList(user.getRole()))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
            return userData;
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

}
