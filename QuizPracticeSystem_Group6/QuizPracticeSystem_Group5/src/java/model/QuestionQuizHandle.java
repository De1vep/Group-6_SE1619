
package model;

import java.util.ArrayList;


public class QuestionQuizHandle {
    private Question question; /*Question entity*/
    private ArrayList<Answer> answerList; /*Arraylist Answer of Question*/
    private int answeredId; /*Answered answer id*/
    private boolean marked; /*Question Marked/Not*/

    public QuestionQuizHandle() {
    }

    public QuestionQuizHandle(Question question, ArrayList<Answer> answerList, int answeredId, boolean marked) {
        this.question = question;
        this.answerList = answerList;
        this.answeredId = answeredId;
        this.marked = marked;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ArrayList<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<Answer> answerList) {
        this.answerList = answerList;
    }

    public int getAnsweredId() {
        return answeredId;
    }

    public void setAnsweredId(int answeredId) {
        this.answeredId = answeredId;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    @Override
    public String toString() {
        return "QuestionQuizHandle{" + "question=" + question + ", answerList=" + answerList + ", answeredId=" + answeredId + ", marked=" + marked + '}';
    }

    

}
