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
import model.Answer;

/**
 *
 * @author Admin
 */
public class AnswerDAO extends DBContext {

    public ArrayList<Answer> getAnswersByQuenstionId(int questionId) throws Exception {

        ArrayList<Answer> answerList = new ArrayList();
        String sql = "SELECT * FROM Answer WHERE questionId=" + questionId;
        /* Sql query */
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                answerList.add(new Answer(rs.getInt("answerId"),
                        rs.getInt("questionId"),
                        rs.getString("answerContent"),
                        rs.getBoolean("isCorrect"),
                        rs.getBoolean("status")));
            }
            return answerList;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void updateAnswerDPV(int answerId, String answerContent) {
        try {
            String sql = "UPDATE [Answer] SET [answerContent] = ? WHERE [answerId] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, answerContent);
            statement.setInt(2, answerId);
            ResultSet rs = statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public ArrayList<Answer> getAnswersByQuestionIdDPV(int questionId) {
        ArrayList<Answer> answerList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Answer WHERE questionId=" + questionId;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Answer s = new Answer();
                s.setAnswerContent(rs.getString("answerContent"));
                s.setAnswerId(rs.getInt("answerId"));
                s.setIsCorrect(rs.getBoolean("isCorrect"));
                s.setQuestionId(rs.getInt("questionId"));
                s.setStatus(rs.getBoolean("status"));
                answerList.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answerList;
    }
}
