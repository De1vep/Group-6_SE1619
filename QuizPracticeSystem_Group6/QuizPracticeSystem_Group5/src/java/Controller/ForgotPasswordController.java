/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Ultils.EmailUtils;
import Ultils.MD5;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Email;
import model.User;
import org.apache.jasper.compiler.PageInfo;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ForgotPasswordController", urlPatterns = {"/login/forgot-password"})
public class ForgotPasswordController extends HttpServlet {

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
            out.println("<title>Servlet forgotPasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet forgotPasswordController at " + request.getContextPath() + "</h1>");
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
        String service = request.getParameter("service");
        if (service != null) {
            if (service.equalsIgnoreCase("submitEmail")) {
                request.getRequestDispatcher("../login/forgotPassword.jsp").forward(request, response);
                return;
            }
            if (service.equalsIgnoreCase("changePassword")) {
                long createTime = Long.parseLong(request.getParameter("createTime"));
                long milisNow  = System.currentTimeMillis();
                if (createTime < milisNow) {
                    request.setAttribute("mess", "Oops, this link is expired");
                    request.getRequestDispatcher("../view/error.jsp").forward(request, response);
                    return ;
                }
                String email = request.getParameter("email");
                request.setAttribute("emailAddress", email);
                request.getRequestDispatcher("../login/change-newpassword.jsp").forward(request, response);
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
        String service = request.getParameter("service");
        UserDAO ud = new UserDAO();
        if (service != null) {
            if (service.equalsIgnoreCase("submitEmail")) {
                String emailAddress = request.getParameter("email");
                User user = ud.findUserByEmail(emailAddress);
                if (user == null) {
                    request.setAttribute("mess", "email are incorrect");
                    request.getRequestDispatcher("../login/forgotPassword.jsp").forward(request, response);

                } else {
                    long milis = System.currentTimeMillis() + 5*60*1000;
                    String link = "http://localhost:56799/QuizPracticeSystem_Group5/login/forgot-password?service=changePassword&&email=" + emailAddress+"&&createTime=" + milis;
                    EmailUtils emailUtils = new EmailUtils();
                    emailUtils.mailResetPassword(emailAddress, link, user.getUserName());
                    request.setAttribute("mess", "An reset password link have been sent to your email address");
                    request.setAttribute("emailAddress", emailAddress);
                    request.getRequestDispatcher("../login/forgotPassword.jsp").forward(request, response);
                }
                return;
            }
            if (service.equalsIgnoreCase("changePassword")) {
                String newPassword = request.getParameter("newPassword");
                String confirmPassword = request.getParameter("confirmPassword");
                String emailAddress = request.getParameter("email");
                User user = ud.findUserByEmail(emailAddress);
                if (!newPassword.equals(confirmPassword)) {
                    request.setAttribute("mess", "Confirm password does not match!");
                    request.setAttribute("emailAddress", emailAddress);
                    request.getRequestDispatcher("../login/change-newpassword.jsp").forward(request, response);
                    return;
                }
                if (user == null) {
                    request.setAttribute("mess", "Email invalid!");
                    request.getRequestDispatcher("../login/change-newpassword.jsp").forward(request, response);
                    return;
                }
                
                ud.updatePassword(newPassword, emailAddress);
                request.setAttribute("mess", "Change password successful!");
                request.getRequestDispatcher("../login/change-newpassword.jsp").forward(request, response);
                    
            }
        }

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
