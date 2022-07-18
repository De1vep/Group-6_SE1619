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
import model.Question;

/**
 *
 * @author Admin
 */
public class QuestionDAO extends DBContext {

    public ArrayList<Question> getQuestionForCreateQuiz(int numberOfQuestion, int subjectId, int dimensionId) throws Exception {
        ArrayList<Question> questionList = new ArrayList<>();
        String sql = "SELECT [questionId]\n"
                + "      ,[subjectId]\n"
                + "      ,[dimensionId]\n"
                + "      ,[lessonId]\n"
                + "      ,[content]\n"
                + "      ,[media]\n"
                + "      ,[explanation]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[Question]\n"
                + "  WHERE subjectId = ? AND dimensionId = ? AND [status] = 1\n"
                + "  ORDER BY NEWID()";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            stm.setInt(2, dimensionId);
            ResultSet rs = stm.executeQuery();
            while (rs.next() && questionList.size() < numberOfQuestion) {
                Question pro = new Question();
                pro.setQuestionId(rs.getInt("questionId"));
                pro.setSubjectId(rs.getInt("subjectId"));
                pro.setDimensionId(rs.getInt("dimensionId"));
                pro.setLessonId(rs.getInt("lessonId"));
                pro.setContent(rs.getString("content"));
                pro.setMedia(rs.getString("media"));
                pro.setStatus(rs.getBoolean("status"));
                questionList.add(pro);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return questionList;
    }

    public ArrayList<Question> getQuestionByQuizId(int quizId) throws Exception {

        ArrayList<Question> questionList = new ArrayList();
        ArrayList<Integer> idList = new ArrayList();
        String sql = "SELECT * FROM [QuizQuestion] WHERE quizId=" + quizId;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                idList.add(rs.getInt("questionId"));
            }
            for (int id : idList) {
                questionList.add(getQuestionById(id));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return questionList;
    }

    public Question getQuestionById(int questionId) throws Exception {

        String sql = "SELECT * FROM Question WHERE questionId=" + questionId;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Question(rs.getInt("questionId"),
                        rs.getInt("subjectId"),
                        rs.getInt("dimensionId"),
                        rs.getInt("lessonId"),
                        rs.getString("content"),
                        rs.getString("media"),
                        rs.getString("explanation"),
                        rs.getBoolean("status"));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return null;
    }

    //==========DPV=============
    public void updateQuestionDPV(Question question) {
        try {
            String sql = "UPDATE [Question] SET [subjectId] = ?, [dimensionId] = ?, [lessonId] = ?, [content] = ?, [media] = ?,[explanation] = ?, [status] = ? WHERE [questionId] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, question.getSubjectId());
            statement.setInt(2, question.getDimensionId());
            statement.setInt(3, question.getLessonId());
            statement.setString(4, question.getContent());
            statement.setString(5, question.getMedia());
            statement.setString(6, question.getExplanation());
            statement.setBoolean(7, question.isStatus());
            statement.setInt(8, question.getQuestionId());
            ResultSet rs = statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Question getQuestionByIdDPV(int questionId) {
        try {
            String sql = "SELECT * FROM Question WHERE questionId=" + questionId;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question s = new Question();
                s.setQuestionId(rs.getInt("questionId"));
                s.setContent(rs.getString("content"));
                s.setDimensionId(rs.getInt("dimensionId"));
                s.setLessonId(rs.getInt("lessonId"));
                s.setSubjectId(rs.getInt("subjectId"));
                s.setMedia(rs.getString("media"));
                s.setExplanation(rs.getString("explanation"));
                s.setStatus(rs.getBoolean("status"));
                s.setAnswers(new AnswerDAO().getAnswersByQuestionIdDPV(questionId));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int countQuestionFilter(int subjectId, int dimensionId, int lessonId) {
        try {
            String sql = "SELECT COUNT(*) FROM [Question] WHERE 1 = 1";
            if (subjectId > 0) {
                sql += "AND subjectId =" + subjectId;
            }
            if (dimensionId > 0) {
                sql += "AND dimensionId =" + dimensionId;
            }
            if (lessonId > 0) {
                sql += "AND lessonId =" + lessonId;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Question> getListQuestionFilterDPV(int page, int page_size, int subjectId, int dimensionId, int lessonId, int status) {
        ArrayList<Question> questionList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Question WHERE 1 = 1";
            if (subjectId > 0) {
                sql += "AND subjectId =" + subjectId;
            }
            if (dimensionId > 0) {
                sql += "AND dimensionId =" + dimensionId;
            }
            if (lessonId > 0) {
                sql += "AND lessonId =" + lessonId;
            }
            if (status >= 0) {
                sql += "AND status =" + status;
            }
            sql += "ORDER BY questionId ASC OFFSET (" + page + "-1)*" + page_size + " ROW FETCH NEXT " + page_size + " ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question s = new Question();
                s.setQuestionId(rs.getInt("questionId"));
                s.setContent(rs.getString("content"));
                s.setDimensionId(rs.getInt("dimensionId"));
                s.setLessonId(rs.getInt("lessonId"));
                s.setSubjectId(rs.getInt("subjectId"));
                s.setMedia(rs.getString("media"));
                s.setExplanation(rs.getString("explanation"));
                s.setStatus(rs.getBoolean("status"));
                questionList.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questionList;
    }

    public int countQuestionSearch(String content) {
        try {
            String sql = "SELECT COUNT(*) FROM [Question] WHERE 1 = 1 AND content like '%" + content.trim() + "%'";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Question> getListQuestionSearchDPV(int page, int page_size, String content) {
        ArrayList<Question> questionList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Question WHERE 1 = 1 AND content like '%" + content.trim() + "%' ORDER BY questionId ASC OFFSET (?-1)*? ROW FETCH NEXT ? ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, page_size);
            stm.setInt(3, page_size);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question s = new Question();
                s.setQuestionId(rs.getInt("questionId"));
                s.setContent(rs.getString("content"));
                s.setDimensionId(rs.getInt("dimensionId"));
                s.setLessonId(rs.getInt("lessonId"));
                s.setSubjectId(rs.getInt("subjectId"));
                s.setMedia(rs.getString("media"));
                s.setExplanation(rs.getString("explanation"));
                s.setStatus(rs.getBoolean("status"));
                questionList.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questionList;
    }

    public int countQuestion() {
        try {
            String sql = "SELECT COUNT(*) FROM [Question] WHERE 1 = 1";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Question> getListQuestionByPaginationDPV(int page, int page_size) {
        ArrayList<Question> questionList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Question WHERE 1 = 1 ORDER BY questionId ASC OFFSET (?-1)*? ROW FETCH NEXT ? ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, page_size);
            stm.setInt(3, page_size);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question s = new Question();
                s.setQuestionId(rs.getInt("questionId"));
                s.setContent(rs.getString("content"));
                s.setDimensionId(rs.getInt("dimensionId"));
                s.setLessonId(rs.getInt("lessonId"));
                s.setSubjectId(rs.getInt("subjectId"));
                s.setMedia(rs.getString("media"));
                s.setExplanation(rs.getString("explanation"));
                s.setStatus(rs.getBoolean("status"));
                questionList.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questionList;
    }
    
    public static void main(String[] args) throws Exception {
        ArrayList<Question> a = new QuestionDAO().getQuestionByQuizId(1);
        for (Question question : a) {
            System.out.println(question.toString());
        }
    }

}
