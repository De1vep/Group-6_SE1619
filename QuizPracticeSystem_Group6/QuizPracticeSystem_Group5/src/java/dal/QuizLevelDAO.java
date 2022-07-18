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
import model.QuizLevel;

/**
 *
 * @author Admin
 */
public class QuizLevelDAO extends DBContext {

    public QuizLevel getQuizLevelById(int quizLevelId) throws Exception {

        String sql = "SELECT * FROM [QuizLevel] where quizLevelId=" + quizLevelId;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new QuizLevel(rs.getInt("quizLevelId"),
                        rs.getString("quizLevelName"),
                        rs.getBoolean("status"));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return null;
    }

    public ArrayList<QuizLevel> getAllStatusQuizLevel() throws Exception {
        ArrayList<QuizLevel> quizLevels = new ArrayList<>();

        QuizLevel quizLevel = null;
        String sql = "SELECT [quizLevelId]\n"
                + "      ,[quizLevelName]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[QuizLevel]";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                quizLevel = new QuizLevel(rs.getInt("quizLevelId"),
                        rs.getString("quizLevelName"),
                        rs.getBoolean("status"));
                quizLevels.add(quizLevel);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return quizLevels;
    }

    public QuizLevel getQuizLevelByIdLoad(int id) {
        try {
            String sql = "SELECT * FROM [QuizLevel] where quizLevelId=" + id;

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new QuizLevel(rs.getInt("quizLevelId"),
                        rs.getString("quizLevelName"),
                        rs.getBoolean("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizLevelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void UpDateQuizLevelSetting(int id, String name, boolean statuss) {
        try {
            String sql = "UPDATE [dbo].[QuizLevel]\n"
                    + "   SET [quizLevelName] = ?\n"
                    + "      ,[status] = ?\n"
                    + " WHERE quizLevelId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setBoolean(2, statuss);
            stm.setInt(3, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        System.out.println(new QuizLevelDAO().getQuizLevelByIdLoad(1).getQuizLevelName());
    }

    public void InsertQuizLevelSetting(String name, boolean statuss) {
        try {
            String sql = "INSERT INTO [dbo].[QuizLevel]\n"
                    + "           ([quizLevelName]\n"
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
