package com.cit.usermanagement.entity;

import java.util.List;

public class GroupRole {

    private String assignedGroupId;
    private String roleId;
    private List <String> customizedPrivileges;

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

    public List<String> getCustomizedPrivileges() {
        return customizedPrivileges;
    }

    public void setCustomizedPrivileges(List<String> customizedPrivileges) {
        this.customizedPrivileges = customizedPrivileges;
    }

}
