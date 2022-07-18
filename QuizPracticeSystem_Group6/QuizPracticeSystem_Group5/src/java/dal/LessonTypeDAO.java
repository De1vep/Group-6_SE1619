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
import model.LessonType;

/**
 *
 * @author Admin
 */
public class LessonTypeDAO extends DBContext {

    public ArrayList<LessonType> getAllStatusLessonType() throws Exception {

        ArrayList<LessonType> lessonTypesList = new ArrayList<>();
        String sql = "SELECT [lessonTypeId]\n"
                + "      ,[lessonTypeName]\n"
                + "      ,[status]\n"
                + "  FROM [LessonType]";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                lessonTypesList.add(
                        new LessonType(rs.getInt("lessonTypeId"),
                                rs.getString("lessonTypeName"),
                                rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return lessonTypesList;
    }

    public LessonType getLessonTypeByIdLoad(int id) {

        try {
            String sql = "SELECT * FROM [LessonType] WHERE lessonTypeId = " + id;

            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return new LessonType(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static void main(String[] args) {
        System.out.println(new LessonTypeDAO().getLessonTypeByIdLoad(1).getLessonTypeName());
    }

    public void UpDateLessonTypeSetting(int id, String name, boolean statuss) {
        try {
            String sql = "UPDATE [dbo].[LessonType]\n"
                    + "   SET [lessonTypeName] = ?\n"
                    + "      ,[status] = ?\n"
                    + " WHERE lessonTypeId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setBoolean(2, statuss);
            stm.setInt(3, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InsertLessonTypeSetting(String name, boolean statuss) {
        try {
            String sql = "INSERT INTO [dbo].[LessonType]\n"
                    + "           ([lessonTypeName]\n"
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
