package com.app.ecom.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService ;

    @GetMapping("/api/users")
    public List<User> getAllUsers(){
        return userService.fetchAllUsers();
    }

    @PostMapping("/api/users")
    public String createUser(@RequestBody User newUser){
       userService.addUser(newUser);
       return "User Has Created Successfully";
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable long id){
        return userService.getUser(id);
    }

}
