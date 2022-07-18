
package model;


public class Dimension {
    private int dimensionId; /*Dimension Id*/
    private int subjectId; /*Subject's Id*/
    private int dimensionTypeId; /*Dimension Type Id*/
    private String dimensionTypeName; /*Dimension Type Name*/
    private String dimensionName; /*Dimension Name*/
    private String description; /*Dimension Description*/
    private boolean status; /*Dimension Status*/

    public Dimension() {
    }

    public Dimension(int dimensionId, int subjectId, int dimensionTypeId, String dimensionTypeName, String dimensionName, String description, boolean status) {
        this.dimensionId = dimensionId;
        this.subjectId = subjectId;
        this.dimensionTypeId = dimensionTypeId;
        this.dimensionTypeName = dimensionTypeName;
        this.dimensionName = dimensionName;
        this.description = description;
        this.status = status;
    }

    public int getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(int dimensionId) {
        this.dimensionId = dimensionId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Dimension{" + "dimensionId=" + dimensionId + ", subjectId=" + subjectId + ", dimensionTypeId=" + dimensionTypeId + ", dimensionTypeName=" + dimensionTypeName + ", dimensionName=" + dimensionName + ", description=" + description + ", status=" + status + '}';
    }

    
    
}
