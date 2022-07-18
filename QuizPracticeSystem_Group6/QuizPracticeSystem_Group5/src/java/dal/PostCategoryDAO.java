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
import model.PostCategory;
import model.UserRole;

/**
 *
 * @author DPV
 */
public class PostCategoryDAO extends DBContext {

    public PostCategory getPostCategoryByBlogId(int blogId) {
        try {
            String sql = "SELECT blogId, pc.postCateId, postCateName, bc.status FROM PostCategory AS pc INNER JOIN BlogCategory AS bc ON pc.postCateId = bc.postCateId WHERE bc.status = 1 and bc.blogId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, blogId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PostCategory s = new PostCategory();
                s.setPostCateId(rs.getInt("postCateId"));
                s.setPostCateName(rs.getString("postCateName"));
                s.setStatus(rs.getBoolean("postCateName"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<PostCategory> getAllPostCategory() {
        ArrayList<PostCategory> listAllPostCategory = new ArrayList<>();
        try {
            String sql = "SELECT * FROM PostCategory where status = 1 ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PostCategory s = new PostCategory();
                s.setPostCateId(rs.getInt("postCateId"));
                s.setPostCateName(rs.getString("postCateName"));
                s.setStatus(rs.getBoolean("postCateName"));
                listAllPostCategory.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAllPostCategory;
    }

    public PostCategory getPostCategoryById(int postCateId) {
        try {
            String sql = "SELECT * FROM PostCategory where status = 1 and postCateId = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postCateId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PostCategory s = new PostCategory();
                s.setPostCateId(rs.getInt("postCateId"));
                s.setPostCateName(rs.getString("postCateName"));
                s.setStatus(rs.getBoolean("postCateName"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getPostCateId(int blogId) {
        try {
            String sql = "SELECT * FROM [BlogCategory] WHERE blogId = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, blogId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("postCateId");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<PostCategory> getAllStatusPostCates() throws Exception {

        ArrayList<PostCategory> allPostCate = new ArrayList();
        String sql = "SELECT * FROM [PostCategory]";
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                allPostCate.add(new PostCategory(rs.getInt("postCateId"),
                        rs.getString("postCateName"),
                        rs.getBoolean("status")));
            }
            return allPostCate;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void main(String[] args) {
        PostCategoryDAO dao = new PostCategoryDAO();
        System.out.println(dao.getPostCategoryById(1));
    }

    public PostCategory getPostCategoryByIdLoad(int id) {

        try {
            String sql = "SELECT * FROM PostCategory where postCateId = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PostCategory s = new PostCategory();
                s.setPostCateId(rs.getInt("postCateId"));
                s.setPostCateName(rs.getString("postCateName"));
                s.setStatus(rs.getBoolean("status"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void UpDatePostCategorySetting(int id, String name, boolean statuss) {
        try {
            String sql = "UPDATE [PostCategory]\n"
                    + "   SET [postCateName] = ?\n"
                    + "      ,[status] = ?\n"
                    + " WHERE postCateId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setBoolean(2, statuss);
            stm.setInt(3, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InsertPostCategorySetting(String name, boolean statuss) {
        try {
            String sql = "INSERT INTO [dbo].[PostCategory]\n"
                    + "           ([postCateName]\n"
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
