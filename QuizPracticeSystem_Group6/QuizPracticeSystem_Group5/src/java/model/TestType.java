
package model;


public class TestType {
    private int testTypeId; /*Test type id*/
    private String testTypeName; /*Test type name*/
    private boolean status; /*Test type status*/

    public TestType() {
    }

    public TestType(int testTypeId, String testTypeName, boolean status) {
        this.testTypeId = testTypeId;
        this.testTypeName = testTypeName;
        this.status = status;
    }

    public int getTestTypeId() {
        return testTypeId;
    }

    public void setTestTypeId(int testTypeId) {
        this.testTypeId = testTypeId;
    }

    public String getTestTypeName() {
        return testTypeName;
    }

    public void setTestTypeName(String testTypeName) {
        this.testTypeName = testTypeName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TestType{" + "testTypeId=" + testTypeId + ", testTypeName=" + testTypeName + ", status=" + status + '}';
    }
    
    
    
    
}
