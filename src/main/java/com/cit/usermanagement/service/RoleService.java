package com.cit.usermanagement.service;

import com.cit.usermanagement.utils.SequenceGeneratorService;
import com.cit.usermanagement.exception.ApplicationException;
import com.cit.usermanagement.repository.RoleDao;
import com.cit.usermanagement.dto.Role;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    static Logger log = LogManager.getLogger(RoleService.class);

    @Autowired
    RoleDao roleDao;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public ResponseEntity<String> addRole(Role viewRole) throws ApplicationException {

        viewRole.setRoleId(String.valueOf(sequenceGeneratorService.generateSequence((com.cit.usermanagement.entity.Role.SEQUENCE_NAME))));
        com.cit.usermanagement.entity.Role modelRole = new com.cit.usermanagement.entity.Role();
        try {
            modelRole.setRoleId(viewRole.getRoleId());
            modelRole.setIsActive(viewRole.getIsActive());
            modelRole.setRoleName(viewRole.getRoleName());
            modelRole.setRoleCode(viewRole.getRoleCode());
            modelRole.setDeniedAccessMethodNames(viewRole.getDeniedAccessMethodNames());
            modelRole.setAllowedAccessMethodNames(viewRole.getAllowedAccessMethodNames());
            roleDao.save(modelRole);
            return new ResponseEntity<>("Dear support Team, A Role is created.",
                    HttpStatus.CREATED);

        }catch (DuplicateKeyException dKE) {
            sequenceGeneratorService.decrementOperation((com.cit.usermanagement.entity.Role.SEQUENCE_NAME));
            dKE.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role name or Role code already exists");
        } catch (Exception e) {
            sequenceGeneratorService.decrementOperation((com.cit.usermanagement.entity.Role.SEQUENCE_NAME));
            e.printStackTrace();
            log.debug(ExceptionUtils.getStackTrace(e));
            throw new ApplicationException(ExceptionUtils.getStackTrace(e));
        }

    }

    public ResponseEntity<List<Role>> getActiveRoles() throws ApplicationException {
        try {
            List<Role> viewRoleList = new ArrayList<>();
            List<com.cit.usermanagement.entity.Role> modelRoleList = roleDao.findByIsActive(true);
            for (com.cit.usermanagement.entity.Role modelRole : modelRoleList) {
                Role viewRole = new Role();
                BeanUtils.copyProperties(modelRole, viewRole);
                viewRoleList.add(viewRole);
            }
            return new ResponseEntity<>(viewRoleList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(ExceptionUtils.getStackTrace(e));
            throw new ApplicationException(ExceptionUtils.getStackTrace(e));
        }
    }

    public ResponseEntity<String> deleteRole(String roleId) {

        try {
            System.out.println(roleId);
            Optional<com.cit.usermanagement.entity.Role> optionalRoleFromDb = roleDao.findById(roleId);
            com.cit.usermanagement.entity.Role roleFromDb = optionalRoleFromDb.get();
            System.out.println(roleFromDb.getRoleId() + roleFromDb.getRoleName());
            roleFromDb.setIsActive(false);
            roleDao.save(roleFromDb);
            return new ResponseEntity<>("Successfully Removed the Role", HttpStatus.GONE);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to remove Role", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> modifyRole(String roleId, Role modifiedRole) {
        try {
            Optional<com.cit.usermanagement.entity.Role> optionalRoleFromDb = roleDao.findById(roleId);
                com.cit.usermanagement.entity.Role roleFromDb = optionalRoleFromDb.get();

                roleFromDb.setRoleName(modifiedRole.getRoleName());
                roleFromDb.setRoleCode(modifiedRole.getRoleCode());
                roleFromDb.setIsActive(modifiedRole.getIsActive());
                roleFromDb.setDeniedAccessMethodNames(modifiedRole.getDeniedAccessMethodNames());
                roleFromDb.setAllowedAccessMethodNames(modifiedRole.getAllowedAccessMethodNames());

                roleDao.save(roleFromDb);
                return ResponseEntity.ok("Role modified successfully");

        } catch (DuplicateKeyException dKE) {
            dKE.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role name or Role code already exists");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to modify Role");
        }
    }




    public ResponseEntity<Role> getRoleById(String roleId) {

        Optional<com.cit.usermanagement.entity.Role> optionalRoleFromDb = roleDao.findById(roleId);
        com.cit.usermanagement.entity.Role roleFromDb = optionalRoleFromDb.get();

        Role viewRole = new Role();

        try{
            BeanUtils.copyProperties(roleFromDb, viewRole);
            return new ResponseEntity<>(viewRole, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Role>> getAllRoles() {
        try {
            List<Role> viewRoleList = new ArrayList<>();
            List<com.cit.usermanagement.entity.Role> modelRoleList = roleDao.findAll();
            for (com.cit.usermanagement.entity.Role modelRole : modelRoleList ) {
                Role viewRole = new Role();
                BeanUtils.copyProperties(modelRole, viewRole);
                viewRoleList.add(viewRole);
            }
            return new ResponseEntity<>(viewRoleList, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            log.debug(ExceptionUtils.getStackTrace(e));
            throw new ApplicationException(ExceptionUtils.getStackTrace(e));
        }
    }
}
