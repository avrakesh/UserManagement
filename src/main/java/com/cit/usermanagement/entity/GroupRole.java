package com.cit.usermanagement.entity;

public class GroupRole {

    private String assignedGroupId;
    private String roleId;

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

    @Override
    public String toString() {
        return "GroupRole{" +
                "assignedGroupId='" + assignedGroupId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
