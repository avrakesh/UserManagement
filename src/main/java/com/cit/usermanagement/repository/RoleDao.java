package com.cit.usermanagement.repository;

import com.cit.usermanagement.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface RoleDao extends MongoRepository<Role, String> {
    Optional<Role> findByRoleName(String roleName);

    List<Role> findByIsActive(boolean value);
}
