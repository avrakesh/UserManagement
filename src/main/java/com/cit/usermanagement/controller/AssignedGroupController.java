package com.cit.usermanagement.controller;

import com.cit.usermanagement.dto.AssignedGroups;
import com.cit.usermanagement.exception.ApplicationException;
import com.cit.usermanagement.service.AssignedUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AssignedGroupController {
    
    @Autowired
    AssignedUserGroupService assignedUserGroupService;

    
    @PostMapping("/addAssignedGroup")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addAssignedGroup(@RequestBody AssignedGroups userGroup) throws ApplicationException{
        System.out.println(userGroup.toString());
        return assignedUserGroupService.addAssignedGroup(userGroup);
    }

    @GetMapping("/getActiveAssignedGroups")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<AssignedGroups>> getActiveAssignedGroups() throws ApplicationException{
        return assignedUserGroupService.getActiveAssignedGroups();
    }
    @GetMapping("/getAllAssignedGroups")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<AssignedGroups>> getAllAssignedGroups() throws ApplicationException{
        return assignedUserGroupService.getAllAssignedGroups();
    }

    @GetMapping("/getAssignedGroupById/{assignedGroupId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<AssignedGroups>
    getAssignedGroupById(@PathVariable String assignedGroupId) throws ApplicationException{
        return assignedUserGroupService.getAssignedGroupById(assignedGroupId);
    }

    @PutMapping("/modifyAssignedGroup/{assignedGroupId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String>
    modifyAssignedGroup(@PathVariable String assignedGroupId,
                        @RequestBody AssignedGroups assignedGroup) throws ApplicationException{
        System.out.println(assignedGroup.getIsActive());
        return assignedUserGroupService.modifyAssignedGroup(assignedGroupId, assignedGroup);
    }

    @DeleteMapping("/deleteAssignedGroup/{groupId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteAssignedGroup(@PathVariable String groupId) throws ApplicationException{

        return assignedUserGroupService.deleteGroup(groupId);
    }
}
