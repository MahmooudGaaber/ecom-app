package com.app.ecom.dto;

import com.app.ecom.enums.UserRole;
import lombok.Data;

@Data
public class UserResponses {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role;
    private AddressDto address;
}
