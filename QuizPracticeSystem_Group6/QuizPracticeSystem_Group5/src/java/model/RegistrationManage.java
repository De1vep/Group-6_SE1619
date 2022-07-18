
package model;

import java.util.Date;


public class RegistrationManage {
     private int regId; /*Registration ID*/
    private String userMail; /*User Mail Registered*/
    private Date regTime; /*Registration Time*/
    private String subjectName;/*Subject Name*/
    private String packName; /*Price Package Name*/
    private double cost; /*Registration Cost*/
    private Date validFrom; /*Starting valid date*/
    private Date validTo; /*Ending valid date*/
    private String lastUpdatedBy; /*Last updated by user*/
    private String note; /*Update note*/
    private String status; /*Registration Status*/

    public RegistrationManage() {
    }

    public RegistrationManage(int regId, String userMail, Date regTime, String subjectName, String packName, double cost, Date validFrom, Date validTo, String lastUpdatedBy, String note, String status) {
        this.regId = regId;
        this.userMail = userMail;
        this.regTime = regTime;
        this.subjectName = subjectName;
        this.packName = packName;
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

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
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

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RegistrationManage{" + "regId=" + regId + ", userMail=" + userMail + ", regTime=" + regTime + ", subjectName=" + subjectName + ", packName=" + packName + ", cost=" + cost + ", validFrom=" + validFrom + ", validTo=" + validTo + ", lastUpdatedBy=" + lastUpdatedBy + ", note=" + note + ", status=" + status + '}';
    }
    
    
    
    
}
