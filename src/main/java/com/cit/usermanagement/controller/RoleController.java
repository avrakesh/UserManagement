package com.cit.usermanagement.controller;

import com.cit.usermanagement.exception.ApplicationException;
import com.cit.usermanagement.service.RoleService;
import com.cit.usermanagement.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/addRole")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addRole
            (@RequestBody Role role) throws ApplicationException {
        System.out.println(role.toString());
        return roleService.addRole(role);
    }

    @GetMapping("/getAllRoles")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Role>> getAllRoles() throws ApplicationException{
        return roleService.getAllRoles();
    }
    @GetMapping("/getActiveRoles")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Role>> getActiveRoles() throws ApplicationException{
        return roleService.getActiveRoles();
    }

    @GetMapping("/getRoleById/{roleId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Role> getRoleById(@PathVariable String roleId) throws ApplicationException{
        return roleService.getRoleById(roleId);
    }

    @PutMapping("/modifyRole/{roleId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> modifyRole(@PathVariable String roleId, @RequestBody Role modifiedRole) throws ApplicationException{
        return roleService.modifyRole(roleId, modifiedRole);
    }

    @DeleteMapping("/deleteRole/{roleId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteRole(@PathVariable String roleId) throws ApplicationException{
        return roleService.deleteRole(roleId);
    }
}
