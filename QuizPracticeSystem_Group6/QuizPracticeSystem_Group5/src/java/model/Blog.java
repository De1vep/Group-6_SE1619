
package model;

import java.sql.Date;


public class Blog {

    private int blogId; /* Blog's Id */
    private String blogTitle;   /* Blog Title */
    private Date created;   /* Blog created date */
    private Date lastEdited;    /* Blog last edited date */
    private User author;    /* Blog's author */
    private String detail;  /* Blog content */
    private String thumbnail; /* Blog thumbnail */
    private Boolean status; /* Blog status */
    private PostCategory postCategory; /* Post Category */

    public Blog() {
    }

    public Blog(int blogId, String blogTitle, Date created, Date lastEdited, User author, String detail, String thumbnail, Boolean status, PostCategory postCategory) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.created = created;
        this.lastEdited = lastEdited;
        this.author = author;
        this.detail = detail;
        this.thumbnail = thumbnail;
        this.status = status;
        this.postCategory = postCategory;
    }


    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public PostCategory getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(PostCategory postCategory) {
        this.postCategory = postCategory;
    }

    @Override
    public String toString() {
        return "Blog{" + "blogId=" + blogId + ", blogTitle=" + blogTitle + ", created=" + created + ", lastEdited=" + lastEdited + ", author=" + author + ", detail=" + detail + ", thumbnail=" + thumbnail + ", status=" + status + ", postCategory=" + postCategory + '}';
    }
    
    
}
