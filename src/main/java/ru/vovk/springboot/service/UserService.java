package ru.vovk.springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.vovk.springboot.model.Role;
import ru.vovk.springboot.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUsername(String username);

    User saveUser(User user);

    User updateUser(Long id, String username, String email, String password, Set<Role> roleNames);

    void deleteUser(Long id);

}
