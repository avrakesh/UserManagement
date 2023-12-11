package com.cit.usermanagement.repository;


import com.cit.usermanagement.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserProfileDao extends MongoRepository<UserProfile,Long> {
    @Query(value = "{}", fields = "{ 'username' : 1, 'password' : 1}")
    List<UserProfile> findAllWithSelectedColumns();

    Optional<UserProfile> findByUsername(String username);

    List<UserProfile> findByIsActive(boolean value);

    List<UserProfile> findByGroupRolesAssignedGroupId(String groupId);
	
	UserProfile findByToken(String token);

}
