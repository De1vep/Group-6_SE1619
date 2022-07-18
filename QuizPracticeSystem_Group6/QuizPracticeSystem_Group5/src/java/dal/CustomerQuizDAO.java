/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.CustomerQuiz;
import model.Quiz;

/**
 *
 * @author Admin
 */
public class CustomerQuizDAO extends DBContext {

    public int CountCustomerQuizByUserId(int userId) throws Exception {
        int count = 0;
        String sql = "SELECT count([quizTakeId])\n"
                + "FROM [QuizSystem].[dbo].[CustomerQuiz]\n"
                + "WHERE userId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return count;
    }

    public ArrayList<CustomerQuiz> getQuizByUserBypageing(int userId, int page, int PAGE_SIZE) throws Exception {

        CustomerQuiz add = null;
        QuizDAO quizDAO = new QuizDAO();
        TestTypeDAO testTypeDAO = new TestTypeDAO();
        ArrayList<CustomerQuiz> custormerQuiz = new ArrayList<>();
        String sql = "SELECT [quizTakeId],[quizId],[userId],[score],[time],[sumitedAt],[status]\n"
                + "FROM [QuizSystem].[dbo].[CustomerQuiz]\n"
                + "WHERE userId = ?\n"
                + "order by [quizTakeId] offset (?-1)*? row fetch next ? rows only";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, page);
            stm.setInt(3, PAGE_SIZE);
            stm.setInt(4, PAGE_SIZE);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Timestamp time = new Timestamp(rs.getTimestamp("sumitedAt").getTime());
                add = new CustomerQuiz(rs.getInt("quizTakeId"),
                        rs.getInt("quizId"),
                        rs.getInt("userId"),
                        rs.getInt("score"),
                        rs.getInt("time"),
                        time,
                        rs.getBoolean("status"));
                Quiz quiz = quizDAO.getQuizById(rs.getInt("quizId"));
                add.setQuizName(quiz.getQuizName());
                add.setSubjectName(quiz.getSubject().getSubjectName());
                add.setTestTypeName(testTypeDAO.getTestTypeById(quiz.getTestTypeId()).getTestTypeName());
                custormerQuiz.add(add);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return custormerQuiz;
    }

    public static void main(String[] args) throws Exception {
        CustomerQuizDAO a = new CustomerQuizDAO();
        ArrayList<CustomerQuiz> custormerQuiz = a.getQuizByUserBypageing(1, 1, 1);
        for (CustomerQuiz customerQuiz : custormerQuiz) {
            System.out.println(customerQuiz.getSubjectName());
        }
    }

    public ArrayList<CustomerQuiz> getQuizByUserBySubjectIdBypageing(int userId, int subjectId, int page, int PAGE_SIZE) throws Exception {
        CustomerQuiz add = null;
        QuizDAO quizDAO = new QuizDAO();
        TestTypeDAO testTypeDAO = new TestTypeDAO();
        ArrayList<CustomerQuiz> custormerQuiz = new ArrayList<>();
        String sql = "SELECT c.[quizTakeId],c.[quizId],c.[userId],c.[score],c.[time],c.[sumitedAt],c.[status]\n"
                + "FROM [CustomerQuiz] c\n"
                + "INNER JOIN Quiz q ON c.quizId = q.quizId\n"
                + "INNER JOIN [Subject] s ON s.subjectId = q.subjectId\n"
                + "WHERE c.userId = ? and s.subjectId = ?\n"
                + "order by [quizTakeId] offset (?-1)*? row fetch next ? rows only";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, subjectId);
            stm.setInt(3, page);
            stm.setInt(4, PAGE_SIZE);
            stm.setInt(5, PAGE_SIZE);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Timestamp time = new Timestamp(rs.getTimestamp("sumitedAt").getTime());
                add = new CustomerQuiz(rs.getInt("quizTakeId"),
                        rs.getInt("quizId"),
                        rs.getInt("userId"),
                        rs.getInt("score"),
                        rs.getInt("time"),
                        time,
                        rs.getBoolean("status"));
                Quiz quiz = quizDAO.getQuizById(rs.getInt("quizId"));
                add.setQuizName(quiz.getQuizName());
                add.setSubjectName(quiz.getSubject().getSubjectName());
                add.setTestTypeName(testTypeDAO.getTestTypeById(quiz.getTestTypeId()).getTestTypeName());
                custormerQuiz.add(add);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return custormerQuiz;

    }

    public int CountCustomerQuizByUserIdBySubjectId(int userId, int subjectId) throws Exception {
        int count = 0;
        String sql = "SELECT count(c.[quizTakeId]) \n"
                + "FROM [CustomerQuiz] c\n"
                + "INNER JOIN Quiz q ON c.quizId = q.quizId\n"
                + "INNER JOIN [Subject] s ON s.subjectId = q.subjectId\n"
                + "WHERE c.userId = ? and s.subjectId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, subjectId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return count;
    }

    public CustomerQuiz getLastAddedCustomerQuiz() throws Exception {
        

        String sql = "SELECT TOP 1 * FROM [CustomerQuiz] ORDER BY quizTakeId DESC";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                Timestamp time = new Timestamp(rs.getTimestamp("sumitedAt").getTime());
                return new CustomerQuiz(rs.getInt("quizTakeId"),
                        rs.getInt("quizId"),
                        rs.getInt("userId"),
                        rs.getInt("score"),
                        rs.getInt("time"),
                        time,
                        rs.getBoolean("status"));
            }
            return null;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public CustomerQuiz getQuizByTakeQuizId(int quizTakeId) throws Exception {
        
        /* Prepared statement for executing sql queries */
        String sql = "SELECT * FROM [CustomerQuiz] WHERE quizTakeId=" + quizTakeId;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            rs = pre.executeQuery();
            if (rs.next()) {
                Timestamp time = new Timestamp(rs.getTimestamp("sumitedAt").getTime());
                return new CustomerQuiz(rs.getInt("quizTakeId"),
                        rs.getInt("quizId"),
                        rs.getInt("userId"),
                        rs.getInt("score"),
                        rs.getInt("time"),
                        time,
                        rs.getBoolean("status"));
            }
            return null;
        } catch (Exception ex) {
            throw ex;
        } 
    }
}
