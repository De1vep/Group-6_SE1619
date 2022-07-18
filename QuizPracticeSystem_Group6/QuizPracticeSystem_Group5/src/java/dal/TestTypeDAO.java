/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TestType;

/**
 *
 * @author Admin
 */
public class TestTypeDAO extends DBContext {

    public TestType getTestTypeById(int testTypeId) throws Exception {

        String sql = "SELECT * FROM [TestType] WHERE testTypeId =" + testTypeId;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new TestType(rs.getInt("testTypeId"),
                        rs.getString("testTypeName"),
                        rs.getBoolean("status"));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return null;
    }

    public ArrayList<TestType> getAllStatusTestTypes() throws Exception {

        TestType testType = null;
        ArrayList<TestType> testTypeList = new ArrayList<>();
        String sql = "SELECT [testTypeId]\n"
                + "      ,[testTypeName]\n"
                + "      ,[status]\n"
                + "  FROM [TestType]";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                testType = new TestType(rs.getInt("testTypeId"),
                        rs.getString("testTypeName"),
                        rs.getBoolean("status"));
                testTypeList.add(testType);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return testTypeList;
    }

    public TestType getTestTypeByIdLoad(int id) {

        try {
            String sql = "SELECT * FROM [TestType] WHERE testTypeId =" + id;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new TestType(rs.getInt("testTypeId"),
                        rs.getString("testTypeName"),
                        rs.getBoolean("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void UpDateTestTypeSetting(int id, String name, boolean statuss) {
        try {
            String sql = "UPDATE [dbo].[TestType]\n"
                    + "   SET [testTypeName] = ?\n"
                    + "      ,[status] = ?\n"
                    + " WHERE testTypeId = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setBoolean(2, statuss);
            stm.setInt(3, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InsertTestTypeSetting(String name, boolean statuss) {
        try {
            String sql = "INSERT INTO [dbo].[TestType]\n"
                    + "           ([testTypeName]\n"
                    + "           ,[status])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setBoolean(2, statuss);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
