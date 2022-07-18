
package model;


public class UserRole {
    private int userRoleId; /*User role id*/
    private String userRoleName; /*User role Name*/
    private boolean status; /*UserRole status*/

    public UserRole() {
    }

    public UserRole(int userRoleId, String userRoleName, boolean status) {
        this.userRoleId = userRoleId;
        this.userRoleName = userRoleName;
        this.status = status;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserRole{" + "userRoleId=" + userRoleId + ", userRoleName=" + userRoleName + ", status=" + status + '}';
    }

    
}
