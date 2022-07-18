
package model;


public class DimensionType {

    private int dimensionTypeId; /*Dimension type id*/
    private String dimensionTypeName; /*Dimension type name*/
    private boolean status; /*Dimension Type Status*/

    public DimensionType() {
    }

    public DimensionType(int dimensionTypeId, String dimensionTypeName, boolean status) {
        this.dimensionTypeId = dimensionTypeId;
        this.dimensionTypeName = dimensionTypeName;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DimensionType{" + "dimensionTypeId=" + dimensionTypeId + ", dimensionTypeName=" + dimensionTypeName + ", status=" + status + '}';
    }

    

}
