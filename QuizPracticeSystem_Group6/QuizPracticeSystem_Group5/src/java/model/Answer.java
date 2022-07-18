
package model;


public class Answer {
    private int answerId;   /* Answer Id */
    private int questionId; /* Question's Id */
    private String answerContent;   /* Answer's Content */
    private boolean isCorrect;  /* If answer is correct */
    private boolean status; /* Answer's status */

    public Answer() {
    }

    public Answer(int answerId, int questionId, String answerContent, boolean isCorrect, boolean status) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.answerContent = answerContent;
        this.isCorrect = isCorrect;
        this.status = status;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Answer{" + "answerId=" + answerId + ", questionId=" + questionId + ", answerContent=" + answerContent + ", isCorrect=" + isCorrect + ", status=" + status + '}';
    }
    
    
    
}
