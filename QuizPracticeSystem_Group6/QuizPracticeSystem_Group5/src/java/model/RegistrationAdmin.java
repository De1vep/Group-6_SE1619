/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author 84915
 */
public class RegistrationAdmin {
    private int regId; /*Registration ID*/
    private User user; /*User Mail Registered*/
    private Date regTime; /*Registration Time*/
    private Subject subject;/*Subject Name*/
    private PricePackage pack; /*Price Package Name*/
    private double cost; /*Registration Cost*/
    private Date validFrom; /*Starting valid date*/
    private Date validTo; /*Ending valid date*/
    private User lastUpdatedBy; /*Last updated by user*/
    private String note; /*Update note*/
    private boolean status; /*Registration Status*/

    public RegistrationAdmin() {
    }

    public RegistrationAdmin(int regId, User user, Date regTime, Subject subject, PricePackage pack, double cost, Date validFrom, Date validTo, User lastUpdatedBy, String note, boolean status) {
        this.regId = regId;
        this.user = user;
        this.regTime = regTime;
        this.subject = subject;
        this.pack = pack;
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

    public User getUser() {
        return user;
    }

    public Date getRegTime() {
        return regTime;
    }

    public Subject getSubject() {
        return subject;
    }

    public PricePackage getPack() {
        return pack;
    }

    public double getCost() {
        return cost;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public User getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public String getNote() {
        return note;
    }

    public boolean isStatus() {
        return status;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setPack(PricePackage pack) {
        this.pack = pack;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public void setLastUpdatedBy(User lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    

    
}
