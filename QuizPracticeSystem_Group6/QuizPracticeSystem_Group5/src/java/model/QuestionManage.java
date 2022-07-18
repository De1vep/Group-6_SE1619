
package model;


public class QuestionManage {
    private int questionId; /*Question ID*/
    private String subjectName; /*Subject Name*/
    private String dimensionName; /*Question Dimension Name*/
    private String lessonName; /*Question Lesson name*/
    private String content; /*Question content*/
    private String media; /*Question media*/
    private String explanation; /*Question Explaination*/
    private boolean status; /*Question status*/

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "QuestionManage{" + "questionId=" + questionId + ", subjectName=" + subjectName + ", dimensionName=" + dimensionName + ", lessonName=" + lessonName + ", content=" + content + ", media=" + media + ", explanation=" + explanation + ", status=" + status + '}';
    }

   
    
    
}
