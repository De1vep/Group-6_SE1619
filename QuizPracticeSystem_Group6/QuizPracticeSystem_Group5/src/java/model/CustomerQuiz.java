
package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class CustomerQuiz {

    private int quizTakeId; /*Quiz take id(The attempt)*/
    private int quizId; /*Quiz Id(The real quiz)*/
    private int userId; /*User Id(Taken the quiz)*/
    private int score; /*Attempt Score*/
    private int time; /*Attempt time*/
    private Timestamp submitedAt; /*Time submitted*/
    private boolean status; /*Status*/
    private String quizName; /*Quiz's name*/
    private String subjectName; /*Subject;s name*/
    private String testTypeName; /*Test type's name*/

    public CustomerQuiz() {
    }

    public CustomerQuiz(int quizTakeId, int quizId, int userId, int score, int time, Timestamp submitedAt, boolean status) {
        this.quizTakeId = quizTakeId;
        this.quizId = quizId;
        this.userId = userId;
        this.score = score;
        this.time = time;
        this.submitedAt = submitedAt;
        this.status = status;
    }

    public CustomerQuiz(int quizTakeId, int quizId, int userId, int score, int time, Timestamp submitedAt, boolean status, String quizName, String subjectName, String testTypeName) {
        this.quizTakeId = quizTakeId;
        this.quizId = quizId;
        this.userId = userId;
        this.score = score;
        this.time = time;
        this.submitedAt = submitedAt;
        this.status = status;
        this.quizName = quizName;
        this.subjectName = subjectName;
        this.testTypeName = testTypeName;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTestTypeName() {
        return testTypeName;
    }

    public void setTestTypeName(String testTypeName) {
        this.testTypeName = testTypeName;
    }

    

    public int getQuizTakeId() {
        return quizTakeId;
    }

    public void setQuizTakeId(int quizTakeId) {
        this.quizTakeId = quizTakeId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Timestamp getSubmitedAt() {
        return submitedAt;
    }

    public void setSubmitedAt(Timestamp submitedAt) {
        this.submitedAt = submitedAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public String getDateTaken() {
        Date date = new Date(submitedAt.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateTaken = simpleDateFormat.format(date);
        return dateTaken;
    }
    
    /**
     * Get duration in string
     * @return 
     */
    public String getDurationString(){
        String durationString = "";
        int minute = time / 60;
        int second = time % 60;
        durationString = minute + ":" + second;
        return durationString;
    }

    @Override
    public String toString() {
        return "CustomerQuiz{" + "quizTakeId=" + quizTakeId + ", quizId=" + quizId + ", userId=" + userId + ", score=" + score + ", time=" + time + ", submitedAt=" + submitedAt + ", status=" + status + '}';
    }
    
}
