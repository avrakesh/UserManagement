package com.cit.usermanagement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "roles")
public class Role {

	@Transient
	public static final String SEQUENCE_NAME = "roles";
    @Id
    private String roleId;
    @Indexed(unique = true)
	private String roleName;
	@Indexed(unique = true)
	private String roleCode;
	private Boolean isActive;
    private Set<String> deniedAccessMethodNames;
    private Set<String> allowedAccessMethodNames;

    


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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean active) {
		isActive = active;
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

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleCode=" + roleCode + ", isActive=" + isActive
				+ ", deniedAccessMethodNames=" + deniedAccessMethodNames + ", allowedAccessMethodNames="
				+ allowedAccessMethodNames + "]";
	}

}
