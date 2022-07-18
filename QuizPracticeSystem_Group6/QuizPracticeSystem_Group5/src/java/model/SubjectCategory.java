
package model;


public class SubjectCategory {
    private int subjectCateId; /*Subject category id*/
    private String subjectCateName; /*Subject category name*/
    private boolean status; /*Subject category status*/

    public SubjectCategory() {
    }

    public SubjectCategory(int subjectCateId, String subjectCateName, boolean status) {
        this.subjectCateId = subjectCateId;
        this.subjectCateName = subjectCateName;
        this.status = status;
    }

    public int getSubjectCateId() {
        return subjectCateId;
    }

    public void setSubjectCateId(int subjectCateId) {
        this.subjectCateId = subjectCateId;
    }

    public String getSubjectCateName() {
        return subjectCateName;
    }

    public void setSubjectCateName(String subjectCateName) {
        this.subjectCateName = subjectCateName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SubjectCategory{" + "subjectCateId=" + subjectCateId + ", subjectCateName=" + subjectCateName + ", status=" + status + '}';
    }

    
}
