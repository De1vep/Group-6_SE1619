/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Ultils.MD5;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name="changePasswordController", urlPatterns={"/view/changepassword"})
public class changePasswordController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String re_newpassword = request.getParameter("re_newpassword");
        MD5 md5 = new MD5();
        String mh_oldpassword = md5.getMd5(oldpassword);
        
        
        
        User acc = (User) session.getAttribute("account");
        UserDAO db = new UserDAO();
        if (!mh_oldpassword.equals(acc.getPassword())) {
            request.setAttribute("falseOldPassword", "falseOldPassword");
            request.getRequestDispatcher("/view/home.jsp").forward(request, response);
        } else {
            if (!newpassword.equals(re_newpassword)) {
                request.setAttribute("falseNewPassword", "falseNewPassword");
                request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            } else {
                User u = new User(acc.getRoleId(), acc.getUserName(), newpassword, acc.getRoleId(), acc.getImage(),
                        acc.getEmail(), acc.isGender(), acc.getPhone(), acc.isStatus());
                db.updatePassword(newpassword, acc.getEmail());
                request.setAttribute("ChangeSuccessfull", "ChangeSuccessfull");
                session.setAttribute("acccout", u);
                request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            }

        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
