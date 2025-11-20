package com.app.ecom.user;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    long usersId = 1L;
    private List<User> userList = new ArrayList<>();


    public List<User> fetchAllUsers(){
        return userList;
    }

    public List<User> addUser( User newUser){
        newUser.setId( usersId++ );
        userList.add(newUser);
        return userList;
    }

    public Optional<User> getUser(long id) {
        return   userList.stream().filter(
                item -> item.getId().equals(id)
        ).findFirst();
    }

    public boolean updateUser (long id , User updatedUser) {
      return  userList.stream()
              .filter(
              item -> item.getId().equals(id)
      ).findFirst()
              .map(existingUser -> {
                  existingUser.setFirstName(updatedUser.getFirstName());
                  existingUser.setLastName(updatedUser.getLastName());
                  return true;
              }).orElse(false);
    }
}
