package com.cit.usermanagement.entity;


import java.util.List;

public class LoginResponse {
	
    private String token;
    private String assignedToId;
    private List<GroupRole> groupRoles;
    private List<Role> roles;



//    private List<String> assignedGroupId;
//
//    private List<String> roleCode;
//
//    private Set<String> deniedAccessMethodNames;
//
//    private Set<String> allowedAccessMethodNames;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(String assignedToId) {
        this.assignedToId = assignedToId;
    }

    public List<GroupRole> getGroupRoles() {
        return groupRoles;
    }

    public void setGroupRoles(List<GroupRole> groupRoles) {
        this.groupRoles = groupRoles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
