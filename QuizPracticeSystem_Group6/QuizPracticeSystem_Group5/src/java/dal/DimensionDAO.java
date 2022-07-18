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
import model.Dimension;

/**
 *
 * @author Admin
 */
public class DimensionDAO extends DBContext {
    
    public ArrayList<Dimension> getAllDimensionDPV() {
        ArrayList<Dimension> listDimension = new ArrayList<>();
        try {

            String sql = "SELECT * FROM [Dimension] WHERE status = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Dimension s = new Dimension();
                s.setDescription(rs.getString("description"));
                s.setDimensionId(rs.getInt("dimensionId"));
                s.setDimensionName(rs.getString("dimensionName"));
                s.setDimensionTypeId(rs.getInt("dimensionTypeId"));
                s.setStatus(rs.getBoolean("status"));
                s.setSubjectId(rs.getInt("subjectId"));
                listDimension.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listDimension;
    }

    public ArrayList<Dimension> getDimensionBySubjectMinh(int subjectId) throws Exception {

        /* Get dimension list of the subject */
        ArrayList<Dimension> dimensions = new ArrayList<>();
        String sql = "SELECT S.[subjectId]\n"
                + "      ,D.[dimensionId]\n"
                + "      ,D.[subjectId]\n"
                + "      ,D.[dimensionTypeId]\n"
                + "      ,D.[dimensionName]\n"
                + "      ,D.[description]\n"
                + "      ,D.[status]\n"
                + "	  ,DT.[dimensionTypeName]\n"
                + "  FROM [Subject] S \n"
                + "  INNER JOIN [Dimension] D ON S.subjectId = D.subjectId \n"
                + "  INNER JOIN DimensionType DT ON DT.dimensionTypeId = D.dimensionTypeId\n"
                + "  WHERE S.subjectId =" + subjectId;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                dimensions.add(new Dimension(rs.getInt("dimensionId"),
                        subjectId,
                        rs.getInt("dimensionTypeId"),
                        rs.getString("dimensionTypeName"),
                        rs.getString("dimensionName"),
                        rs.getString("description"),
                        rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return dimensions;
    }
    
    public ArrayList<Dimension> getDimensionBySubject(int subjectId) throws Exception {

        /* Get dimension list of the subject */
        ArrayList<Dimension> dimensions = new ArrayList<>();
        String sql = "SELECT S.[subjectId]\n"
                + "      ,D.[dimensionId]\n"
                + "      ,D.[subjectId]\n"
                + "      ,D.[dimensionTypeId]\n"
                + "      ,D.[dimensionName]\n"
                + "      ,D.[description]\n"
                + "      ,D.[status]\n"
                + "	  ,DT.[dimensionTypeName]\n"
                + "  FROM [QuizSystem].[dbo].[Subject] S \n"
                + "  INNER JOIN [QuizSystem].[dbo].[Dimension] D ON S.subjectId = D.subjectId \n"
                + "  INNER JOIN [QuizSystem].[dbo].DimensionType DT ON DT.dimensionTypeId = D.dimensionTypeId\n"
                + "  WHERE S.subjectId =" + subjectId;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                dimensions.add(new Dimension(rs.getInt("dimensionId"),
                        subjectId,
                        rs.getInt("dimensionTypeId"),
                        rs.getString("dimensionTypeName"),
                        rs.getString("dimensionName"),
                        rs.getString("description"),
                        rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return dimensions;
    }

    public ArrayList<Dimension> getAllDimension() throws Exception {
        ArrayList<Dimension> listDimension = new ArrayList();
        String sql = "SELECT  [dimensionId]\n"
                + "      ,[subjectId]\n"
                + "      ,[dimensionTypeId]\n"
                + "      ,[dimensionName]\n"
                + "      ,[description]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[Dimension]";
        /* Get the dimension */
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            /* Get information from resultset and add it to arrayList */
            while (rs.next()) {
                int dimensionId = rs.getInt("dimensionId");
                int subjectId = rs.getInt("subjectId");
                int dimensionTypeId = rs.getInt("dimensionTypeId");
                String dimensionName = rs.getString("dimensionName");
                String description = rs.getString("description");
                Boolean status = rs.getBoolean("status");

                listDimension.add(new Dimension(dimensionId, subjectId, dimensionTypeId, dimensionName, dimensionName, description, status));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return listDimension;
    }
    
    //===========================Minh inter 3======================
    public ArrayList<Dimension> getListSubjectByKeyword(int page, int page_size, ArrayList<Dimension> ListSubjectByKeyword) {
        int start, end;
        start = (page - 1) * page_size;
        if (page * page_size > ListSubjectByKeyword.size()) {
            end = ListSubjectByKeyword.size();
        } else {
            end = page * page_size;
        }
        ArrayList<Dimension> listBlogPaging = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listBlogPaging.add(ListSubjectByKeyword.get(i));
        }
        return listBlogPaging;
    }

    
    public int deleteDimension(int dimensionId) throws Exception {
        String sql = " DELETE FROM [Dimension] WHERE dimensionId = ?";
        int check = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, dimensionId);
            check = stm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
        return check;
    }
    
    public int editDimension(int dimensionId, Dimension updateDimension) throws Exception {
        String sql = " UPDATE [Dimension] set dimensionTypeId = ?, dimensionName = ?,  [description] = ? where dimensionId = ?";
        int check = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, updateDimension.getDimensionTypeId());
            stm.setString(2, updateDimension.getDimensionName());
            stm.setString(3, updateDimension.getDescription());
            stm.setInt(4, dimensionId);
            check = stm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
        return check;
    }
    
    public int addDimension(Dimension updateDimension)throws Exception{
        String sql = "INSERT INTO dbo.Dimension(dimensionName,dimensionTypeId,subjectId,[description],[status]) VALUES(?,?,?,?,?);";
        int check = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, updateDimension.getDimensionName());
            stm.setInt(2, updateDimension.getDimensionTypeId());
            stm.setInt(3, updateDimension.getSubjectId());
            stm.setString(4, updateDimension.getDescription());
            stm.setBoolean(5, updateDimension.isStatus());
            check = stm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
        return check;
    }
    
    public int deleteDimensionbySubID(int subjectId) throws Exception {
        String sql = " DELETE FROM [Dimension] WHERE subjectId = ?";
        int check = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            check = stm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
        return check;
    }
}
