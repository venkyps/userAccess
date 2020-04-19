package com.user.access.repo;

import com.user.access.entity.TeamHierarchy;
import com.user.access.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeamHierarchyRepository extends CrudRepository<TeamHierarchy, String> {


}
