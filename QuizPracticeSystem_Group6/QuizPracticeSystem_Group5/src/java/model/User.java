
package model;

public class User {
    private int userId; /*User ID*/
    private String userName; /*User Name*/
    private String password; /*User password*/
    private int roleId; /*User Role ID*/
    private String image; /*User profile picture*/
    private String  email; /*User Mail*/
    private boolean gender; /*User gender*/
    private String phone; /*User mobile*/
    private boolean status; /*User status*/
    private UserRole userRole; /*User Role Entity*/
    
    
    public User() {
    }



    public User(int userId, String userName, String password, int roleId, String image, String email, boolean gender, String phone, boolean status){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.roleId = roleId;
        this.image = image;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", password=" + password + ", roleId=" + roleId + ", image=" + image + ", email=" + email + ", gender=" + gender + ", phone=" + phone + ", status=" + status + ", userRole=" + userRole.toString() + '}';
    }

    
    
}
