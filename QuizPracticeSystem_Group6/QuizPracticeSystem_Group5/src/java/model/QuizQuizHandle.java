
package model;

import dal.QuestionQuizHandleDAO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizQuizHandle {
    private Quiz quiz; /*Quiz entiry*/
    private ArrayList<QuestionQuizHandle> questions; /*List of question*/
    private User user; /*User Entity(taken the quiz)*/
    private int Time;   /*Quiz time taken(in second)*/

    public QuizQuizHandle() {
    }

    public QuizQuizHandle(Quiz quiz, ArrayList<QuestionQuizHandle> questions, User user, int Time) {
        this.quiz = quiz;
        this.questions = questions;
        this.user = user;
        this.Time = Time;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public ArrayList<QuestionQuizHandle> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<QuestionQuizHandle> questions) {
        this.questions = questions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int Time) {
        this.Time = Time;
    }

    public QuestionQuizHandle getQuestionByNumber(int questionNumber){
        return questions.get(questionNumber-1);
    }

    /**
     * Add question
     * @param question 
     */
    public void addQuestion(QuestionQuizHandle question) {
        questions.add(question);
    }

    /**
     * Remove question
     * @param question 
     */
    public void removeQuestion(QuestionQuizHandle question) {
        questions.remove(question);
    }
    
    @Override
    public String toString() {
        return "QuizQuizHandle{" + "quiz=" + quiz + ", questions=" + questions + ", user=" + user + ", Time=" + Time + '}';
    }

    public static void main(String[] args) {
        try {
//            QuestionQuizHandle questionQH = new QuestionQuizHandleDAO().generateQuestionById(1);//turn a question into question quiz handle
//            System.out.println(questionQH.toString());
//            System.out.println("=================");
            QuizQuizHandle a = new QuizQuizHandle();
            ArrayList<Answer> answerList = new ArrayList<>();
            answerList.add(new Answer(1, 1, "bbb", true, true));
            a.addQuestion(new QuestionQuizHandle(new Question(1, 1, 1, 5, "Watashi", "https://www.youtube.com/embed/0PQ9mgc55ic", "nihongo a", true), answerList, 0, true));
            for (QuestionQuizHandle b : a.getQuestions()) {
                System.out.println(b.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(QuizQuizHandle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

}
