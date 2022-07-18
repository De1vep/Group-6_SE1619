/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Ultils.MD5;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import model.UserRole;

/**
 *
 * @author DPV
 */
public class UserDAO extends DBContext {

    MD5 md5 = new MD5();

    public boolean deleteSubjectExpert(int subjectId) {

        String sql = "DELETE FROM [dbo].[SubjectExpert]\n"
                + "      WHERE subjectId = ?";
        try {

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> newUserList = new ArrayList();

        try {

            PreparedStatement stm = connection.prepareStatement("SELECT * FROM [User]");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                newUserList.add(new User(rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getInt("roleId"),
                        rs.getString("image"),
                        rs.getString("email"),
                        rs.getBoolean("gender"),
                        rs.getString("phone"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newUserList;
    }

    public User checkLogin(String email, String password) {
        try {
            String md5Password = md5.getMd5(password);
            String sql = "SELECT * FROM [User] WHERE [email] = ? AND password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, md5Password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setRoleId(rs.getInt("roleId"));
                user.setImage(rs.getString("image"));
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getBoolean("gender"));
                user.setPhone(rs.getString("phone"));
                user.setStatus(rs.getBoolean("status"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User checkUser(String email) {
        String sql = "SELECT * FROM [User] WHERE [email] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User username = new User();
                username.setUserName(rs.getString("userName"));
                username.setPassword(rs.getString("password"));
                username.setEmail(rs.getString("email"));
                username.setImage(rs.getString("image"));
                username.setPhone(rs.getString("phone"));
                username.setUserId(rs.getInt("userId"));
                username.setGender(rs.getBoolean("gender"));
                username.setStatus(rs.getBoolean("status"));
                username.setRoleId(rs.getInt("roleId"));
                return username;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean registerAccount(String userName, String password, int roleId, String email, boolean gender, boolean status, String phone) {
        String md5password = md5.getMd5(password);

        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([userName]\n"
                + "           ,[password]\n"
                + "           ,[roleId]\n"
                + "           ,[email]\n"
                + "           ,[gender]\n"
                + "           ,[status]\n"
                + "           ,[phone])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userName);
            stm.setString(2, md5password);
            stm.setInt(3, roleId);
            stm.setString(4, email);
            stm.setBoolean(5, gender);
            stm.setBoolean(6, status);
            stm.setString(7, phone);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public User findUserByEmail(String email) {
        try {
            String sql = "SELECT * FROM [User] where email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setEmail(rs.getString(6));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updatePassword(String newPass, String emailAddress) {

        try {
            String md5newPass = md5.getMd5(newPass);
            String sql = "UPDATE [User]\n"
                    + "   SET [password] = ?\n"
                    + " WHERE  email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, md5newPass);
            stm.setString(2, emailAddress);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean userUpdate(User u) {
        try {
            String sql = "UPDATE [dbo].[User]\n"
                    + "   SET [userName] = ?\n"
                    + "      ,[gender] = ?\n"
                    + "      ,[phone] = ?\n"
                    + "      ,[image] = ?\n"
                    + " WHERE [email] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, u.getUserName());
            stm.setBoolean(2, u.isGender());
            stm.setString(3, u.getPhone());
            stm.setString(4, u.getImage());
            stm.setString(5, u.getEmail());
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean changeStatus(int userId, boolean newStatus) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [status] = ?\n"
                + " WHERE [userId] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setBoolean(1, newStatus);
            stm.setInt(2, userId);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public User getExpert() {
        String sql = " select se.userId,u.userName ,s.subjectId,s.subjectName from [User] u inner join SubjectExpert se \n"
                + "					   on u. userId = se.userId inner join Subject s on se.subjectId = s.subjectId";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User username = new User();
                username.setUserName(rs.getString("userName"));
                username.setUserId(rs.getInt("userId"));
                return username;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> GetAllExpert() {
        ArrayList<User> SubjectCategorys = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [User] WHERE RoleId =4";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();

                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setRoleId(rs.getInt("roleId"));
                user.setImage(rs.getString("image"));
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getBoolean("gender"));
                user.setPhone(rs.getString("phone"));
                user.setStatus(rs.getBoolean("status"));
                SubjectCategorys.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(registrationDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SubjectCategorys;
    }

    public ArrayList<User> getAllUserAdminbyPaging(int page, int PAGE_SIZE) {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [user] u \n"
                    + "INNER JOIN \n"
                    + "UserRole ur ON u.roleId = ur.userRoleId\n"
                    + "order by u.userId offset (?-1)*? row fetch next ? rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, PAGE_SIZE);
            stm.setInt(3, PAGE_SIZE);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User a = new User();
                a.setUserId(rs.getInt(1));
                a.setUserName(rs.getString(2));
                a.setRoleId(rs.getInt(4));
                a.setImage(rs.getString(5));
                a.setEmail(rs.getString(6));
                a.setGender(rs.getBoolean(7));
                a.setPhone(rs.getString(8));
                a.setStatus(rs.getBoolean(9));
                UserRole ur = new UserRole();
                ur.setUserRoleId(rs.getInt(11));
                ur.setUserRoleName(rs.getString(12));
                ur.setStatus(rs.getBoolean(13));
                a.setUserRole(ur);
                users.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public int countUser() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(u.userId) FROM [user] u \n"
                    + "INNER JOIN \n"
                    + "UserRole ur ON u.roleId = ur.userRoleId ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public ArrayList<User> getAllUserAdminbyPagingbySearch(int page, int page_size, String Name, String mail, String Phone) {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [user] u \n"
                    + "INNER JOIN \n"
                    + "UserRole ur ON u.roleId = ur.userRoleId\n"
                    + "WHERE ";

            if (Name != null) {
                sql += "u.userName LIKE '%" + Name + "%'";
            }
            if (mail != null) {
                sql += "u.email LIKE '%" + mail + "%'";
            }
            if (Phone != null) {
                sql += "u.phone LIKE '%" + Phone + "%'";
            }
            sql += "order by u.userId offset (?-1)*? row fetch next ? rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, page_size);
            stm.setInt(3, page_size);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User a = new User();
                a.setUserId(rs.getInt(1));
                a.setUserName(rs.getString(2));
                a.setRoleId(rs.getInt(4));
                a.setImage(rs.getString(5));
                a.setEmail(rs.getString(6));
                a.setGender(rs.getBoolean(7));
                a.setPhone(rs.getString(8));
                a.setStatus(rs.getBoolean(9));
                UserRole ur = new UserRole();
                ur.setUserRoleId(rs.getInt(11));
                ur.setUserRoleName(rs.getString(12));
                ur.setStatus(rs.getBoolean(13));
                a.setUserRole(ur);
                users.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public int countUserBySearch(String Name, String mail, String Phone) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(u.userId) FROM [user] u \n"
                    + "INNER JOIN \n"
                    + "UserRole ur ON u.roleId = ur.userRoleId\n"
                    + "WHERE ";

            if (Name != null) {
                sql += "u.userName LIKE '%" + Name + "%'";
            }
            if (mail != null) {
                sql += "u.email LIKE '%" + mail + "%'";
            }
            if (Phone != null) {
                sql += "u.phone LIKE '%" + Phone + "%'";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new UserDAO().countUserBySearch("", "", "123"));
    }

    public int countUserByFilter(int gender, int role, int status) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(u.userId) FROM [user] u \n"
                    + "INNER JOIN \n"
                    + "UserRole ur ON u.roleId = ur.userRoleId\n";

            if (gender != -1) {
                sql += "WHERE u.gender = " + gender;
            }
            if (gender == -1 && role != -1) {
                sql += "WHERE u.roleId = " + role;
            } else if (gender != -1 && role != -1) {
                sql += " AND u.roleId = " + role;
            }
            if (gender == -1 && role == -1 && status != -1) {
                sql += " WHERE u.status = " + status;
            } else if ((gender != -1 || role != -1) && status != -1) {
                sql += "AND u.status = " + status;
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public ArrayList<User> getAllUserAdminbyPagingbyFilter(int page, int page_size, int gender, int role, int status) {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [user] u \n"
                    + "INNER JOIN \n"
                    + "UserRole ur ON u.roleId = ur.userRoleId\n";

            if (gender != -1) {
                sql += "WHERE u.gender = " + gender;
            }
            if (gender == -1 && role != -1) {
                sql += "WHERE u.roleId = " + role;
            } else if (gender != -1 && role != -1) {
                sql += " AND u.roleId = " + role;
            }
            if (gender == -1 && role == -1 && status != -1) {
                sql += " WHERE u.status = " + status;
            } else if ((gender != -1 || role != -1) && status != -1) {
                sql += "AND u.status = " + status;
            }
            sql += " order by u.userId offset (?-1)*? row fetch next ? rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, page_size);
            stm.setInt(3, page_size);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User a = new User();
                a.setUserId(rs.getInt(1));
                a.setUserName(rs.getString(2));
                a.setRoleId(rs.getInt(4));
                a.setImage(rs.getString(5));
                a.setEmail(rs.getString(6));
                a.setGender(rs.getBoolean(7));
                a.setPhone(rs.getString(8));
                a.setStatus(rs.getBoolean(9));
                UserRole ur = new UserRole();
                ur.setUserRoleId(rs.getInt(11));
                ur.setUserRoleName(rs.getString(12));
                ur.setStatus(rs.getBoolean(13));
                a.setUserRole(ur);
                users.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public User getUserByID(int id) {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [user] u \n"
                    + "INNER JOIN \n"
                    + "UserRole ur ON u.roleId = ur.userRoleId\n"
                    + "WHERE userId = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User a = new User();
                a.setUserId(rs.getInt(1));
                a.setUserName(rs.getString(2));
                a.setRoleId(rs.getInt(4));
                a.setImage(rs.getString(5));
                a.setEmail(rs.getString(6));
                a.setGender(rs.getBoolean(7));
                a.setPhone(rs.getString(8));
                a.setStatus(rs.getBoolean(9));
                UserRole ur = new UserRole();
                ur.setUserRoleId(rs.getInt(11));
                ur.setUserRoleName(rs.getString(12));
                ur.setStatus(rs.getBoolean(13));
                a.setUserRole(ur);
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void UpDateUserAdmin(int userId,int roleId, boolean status) {
        try {
            String sql = "UPDATE [User]\n"
                    + "   SET [roleId] = ?  \n"
                    + "      ,[status] = ?\n"
                    + "\n"
                    + " WHERE userId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, roleId);
            stm.setBoolean(2, status);
            stm.setInt(3, userId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
