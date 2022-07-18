/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Subject;
import model.SubjectCategory;
import model.User;

/**
 *
 * @author DPV
 */
public class SubjectDAO extends DBContext {
    //========MANHDSZ==============
    public ArrayList<Subject> getAllSubject() throws Exception {
        ArrayList<Subject> Subjects = new ArrayList<>();
        String sql = "SELECT * FROM [subject]";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject a = new Subject();
                a.setSubjectId(rs.getInt(1));
                a.setSubjectName(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setThumbnail(rs.getString(4));
                a.setFeaturedSubject(rs.getBoolean(5));
                a.setStatus(rs.getBoolean(6));
                Subjects.add(a);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return Subjects;
    }
    
    public Subject getSubjectbyId(int subjectId) throws Exception {
        Subject subjectById = null;
        DimensionDAO dimensionDAO = new DimensionDAO();
        SubjectCategoryDAO subjectCateDAO = new SubjectCategoryDAO();

        String sqlSubject = "SELECT * FROM [Subject] WHERE [subjectId] = ?";

        /* Get the subject */
        try {
            PreparedStatement stm = connection.prepareStatement(sqlSubject);
            stm.setInt(1, subjectId);
            ResultSet rs = stm.executeQuery();
            /* Get information from resultset and set it to the created pointer */
            if (rs.next()) {
                int subjectIdResult = rs.getInt("subjectId");
                String subjectName = rs.getString("subjectName");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                Boolean featuredSubject = rs.getBoolean("featuredSubject");
                Boolean status = rs.getBoolean("status");

                subjectById = new Subject(subjectId, subjectName, description,
                        thumbnail, featuredSubject, status,
                        dimensionDAO.getDimensionBySubject(subjectId),
                        subjectCateDAO.getSubjectCateBySubject(subjectId));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return subjectById;
    }
    
    //==============================MINHNGU===========
    
    public boolean insertSubjectExpert(int sid, int ExpertId, boolean status) {
        status = true;
        String sql = "INSERT INTO [dbo].[SubjectExpert]\n"
                + "           ([subjectId]\n"
                + "           ,[userId]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        try {

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setInt(2, ExpertId);
            stm.setBoolean(3, status);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // insert

    
    public boolean insertSubject(String subname, String description, String thumbnail, boolean feature, boolean status) {

        String sql = "INSERT INTO [dbo].[Subject]\n"
                + "           ([subjectName]\n"
                + "           ,[description]\n"
                + "           ,[thumbnail]\n"
                + "           ,[featuredSubject]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subname);
            stm.setString(2, description);
            stm.setString(3, thumbnail);
            stm.setBoolean(4, feature);
            stm.setBoolean(5, status);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
//  phan trang all for admin

    public ArrayList<Subject> getListSubjectByPagination(int page, int page_size) {
        ArrayList<Subject> listSubjectByPagination = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Subject "
                    + " WHERE status = 1 ORDER BY subjectId ASC OFFSET (?-1)*? ROW FETCH NEXT ? ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, page_size);
            stm.setInt(3, page_size);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                listSubjectByPagination.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSubjectByPagination;
    }
//  phan trang all for Expert

    public ArrayList<Subject> getListSubjectByPaginationEX(int page, int page_size, int userId) {
        ArrayList<Subject> listSubjectByPagination = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Subject a Full join SubjectExpert b\n"
                    + "on a.subjectId = b.subjectId"
                    + " WHERE a.status = 1 and userId = ? ORDER BY a.subjectId ASC OFFSET (?-1)*? ROW FETCH NEXT ? ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, page);
            stm.setInt(3, page_size);
            stm.setInt(4, page_size);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                listSubjectByPagination.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSubjectByPagination;
    }

    public int countSubject() {
        try {
            String sql = "SELECT COUNT(*) FROM Subject WHERE status = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }


    public ArrayList<Subject> getFeatureSubject() {
        ArrayList<Subject> listFeatureSubject = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Subject WHERE featuredSubject = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                listFeatureSubject.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listFeatureSubject;
    }

    public ArrayList<Subject> getSujbectByName(String subjectname) {
        ArrayList<Subject> SubjectList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Subject";
            if (subjectname != null) {
                sql = sql.concat("WHERE subjectName like ?");
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + subjectname + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                SubjectList.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SubjectList;
    }

    public ArrayList<Subject> getSujbectByCategory(String subjectCategory) {
        ArrayList<Subject> SubjectListByCategory = new ArrayList<>();
        try {
            String sql = "SELECT s.subjectId,s.subjectName,s.description,s.thumbnail,s.featuredSubject,s.status FROM ((Subject s INNER JOIN CategorySubject c \n"
                    + "ON s.subjectId = c. subjectId) INNER JOIN SubjectCategory sc\n"
                    + "ON c.cateId = sc.subjectCateId)";
            if (subjectCategory != null) {
                sql = sql.concat("Where sc.subjectCateName = ?");
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subjectCategory);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                SubjectListByCategory.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SubjectListByCategory;
    }

    public ArrayList<Subject> getSujbectBySubjectStatus(String subjectStatus) {
        ArrayList<Subject> SubjectListByStatus = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Subject";
            if (subjectStatus.equalsIgnoreCase("true") || subjectStatus.equalsIgnoreCase("false")) {
                sql = sql.concat("Where status = ?");
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subjectStatus);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                SubjectListByStatus.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SubjectListByStatus;
    }

    public ArrayList<Subject> getFiterByCategoryAndStatus(int subjectCateId, int status) {
        ArrayList<Subject> SubjectListByStatus = new ArrayList<>();
        try {
            String sql = "select a.subjectId, a.subjectName, a.description, a.thumbnail, a.featuredSubject, a.status\n "
                    + ",b.cateId,subjectCateName FROM (Subject AS a inner join CategorySubject b\n"
                    + "on a.subjectId= b.subjectId)  inner join SubjectCategory  \n"
                    + "on b.cateId = subjectCateId";
            if (subjectCateId != -1) {
                sql += " Where subjectCateId =" + subjectCateId;
            }
            if (status != -1) {
                sql += " And a.status =" + status;
            }

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                SubjectListByStatus.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SubjectListByStatus;
    }

    public boolean insertCateSubject(int sid, int subcateId) {
        String sql = "INSERT INTO [dbo].[CategorySubject]\n"
                + "           ([subjectId]\n"
                + "           ,[cateId])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";
        try {

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setInt(2, subcateId);

            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
// search list subject  by keyword for Expert

    public ArrayList<Subject> getListSubjectByKeywordE(String keyword, int userId) {
        ArrayList<Subject> listPostByKeywordAndCateId = new ArrayList<>();
        try {
            String sql = "SELECT s.subjectId, s.subjectName, s.description, s.thumbnail, s.featuredSubject, se.userId, s.status\n"
                    + "                                FROM Subject as s full join  SubjectExpert as se on s.subjectId = se.subjectId Where se.userId = ? ";
            if (keyword.length() > 0) {
                sql += "And s.subjectName like ? ";
            }

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setString(2, "%" + keyword + "%");

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                listPostByKeywordAndCateId.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPostByKeywordAndCateId;
    }

    // keyword null
    public ArrayList<Subject> getListSubjectByKeywordE(int userId) {
        ArrayList<Subject> listPostByKeywordAndCateId = new ArrayList<>();
        try {
            String sql = "SELECT a.subjectId, a.subjectName,a.description, a.thumbnail, a.featuredSubject, s.userId, a.status\n"
                    + "                FROM Subject a full join  SubjectExpert s on a.subjectId = s.subjectId Where userId =?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                listPostByKeywordAndCateId.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPostByKeywordAndCateId;
    }
//    
//  search list subject  by keyword for admin

    public ArrayList<Subject> getListSubjectByKeyword(String keyword) {
        ArrayList<Subject> listPostByKeywordAndCateId = new ArrayList<>();
        try {
            String sql = "SELECT a.subjectId, a.subjectName,a.description, a.thumbnail, a.featuredSubject, a.status\n"
                    + "                    FROM Subject a ";

            if (keyword.length() > 0) {
                sql += "WHERE a.subjectName like ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                listPostByKeywordAndCateId.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPostByKeywordAndCateId;
    }
//  phan trang theo list

    public ArrayList<Subject> getListSubjectByKeyword(int page, int page_size, ArrayList<Subject> ListSubjectByKeyword) {
        int start, end;
        start = (page - 1) * page_size;
        if (page * page_size > ListSubjectByKeyword.size()) {
            end = ListSubjectByKeyword.size();
        } else {
            end = page * page_size;
        }
        ArrayList<Subject> listBlogPaging = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listBlogPaging.add(ListSubjectByKeyword.get(i));
        }
        return listBlogPaging;
    }

    public static void main(String[] args) {
        SubjectDAO dao = new SubjectDAO();

        System.out.println(dao.getFiterByCategoryAndStatus(2, 1, 6));
    }

    public ArrayList<Subject> getFiterByCategoryAndStatus(int subjectCateId, int status, int userId) {
        ArrayList<Subject> SubjectListByStatus = new ArrayList<>();
        try {
            String sql = "select a.subjectId, a.subjectName, a.description, a.thumbnail, a.featuredSubject, a.status\n"
                    + "                     ,b.cateId,subjectCateName FROM ((Subject AS a inner join CategorySubject b \n"
                    + "                     on a.subjectId = b.subjectId)  inner join SubjectCategory c\n"
                    + "                     on b.cateId = c.subjectCateId)  inner join SubjectExpert d\n"
                    + "					 on a.subjectId = d.subjectId Where userId = " + userId;
            if (subjectCateId != -1) {
                sql += " and subjectCateId =" + subjectCateId;
            }
            if (status != -1) {
                sql += " And a.status =" + status;
            }

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                SubjectListByStatus.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SubjectListByStatus;
    }
    
    
    //========================DPV==========
    
    public User getSubjectExpertDPV(int subjectId) {
        try {
            String sql = "SELECT * FROM SubjectExpert AS se INNER JOIN [User] AS u ON se.userId = u.userId WHERE subjectId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User s = new User();
                s.setUserName(rs.getString("userName"));
                s.setPassword(rs.getString("password"));
                s.setEmail(rs.getString("email"));
                s.setImage(rs.getString("image"));
                s.setPhone(rs.getString("phone"));
                s.setUserId(rs.getInt("userId"));
                s.setGender(rs.getBoolean("gender"));
                s.setStatus(rs.getBoolean("status"));
                s.setRoleId(rs.getInt("roleId"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Subject getSubjectByIdDPV(int subjectId) {
        try {
            String sql = "SELECT * FROM [subject] WHERE status = 1 AND subjectId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                s.setCreated(rs.getDate("created"));
                s.setLastEdited(rs.getDate("lastEdited"));
                s.setCategories(getSubjectCategoryBySubjectIDDPV(rs.getInt("subjectId")));
                s.setPricePackage(new PricePackageDAO().getPricePackageBySubjectIdDPV(rs.getInt("subjectId")));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Subject> getListSubjectByKeywordAndCateIdDPV(String[] cateId, String keyword, int page, int page_size) {
        ArrayList<Subject> listSubjectByKeywordAndCateId = new ArrayList<>();
        try {
            String sql = "SELECT cs.cateId, s.subjectId, s.subjectName, s.description, s.featuredSubject, s.thumbnail, s.status, s.created, s.lastEdited "
                    + "FROM [Subject] AS s JOIN CategorySubject AS cs ON cs.subjectId = s.subjectId "
                    + "WHERE s.status = 1";

            if (cateId != null) {
                sql += "AND cs.cateId in(";
                for (int i = 0; i < cateId.length; i++) {
                    sql += cateId[i] + ",";
                }
                sql += cateId[cateId.length - 1] + ")";
            }

            if (keyword.length() > 0) {
                sql += "AND s.subjectName like '%" + keyword.trim() + "%'";
            }

            sql += "ORDER BY subjectId DESC OFFSET (" + page + "-1)*" + page_size + " ROW FETCH NEXT " + page_size + " ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                s.setCreated(rs.getDate("created"));
                s.setLastEdited(rs.getDate("lastEdited"));
                s.setCategories(getSubjectCategoryBySubjectIDDPV(rs.getInt("subjectId")));
                s.setPricePackage(new PricePackageDAO().getPricePackageBySubjectIdDPV(rs.getInt("subjectId")));
                listSubjectByKeywordAndCateId.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSubjectByKeywordAndCateId;
    }

    public int countSubjectByKeywordAndCateIdDPV(String[] cateId, String keyword) {
        try {
            String sql = "SELECT COUNT(cs.subjectId) FROM [Subject] AS s JOIN CategorySubject AS cs ON cs.subjectId = s.subjectId "
                    + "WHERE s.status = 1";

            if (cateId != null) {
                sql += "AND cs.cateId in(";
                for (int i = 0; i < cateId.length; i++) {
                    sql += cateId[i] + ",";
                }
                sql += cateId[cateId.length - 1] + ")";
            }

            if (keyword.length() > 0) {
                sql += "AND s.subjectName like '%" + keyword.trim() + "%'";
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Subject> getFeatureSubjectDPV() {
        ArrayList<Subject> listFeatureSubject = new ArrayList<>();
        try {

            String sql = "SELECT TOP 4 * FROM Subject WHERE featuredSubject = 1 ORDER BY subjectId DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                s.setCreated(rs.getDate("created"));
                s.setLastEdited(rs.getDate("lastEdited"));
                listFeatureSubject.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listFeatureSubject;
    }

    public ArrayList<Subject> getAllSubjectDPV() {
        ArrayList<Subject> listAllSubject = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Subject WHERE status = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                listAllSubject.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAllSubject;
    }

    public ArrayList<Subject> getListSubjectByPaginationDPV(int page, int page_size) {
        ArrayList<Subject> listSubjectByPagination = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [subject] WHERE status = 1 ORDER BY subjectId DESC OFFSET (?-1)*? ROW FETCH NEXT ? ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, page_size);
            stm.setInt(3, page_size);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getBoolean("status"));
                s.setCreated(rs.getDate("created"));
                s.setLastEdited(rs.getDate("lastEdited"));
                s.setCategories(getSubjectCategoryBySubjectIDDPV(rs.getInt("subjectId")));
                s.setPricePackage(new PricePackageDAO().getPricePackageBySubjectIdDPV(rs.getInt("subjectId")));
                listSubjectByPagination.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSubjectByPagination;
    }

    public int countSubjectDPV() {
        try {
            String sql = "SELECT COUNT(*) FROM [Subject] WHERE status = 1";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int countQuestionInSubjectDPV(int subjectId) {
        try {
            String sql = "SELECT COUNT(*) FROM Question WHERE subjectId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, subjectId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<SubjectCategory> getSubjectCategoryBySubjectIDDPV(int subjectId) {
        ArrayList<SubjectCategory> listSubjectCategoryBySubjectID = new ArrayList<>();
        try {
            String sql = "SELECT sc.subjectCateId, sc.subjectCateName, sc.status FROM [Subject] AS s INNER JOIN CategorySubject AS cs ON s.subjectId = cs.subjectId INNER JOIN SubjectCategory AS sc ON cs.cateId = sc.subjectCateId WHERE sc.status = 1 AND s.subjectId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SubjectCategory s = new SubjectCategory();
                s.setSubjectCateId(rs.getInt("subjectCateId"));
                s.setSubjectCateName(rs.getString("subjectCateName"));
                s.setStatus(rs.getBoolean("status"));
                listSubjectCategoryBySubjectID.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSubjectCategoryBySubjectID;
    }


    //minh
    public int updateSubjectBasic(int subjectId, Subject subject) throws Exception {
        int i = 0;

        String sql = "UPDATE Subject\n"
                + "  SET subjectName = ?,\n"
                + "  description = ?,\n"
                + "  thumbnail = ?,\n"
                + "  featuredSubject = ?,\n"
                + "  status = ?\n"
                + "  WHERE subjectId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, subject.getSubjectName());
            stm.setString(2, subject.getDescription());
            stm.setString(3, subject.getThumbnail());
            stm.setBoolean(4, subject.isFeaturedSubject());
            stm.setBoolean(5, subject.isStatus());
            stm.setInt(6, subjectId);
            i = stm.executeUpdate();
        } catch (Exception ex) {
            throw ex;

        }
        return i;
    }
    
    public boolean deleteSubject(int subjectId) {

        String sql = "DELETE FROM [dbo].[Subject]\n" +
"      WHERE subjectId = ?";
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
    
    public boolean insertSubject(String subname, String description, String thumbnail, boolean feature, boolean status, Date edate) {

        String sql = "INSERT INTO [dbo].[Subject]\n"
                + "           ([subjectName]\n"
                + "           ,[description]\n"
                + "           ,[thumbnail]\n"
                + "           ,[featuredSubject]\n"
                + "           ,[status]\n"
                + "           ,[created])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subname);
            stm.setString(2, description);
            stm.setString(3, thumbnail);
            stm.setBoolean(4, feature);
            stm.setBoolean(5, status);
            stm.setDate(6, edate);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Subject getSubjectbyIdMinh(int subjectId) throws Exception {
        Subject subjectById = null;
        DimensionDAO dimensionDAO = new DimensionDAO();
        SubjectCategoryDAO subjectCateDAO = new SubjectCategoryDAO();
        PricePackageDAO pricePackageDAO = new PricePackageDAO();
        String sqlSubject = "SELECT * FROM [Subject] WHERE [subjectId] = ?";

        /* Get the subject */
        try {
            PreparedStatement stm = connection.prepareStatement(sqlSubject);
            stm.setInt(1, subjectId);
            ResultSet rs = stm.executeQuery();
            /* Get information from resultset and set it to the created pointer */
            if (rs.next()) {
                int subjectIdResult = rs.getInt("subjectId");
                String subjectName = rs.getString("subjectName");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                Boolean featuredSubject = rs.getBoolean("featuredSubject");
                Boolean status = rs.getBoolean("status");

                subjectById = new Subject(subjectId, subjectName, description,
                        thumbnail, featuredSubject, status,
                        dimensionDAO.getDimensionBySubjectMinh(subjectId),
                        pricePackageDAO.getPricePackageBySubjectIdDPV(subjectId),
                        subjectCateDAO.getSubjectCateBySubject(subjectId));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return subjectById;
    }
}
