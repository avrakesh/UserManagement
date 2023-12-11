package com.cit.usermanagement.dto;

/**
 * show all the below fields along with openedDate and issue description.
 */

public class IncidentDetails {
	
	private String incidentDetailId;
	private Long incidentId;
	private String status;
	private String assignedTo;
	private String assignedGroup;
	private String assignedDate;
	private String resolvedDate;
	private String responseTime;
	private String remarks;
	private String openedDate;
	private String dueDate;
	private String openedBy;
	public String getIncidentDetailId() {
		return incidentDetailId;
	}
	public void setIncidentDetailId(String incidentDetailId) {
		this.incidentDetailId = incidentDetailId;
	}
	public Long getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(Long incidentId) {
		this.incidentId = incidentId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getAssignedGroup() {
		return assignedGroup;
	}
	public void setAssignedGroup(String assignedGroup) {
		this.assignedGroup = assignedGroup;
	}
	public String getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}
	public String getResolvedDate() {
		return resolvedDate;
	}
	public void setResolvedDate(String resolvedDate) {
		this.resolvedDate = resolvedDate;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getOpenedDate() {
		return openedDate;
	}
	public void setOpenedDate(String openedDate) {
		this.openedDate = openedDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getOpenedBy() {
		return openedBy;
	}
	public void setOpenedBy(String openedBy) {
		this.openedBy = openedBy;
	}
	@Override
	public String toString() {
		return "IncidentDetails [incidentDetailId=" + incidentDetailId + ", incidentId=" + incidentId + ", status="
				+ status + ", assignedTo=" + assignedTo + ", assignedGroup=" + assignedGroup + ", assignedDate="
				+ assignedDate + ", resolvedDate=" + resolvedDate + ", responseTime=" + responseTime + ", remarks="
				+ remarks + ", openedDate=" + openedDate + ", dueDate=" + dueDate + ", openedBy=" + openedBy + "]";
	}
	
}
