/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.BlogDAO;
import dal.PostCategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.PostCategory;

/**
 *
 * @author 84915
 */
@WebServlet(name = "BlogAdminController", urlPatterns = {"/view/BlogAdminController"})
public class BlogAdminController extends HttpServlet {

    BlogDAO dbBlog = new BlogDAO();
    PostCategoryDAO dbCate = new PostCategoryDAO();
    ArrayList<Blog> listBlog;
    ArrayList<Blog> listBlogFilter;
    int maxPage = 1;
    ArrayList<Blog> listBlogPerPage;
    ArrayList<Blog> listAll;

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
        String action = "";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        int page = 1;
        int filter = 0;       

        switch (action) {
            case "detail":
                int id = Integer.parseInt(request.getParameter("id"));
                Blog s = listAll.get(id - 1);
                ArrayList<PostCategory> listCate = dbCate.getAllPostCategory();
                request.setAttribute("blog", s);
                request.setAttribute("listCate", listCate);
                request.getRequestDispatcher("../view/BlogAdminDetail.jsp").forward(request, response);
                break;
            case "Update":
                try {
                update(request, response);
            } catch (Exception e) {
            }
            list(request, response, page, 0);
            break;
            case "changePage":
                try {
                page = Integer.parseInt(request.getParameter("page"));               
                //update(request, response);
            } catch (Exception e) {                
            }
            changePage(request, response, page);
            break;
            case "filter":
                try {
                //page = Integer.parseInt(request.getParameter("page"));
                filter = Integer.parseInt(request.getParameter("filterType"));
                //update(request, response);
            } catch (Exception e) {
                filter = 0;
            }
            list(request, response, 1, filter);
            break;
            default:
                list(request, response, 1, 0);
                break;
        }
    }

    protected void list(HttpServletRequest request, HttpServletResponse response, int page, int filter) {
        try {

            listAll = new ArrayList<>();
            listBlog = new ArrayList<>();
            listAll = dbBlog.getAllBlog();
            if (filter == 1) {
                String tilte = request.getParameter("filterTitle");
                for (Blog item : listAll) {
                    if (item.getBlogTitle().toLowerCase().contains(tilte.toLowerCase())) {
                        listBlog.add(item);
                    }
                }
            request.setAttribute("tilte", tilte);

            } else if (filter == 2) {
                String blogCreatedAfterString = request.getParameter("filterCreateAfter");
                String blogCreatedBeforeString = request.getParameter("filterCreateBefore");
                if (!blogCreatedAfterString.isEmpty() && !blogCreatedBeforeString.isEmpty()) {
                    Date blogCreatedAfter = Date.valueOf(blogCreatedAfterString);
                    Date blogCreatedBefore = Date.valueOf(blogCreatedBeforeString);
                    for (Blog item : listAll) {
                        if (item.getCreated().after(blogCreatedAfter) && item.getCreated().before(blogCreatedBefore)) {
                            listBlog.add(item);
                        }
                    }
                    request.setAttribute("blogCreatedAfter", blogCreatedAfter);
                    request.setAttribute("blogCreatedBefore", blogCreatedBefore);
                } else if(!blogCreatedAfterString.isEmpty() && blogCreatedBeforeString.isEmpty()){
                    Date blogCreatedAfter = Date.valueOf(blogCreatedAfterString);                   
                    for (Blog item : listAll) {
                        if (item.getCreated().after(blogCreatedAfter)) {
                            listBlog.add(item);
                        }
                    }
                    request.setAttribute("blogCreatedAfter", blogCreatedAfter);

                } else if(blogCreatedAfterString.isEmpty() && !blogCreatedBeforeString.isEmpty()){
                    Date blogCreatedBefore = Date.valueOf(blogCreatedBeforeString);
                    for (Blog item : listAll) {
                        if (item.getCreated().before(blogCreatedBefore)) {
                            listBlog.add(item);
                        }
                    }
                    request.setAttribute("blogCreatedBefore", blogCreatedBefore);
                }
            } else if (filter == 3) {

                String blogLastEditAfterStr = request.getParameter("filterLastEditAfter");
                String blogLastEditBeforeStr = request.getParameter("filterLastEditBefore");
                if (!blogLastEditAfterStr.isEmpty() && !blogLastEditBeforeStr.isEmpty()) {
                    Date blogLastAfter = Date.valueOf(blogLastEditAfterStr);
                    Date blogLastBefore = Date.valueOf(blogLastEditBeforeStr);
                    for (Blog item : listAll) {
                        if (item.getLastEdited().after(blogLastAfter)&&item.getLastEdited().before(blogLastBefore)) {
                            listBlog.add(item);
                        }
                    }
                    request.setAttribute("blogLastEditAfterStr", blogLastAfter);
                    request.setAttribute("blogLastEditBeforeStr", blogLastBefore);
                }

            } else if (filter == 4) {

                String blogAuthor = request.getParameter("filterAuthor");
                for (Blog item : listAll) {
                    if (item.getAuthor().getUserName().toLowerCase().contains(blogAuthor.toLowerCase())) {
                        listBlog.add(item);
                    }
                }
            request.setAttribute("blogAuthor", blogAuthor);

            } else if (filter == 5) {

                int cate = Integer.parseInt(request.getParameter("cateFilter"));
                int blogStatus = Integer.parseInt(request.getParameter("statusFilter"));
                boolean status = true;
                if(blogStatus == 2){
                    status = false;
                }
                if(cate >0 && blogStatus >0){
                    for (Blog item : listAll) {
                        if (item.getPostCategory().getPostCateId() == cate && item.getStatus() == status) {
                            listBlog.add(item);
                        }
                    }
                }
                else if(cate <0 && blogStatus >0){
                    for (Blog item : listAll) {
                        if (item.getStatus() == status) {
                            listBlog.add(item);
                        }
                    }
                }
                else if(cate >0 && blogStatus <0){
                    for (Blog item : listAll) {
                        if (item.getPostCategory().getPostCateId() == cate) {
                            listBlog.add(item);
                        }
                    }
                }
                else{
                    listBlog = listAll;
                }
                request.setAttribute("blogStatus", blogStatus);
                request.setAttribute("cate", cate);

            }
            

            else {
                listBlog = listAll;
            }
            listBlogPerPage = new ArrayList<>();
            maxPage = listBlog.size() / 5;
            if (listBlog.size() % 5 != 0) {
                maxPage += 1;
            }
            if (listBlog.isEmpty()) {
                maxPage = 1;
            }
            for (int i = (page - 1) * 5; i < (page * 5); i++) {
                if (i < listBlog.size()) {
                    listBlogPerPage.add(listBlog.get(i));
                }
            }
            reDirect(request, response, listBlogPerPage, page, maxPage);
        } catch (Exception ex) {
            Logger.getLogger(BlogAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void changePage(HttpServletRequest request, HttpServletResponse response, int page) {
        listBlogPerPage = new ArrayList<>();
        for (int i = (page - 1) * 5; i < (page * 5); i++) {
            if (i < listBlog.size()) {
                listBlogPerPage.add(listBlog.get(i));
            }
        }
        reDirect(request, response, listBlogPerPage, page, maxPage);
    }

    protected void reDirect(HttpServletRequest request, HttpServletResponse response, ArrayList<Blog> listShow, int page, int maxpage) {
        request.setAttribute("listBlog", listShow);
        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxpage);
        try {
            request.getRequestDispatcher("../view/BlogAdmin.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(BlogAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BlogAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        int blogId = Integer.parseInt(request.getParameter("blogId"));
        String blogTitle = request.getParameter("blogTitle");
        String blogCreatedString = request.getParameter("blogCreated");
        String blogLastEditString = request.getParameter("blogLastEdit");
        String blogDetail = request.getParameter("blogDetail");
        String thumnail = request.getParameter("thumnail");
        boolean blogStatus = false;
        if (request.getParameter("blogStatus").equals("1")) {
            blogStatus = true;
        }
        int cateId = Integer.parseInt(request.getParameter("blogCate"));
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date blogCreated = Date.valueOf(blogCreatedString);
        Date blogLastEdit = Date.valueOf(blogLastEditString);

        Blog s = dbBlog.getAllBlog().get(blogId - 1);
        s.setBlogTitle(blogTitle);
        s.setCreated(blogCreated);
        s.setLastEdited(blogLastEdit);
        s.setDetail(blogDetail);
        s.setThumbnail(thumnail);
        s.setStatus(blogStatus);
        int row_effects = dbBlog.updateBlog(s);
        int check = dbBlog.updateBlogCate(s, cateId);
        if (row_effects > 0) {
            System.out.println("Blog updated");
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
        processRequest(request, response);
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
