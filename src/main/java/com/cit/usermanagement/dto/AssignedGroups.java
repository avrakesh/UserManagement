package com.cit.usermanagement.dto;

public class AssignedGroups {

	private String groupId;
    private String groupName;
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

	@Override
	public String toString() {
		return "AssignedGroups{" +
				"groupId='" + groupId + '\'' +
				", groupName='" + groupName + '\'' +
				", isActive=" + isActive +
				'}';
	}
}
