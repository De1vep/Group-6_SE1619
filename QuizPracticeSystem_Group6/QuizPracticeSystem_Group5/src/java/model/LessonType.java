
package model;


public class LessonType {
    private int lessonTypeId; /*Lesson type id*/
    private String lessonTypeName; /*Lesson type name*/
    private boolean status; /*Lesson Status*/

    public LessonType() {
    }

    public LessonType(int lessonTypeId, String lessonTypeName, boolean status) {
        this.lessonTypeId = lessonTypeId;
        this.lessonTypeName = lessonTypeName;
        this.status = status;
    }

    public int getLessonTypeId() {
        return lessonTypeId;
    }

    public void setLessonTypeId(int lessonTypeId) {
        this.lessonTypeId = lessonTypeId;
    }

    public String getLessonTypeName() {
        return lessonTypeName;
    }

    public void setLessonTypeName(String lessonTypeName) {
        this.lessonTypeName = lessonTypeName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LessonType{" + "lessonTypeId=" + lessonTypeId + ", lessonTypeName=" + lessonTypeName + ", status=" + status + '}';
    }

    
    
}
