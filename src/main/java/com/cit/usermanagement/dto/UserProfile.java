package com.cit.usermanagement.dto;


import com.cit.usermanagement.entity.GroupRole;

import java.util.Arrays;
import java.util.List;

public class UserProfile {
    
    private Long userId;
    private String username;
    private String password;
    //private String role;
    //private String applicationName;
    private String company;
    private String createdBy;
    private String updatedBy;
    private String createdOn;
    private String updatedOn;
   // private List<String> assignedGroup;
	private Boolean isActive;
	private String emailAddress;
	private String phoneNumber;
	private byte[] image;
	private List<GroupRole> groupRoles;
	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public List<GroupRole> getGroupRoles() {
		return groupRoles;
	}
	
	public void setGroupRoles(List<GroupRole> groupRoles) {
		this.groupRoles = groupRoles;
	}
	
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}

//	public String getApplicationName() {
//		return applicationName;
//	}
//
//	public void setApplicationName(String applicationName) {
//		this.applicationName = applicationName;
//	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

//	public List<String> getAssignedGroup() {
//		return assignedGroup;
//	}
//
//	public void setAssignedGroup(List<String> assignedGroup) {
//		this.assignedGroup = assignedGroup;
//	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean active) {
		isActive = active;
	}

	@Override
	public String toString() {
		return "UserProfile{" +
				"userId=" + userId +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", company='" + company + '\'' +
				", createdBy='" + createdBy + '\'' +
				", updatedBy='" + updatedBy + '\'' +
				", createdOn='" + createdOn + '\'' +
				", updatedOn='" + updatedOn + '\'' +
				", isActive=" + isActive +
				", emailAddress='" + emailAddress + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", image=" + Arrays.toString(image) +
				", groupRoles=" + groupRoles +
				'}';
	}

}
