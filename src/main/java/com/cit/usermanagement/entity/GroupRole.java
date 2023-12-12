package com.cit.usermanagement.entity;

import java.util.List;

public class GroupRole {

    private String assignedGroupId;
    private String roleId;
    private List<String> deniedAccessMethodNames;

    public String getAssignedGroupId() {
        return assignedGroupId;
    }

    public void setAssignedGroupId(String assignedGroupId) {
        this.assignedGroupId = assignedGroupId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getDeniedAccessMethodNames() {
        return deniedAccessMethodNames;
    }

    public void setDeniedAccessMethodNames(List<String> deniedAccessMethodNames) {
        this.deniedAccessMethodNames = deniedAccessMethodNames;
    }

    @Override
    public String toString() {
        return "GroupRole{" +
                "assignedGroupId='" + assignedGroupId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
