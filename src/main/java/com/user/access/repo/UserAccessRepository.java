package com.user.access.repo;

import com.user.access.entity.User;
import com.user.access.entity.UserAccess;
import org.springframework.data.repository.CrudRepository;

public interface UserAccessRepository extends CrudRepository<UserAccess, String> {
}
