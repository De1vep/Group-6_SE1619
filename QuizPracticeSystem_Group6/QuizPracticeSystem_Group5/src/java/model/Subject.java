package model;

import java.sql.Date;
import java.util.ArrayList;

public class Subject {

        private int subjectId;
    /* Subject's id */
    private String subjectName;
    /* Subject's Name */
    private String description;
    /* Subject's Description */
    private String thumbnail;
    /* Subject's Thumbnail */
    private boolean featuredSubject;
    /* Is featuredSubject or not */
    private boolean status;
    /* Blog created date */
    private Date created;
    /* Blog last edited date */
    private Date lastEdited;
    /* Subject's Status */
    private ArrayList<Dimension> dimensions;
    /* Subject's PricePackage */
    private ArrayList<PricePackage> pricePackage;
    /* Subject's dimensions */
    private ArrayList<SubjectCategory> categories;/* Subject's categories */

    public Subject(int subjectId, String subjectName, String description, String thumbnail, boolean featuredSubject, boolean status, Date created, Date lastEdited, ArrayList<Dimension> dimensions, ArrayList<PricePackage> pricePackage, ArrayList<SubjectCategory> categories) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.description = description;
        this.thumbnail = thumbnail;
        this.featuredSubject = featuredSubject;
        this.status = status;
        this.created = created;
        this.lastEdited = lastEdited;
        this.dimensions = dimensions;
        this.pricePackage = pricePackage;
        this.categories = categories;
    }
    
    public Subject() {
    }

    public Subject(int subjectId, String subjectName, String description, String thumbnail, boolean featuredSubject, boolean status, ArrayList<Dimension> dimensions, ArrayList<SubjectCategory> categories) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.description = description;
        this.thumbnail = thumbnail;
        this.featuredSubject = featuredSubject;
        this.status = status;
        this.dimensions = dimensions;
        this.categories = categories;
    }

    public Subject(int subjectId, String subjectName, String description, String thumbnail, boolean featuredSubject, boolean status) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.description = description;
        this.thumbnail = thumbnail;
        this.featuredSubject = featuredSubject;
        this.status = status;
    }

    public Subject(int subjectId, String subjectName, String description, String thumbnail, Boolean featuredSubject, Boolean status, ArrayList<Dimension> dimensions, ArrayList<PricePackage> pricePackage, ArrayList<SubjectCategory> categories) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.description = description;
        this.thumbnail = thumbnail;
        this.featuredSubject = featuredSubject;
        this.status = status;
        this.dimensions = dimensions;
        this.pricePackage = pricePackage;
        this.categories = categories;
    }
    

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean isFeaturedSubject() {
        return featuredSubject;
    }

    public void setFeaturedSubject(boolean featuredSubject) {
        this.featuredSubject = featuredSubject;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<Dimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(ArrayList<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    public ArrayList<SubjectCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<SubjectCategory> categories) {
        this.categories = categories;
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

    public ArrayList<PricePackage> getPricePackage() {
        return pricePackage;
    }

    public void setPricePackage(ArrayList<PricePackage> pricePackage) {
        this.pricePackage = pricePackage;
    }

    @Override
    public String toString() {
        return "Subject{" + "subjectId=" + subjectId + ", subjectName=" + subjectName + ", description=" + description + ", thumbnail=" + thumbnail + ", featuredSubject=" + featuredSubject + ", status=" + status + ", created=" + created + ", lastEdited=" + lastEdited + ", dimensions=" + dimensions + ", pricePackage=" + pricePackage + ", categories=" + categories + '}';
    }
    
}
