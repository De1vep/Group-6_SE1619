
package model;


public class PricePackage {
    private int packId; /*Price package id*/
    private String packName; /*Price package name*/
    private int subjectId; /*Subject Id*/
    private int duration; /*Price package duration (months)*/
    private float listPrice; /*Price package original price*/
    private float salePrice; /*Price package sale price*/
    private boolean status; /*Price package status*/
    private String subjectName;
    
    public PricePackage() {
    }

    public PricePackage(int packId, String packName, int subjectId, int duration, float listPrice, float salePrice, boolean status, String subjectName) {
        this.packId = packId;
        this.packName = packName;
        this.subjectId = subjectId;
        this.duration = duration;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.status = status;
        this.subjectName = subjectName;
    }

   public PricePackage(int packId, String packName, int subjectId, int duration, float listPrice, float salePrice, boolean status) {
        this.packId = packId;
        this.packName = packName;
        this.subjectId = subjectId;
        this.duration = duration;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.status = status;
    }

    public int getPackId() {
        return packId;
    }

    public void setPackId(int packId) {
        this.packId = packId;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getListPrice() {
        return listPrice;
    }

    public void setListPrice(float listPrice) {
        this.listPrice = listPrice;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "PricePackage{" + "packId=" + packId + ", packName=" + packName + ", subjectId=" + subjectId + ", duration=" + duration + ", listPrice=" + listPrice + ", salePrice=" + salePrice + ", status=" + status + ", subjectName=" + subjectName + '}';
    }

    
    
    

    
}
