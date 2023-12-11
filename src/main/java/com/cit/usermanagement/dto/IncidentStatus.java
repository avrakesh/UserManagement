package com.cit.usermanagement.dto;

public class IncidentStatus {

    private String statusId;
    private String statusValue;

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    @Override
    public String toString() {
        return "IncidentStatus{" +
                "statusId=" + statusId +
                ", statusValue='" + statusValue + '\'' +
                '}';
    }
}
