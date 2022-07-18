
package model;

import java.util.Date;

public class Registration {
    private int regId; /*Registration ID*/
    private int userId; /*ID User Registered*/
    private Date regTime; /*Registration Time*/
    private int packId; /*Price Package ID*/
    private double cost; /*Registration Cost*/
    private Date validFrom; /*Starting valid date*/
    private Date validTo; /*Ending valid date*/
    private int lastUpdatedBy; /*Last updated by user(id)*/
    private String note; /*Update note*/
    private boolean status; /*Registration Status*/
    private Subject subject;

    public Registration() {
    }

    public Registration(int regId, int userId, Date regTime, int packId, double cost, Date validFrom, Date validTo, int lastUpdatedBy, String note, boolean status) {
        this.regId = regId;
        this.userId = userId;
        this.regTime = regTime;
        this.packId = packId;
        this.cost = cost;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.lastUpdatedBy = lastUpdatedBy;
        this.note = note;
        this.status = status;
    }

    public int getRegId() {
        return regId;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public int getPackId() {
        return packId;
    }

    public void setPackId(int packId) {
        this.packId = packId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public int getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(int lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Registration{" + "regId=" + regId + ", userId=" + userId + ", regTime=" + regTime + ", packId=" + packId + ", cost=" + cost + ", validFrom=" + validFrom + ", validTo=" + validTo + ", lastUpdatedBy=" + lastUpdatedBy + ", note=" + note + ", status=" + status + '}';
    }

    
}
