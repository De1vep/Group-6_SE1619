/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Question;
import model.QuestionQuizHandle;
import model.Quiz;
import model.QuizQuizHandle;
import model.User;

/**
 *
 * @author Admin
 */
public class QuizQuizHandleDAO extends DBContext {

    public QuizQuizHandle generateQuiz(ArrayList<Question> questionList, int quizId, User user) throws Exception {
        QuizQuizHandle quiz = new QuizQuizHandle();
        QuestionQuizHandleDAO questionQuizzHandleDAO = new QuestionQuizHandleDAO();
        QuizDAO quizDAO = new QuizDAO();
        Quiz quizInDatabase = quizDAO.getQuizById(quizId);
        for (Question question : questionList) {
            int questionId = question.getQuestionId();
            QuestionQuizHandle questionQH = questionQuizzHandleDAO.generateQuestionById(questionId);//turn a question into question quiz handle
            quiz.addQuestion(questionQH);                                                           //add question to list           
        }

        quiz.setQuiz(quizInDatabase);
        quiz.setTime(quizInDatabase.getQuizDuration());
        quiz.setUser(user);
        return quiz;
    }

    public int getAnsweredQuestion(QuizQuizHandle quiz) throws Exception {
        ArrayList<QuestionQuizHandle> questionList = quiz.getQuestions();
        int count = 0;
        for (QuestionQuizHandle question : questionList) {
            if (question.getAnsweredId() != 0) {
                count += 1;
            }
        }
        return count;
    }

    public QuizQuizHandle getReviewQuiz(int quizTakeId) throws Exception {
        QuestionDAO questionDAO = new QuestionDAO();
        QuizQuizHandle reviewQuiz = new QuizQuizHandle();
        QuizDAO quizDAO = new QuizDAO();
        Quiz quiz = quizDAO.getQuizByQuizTakeId(quizTakeId);
        CustomerQuizDAO customerQuizInterface = new CustomerQuizDAO();

        AnswerDAO answerDAO = new AnswerDAO();
        ArrayList<QuestionQuizHandle> questionList = new ArrayList();
        String sql = "SELECT * FROM [TakeAnswer] WHERE quizTakeId=" + quizTakeId;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question question = questionDAO.getQuestionById(rs.getInt("questionId"));
                ArrayList<Answer> answers = answerDAO.getAnswersByQuenstionId(rs.getInt("questionId"));
                questionList.add(new QuestionQuizHandle(question,
                        answers,
                        rs.getInt("answerId"),
                        rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        }
        reviewQuiz.setQuestions(questionList);
        reviewQuiz.setQuiz(quiz);
        reviewQuiz.setTime(customerQuizInterface.getQuizByTakeQuizId(quizTakeId).getTime());
        return reviewQuiz;
    }

    public static void main(String[] args) {
        try {
            QuestionDAO questionInterface = new QuestionDAO();
            ArrayList<Question> questionList = questionInterface.getQuestionByQuizId(1);
            User account = new UserDAO().checkLogin("Manhnkhe153424@fpt.edu.vn", "123");
            QuestionQuizHandle questionQH = new QuestionQuizHandleDAO().generateQuestionById(questionList.get(0).getQuestionId());
            System.out.println(questionQH.toString());
            QuizQuizHandle a = new QuizQuizHandleDAO().generateQuiz(questionList, 1, account);
        } catch (Exception ex) {
            Logger.getLogger(QuizQuizHandleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
