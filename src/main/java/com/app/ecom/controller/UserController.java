package com.app.ecom.controller;

import com.app.ecom.dto.UserRequests;
import com.app.ecom.dto.UserResponses;
import com.app.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService ;

    @GetMapping()
    public ResponseEntity<List<UserResponses>> getAllUsers(){
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody UserRequests newUser){
       userService.addUser(newUser);
       return ResponseEntity.ok("User Has Created Successfully");
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponses> getUser(@PathVariable long id){
        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(
                        ()-> ResponseEntity.notFound().build()
                );
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUser (@PathVariable long id ,@RequestBody UserRequests updatedUser) {
        boolean isUpdated = userService.updateUser(id, updatedUser);

        if(isUpdated)
            return ResponseEntity.ok("Updated Successfully");

        return ResponseEntity.notFound().build();
    }

}
