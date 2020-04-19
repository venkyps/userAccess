package com.user.access.repo;

import com.user.access.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    @Query("select user from User user where user.empID in (?1,?2)")
    List<User> getUserList(String empID, String managerID) throws  Exception;
}
