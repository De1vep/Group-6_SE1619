/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;
import model.Slider;
import model.User;

/**
 *
 * @author DPV
 */
public class BlogDAO extends DBContext {

    public ArrayList<Blog> getListPostByKeywordAndCateId(String[] cateId, String keyword) {
        ArrayList<Blog> listPostByKeywordAndCateId = new ArrayList<>();
        try {
            String sql = "SELECT a.blogId, a.blogTitle, a.created, a.detail, a.lastEdited, a.status, a.thumbnail, a.author, b.postCateId \n"
                    + "FROM [Blog] AS a JOIN [BlogCategory] AS b ON a.blogId = b.blogId\n"
                    + "WHERE a.status = 1 ";

            if (cateId != null) {
                sql += "AND b.postCateId in (";
                for (int i = 0; i < cateId.length; i++) {
                    sql += cateId[i] + ",";
                }
                sql += cateId[cateId.length - 1] + ")";
            }

            if (keyword.length() > 0) {
                sql += "AND a.blogTitle like '%" + keyword.trim() + "%'";
            }
            
            sql += "ORDER BY lastEdited DESC";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog s = new Blog();
                s.setBlogId(rs.getInt("blogId"));
                s.setAuthor(getAuthor(rs.getInt("author")));
                s.setBlogTitle(rs.getString("blogTitle"));
                s.setCreated(rs.getDate("created"));
                s.setDetail(rs.getString("detail"));
                s.setLastEdited(rs.getDate("lastEdited"));
                s.setStatus(rs.getBoolean("status"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setPostCategory(new PostCategoryDAO().getPostCategoryByBlogId(rs.getInt("blogId")));
                listPostByKeywordAndCateId.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPostByKeywordAndCateId;
    }

    public ArrayList<Blog> getListBlogByPaging(int page, int page_size, ArrayList<Blog> listBlog) {
        int start, end;
        start = (page - 1) * page_size;
        if (page * page_size > listBlog.size()) {
            end = listBlog.size();
        } else {
            end = page * page_size;
        }
        ArrayList<Blog> listBlogPaging = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listBlogPaging.add(listBlog.get(i));
        }
        return listBlogPaging;
    }

    public Blog getBlogById(int blogId) {
        try {
            String sql = "SELECT * FROM [Blog] WHERE blogId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, blogId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog s = new Blog();
                s.setBlogId(rs.getInt("blogId"));
                s.setAuthor(getAuthor(rs.getInt("author")));
                s.setBlogTitle(rs.getString("blogTitle"));
                s.setCreated(rs.getDate("created"));
                s.setDetail(rs.getString("detail"));
                s.setLastEdited(rs.getDate("lastEdited"));
                s.setStatus(rs.getBoolean("status"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setPostCategory(new PostCategoryDAO().getPostCategoryByBlogId(blogId));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Blog> getListPostByPagination(int page, int page_size) {
        ArrayList<Blog> listPostByPagination = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [Blog] WHERE status = 1 ORDER BY lastEdited DESC OFFSET (?-1)*? ROW FETCH NEXT ? ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, page_size);
            stm.setInt(3, page_size);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog s = new Blog();
                s.setBlogId(rs.getInt("blogId"));
                s.setAuthor(getAuthor(rs.getInt("author")));
                s.setBlogTitle(rs.getString("blogTitle"));
                s.setCreated(rs.getDate("created"));
                s.setDetail(rs.getString("detail"));
                s.setLastEdited(rs.getDate("lastEdited"));
                s.setStatus(rs.getBoolean("status"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setPostCategory(new PostCategoryDAO().getPostCategoryByBlogId(rs.getInt("blogId")));
                listPostByPagination.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPostByPagination;
    }

    public int countPosts() {
        try {
            String sql = "SELECT COUNT(*) FROM [Blog] WHERE status = 1";
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

    public ArrayList<Blog> getLatestPost() {
        ArrayList<Blog> listLatestPost = new ArrayList<>();
        try {
            String sql = "SELECT TOP 3 * FROM [Blog] where status = 1 ORDER BY created DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog s = new Blog();
                s.setBlogId(rs.getInt("blogId"));
                s.setAuthor(getAuthor(rs.getInt("author")));
                s.setBlogTitle(rs.getString("blogTitle"));
                s.setCreated(rs.getDate("created"));
                s.setDetail(rs.getString("detail"));
                s.setLastEdited(rs.getDate("lastEdited"));
                s.setStatus(rs.getBoolean("status"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setPostCategory(new PostCategoryDAO().getPostCategoryByBlogId(rs.getInt("blogId")));
                listLatestPost.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLatestPost;
    }

    public User getAuthor(int userId) {
        try {
            String sql = "SELECT * FROM [User] WHERE userId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User s = new User();
                s.setUserId(rs.getInt("userId"));
                s.setUserName(rs.getString("userName"));
                s.setEmail(rs.getString("email"));
                s.setGender(rs.getBoolean("gender"));
                s.setImage(rs.getString("image"));
                s.setPassword(rs.getString("password"));
                s.setPhone(rs.getString("phone"));
                s.setRoleId(rs.getInt("roleId"));
                s.setStatus(rs.getBoolean("status"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Blog> getAllBlog() {
        ArrayList<Blog> listPostByPagination = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [Blog]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blog s = new Blog();
                s.setBlogId(rs.getInt("blogId"));
                s.setAuthor(getAuthor(rs.getInt("author")));
                s.setBlogTitle(rs.getString("blogTitle"));
                s.setCreated(rs.getDate("created"));
                s.setDetail(rs.getString("detail"));
                s.setLastEdited(rs.getDate("lastEdited"));
                s.setStatus(rs.getBoolean("status"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setPostCategory(new PostCategoryDAO().getPostCategoryByBlogId(rs.getInt("blogId")));
                listPostByPagination.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPostByPagination;
    }
    
    public int updateBlog(Blog blog){
        try {
            PreparedStatement stm = connection.prepareStatement("UPDATE [dbo].[Blog] SET [blogTitle] = ?,[created] = ?,[lastEdited] = ?,[detail] = ?, [thumbnail]=?,[status] = ? WHERE [blogId] = ?");
            stm.setString(1, blog.getBlogTitle());
            stm.setDate(2, blog.getCreated());
            stm.setDate(3, blog.getLastEdited());
            stm.setString(4, blog.getDetail());
            stm.setString(5, blog.getThumbnail());
            stm.setBoolean(6, blog.getStatus());
            stm.setInt(7, blog.getBlogId());

            int status = stm.executeUpdate();
            return 1;
        } catch (SQLException ex) {           
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int updateBlogCate(Blog blog, int cateId){
        try {
            PreparedStatement stm = connection.prepareStatement("UPDATE [dbo].[BlogCategory] SET [postCateId] = ? WHERE [blogId] = ?");
            stm.setInt(1, cateId);
            stm.setInt(2, blog.getBlogId());

            int status = stm.executeUpdate();
            return 1;
        } catch (SQLException ex) {           
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public static void main(String[] args) {
        BlogDAO dao = new BlogDAO();
        System.out.println(dao.getListPostByPagination(1, 5));
    }
}
