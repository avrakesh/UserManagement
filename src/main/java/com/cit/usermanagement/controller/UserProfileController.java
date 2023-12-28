package com.cit.usermanagement.controller;

import com.cit.usermanagement.entity.*;
import com.cit.usermanagement.exception.ApplicationException;
import com.cit.usermanagement.jwtService.JwtService;
import com.cit.usermanagement.repository.AssignedGroupDao;
import com.cit.usermanagement.repository.RoleDao;
import com.cit.usermanagement.repository.UserProfileDao;
import com.cit.usermanagement.service.UserProfileService;
import com.cit.usermanagement.dto.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * This controller has RESTful end points for fetching all the users 
 * and adding a user and authenticating the user
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class UserProfileController {
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    UserProfileDao userProfileDao;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleDao roleDao;

    @Autowired
    AssignedGroupDao assignedGroupDao;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/getAllUsers")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<UserProfile>> getAllUsers() throws ApplicationException {

        return userProfileService.getAllUsers();
    }
    @GetMapping("/getActiveUsers")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<UserProfile>> getActiveUsers() throws ApplicationException{

        return userProfileService.getActiveUsers();
    }

    @GetMapping("/getUsersByAssignedGroupId/{groupId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<UserProfile>>
    getUsersByAssignedGroupId(@PathVariable String groupId) throws ApplicationException{

        return userProfileService.getUsersByAssignedGroupId(groupId);
    }

    @GetMapping("/getUserById/{userId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<UserProfile>
    getUserById(@PathVariable Long userId) throws ApplicationException{

        return userProfileService.getUserById(userId);
    }

    @PostMapping("/addUser")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addUser(@RequestBody UserProfile userProfile) throws ApplicationException{

            return userProfileService.addUser(userProfile);
    }

    @PostMapping("/uploadProfilePicture/{userId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> uploadPersonalPhoto(@RequestParam("file") MultipartFile file, @PathVariable Long userId) throws ApplicationException{
        try {
            return userProfileService.uploadImage(userId, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApplicationException("input file is not valid",e);
        }
    }

    @DeleteMapping("/deleteProfilePicture/{userId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deletePersonalPhoto(@PathVariable Long userId) throws ApplicationException{
        return userProfileService.deleteImage(userId);
    }
    

//    @PostMapping("/loginUser")
//    @CrossOrigin(origins = "*")
//    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) throws ApplicationException{
//        return userProfileService.loginUser(loginRequest);
//    }


    @PutMapping("/modifyUser/{userId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> modifyUser(@PathVariable Long userId, @RequestBody UserProfile userProfile) throws ApplicationException{

        return userProfileService.modifyUser(userId, userProfile);
    }

/*    @PutMapping("/addUserToGroupWithRole/{userId}/{groupId}/{roleId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> adUserToGroupWithRole(@PathVariable Long userId, @PathVariable String groupId, @PathVariable String roleId) throws ApplicationException{

        return userProfileService.addUserToGroupWithRole(userId, groupId, roleId);
    }

    @PutMapping("/deleteUserFromGroup/{userId}/{groupId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteUserFromGroup(@PathVariable Long userId, @PathVariable String groupId) throws ApplicationException{

        return userProfileService.deleteUserFromGroupWithRole(userId, groupId);
    }*/

    @DeleteMapping("/deleteUser/{userId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteUser( @PathVariable Long userId) throws ApplicationException{

        return userProfileService.deleteUser(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest loginRequest) {
        Optional<com.cit.usermanagement.entity.UserProfile> userProfile = userProfileDao.findByUsername(loginRequest.getUsername());
        if(userProfile.isPresent()){
            if (passwordEncoder.matches(loginRequest.getPassword(), userProfile.get().getPassword())){

               LoginResponse loginResponse = new LoginResponse();
               loginResponse.setToken(jwtService.generateToken(loginRequest.getUsername()));
               loginResponse.setAssignedToId(String.valueOf(userProfile.get().getUserId()));
               loginResponse.setGroupRoles(userProfile.get().getGroupRoles());

               Set<String> roleIds = new HashSet<>();
               if (userProfile.get().getGroupRoles() != null) {
                    for (GroupRole groupRole : userProfile.get().getGroupRoles()) {
                        roleIds.add(groupRole.getRoleId());
                    }
                }

               List<Role> roles = new ArrayList<>();
               for(String roleId: roleIds){
                   Optional<Role> optionalRole = roleDao.findById(roleId);
                   Role role = optionalRole.get();
                   roles.add(role);
               }

               loginResponse.setRoles(roles);

                List<Group> groups = new ArrayList<>();


                if (userProfile.get().getGroupRoles() != null) {

                    for (GroupRole groupRole : userProfile.get().getGroupRoles()) {
                        if (groupRole.getAssignedGroupId() != null) {
                            Group group = new Group();
                            group.setGroupId(groupRole.getAssignedGroupId());
                            Optional<AssignedGroups> assignedGroup = assignedGroupDao.findByGroupId(groupRole.getAssignedGroupId());
                            group.setGroupName(assignedGroup.get().getGroupName());
                            groups.add(group);
                        }
                    }
                }
                loginResponse.setGroups(groups);


               return new ResponseEntity<>(loginResponse, HttpStatus.OK);

            } else {
                throw new UsernameNotFoundException("Wrong Credentials!");
            }
        }else{
            throw  new UsernameNotFoundException("Wrong Credentials!");
        }

    }


}