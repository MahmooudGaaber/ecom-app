package com.app.ecom.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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
                  userRepository.save(existingUser);
                  return true;
              }).orElse(false);
    }
}
