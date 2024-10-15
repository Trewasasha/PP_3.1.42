package ru.vovk.springboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.vovk.springboot.model.User;
import ru.vovk.springboot.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "User  not found"));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewUser (@RequestBody User user) {
        userService.saveUser (user);
        return new ResponseEntity<>("User  saved successfully", HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<User> updateUser (@PathVariable Long id, @RequestBody User user) {
        userService.updateUser (id, user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser (@PathVariable Long id) {
        if (userService.getUserById(id).isPresent()) {
            userService.deleteUser (id);
            return new ResponseEntity<>("User  removed successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User  not found", HttpStatus.NOT_FOUND);
    }
}
