package com.cit.usermanagement.repository;

import com.cit.usermanagement.entity.AssignedGroups;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AssignedGroupDao extends MongoRepository<AssignedGroups, String> {

    AssignedGroups save(AssignedGroups group);
    List<AssignedGroups> findAll();
    Optional<AssignedGroups> findByGroupId(String id);

    List<AssignedGroups> findByIsActive(boolean value);

    Optional<AssignedGroups> findByGroupName(String groupName);
}
