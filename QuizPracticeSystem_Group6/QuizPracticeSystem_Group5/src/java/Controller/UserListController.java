/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.UserDAO;
import dal.UserRoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import model.UserRole;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UserListController", urlPatterns = {"/view/userList"})
public class UserListController extends HttpServlet {

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
            String service = request.getParameter("service");

            if (service.equals("LoadUserList")) {
                UserDAO ud = new UserDAO();

                int page = 1;
                int page_size = 5;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                int countUser = ud.countUser();
                int totalPage = countUser / page_size;
                if (countUser % page_size != 0) {
                    totalPage += 1;
                }

                ArrayList<User> users = ud.getAllUserAdminbyPaging(page, page_size);
                request.setAttribute("users", users);

                ArrayList<UserRole> userRoleList = new UserRoleDAO().getAllUserRole();
                request.setAttribute("userRoleList", userRoleList);

                String previousLink = "userList?service=LoadUserList&page=";

                request.setAttribute("previousLink", previousLink);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);

                request.getRequestDispatcher("../view/UserListAdmin.jsp").forward(request, response);
                return;
            }
            if (service.equalsIgnoreCase("createUser")) {
                response.getWriter().print("aa");
                return;
            }
            if (service.equalsIgnoreCase("search")) {

                UserDAO ud = new UserDAO();
                String Name = request.getParameter("Name");
                String Mail = request.getParameter("Mail");
                String Mobile = request.getParameter("Mobile");

                int page = 1;
                int page_size = 5;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                int countUser = ud.countUserBySearch(Name, Mail, Mobile);
                int totalPage = countUser / page_size;
                if (countUser % page_size != 0) {
                    totalPage += 1;
                }

                ArrayList<User> users = ud.getAllUserAdminbyPagingbySearch(page, page_size, Name, Mail, Mobile);
                request.setAttribute("users", users);
                ArrayList<UserRole> userRoleList = new UserRoleDAO().getAllUserRole();
                request.setAttribute("userRoleList", userRoleList);

                String previousLink = "userList?service=search";
                if (Name != null) {
                    previousLink += "&Name=" + Name;
                    request.setAttribute("Names", Name);
                }
                if (Mail != null) {
                    previousLink += "&Mail=" + Mail;
                    request.setAttribute("Mails", Mail);
                }
                if (Mobile != null) {
                    previousLink += "&Mobile=" + Mobile;
                    request.setAttribute("Mobiles", Mobile);
                }

                previousLink += "&page=";
                request.setAttribute("previousLink", previousLink);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.getRequestDispatcher("../view/UserListAdmin.jsp").forward(request, response);
                return;
            }

            if (service.equalsIgnoreCase("filter")) {
                UserDAO ud = new UserDAO();
                int gender = Integer.parseInt(request.getParameter("genderFilter"));
                int role = Integer.parseInt(request.getParameter("roleFilter"));
                int status = Integer.parseInt(request.getParameter("statusFilter"));
                request.setAttribute("genderFilter", gender);
                request.setAttribute("roleFilter", role);
                request.setAttribute("statusFilter", status);
                int page = 1;
                int page_size = 5;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                int countUser = ud.countUserByFilter(gender, role, status);
                int totalPage = countUser / page_size;
                if (countUser % page_size != 0) {
                    totalPage += 1;
                }

                ArrayList<User> users = ud.getAllUserAdminbyPagingbyFilter(page, page_size, gender, role, status);
                request.setAttribute("users", users);
                ArrayList<UserRole> userRoleList = new UserRoleDAO().getAllUserRole();
                request.setAttribute("userRoleList", userRoleList);

                String previousLink = "userList?service=filter&genderFilter="+gender+"&roleFilter="+role+"&statusFilter="+status+"&page=";
                
                request.setAttribute("previousLink", previousLink);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.getRequestDispatcher("../view/UserListAdmin.jsp").forward(request, response);
                return;
            }
            
            if (service.equals("loadUser")) {
                int id= Integer.parseInt(request.getParameter("id")) ;
                User user = new UserDAO().getUserByID(id);
                request.setAttribute("user", user);
                ArrayList<UserRole> userRoleList = new UserRoleDAO().getAllUserRole();
                request.getSession().setAttribute("idSession", id);
                request.setAttribute("userRoleList", userRoleList);
                request.getRequestDispatcher("../view/UserListDetail.jsp").forward(request, response);
                return ;
            }
            if (service.equals("editUser")) {
                int roleId = Integer.parseInt(request.getParameter("roleId")) ;
                int statuss = Integer.parseInt(request.getParameter("status")) ;
                boolean status = true;
                if (statuss !=1) {
                    status = false;
                }
                int userId = (int) request.getSession().getAttribute("idSession");
                new UserDAO().UpDateUserAdmin(userId,roleId,status);
                request.getRequestDispatcher("userList?service=LoadUserList").forward(request, response);
                return ;
            }
            
            
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
