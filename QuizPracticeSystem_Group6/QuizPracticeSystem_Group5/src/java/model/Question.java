
package model;

import java.util.ArrayList;


public class Question {

    private int questionId; /*Question ID*/
    private int subjectId; /*Subject ID*/
    private int dimensionId; /*Dimension ID*/
    private int lessonId; /*Lesson ID*/
    private String content; /*Question Content*/
    private String media; /*Question Media*/
    private String explanation; /*Question Explanation*/
    private boolean status; /*Question Status*/
    private ArrayList<Answer> answers = new ArrayList<>(); /*Question answers*/

    public Question() {
    }

    public Question(int questionId, int subjectId, int dimensionId, int lessonId, String content, String media, String explanation, boolean status) {
        this.questionId = questionId;
        this.subjectId = subjectId;
        this.dimensionId = dimensionId;
        this.lessonId = lessonId;
        this.content = content;
        this.media = media;
        this.explanation = explanation;
        this.status = status;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(int dimensionId) {
        this.dimensionId = dimensionId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
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

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" + "questionId=" + questionId + ", subjectId=" + subjectId + ", dimensionId=" + dimensionId + ", lessonId=" + lessonId + ", content=" + content + ", media=" + media + ", explanation=" + explanation + ", status=" + status + ", answers=" + answers + '}';
    }
    
    
    
    

}
