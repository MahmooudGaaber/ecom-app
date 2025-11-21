package com.app.ecom.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// ? Repository Usage
// * it's an easy way to setup all queries that i will write
// * it's only take 2 parameters
// * ( User ) it's the data class that i will execute queries on
// * ( Long ) the data type of the Primary Key
@Repository
public interface UserRepository extends JpaRepository<User,Long> {}
