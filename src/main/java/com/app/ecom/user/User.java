package com.app.ecom.user;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "user_table")
// * Replace of No Args Constructor Provided From LOMBOK
@NoArgsConstructor
// * Replace of All Args Constructor Provided From LOMBOK
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role = UserRole.CUSTOMER;

    // ? Mandatory Empty Constructor to active Jpa
    // * uses reflection to create entity objects
    // * so i need to add no Arg Constructor or Create one Manually
}
