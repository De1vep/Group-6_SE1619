/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DimensionType;
import model.Lesson;
import model.Quiz;
import model.QuizLevel;
import model.Subject;
import model.TestType;

/**
 *
 * @author Admin
 */
public class QuizDAO extends DBContext {

    public Quiz getQuizById(int quizId) throws Exception {

        String quizLevelName = null;
        String testTypeName = null;
        String dimensionTypeName = null;
        String sql = "SELECT * FROM [Quiz] WHERE quizId=" + quizId;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                if (rs.getObject("quizLevelId") != null) {
                    QuizLevelDAO quizLevelDAO = new QuizLevelDAO();
                    QuizLevel quizLevel = quizLevelDAO.getQuizLevelById(rs.getInt("quizLevelId"));
                    quizLevelName = quizLevel.getQuizLevelName();
                }
                if (rs.getObject("testTypeId") != null) {
                    TestTypeDAO testTypeDAO = new TestTypeDAO();
                    TestType testType = testTypeDAO.getTestTypeById(rs.getInt("testTypeId"));
                    testTypeName = testType.getTestTypeName();
                }
                if (rs.getObject("dimensionTypeId") != null) {
                    DimensionTypeDAO dimensionTypeDAO = new DimensionTypeDAO();
                    DimensionType dimensionType = dimensionTypeDAO.getDimensionTypeById(rs.getInt("dimensionTypeId"));
                    dimensionTypeName = dimensionType.getDimensionTypeName();
                }
                LessonDAO lessonDAO = new LessonDAO();
                Lesson lesson = lessonDAO.getLessonById(rs.getInt("lessonId"));
                SubjectDAO subjectDAO = new SubjectDAO();
                Subject subject = subjectDAO.getSubjectbyId(rs.getInt("subjectId"));
                return new Quiz(rs.getInt("quizId"),
                        lesson,
                        subject,
                        rs.getString("quizName"),
                        rs.getInt("quizLevelId"),
                        quizLevelName,
                        rs.getInt("quizDuration"),
                        rs.getInt("passRate"),
                        rs.getInt("testTypeId"),
                        testTypeName,
                        rs.getString("description"),
                        rs.getInt("numberQuestion"),
                        rs.getInt("dimensionTypeId"),
                        dimensionTypeName,
                        rs.getBoolean("status"));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return null;
    }

    public int addQuiz(Quiz quiz) throws Exception {
        int i = 0;

        /* Prepared statement for executing sql queries */
        String sql = "INSERT INTO dbo.Quiz(lessonId,"
                + "subjectId,"
                + "quizName,"
                + "quizLevelId,"
                + "quizDuration,"
                + "passRate,"
                + "testTypeId,"
                + "[description],"
                + "numberQuestion,"
                + "dimensionTypeId,"
                + "[status])\n"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            if (quiz.getLesson() != null) {
                stm.setInt(1, quiz.getLesson().getLessonId());
            } else {
                stm.setObject(1, null);
            }

            stm.setInt(2, quiz.getSubject().getSubjectId());
            stm.setString(3, quiz.getQuizName());

            if (quiz.getQuizLevelId() != 0) {
                stm.setInt(4, quiz.getQuizLevelId());
            } else {
                stm.setObject(4, null);
            }

            stm.setInt(5, quiz.getQuizDuration());

            if (quiz.getPassRate() != 0) {
                stm.setInt(6, quiz.getPassRate());
            } else {
                stm.setObject(6, null);
            }
            stm.setInt(7, quiz.getTestTypeId());
            stm.setString(8, quiz.getDescription());
            stm.setInt(9, quiz.getNumberQuestion());
            stm.setInt(10, quiz.getDimensionTypeId());
            stm.setInt(11, 1);
            i = stm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
        return i;
    }

    public int getQuizIdCreated(Quiz quiz) throws Exception {

        /* Prepared statement for executing sql queries */
        int quizId = -1;
        String sql = "SELECT TOP 1 [quizId]\n"
                + "      ,[lessonId]\n"
                + "      ,[subjectId]\n"
                + "      ,[quizName]\n"
                + "      ,[quizLevelId]\n"
                + "      ,[quizDuration]\n"
                + "      ,[passRate]\n"
                + "      ,[testTypeId]\n"
                + "      ,[description]\n"
                + "      ,[numberQuestion]\n"
                + "      ,[dimensionTypeId]\n"
                + "      ,[status]\n"
                + "FROM [QuizSystem].[dbo].[Quiz]\n"
                + "WHERE subjectId = ? \n"
                + "AND quizDuration = ? \n"
                + "AND testTypeId = ? \n"
                + "AND numberQuestion = ? \n"
                + "AND dimensionTypeId = ?\n"
                + "ORDER BY [quizId] DESC";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, quiz.getSubject().getSubjectId());
            pre.setInt(2, quiz.getQuizDuration());
            pre.setInt(3, quiz.getTestTypeId());
            pre.setInt(4, quiz.getNumberQuestion());
            pre.setInt(5, quiz.getDimensionTypeId());
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                quizId = rs.getInt("quizId");
            }
        } catch (Exception ex) {
            throw ex;
        }
        return quizId;
    }

    public int addQuizQuestion(int quizId, int questionId) throws Exception {

        /* Prepared statement for executing sql queries */
        int question = 0;
        String sql = "INSERT INTO dbo.QuizQuestion(quizId,questionId,[status]) VALUES(?,?,1)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, quizId);
            pre.setInt(2, questionId);
            question = pre.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
        return question;
    }

    public Quiz getQuizByQuizTakeId(int quizTakeId) throws Exception {

        String quizLevelName = null;
        String testTypeName = null;
        String dimensionTypeName = null;
        String sql = "select * from Quiz as a join CustomerQuiz as b on a.quizId = b.quizId where quizTakeId=" + quizTakeId;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                if (rs.getObject("quizLevelId") != null) {
                    QuizLevelDAO quizLevelDAO = new QuizLevelDAO();
                    QuizLevel quizLevel = quizLevelDAO.getQuizLevelById(rs.getInt("quizLevelId"));
                    quizLevelName = quizLevel.getQuizLevelName();
                }
                if (rs.getObject("testTypeId") != null) {
                    TestTypeDAO testTypeDAO = new TestTypeDAO();
                    TestType testType = testTypeDAO.getTestTypeById(rs.getInt("testTypeId"));
                    testTypeName = testType.getTestTypeName();
                }
                if (rs.getObject("dimensionTypeId") != null) {
                    DimensionTypeDAO dimensionTypeDAO = new DimensionTypeDAO();
                    DimensionType dimensionType = dimensionTypeDAO.getDimensionTypeById(rs.getInt("dimensionTypeId"));
                    dimensionTypeName = dimensionType.getDimensionTypeName();
                }
                LessonDAO lessonDAO = new LessonDAO();
                Lesson lesson = lessonDAO.getLessonById(rs.getInt("lessonId"));
                SubjectDAO subjectDAO = new SubjectDAO();
                Subject subject = subjectDAO.getSubjectbyId(rs.getInt("subjectId"));
                return new Quiz(rs.getInt("quizId"),
                        lesson,
                        subject,
                        rs.getString("quizName"),
                        rs.getInt("quizLevelId"),
                        quizLevelName,
                        rs.getInt("quizDuration"),
                        rs.getInt("passRate"),
                        rs.getInt("testTypeId"),
                        testTypeName,
                        rs.getString("description"),
                        rs.getInt("numberQuestion"),
                        rs.getInt("dimensionTypeId"),
                        dimensionTypeName,
                        rs.getBoolean("status"));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return null;
    }

    public ArrayList<Quiz> getAllSimulationQuizByUser(int userId, int subjectId, String quizName) throws Exception {

        registrationDBcontext IRegistration = new registrationDBcontext();
        ArrayList<Subject> subjectList = IRegistration.getRegistedSubject(userId);
        ArrayList<Quiz> quizList = new ArrayList<>();
        if (!subjectList.isEmpty()) {
            String sql = "SELECT q.quizLevelId, q.testTypeId,q.dimensionTypeId,q.lessonId,\n"
                    + "		q.subjectId,q.quizId,q.quizName,q.quizDuration,q.passRate,\n"
                    + "		q.description,q.numberQuestion,q.status\n"
                    + "from Registration r\n"
                    + "INNER JOIN \n"
                    + "PricePackage p ON r.packId = p.packId\n"
                    + "INNER JOIN \n"
                    + "[Subject] s ON s.subjectId = p.subjectId\n"
                    + "INNER JOIN \n"
                    + "Quiz q ON q.subjectId = s.subjectId WHERE q.testTypeId=1 AND q.status =1 AND r.userId = ? AND r.status = 1";
            if (subjectId == 0) {
                int subjectIdList[] = new int[subjectList.size()];
                for (int i = 0; i < subjectIdList.length; i++) {
                    subjectIdList[i] = subjectList.get(i).getSubjectId();
                }
                sql += " AND q.subjectId IN(";
                for (int i = 0; i < subjectList.size() - 1; i++) {
                    sql += subjectIdList[i] + ",";
                }
                sql += subjectIdList[subjectList.size() - 1] + ")";

            } else {
                sql += " AND q.subjectId=" + subjectId;
            }
            if (quizName != null && !quizName.equalsIgnoreCase("")) {
                sql += " AND s.subjectName like '%" + quizName.toLowerCase().trim() + "%'";
            }
            try {

                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setInt(1, userId);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    QuizLevelDAO quizLevelDAO = new QuizLevelDAO();
                    QuizLevel quizLevel = quizLevelDAO.getQuizLevelById(rs.getInt(1));
                    String quizLevelName = quizLevel.getQuizLevelName();
                    TestTypeDAO testTypeDAO = new TestTypeDAO();
                    TestType testType = testTypeDAO.getTestTypeById(rs.getInt(2));
                    String testTypeName = testType.getTestTypeName();
                    DimensionTypeDAO dimensionTypeDAO = new DimensionTypeDAO();
                    DimensionType dimensionType = dimensionTypeDAO.getDimensionTypeById(rs.getInt(3));
                    String dimensionTypeName = dimensionType.getDimensionTypeName();
                    LessonDAO lessonDAO = new LessonDAO();
                    Lesson lesson = lessonDAO.getLessonById(rs.getInt(4));
                    SubjectDAO subjectDAO = new SubjectDAO();
                    Subject subject = subjectDAO.getSubjectbyId(rs.getInt(5));
                    quizList.add(new Quiz(rs.getInt(6),
                            lesson,
                            subject,
                            rs.getString(7),
                            rs.getInt(1),
                            quizLevelName,
                            rs.getInt(8),
                            rs.getInt(9),
                            rs.getInt(2),
                            testTypeName,
                            rs.getString(10),
                            rs.getInt(11),
                            rs.getInt(3),
                            dimensionTypeName,
                            rs.getBoolean(12)));
                }
            } catch (Exception e) {
            }
        }
        return quizList;
    }

    public static void main(String[] args) throws Exception {
        QuizDAO a = new QuizDAO();
        ArrayList<Quiz> getAllSimulationQuizByUser = a.getAllSimulationQuizByUser(1, 0, "");

        for (Quiz quiz : getAllSimulationQuizByUser) {
            System.out.println(quiz.toString());
        }
    }
}
