package com.cit.usermanagement.dto;

import java.util.List;

public class AssignedGroups {

	private String groupId;
    private String groupName;

	private String groupAdminId;

	private List<String> applications;
	private Boolean isActive;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean active) {
		isActive = active;
	}

	public String getGroupAdminId() {
		return groupAdminId;
	}

	public void setGroupAdminId(String groupAdminId) {
		this.groupAdminId = groupAdminId;
	}

	public List<String> getApplications() {
		return applications;
	}

	public void setApplications(List<String> applications) {
		this.applications = applications;
	}

	@Override
	public String toString() {
		return "AssignedGroups{" +
				"groupId='" + groupId + '\'' +
				", groupName='" + groupName + '\'' +
				", isActive=" + isActive +
				'}';
	}
}
