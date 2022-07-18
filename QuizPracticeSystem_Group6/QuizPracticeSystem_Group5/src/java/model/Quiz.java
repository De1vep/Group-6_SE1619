
package model;


public class Quiz {
    private int quizId; /*Quiz ID*/
    private Lesson lesson; /*Lesson Entity*/
    private Subject subject; /*Subject Entity*/
    private String quizName; /*Quiz Name*/
    private int quizLevelId; /*Quiz Level ID*/
    private String quizLevelName; /*Quiz Level Name*/
    private int quizDuration; /*Quiz duration In second*/
    private int passRate; /*Quiz required passrate*/
    private int testTypeId; /*Test type ID*/
    private String testTypeName; /*Test Type Name*/
    private String description; /*Quiz Description*/
    private int numberQuestion; /*Quiz's number of question*/
    private int dimensionTypeId; /*Quiz Dimension Type ID*/
    private String dimensionTypeName; /*Quiz Dimension Type Name*/
    private Boolean status; /*Quiz Status*/

    public Quiz() {
    }

    public Quiz(int quizId, Lesson lesson, Subject subject, String quizName, int quizLevelId, String quizLevelName, int quizDuration, int passRate, int testTypeId, String testTypeName, String description, int numberQuestion, int dimensionTypeId, String dimensionTypeName, Boolean status) {
        this.quizId = quizId;
        this.lesson = lesson;
        this.subject = subject;
        this.quizName = quizName;
        this.quizLevelId = quizLevelId;
        this.quizLevelName = quizLevelName;
        this.quizDuration = quizDuration;
        this.passRate = passRate;
        this.testTypeId = testTypeId;
        this.testTypeName = testTypeName;
        this.description = description;
        this.numberQuestion = numberQuestion;
        this.dimensionTypeId = dimensionTypeId;
        this.dimensionTypeName = dimensionTypeName;
        this.status = status;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
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

    public int getQuizDuration() {
        return quizDuration;
    }

    public void setQuizDuration(int quizDuration) {
        this.quizDuration = quizDuration;
    }

    public int getPassRate() {
        return passRate;
    }

    public void setPassRate(int passRate) {
        this.passRate = passRate;
    }

    public int getTestTypeId() {
        return testTypeId;
    }

    public void setTestTypeId(int testTypeId) {
        this.testTypeId = testTypeId;
    }

    public String getTestTypeName() {
        return testTypeName;
    }

    public void setTestTypeName(String testTypeName) {
        this.testTypeName = testTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public int getDimensionTypeId() {
        return dimensionTypeId;
    }

    public void setDimensionTypeId(int dimensionTypeId) {
        this.dimensionTypeId = dimensionTypeId;
    }

    public String getDimensionTypeName() {
        return dimensionTypeName;
    }

    public void setDimensionTypeName(String dimensionTypeName) {
        this.dimensionTypeName = dimensionTypeName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Quiz{" + "quizId=" + quizId + ", lesson=" + lesson + ", subject=" + subject + ", quizName=" + quizName + ", quizLevelId=" + quizLevelId + ", quizLevelName=" + quizLevelName + ", quizDuration=" + quizDuration + ", passRate=" + passRate + ", testTypeId=" + testTypeId + ", testTypeName=" + testTypeName + ", description=" + description + ", numberQuestion=" + numberQuestion + ", dimensionTypeId=" + dimensionTypeId + ", dimensionTypeName=" + dimensionTypeName + ", status=" + status + '}';
    }
    
    public String getDurationStringg(){
        String durationString = "";
        int minute = getQuizDuration() / 60;
        int second = getQuizDuration() % 60;
        durationString = minute + ":" + second;
        return durationString;
    }
    
}
