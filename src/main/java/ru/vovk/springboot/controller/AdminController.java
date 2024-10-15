package ru.vovk.springboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vovk.springboot.model.Role;
import ru.vovk.springboot.model.User;
import ru.vovk.springboot.service.RoleService;
import ru.vovk.springboot.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List <User>user=userService.getAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity <HttpStatus> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("/users")
    public String editUser(@RequestParam("id") Long id,
                           @RequestParam("username") String username,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("roles") Set<Role> roleNames) {
        userService.updateUser(id, username, email, password, roleNames);
        return String.valueOf(new ResponseEntity<>( HttpStatus.OK));
    }


    @DeleteMapping("/users/{id}")

    public ResponseEntity<HttpStatus> deleteUser (@PathVariable("id") long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}