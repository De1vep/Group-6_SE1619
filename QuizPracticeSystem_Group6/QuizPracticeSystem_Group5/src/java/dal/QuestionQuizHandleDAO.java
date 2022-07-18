/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Question;
import model.QuestionQuizHandle;

/**
 *
 * @author Admin
 */
public class QuestionQuizHandleDAO extends DBContext{
    public QuestionQuizHandle generateQuestionById(int questionId) throws Exception {
        QuestionDAO questionDAO = new QuestionDAO();
        AnswerDAO answerDAO = new AnswerDAO();
        Question question = questionDAO.getQuestionById(questionId);                        //get question
        ArrayList<Answer> answers = answerDAO.getAnswersByQuenstionId(questionId);          //get question's answer list
        return new QuestionQuizHandle(question, answers, 0, false);                         //constructor(question,question's answers list, user's answerd id,marked)
    }
    
    public Answer getRightAnswer(QuestionQuizHandle question) throws Exception {
        ArrayList<Answer> answerList = question.getAnswerList();
        for (Answer answer : answerList) {
            if (answer.isIsCorrect()) {
                return answer;
            }
        }
        return null;
    }
    
    public void markQuestion(QuestionQuizHandle question) throws Exception {
        if (question.isMarked()) {
            question.setMarked(false);
        } else {
            question.setMarked(true);
        }
    }
    
    public static void main(String[] args) {
        try {
            QuestionQuizHandle a = new QuestionQuizHandleDAO().generateQuestionById(1);
            System.out.println(a.toString());
        } catch (Exception ex) {
            Logger.getLogger(QuestionQuizHandleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
