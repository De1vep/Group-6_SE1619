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
import model.RegistrationManage;
import model.SubjectCategory;

/**
 *
 * @author Admin
 */
public class SubjectCategoryDAO extends DBContext {

    public ArrayList<SubjectCategory> getAllSubjectCategoryDPV() {
        ArrayList<SubjectCategory> listAllSubjectCategory = new ArrayList<>();
        try {
            String sql = "SELECT sc.subjectCateId, sc.subjectCateName, sc.status FROM CategorySubject AS cs INNER JOIN [Subject] AS s ON cs.subjectId = s.subjectId INNER JOIN SubjectCategory AS sc ON cs.cateId = sc.subjectCateId WHERE sc.status = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SubjectCategory s = new SubjectCategory();
                s.setSubjectCateId(rs.getInt("subjectCateId"));
                s.setSubjectCateName(rs.getString("subjectCateName"));
                s.setStatus(rs.getBoolean("status"));
                listAllSubjectCategory.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAllSubjectCategory;
    }

    public ArrayList<SubjectCategory> GetAllSubjectCategory() {
        ArrayList<SubjectCategory> SubjectCategorys = new ArrayList<>();
        try {
            String sql = "SELECT * FROM SubjectCategory ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SubjectCategory a = new SubjectCategory();
                a.setSubjectCateId(rs.getInt(1));
                a.setSubjectCateName(rs.getString(2));
                a.setStatus(rs.getBoolean(3));
                SubjectCategorys.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(registrationDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SubjectCategorys;
    }

    public ArrayList<SubjectCategory> getAllSubjectCategory() {
        ArrayList<SubjectCategory> listAllSubjectCategory = new ArrayList<>();
        try {
            String sql = "SELECT sc.subjectCateId, sc.subjectCateName, sc.status FROM CategorySubject AS cs INNER JOIN [Subject] AS s ON cs.subjectId = s.subjectId INNER JOIN SubjectCategory AS sc ON cs.cateId = sc.subjectCateId WHERE sc.status = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SubjectCategory s = new SubjectCategory();
                s.setSubjectCateId(rs.getInt("subjectCateId"));
                s.setSubjectCateName(rs.getString("subjectCateName"));
                s.setStatus(rs.getBoolean("status"));
                listAllSubjectCategory.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAllSubjectCategory;
    }

    public ArrayList<SubjectCategory> getSubjectCateBySubject(int subjectId) throws Exception {

        /* Getcategory list of the subject */
        ArrayList<SubjectCategory> categories = new ArrayList<>();
        String sql = "SELECT C.[subjectId]\n"
                + "      ,C.[cateId]\n"
                + "	   ,S.[status]\n"
                + "	   ,S.subjectCateName\n"
                + "  FROM [CategorySubject] C \n"
                + "  INNER JOIN SubjectCategory S\n"
                + "  ON C.cateId = S.subjectCateId WHERE C.subjectId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                categories.add(new SubjectCategory(rs.getInt("cateId"), rs.getString("subjectCateName"), rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return categories;
    }

    //minh
    public ArrayList<SubjectCategory> getRemainSubjectCateBySubject(int subjectId) throws Exception {
        ArrayList<SubjectCategory> remainCategories = new ArrayList<>();
        String sql = " SELECT [subjectCateId]\n"
                + "                      ,[subjectCateName]\n"
                + "                      ,[status]\n"
                + "                 FROM [SubjectCategory]\n"
                + "                  WHERE [subjectCateId] NOT IN (SELECT C.[cateId]\n"
                + "                			FROM [CategorySubject] C\n"
                + "                			INNER JOIN SubjectCategory S\n"
                + "                			ON C.cateId = S.subjectCateId WHERE C.subjectId = ?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                remainCategories.add(new SubjectCategory(rs.getInt("subjectCateId"), rs.getString("subjectCateName"), rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return remainCategories;
    }

    //minh
    public String[] getSubjectCateIdBySubject(int subjectId) throws Exception {

        ArrayList<String> categoryId = new ArrayList<>();
        String sql = "SELECT C.[cateId]\n"
                + "  FROM [CategorySubject] C \n"
                + "  INNER JOIN SubjectCategory S\n"
                + "  ON C.cateId = S.subjectCateId WHERE C.subjectId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                categoryId.add(rs.getString("cateId"));
            }
        } catch (Exception ex) {
            throw ex;
        }

        String[] idString = new String[categoryId.size()];
        return categoryId.toArray(idString);
    }

    public int addCategorySubject(int subjectId, int categoryId) throws Exception {

        /* Prepared statement for executing sql queries */
        String sql = "INSERT INTO dbo.CategorySubject(subjectId,cateId) "
                + "VALUES(?,?)";
        int check = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            stm.setInt(2, categoryId);
            check = stm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
        return check;
    }

    public int deteleCategorySubject(int subjectId, int categoryId) throws Exception {

        String sql = "DELETE FROM dbo.CategorySubject "
                + "WHERE subjectId = ? AND "
                + "cateId = ?";
        int check = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, subjectId);
            stm.setInt(2, categoryId);
            check = stm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
        return check;
    }

    public boolean deleteSubjectCate(int subjectId) {

        String sql = "DELETE FROM [dbo].[CategorySubject]\n"
                + "      WHERE subjectId = ?";
        try {

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<SubjectCategory> getAllStatusSubjectCates() throws Exception {

        ArrayList<SubjectCategory> allCategory = new ArrayList<>();
        String sql = "SELECT [subjectCateId]\n"
                + "      ,[subjectCateName]\n"
                + "      ,[status]\n"
                + "  FROM [SubjectCategory]";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                allCategory.add(new SubjectCategory(rs.getInt("subjectCateId"),
                        rs.getString("subjectCateName"),
                        rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return allCategory;
    }

    public SubjectCategory getSubjectCategoryByIdLoad(int id) {
        try {
            String sql = "SELECT * FROM SubjectCategory Where subjectCateId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SubjectCategory a = new SubjectCategory();
                a.setSubjectCateId(rs.getInt(1));
                a.setSubjectCateName(rs.getString(2));
                a.setStatus(rs.getBoolean(3));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(registrationDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void UpDateSubjectCategorySetting(int id, String name, boolean statuss) {
        try {
            String sql = "UPDATE [dbo].[SubjectCategory]\n"
                    + "   SET [subjectCateName] = ?\n"
                    + "      ,[status] =?\n"
                    + " WHERE subjectCateId = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setBoolean(2, statuss);
            stm.setInt(3, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InsertSubjectCategorySetting(String name, boolean statuss) {
        try {
            String sql = "INSERT INTO [dbo].[SubjectCategory]\n"
                    + "           ([subjectCateName]\n"
                    + "           ,[status]\n"
                    + "           ,[parentCateId])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,NULL)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setBoolean(2, statuss);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
