
package model;

public class QuizLevel {
    private int quizLevelId; /*Quiz Level Id*/
    private String quizLevelName; /*Quiz Level Name*/
    private boolean status; /*Quiz Status*/

    public QuizLevel() {
    }

    public QuizLevel(int quizLevelId, String quizLevelName, boolean status) {
        this.quizLevelId = quizLevelId;
        this.quizLevelName = quizLevelName;
        this.status = status;
    }

    public int getQuizLevelId() {
        return quizLevelId;
    }

    public void setQuizLevelId(int quizLevelId) {
        this.quizLevelId = quizLevelId;
    }

    public String getQuizLevelName() {
        return quizLevelName;
    }

    public void setQuizLevelName(String quizLevelName) {
        this.quizLevelName = quizLevelName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "QuizLevel{" + "quizLevelId=" + quizLevelId + ", quizLevelName=" + quizLevelName + ", status=" + status + '}';
    }

    
}