/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HUY
 */
public class LessonAdmin {
    private int lessonId; /*Lesson id*/
    private Subject subject; /*Subject Id*/
    private String lessonName; /*Lesson Name*/
    private int lessonOrder; /*Lesson order number*/
    private LessonType lessonType; /*Lesson type Id*/
    private String videoLink; /*Lesson video link*/
    private String content; /*Lesson content*/
    private boolean status; /*Lesson status*/

    public LessonAdmin() {
    }

    public LessonAdmin(int lessonId, Subject subjectId, String lessonName, int lessonOrder, LessonType lessonType, String videoLink, String content, boolean status) {
        this.lessonId = lessonId;
        this.subject = subjectId;
        this.lessonName = lessonName;
        this.lessonOrder = lessonOrder;
        this.lessonType = lessonType;
        this.videoLink = videoLink;
        this.content = content;
        this.status = status;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public int getLessonOrder() {
        return lessonOrder;
    }

    public void setLessonOrder(int lessonOrder) {
        this.lessonOrder = lessonOrder;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lesson{" + "lessonId=" + lessonId + ", subject=" + subject.getSubjectName() + ", lessonName=" + lessonName + ", lessonOrder=" + lessonOrder + ", lessonType=" + lessonType.getLessonTypeName() + ", videoLink=" + videoLink + ", content=" + content + ", status=" + status + '}';
    }
}
