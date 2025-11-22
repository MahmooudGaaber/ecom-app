package com.app.ecom.service;

import com.app.ecom.repository.UserRepository;
import com.app.ecom.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;




    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

    public void addUser( User newUser){
        userRepository.save(newUser);
    }

    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    public boolean updateUser (long id , User updatedUser) {
      return  getUser(id)
              .map(existingUser -> {
                  existingUser.setFirstName(updatedUser.getFirstName());
                  existingUser.setLastName(updatedUser.getLastName());
                  existingUser.setEmail(updatedUser.getEmail());
                  existingUser.setPhone(updatedUser.getPhone());
                  existingUser.setRole(updatedUser.getRole());
                  userRepository.save(existingUser);
                  return true;
              }).orElse(false);
    }
}
