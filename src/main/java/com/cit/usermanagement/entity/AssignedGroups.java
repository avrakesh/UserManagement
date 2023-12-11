package com.cit.usermanagement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "assignedGroups")
public class AssignedGroups {

	@Transient
	public static final String SEQUENCE_NAME = "assignedGroups";
	@Id
	private String groupId;
	@Indexed(unique = true)
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
