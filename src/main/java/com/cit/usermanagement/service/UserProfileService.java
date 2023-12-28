package com.cit.usermanagement.service;

import com.cit.usermanagement.config.MailConfig;
import com.cit.usermanagement.entity.GroupRole;
import com.cit.usermanagement.exception.ApplicationException;
import com.cit.usermanagement.repository.UserProfileDao;
import com.cit.usermanagement.utils.SequenceGeneratorService;
import com.cit.usermanagement.utils.TokenGenerator;
import com.cit.usermanagement.dto.UserProfile;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.BsonTimestamp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {
    static Logger log = LogManager.getLogger(UserProfileService.class);
    @Autowired
    UserProfileDao userProfileDao;
    AssignedUserGroupService assignedUserGroupService;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    MailConfig emailService;
    @Autowired
    private PasswordEncoder encoder;


    public ResponseEntity<List<UserProfile>> getAllUsers() throws ApplicationException {
        log.info("Entered the getAllUsers method.");
        try {
            List<com.cit.usermanagement.entity.UserProfile> userProfiles = userProfileDao.findAll();

            List<UserProfile> profileResults = new ArrayList<>();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            for (com.cit.usermanagement.entity.UserProfile profile : userProfiles) {
                ZonedDateTime c = Instant.ofEpochMilli(profile.getCreatedOn().asTimestamp().getValue()).atZone(ZoneOffset.UTC);
                String formattedCreatedDate = df.format(c);
                ZonedDateTime u = Instant.ofEpochMilli(profile.getCreatedOn().asTimestamp().getValue()).atZone(ZoneOffset.UTC);
                String formattedUpdatedDate = df.format(u);
                UserProfile viewProfile = new UserProfile();
                BeanUtils.copyProperties(profile, viewProfile);
                viewProfile.setCreatedOn(formattedCreatedDate);
                viewProfile.setUpdatedOn(formattedUpdatedDate);
                profileResults.add(viewProfile);
            }
            log.info("Closing the getAllUsers method successfully.");
            return new ResponseEntity<>(profileResults, HttpStatus.OK);

        } catch (DateTimeParseException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            throw new ApplicationException("Date Format Parser Exception", e);
        } catch (NullPointerException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            throw new ApplicationException("Null Pointer Exception", e);
        } catch (Exception e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            throw new ApplicationException(ExceptionUtils.getStackTrace(e));
        }
    }

    public ResponseEntity<List<UserProfile>> getActiveUsers() {
        log.info("Entered the getActiveUsers method.");
        try {
            List<com.cit.usermanagement.entity.UserProfile> profiles = userProfileDao.findByIsActive(true);
            List<UserProfile> profileResults = new ArrayList<>();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            for (com.cit.usermanagement.entity.UserProfile profile : profiles) {
                ZonedDateTime c = Instant.ofEpochMilli(profile.getCreatedOn().asTimestamp().getValue()).atZone(ZoneOffset.UTC);
                String formattedCreatedDate = df.format(c);
                ZonedDateTime u = Instant.ofEpochMilli(profile.getCreatedOn().asTimestamp().getValue()).atZone(ZoneOffset.UTC);
                String formattedUpdatedDate = df.format(u);
                UserProfile viewProfile = new UserProfile();
                BeanUtils.copyProperties(profile, viewProfile);
                viewProfile.setCreatedOn(formattedCreatedDate);
                viewProfile.setUpdatedOn(formattedUpdatedDate);
                profileResults.add(viewProfile);
            }
            log.info("Closing the getAllUsers method successfully.");
            return new ResponseEntity<>(profileResults, HttpStatus.OK);

        } catch (DateTimeParseException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            throw new ApplicationException("Date Format Parser Exception", e);
        } catch (NullPointerException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            throw new ApplicationException("Null Pointer Exception", e);
        } catch (Exception e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            throw new ApplicationException(ExceptionUtils.getStackTrace(e));
        }
    }
    
    public ResponseEntity<String> uploadImage(Long userId, byte[] image) {
    	Optional<com.cit.usermanagement.entity.UserProfile> user = userProfileDao.findById(userId);
    	com.cit.usermanagement.entity.UserProfile userProf = user.get();
    	userProf.setImage(image);
    	userProfileDao.save(userProf);
		return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<String> deleteImage(Long userId) {
        Optional<com.cit.usermanagement.entity.UserProfile> user = userProfileDao.findById(userId);
        com.cit.usermanagement.entity.UserProfile userProf = user.get();
        userProf.setImage(new byte[0]);
        userProfileDao.save(userProf);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    public ResponseEntity<UserProfile> getUserById(Long userId) {
        log.info("Entered the getUserById method.");
        try {
            Optional<com.cit.usermanagement.entity.UserProfile> optionalUserProfileFromDb = userProfileDao.findById(userId);
            com.cit.usermanagement.entity.UserProfile userProfileFromDb =optionalUserProfileFromDb.get();

            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                ZonedDateTime c = Instant.ofEpochMilli(userProfileFromDb.getCreatedOn().asTimestamp().getValue()).atZone(ZoneOffset.UTC);
                String formattedCreatedDate = df.format(c);
                ZonedDateTime u = Instant.ofEpochMilli(userProfileFromDb.getCreatedOn().asTimestamp().getValue()).atZone(ZoneOffset.UTC);
                String formattedUpdatedDate = df.format(u);
            UserProfile viewUserProfile = new UserProfile();
                BeanUtils.copyProperties(userProfileFromDb, viewUserProfile);
                viewUserProfile.setCreatedOn(formattedCreatedDate);
                viewUserProfile.setUpdatedOn(formattedUpdatedDate);

            log.info("Closing the getAllUsers method successfully.");
            return new ResponseEntity<>(viewUserProfile, HttpStatus.OK);

        } catch (DateTimeParseException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            throw new ApplicationException("Date Format Parser Exception", e);
        } catch (NullPointerException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            throw new ApplicationException("Null Pointer Exception", e);
        } catch (Exception e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            throw new ApplicationException(ExceptionUtils.getStackTrace(e));
        }
    }
    public ResponseEntity<String> addUser(UserProfile userProfile) throws ApplicationException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        log.info("Entered the addUser method");

        userProfile.setUserId(sequenceGeneratorService.generateSequence((com.cit.usermanagement.entity.UserProfile.SEQUENCE_NAME)));
        com.cit.usermanagement.entity.UserProfile modelUserProfile = new com.cit.usermanagement.entity.UserProfile();
        try {

            //creating a new record in userProfile table
            LocalDateTime createdDate = LocalDateTime.parse(userProfile.getCreatedOn(), df);
           // LocalDateTime updatedDate = LocalDateTime.parse(userProfile.getUpdatedOn(), df);
            BsonTimestamp dtimes = new BsonTimestamp(createdDate.toInstant(ZoneOffset.UTC).toEpochMilli());
            //BsonTimestamp rtimes = new BsonTimestamp(updatedDate.toInstant(ZoneOffset.UTC).toEpochMilli());
            BeanUtils.copyProperties(userProfile, modelUserProfile);
            modelUserProfile.setCreatedOn(dtimes);
            //modelUserProfile.setUpdatedOn(rtimes);
            modelUserProfile.setPassword(encoder.encode(userProfile.getPassword()));
            modelUserProfile.setToken(TokenGenerator.generateToken());
            userProfileDao.save(modelUserProfile);
            emailService.sendVerificationEmail(modelUserProfile.getEmailAddress(), modelUserProfile.getToken());
            log.info("Closing the addUser method successfully.");
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DateTimeParseException e) {
            sequenceGeneratorService.decrementOperation((com.cit.usermanagement.entity.UserProfile.SEQUENCE_NAME));
            log.debug(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            throw new ApplicationException("Date Format Parser Exception", e);
        } catch (NullPointerException e) {
            sequenceGeneratorService.decrementOperation((com.cit.usermanagement.entity.UserProfile.SEQUENCE_NAME));
            log.debug(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            throw new ApplicationException("Null Pointer Exception", e);
        } catch (Exception e) {
            sequenceGeneratorService.decrementOperation((com.cit.usermanagement.entity.UserProfile.SEQUENCE_NAME));
            log.debug(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            throw new ApplicationException(ExceptionUtils.getStackTrace(e));
        }
    }

//    public ResponseEntity<String> loginUser(LoginRequest loginRequest) throws ApplicationException {
//        log.info("Entered the loginUser method");
//        List<UserProfile> userProfiles = userProfileDao.findAllWithSelectedColumns();
//        for (UserProfile user : userProfiles) {
//            if (user.getUsername().equals(loginRequest.getUsername())
//                    && user.getPassword().equals(loginRequest.getPassword())) {
//                return new ResponseEntity<String>("Logged in successfully.", HttpStatus.OK);
//            }
//        }
//        log.info("Closing the loginUser method");
//        return new ResponseEntity<String>("invalid username or password.", HttpStatus.UNAUTHORIZED);
//    }

    public ResponseEntity<String> deleteUser(Long userId) {

        try {
            Optional<com.cit.usermanagement.entity.UserProfile> optionalUserProfileFromDb = userProfileDao.findById(userId);
            com.cit.usermanagement.entity.UserProfile userProfileFromDb = optionalUserProfileFromDb.get();
            userProfileFromDb.setIsActive(false);
            userProfileDao.save(userProfileFromDb);

            return new ResponseEntity<>("User removed Successfully", HttpStatus.GONE);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to remove User", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> modifyUser(Long userId, UserProfile viewUserProfile) {

        Optional<com.cit.usermanagement.entity.UserProfile> optionalUserProfileFromDb = userProfileDao.findById(userId);

        com.cit.usermanagement.entity.UserProfile userProfileFromDb = optionalUserProfileFromDb.get();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        log.info("Entered the modifyUser method");

        try {
            //updating userProfile table
            LocalDateTime updatedDate = LocalDateTime.parse(LocalDateTime.now().format(df), df);
            BsonTimestamp rtimes = new BsonTimestamp(updatedDate.toInstant(ZoneOffset.UTC).toEpochMilli());

            if (viewUserProfile.getUsername() !=null) {
                userProfileFromDb.setUsername(viewUserProfile.getUsername());
            }
            if (viewUserProfile.getPassword() !=null) {
                userProfileFromDb.setPassword(encoder.encode(viewUserProfile.getPassword()));
            }
            if (viewUserProfile.getCompany() !=null) {
                userProfileFromDb.setCompany(viewUserProfile.getCompany());
            }
            if (viewUserProfile.getUpdatedBy() !=null) {
                userProfileFromDb.setUpdatedBy(viewUserProfile.getUpdatedBy());
            }
            if (viewUserProfile.getIsActive() !=null) {
                userProfileFromDb.setIsActive(viewUserProfile.getIsActive());
            }
            if (viewUserProfile.getEmailAddress() !=null) {
                userProfileFromDb.setEmailAddress((viewUserProfile.getEmailAddress()));
            }
            if (viewUserProfile.getPhoneNumber() !=null) {
                userProfileFromDb.setPhoneNumber(viewUserProfile.getPhoneNumber());
            }
            userProfileFromDb.setUpdatedOn(rtimes);
            if (viewUserProfile.getGroupRoles() !=null) {
                userProfileFromDb.setGroupRoles(viewUserProfile.getGroupRoles());////////modify
            }

            userProfileDao.save(userProfileFromDb);
            log.info("Closing the modify method successfully.");
            return new ResponseEntity<>("User modified Successfully", HttpStatus.OK);

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            log.debug(ExceptionUtils.getStackTrace(e));
            return new ResponseEntity<>("Failed to update User, Date Time Exception", HttpStatus.BAD_REQUEST);
        } catch (NullPointerException e) {
            e.printStackTrace();
            log.debug(ExceptionUtils.getStackTrace(e));
            return new ResponseEntity<>("Failed to update User, Null Pointer Exception", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(ExceptionUtils.getStackTrace(e));
            return new ResponseEntity<>("Failed to update User, Exception occurred", HttpStatus.BAD_REQUEST);
        }


    }


    public ResponseEntity<List<UserProfile>> getUsersByAssignedGroupId(String groupId) {
        log.info("Entered the getUsersByAssignedGroupId method.");
        try {

            List<com.cit.usermanagement.entity.UserProfile> userProfiles =  userProfileDao.findByGroupRolesAssignedGroupId(groupId);
            //List<UserProfile> profiles = userProfileDao.findByAssignedGroup(groupId);
            List<UserProfile> userProfileViewList = new ArrayList<>();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


            for (com.cit.usermanagement.entity.UserProfile userProfile : userProfiles) {
                ZonedDateTime c = Instant.ofEpochMilli(userProfile.getCreatedOn().asTimestamp().getValue()).atZone(ZoneOffset.UTC);
                String formattedCreatedDate = df.format(c);
                ZonedDateTime u = Instant.ofEpochMilli(userProfile.getCreatedOn().asTimestamp().getValue()).atZone(ZoneOffset.UTC);
                String formattedUpdatedDate = df.format(u);
                UserProfile viewProfile = new UserProfile();

                UserProfile userProfileView = new UserProfile();
                BeanUtils.copyProperties(userProfile, userProfileView);
                viewProfile.setUpdatedOn(formattedUpdatedDate);
                userProfileViewList.add(userProfileView);
            }
            log.info("Closing the getAllUsers method successfully.");
            return new ResponseEntity<>(userProfileViewList, HttpStatus.OK);

        } catch (DateTimeParseException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            throw new ApplicationException("Date Format Parser Exception", e);
        } catch (NullPointerException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            throw new ApplicationException("Null Pointer Exception", e);
        } catch (Exception e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            throw new ApplicationException(ExceptionUtils.getStackTrace(e));
        }
    }

//    public ResponseEntity<String> addUserToGroupWithRoleAndPrivilege(Long userId, GroupRole groupRole) {
//        log.info("Entered the addUserToGroup method");
//
//        try {
//            Optional<UserProfile> optionalUserProfile = userProfileDao.findById(userId);
//            UserProfile userProfile = optionalUserProfile.get();
//
//            GroupRole groupRoleModel = new GroupRole();
//            groupRoleModel.setRoleId(groupRole.getRoleId());
//            groupRoleModel.setAssignedGroupId(groupRole.getAssignedGroupId());
//            groupRoleModel.setCustomizedPrivileges(groupRole.getCustomizedPrivileges());
//
//            List<GroupRole> groupRoleList = userProfile.getGroupRoles();
//
//            if (groupRoleList == null) {
//                groupRoleList = new ArrayList<>();
//            }
//            groupRoleList.add(groupRoleModel);
//
//            userProfile.setGroupRoles(groupRoleList);
//
//            userProfileDao.save(userProfile);
//            log.info("Closing the modify method successfully.");
//            return new ResponseEntity<>("User added to group Successfully", HttpStatus.OK);
//
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            log.debug(ExceptionUtils.getStackTrace(e));
//            return new ResponseEntity<>("Failed to add User to group, Null Pointer Exception", HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.debug(ExceptionUtils.getStackTrace(e));
//            return new ResponseEntity<>("Failed to add User to group, Exception occurred", HttpStatus.BAD_REQUEST);
//        }
//
//    }
//
//    public ResponseEntity<String> deleteUserFromGroupWithRole(Long userId, String groupId) {
//        log.info("Entered the deleteUserFromGroupWithRole method");
//
//        try {
//            Optional<UserProfile> optionalUserProfile = userProfileDao.findById(userId);
//            UserProfile userProfile = optionalUserProfile.get();
//
//            List<GroupRole> groupRoleList = userProfile.getGroupRoles();
//
//            for (GroupRole groupRole : groupRoleList) {
//                if (groupRole.getAssignedGroupId().equals(groupId)) {
//                    groupRoleList.remove(groupRole);
//                }
//            }
//            userProfileDao.save(userProfile);
//            log.info("Closing the deleteUserFromGroupWithRole method successfully.");
//            return new ResponseEntity<>("User removed from group Successfully", HttpStatus.OK);
//
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            log.debug(ExceptionUtils.getStackTrace(e));
//            return new ResponseEntity<>("Failed to remove User from group, Null Pointer Exception", HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.debug(ExceptionUtils.getStackTrace(e));
//            return new ResponseEntity<>("Failed to remove User from group, Exception occurred", HttpStatus.BAD_REQUEST);
//        }
//
//    }

//    public ResponseEntity<String> addUserPrivilege(Long userId, String userPrivilege) {
//        try {
//            Optional<UserProfile> optionalUserProfile = userProfileDao.findById(userId);
//
//            UserProfile userProfile = optionalUserProfile.get();
//
//            List<String> userPrivilegeList = userProfile.getUserExtraPrivileges();
//            if(userPrivilegeList == null){
//                userPrivilegeList = new ArrayList<>();
//            }
//            userPrivilegeList.add(userPrivilege);
//
//            userProfile.setUserExtraPrivileges(userPrivilegeList);
//
//            userProfileDao.save(userProfile);
//            return new ResponseEntity<>("Modified user privileges", HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Failed to modify user privileges", HttpStatus.BAD_REQUEST);
//        }
//    }
//    public ResponseEntity<String> removeUserPrivilege(Long userId, String userPrivilege) {
//        try {
//            Optional<UserProfile> optionalUserProfile = userProfileDao.findById(userId);
//
//            UserProfile userProfile = optionalUserProfile.get();
//
//            System.out.println(userProfile.toString());
//
//            List<String> userPrivilegeList = userProfile.getUserExtraPrivileges();
//            for(String privilege : userPrivilegeList){
//                if(privilege.equals(userPrivilege)){
//                    userPrivilegeList.remove(privilege);
//                }
//            }
//
//            userProfileDao.save(userProfile);
//            return new ResponseEntity<>("Modified user privileges", HttpStatus.OK);
//
//        } catch (Exception e) {
//            log.debug(ExceptionUtils.getStackTrace(e));
//            return new ResponseEntity<>("Failed to modify user privileges", HttpStatus.BAD_REQUEST);
//        }
//    }
}
