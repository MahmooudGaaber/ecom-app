package com.app.ecom.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService ;

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody User newUser){
       userService.addUser(newUser);
       return ResponseEntity.ok("User Has Created Successfully");
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(
                        ()-> ResponseEntity.notFound().build()
                );
    }

}
