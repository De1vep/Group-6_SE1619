
package model;


public class PostCategory {
    private int postCateId; /*Post category Id*/
    private String postCateName; /*Post category name*/
    private boolean status; /*Post category status*/

    public PostCategory() {
    }

    public PostCategory(int postCateId, String postCateName, boolean status) {
        this.postCateId = postCateId;
        this.postCateName = postCateName;
        this.status = status;
    }

    public int getPostCateId() {
        return postCateId;
    }

    public void setPostCateId(int postCateId) {
        this.postCateId = postCateId;
    }

    public String getPostCateName() {
        return postCateName;
    }

    public void setPostCateName(String postCateName) {
        this.postCateName = postCateName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PostCategory{" + "postCateId=" + postCateId + ", postCateName=" + postCateName + ", status=" + status + '}';
    }
    
    
    
    
    
    
}
