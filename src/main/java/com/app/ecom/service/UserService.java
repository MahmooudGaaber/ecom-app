package com.app.ecom.service;

import com.app.ecom.dto.AddressDto;
import com.app.ecom.dto.UserRequests;
import com.app.ecom.dto.UserResponses;
import com.app.ecom.entity.Address;
import com.app.ecom.repository.UserRepository;
import com.app.ecom.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserResponses> fetchAllUsers(){

        // ? This Line Return all Users Find
        return userRepository.findAll()
                // ? Stream Used To iterator on all Items
                .stream()
                // ? Map To Pass Every Item to Convert User To DTO Response
                .map(this::mapToUserResponses)
                // ? It User To Collect All Data Again Into List To Fit The Response Type
                .collect(Collectors.toList());
    }

    public void addUser( UserRequests newUser){
        User user = new User();
        updateUserFromRequest(user , newUser);
        userRepository.save(user);
    }



    public Optional<UserResponses> getUser(long id) {
        return userRepository.findById(id)
                .map(this::mapToUserResponses);
    }

    public boolean updateUser (long id , UserRequests updatedUser) {
      return  userRepository.findById(id)
              .map(existingUser -> {
                  updateUserFromRequest(existingUser,updatedUser);
                  userRepository.save(existingUser);
                  return true;
              }).orElse(false);
    }


    // ? Use Of DTO to control the data that send and receive from client side
    // * User Responses is a class that hold the data can transfer it to client
    // * we shift data one by one from the main class to DTO Classes
    private UserResponses mapToUserResponses (User user)
    {
        UserResponses responses = new UserResponses();
        responses.setFirstName(user.getFirstName());
        responses.setLastName(user.getLastName());
        responses.setEmail(user.getEmail());
        responses.setId( String.valueOf(user.getId()));
        responses.setPhone(user.getPhone());
        responses.setRole(user.getRole());

        if (user.getAddress() != null ){
            AddressDto addressDto = new AddressDto();
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setCountry(user.getAddress().getCountry());
            addressDto.setState(user.getAddress().getState());
            addressDto.setStreet(user.getAddress().getStreet());
            addressDto.setZipcode(user.getAddress().getZipcode());
            addressDto.setId(String.valueOf(user.getAddress().getId()));
            responses.setAddress(addressDto);
        }
        return responses ;
    }

    // ? Use Of DTO to control the data that send and receive from client side
    // * User Requests is a class that hold the data get from client
    // * we shift data one by one from the main class to DTO Classes
    private void updateUserFromRequest(User user, UserRequests newUser) {
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPhone(newUser.getPhone());

        if (newUser.getAddress() != null){
            Address address = new Address();
            address.setCity(newUser.getAddress().getCity());
            address.setCountry(newUser.getAddress().getCountry());
            address.setStreet(newUser.getAddress().getStreet());
            address.setState(newUser.getAddress().getState());
            address.setZipcode(newUser.getAddress().getZipcode());
            user.setAddress(address);

        }
    }
}
