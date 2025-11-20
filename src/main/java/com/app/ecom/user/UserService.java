package com.app.ecom.user;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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

    public User getUser(long id) {
        for (User user : userList){
            if(user.getId().equals(id))
               return  user;
        }
        return null;
    }
}
