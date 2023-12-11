package com.cit.usermanagement.dto;

import java.util.Set;

public class Role {

    private String roleId;
    private String roleName;
    private String roleCode;
	private Boolean isActive;
    private Set<String> deniedAccessMethodNames;
    private Set<String> allowedAccessMethodNames;

    
    public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

	public Set<String> getDeniedAccessMethodNames() {
		return deniedAccessMethodNames;
	}

	public void setDeniedAccessMethodNames(Set<String> deniedAccessMethodNames) {
		this.deniedAccessMethodNames = deniedAccessMethodNames;
	}

	public Set<String> getAllowedAccessMethodNames() {
		return allowedAccessMethodNames;
	}

	public void setAllowedAccessMethodNames(Set<String> allowedAccessMethodNames) {
		this.allowedAccessMethodNames = allowedAccessMethodNames;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean active) {
		isActive = active;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleCode=" + roleCode + ", isActive=" + isActive
				+ ", deniedAccessMethodNames=" + deniedAccessMethodNames + ", allowedAccessMethodNames="
				+ allowedAccessMethodNames + "]";
	}

}
