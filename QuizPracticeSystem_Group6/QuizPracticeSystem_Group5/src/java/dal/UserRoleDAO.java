/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserRole;

/**
 *
 * @author Admin
 */
public class UserRoleDAO extends DBContext {

    public ArrayList<UserRole> getAllUserRole() {
        ArrayList<UserRole> UserRoles = new ArrayList<>();
        try {
            String sql = "SELECT * FROM UserRole";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                UserRole a = new UserRole();
                a.setUserRoleId(rs.getInt(1));
                a.setUserRoleName(rs.getString(2));
                a.setStatus(rs.getBoolean(3));
                UserRoles.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return UserRoles;
    }

    public ArrayList<UserRole> getAllStatusUserRole() throws Exception {
        String sql = "SELECT [userRoleId],[userRoleName],[status] FROM [UserRole]";
        ArrayList<UserRole> allUserRole = new ArrayList<>();
        UserRole add = null;
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                add = new UserRole(rs.getInt("userRoleId"), rs.getString("userRoleName"), rs.getBoolean("status"));
                allUserRole.add(add);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return allUserRole;
    }

    public UserRole getUserRoleByIdLoad(int id) {

        try {
            String sql = "SELECT * FROM UserRole WHERE userRoleId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                UserRole a = new UserRole();
                a.setUserRoleId(rs.getInt(1));
                a.setUserRoleName(rs.getString(2));
                a.setStatus(rs.getBoolean(3));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void main(String[] args) {
        UserRoleDAO a = new UserRoleDAO();
        UserRole b = a.getUserRoleByIdLoad(1);
        System.out.println(b.getUserRoleName());
    }

    public void UpDateUserRolesSetting(int id, String name, boolean statuss) {
        try {
            String sql = "UPDATE [UserRole]\n"
                    + "   SET [userRoleName] = ?\n"
                    + "      ,[status] = ?\n"
                    + " WHERE  userRoleId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(3, id);
            stm.setString(1, name);
            stm.setBoolean(2, statuss);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InsertUserRolesSetting(String name, boolean statuss) {
        try {
            String sql = "INSERT INTO [dbo].[UserRole]\n"
                    + "           ([userRoleName]\n"
                    + "           ,[status])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setBoolean(2, statuss);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
