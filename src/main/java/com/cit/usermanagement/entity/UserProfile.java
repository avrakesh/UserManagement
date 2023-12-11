package com.cit.usermanagement.entity;


import org.bson.BsonTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Document(collection = "userProfile")
public class UserProfile {

	@Transient
	public static final String SEQUENCE_NAME = "userProfile";
    @Id
    private Long userId;
    @Indexed(unique=true)
	private String username;
    private String password;
    //private String applicationName;
    private String company;
    private String createdBy;
    private String updatedBy;
    private BsonTimestamp createdOn;
    private BsonTimestamp updatedOn;
	private Boolean isActive;
	private String emailAddress;
	private String token;
	private String EmailVerified;
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
	
	public List<GroupRole> getGroupRoles() {
		return groupRoles;
	}

	public void setGroupRoles(List<GroupRole> groupRoles) {
		this.groupRoles = groupRoles;
	}
	
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the emailVerified
	 */
	public String getEmailVerified() {
		return EmailVerified;
	}

	/**
	 * @param emailVerified the emailVerified to set
	 */
	public void setEmailVerified(String emailVerified) {
		EmailVerified = emailVerified;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
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

	public BsonTimestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(BsonTimestamp createdOn) {
		this.createdOn = createdOn;
	}

	public BsonTimestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(BsonTimestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//
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
		return "UserProfile [userId=" + userId + ", username=" + username + ", password=" + password
				+ ", company=" + company + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", isActive="
				+ isActive + ", emailAddress=" + emailAddress + ", token=" + token + ", EmailVerified=" + EmailVerified
				+ ", phoneNumber=" + phoneNumber + ", image=" + Arrays.toString(image) + ", groupRoles=" + groupRoles
				+ "]";
	}

}