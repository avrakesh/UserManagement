package com.cit.usermanagement.service;

import com.cit.usermanagement.exception.ApplicationException;
import com.cit.usermanagement.repository.AssignedGroupDao;
import com.cit.usermanagement.utils.SequenceGeneratorService;
import com.cit.usermanagement.dto.AssignedGroups;
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
public class AssignedUserGroupService {


    @Autowired
    AssignedGroupDao groupDao;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public ResponseEntity<String> addAssignedGroup(AssignedGroups group) throws ApplicationException {
        com.cit.usermanagement.entity.AssignedGroups assignedGroup = new com.cit.usermanagement.entity.AssignedGroups();

        group.setGroupId(String.valueOf(sequenceGeneratorService.generateSequence((com.cit.usermanagement.entity.AssignedGroups.SEQUENCE_NAME))));

        try {
            assignedGroup.setGroupId(group.getGroupId());
            assignedGroup.setGroupName(group.getGroupName());
            assignedGroup.setIsActive(group.getIsActive());
            assignedGroup.setGroupAdminId(group.getGroupAdminId());
            assignedGroup.setApplications(group.getApplications());
            groupDao.save(assignedGroup);
            return new ResponseEntity<>("created an entry in group", HttpStatus.OK);

        }catch (DuplicateKeyException dKE) {
            sequenceGeneratorService.decrementOperation(com.cit.usermanagement.entity.AssignedGroups.SEQUENCE_NAME);
            dKE.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Group name already exists");
        } catch (Exception e) {
            e.printStackTrace();
            sequenceGeneratorService.decrementOperation(com.cit.usermanagement.entity.AssignedGroups.SEQUENCE_NAME);
            return new ResponseEntity<>("Failed to create group", HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<List<AssignedGroups>> getActiveAssignedGroups() throws ApplicationException {
        try {
            List<AssignedGroups> viewList = new ArrayList<>();
            List<com.cit.usermanagement.entity.AssignedGroups> list = groupDao.findByIsActive(true);
            for (com.cit.usermanagement.entity.AssignedGroups model : list) {
                AssignedGroups view = new AssignedGroups();
                BeanUtils.copyProperties(model, view);
                viewList.add(view);
            }
            return new ResponseEntity<>(viewList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<AssignedGroups> getAssignedGroupById(String assignedGroupId) {

        try {
            Optional<com.cit.usermanagement.entity.AssignedGroups> optionalAssignedGroup = groupDao.findById(assignedGroupId);
            AssignedGroups viewAssignedGroup = new AssignedGroups();
            com.cit.usermanagement.entity.AssignedGroups assignedGroup = optionalAssignedGroup.get();

            BeanUtils.copyProperties(assignedGroup, viewAssignedGroup);

            return new ResponseEntity<>(viewAssignedGroup, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    public ResponseEntity<String> deleteGroup(String groupId) {
        try {
            Optional<com.cit.usermanagement.entity.AssignedGroups> optionalAssignedGroupFromDb = groupDao.findById(groupId);
            com.cit.usermanagement.entity.AssignedGroups assignedGroupFromDb = optionalAssignedGroupFromDb.get();
            assignedGroupFromDb.setIsActive(false);
            groupDao.save(assignedGroupFromDb);
            return new ResponseEntity<>("Group removed Successfully", HttpStatus.GONE);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to remove Group", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> modifyAssignedGroup(String assignedGroupId, AssignedGroups viewAssignedGroup) {

        try {
            Optional<com.cit.usermanagement.entity.AssignedGroups> optionalAssignedGroupFromDb = groupDao.findById(assignedGroupId);
            com.cit.usermanagement.entity.AssignedGroups assignedGroupFromDb = optionalAssignedGroupFromDb.get();

            System.out.println(viewAssignedGroup.getIsActive());

            if(viewAssignedGroup.getGroupName() != null) {
                assignedGroupFromDb.setGroupName(viewAssignedGroup.getGroupName());
            }
            if(viewAssignedGroup.getApplications() != null) {
                assignedGroupFromDb.setApplications(viewAssignedGroup.getApplications());
            }
            if(viewAssignedGroup.getGroupAdminId() != null) {
                assignedGroupFromDb.setGroupAdminId(viewAssignedGroup.getGroupAdminId());
            }
            if(viewAssignedGroup.getIsActive() != null) {
                assignedGroupFromDb.setIsActive(viewAssignedGroup.getIsActive());
            }
            System.out.println(optionalAssignedGroupFromDb.get());
            System.out.println(assignedGroupFromDb);
            groupDao.save(assignedGroupFromDb);
            return new ResponseEntity<>("Group modified successfully", HttpStatus.OK);
        }catch (DuplicateKeyException dKE) {
            dKE.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Group name already exists");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to modify Group", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<AssignedGroups>> getAllAssignedGroups() {
        try {
            List<AssignedGroups> viewList = new ArrayList<>();
            List<com.cit.usermanagement.entity.AssignedGroups> list = groupDao.findAll();
            for (com.cit.usermanagement.entity.AssignedGroups model : list) {
                AssignedGroups view = new AssignedGroups();
                BeanUtils.copyProperties(model, view);
                viewList.add(view);
            }
            return new ResponseEntity<>(viewList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
