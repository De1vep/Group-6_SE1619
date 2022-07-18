/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.BlogDAO;
import dal.PostCategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.PostCategory;

/**
 *
 * @author DPV
 */
@WebServlet(name = "BlogController", urlPatterns = {"/view/blog"})
public class BlogController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BlogController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BlogDAO blogDao = new BlogDAO();
        PostCategoryDAO postCategoryDao = new PostCategoryDAO();
        String service = request.getParameter("service");

        if (service != null) {
            if (service.equals("blogList")) {

                /*Process pagination*/
                int page = 1;
                int page_size = 5;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                int countPosts = blogDao.countPosts();
                int totalPage = countPosts / page_size;
                if (countPosts % page_size != 0) {
                    totalPage += 1;
                }

                /*url to page=?, with service=blogList*/
                String previousLink = "../view/blog?service=blogList&page=";

                /*get list blog by page*/
                ArrayList<Blog> listPostByPagination = blogDao.getListPostByPagination(page, page_size);

                /*get list latest blog and list category*/
                ArrayList<Blog> listLatestPost = blogDao.getLatestPost();
                ArrayList<PostCategory> listPostCategory = postCategoryDao.getAllPostCategory();

                /*send page now, toltal page and url to page=? */
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("previousLink", previousLink);

                /*send list latest blog and list blog*/
                request.setAttribute("listPost", listPostByPagination);

                /*send list latest blog and list category*/
                request.getSession().setAttribute("listLatestPost", listLatestPost);
                request.getSession().setAttribute("listPostCategory", listPostCategory);
                request.getRequestDispatcher("../view/blog.jsp").forward(request, response);
                return;
            }

            if (service.equals("search")) {
                /*categoryId and keyword using for search*/
                String[] cateId = request.getParameterValues("cateId");
                String keyword = request.getParameter("keyword");

                /*url to page=?, with service=search*/
                String previousLink = "../view/blog?service=search&keyword=" + keyword;

                if (cateId != null) {
                    for (int i = 0; i < cateId.length; i++) {
                        previousLink += "&cateId=" + cateId[i];
                    }
                }

                previousLink += "&page=";

                /*Process pagination*/
                int page = 1;
                int page_size = 5;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }

                /*get list blog by keyword and category Id,
                After get list blog by pagination using page number and list blog get by keyword and categoryId*/
                ArrayList<Blog> listPostByKeywordAndCateId = blogDao.getListPostByKeywordAndCateId(cateId, keyword);
                ArrayList<Blog> listPost = blogDao.getListBlogByPaging(page, page_size, listPostByKeywordAndCateId);

                int countPosts = listPostByKeywordAndCateId.size();
                int totalPage = countPosts / page_size;
                if (countPosts % page_size != 0) {
                    totalPage += 1;
                }

                /*send page now, toltal page, cateId, keyword and url to page=? */
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("cateId", cateId);
                request.setAttribute("keyword", keyword);
                request.setAttribute("previousLink", previousLink);

                /*send list blog by page*/
                request.setAttribute("listPost", listPost);

                request.getRequestDispatcher("../view/blog.jsp").forward(request, response);
                return;
            }

            if (service.equals("blogDetails")) {
                
                /*get blog by blogId*/
                int blogId = Integer.parseInt(request.getParameter("blogId"));
                Blog blog = blogDao.getBlogById(blogId);

                /*get list latest blog and list category*/
                ArrayList<Blog> listLatestPost = blogDao.getLatestPost();
                ArrayList<PostCategory> listPostCategory = postCategoryDao.getAllPostCategory();

                /*send list latest blog and list category*/
                request.setAttribute("listLatestPost", listLatestPost);
                request.setAttribute("listPostCategory", listPostCategory);

                /*send blog*/
                request.setAttribute("blog", blog);
                request.getRequestDispatcher("../view/blog-details.jsp").forward(request, response);
                return;
            }

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
