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
import model.DimensionType;

/**
 *
 * @author Admin
 */
public class DimensionTypeDAO extends DBContext {

    public DimensionType getDimensionTypeById(int dimensionTypeId) throws Exception {

        String sql = "SELECT * FROM [DimensionType] WHERE dimensionTypeId=" + dimensionTypeId;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new DimensionType(rs.getInt("dimensionTypeId"),
                        rs.getString("dimensionTypeName"),
                        rs.getBoolean("status"));
            }
        } catch (Exception ex) {
            throw ex;
        }

        return null;
    }

    //minh
    public ArrayList<DimensionType> getAllDimensionTypes() throws Exception {
        ArrayList<DimensionType> dimensionTypesList = new ArrayList<>();
        String sql = "SELECT [dimensionTypeId]\n"
                + "      ,[dimensionTypeName]\n"
                + "      ,[status]\n"
                + "  FROM [DimensionType]\n"
                + "  WHERE status = 1 ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                dimensionTypesList.add(new DimensionType(rs.getInt("dimensionTypeId"),
                        rs.getString("dimensionTypeName"),
                        rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return dimensionTypesList;
    }

    public ArrayList<DimensionType> getAllStatusDimensionTypes() throws Exception {
        ArrayList<DimensionType> dimensionTypesList = new ArrayList<>();
        String sql = "SELECT [dimensionTypeId]\n"
                + "      ,[dimensionTypeName]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[DimensionType]";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                dimensionTypesList.add(
                        new DimensionType(rs.getInt("dimensionTypeId"),
                                rs.getString("dimensionTypeName"),
                                rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return dimensionTypesList;
    }

    public DimensionType getDimensionTypeByIdLoad(int id) {

        try {
            String sql = "SELECT [dimensionTypeId]\n"
                    + "      ,[dimensionTypeName]\n"
                    + "      ,[status]\n"
                    + "  FROM [DimensionType]\n"
                    + "  WHERE dimensionTypeId =  " + id;

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new DimensionType(rs.getInt("dimensionTypeId"),
                        rs.getString("dimensionTypeName"),
                        rs.getBoolean("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DimensionTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void UpDateDimensionTypeSetting(int id, String name, boolean statuss) {
        try {
            String sql = "UPDATE [dbo].[DimensionType]\n"
                    + "   SET [dimensionTypeName] = ?\n"
                    + "      ,[status] = ?\n"
                    + " WHERE dimensionTypeId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setBoolean(2, statuss);
            stm.setInt(3, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InsertDimensionTypeSetting(String name, boolean statuss) {
        try {
            String sql = "INSERT INTO [dbo].[DimensionType]\n"
                    + "           ([dimensionTypeName]\n"
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
