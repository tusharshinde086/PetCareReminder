package com.petcare.model;

/**
 * Model class representing an Activity performed by a Pet
 * in the Pet Care Management System.
 */
public class Activity {

    // --- Fields ---
    private int activityId;
    private int petId;
    private String activityName;
    private String activityTime;
    private String status;

    // --- Constructors ---
    public Activity() {
        // Default constructor
    }

    public Activity(int activityId, int petId, String activityName, String activityTime, String status) {
        this.activityId = activityId;
        this.petId = petId;
        this.activityName = activityName;
        this.activityTime = activityTime;
        this.status = status;
    }

    // Constructor without activityId (for new inserts)
    public Activity(int petId, String activityName, String activityTime, String status) {
        this.petId = petId;
        this.activityName = activityName;
        this.activityTime = activityTime;
        this.status = status;
    }

    // --- Getters and Setters ---
    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // --- toString() for debugging ---
    @Override
    public String toString() {
        return "Activity{" +
                "activityId=" + activityId +
                ", petId=" + petId +
                ", activityName='" + activityName + '\'' +
                ", activityTime='" + activityTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
