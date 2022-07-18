/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class settingList {
    int id;
    String settingType;
    String Name;
    boolean status;

    public settingList() {
    }

    public settingList(int id, String settingType, String Name, boolean status) {
        this.id = id;
        this.settingType = settingType;
        this.Name = Name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSettingType() {
        return settingType;
    }

    public void setSettingType(String settingType) {
        this.settingType = settingType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "settingList{" + "id=" + id + ", settingType=" + settingType + ", Name=" + Name + ", status=" + status + '}';
    }
    
}
