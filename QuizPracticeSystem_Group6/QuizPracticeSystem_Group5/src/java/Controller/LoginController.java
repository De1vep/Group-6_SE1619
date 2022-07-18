/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author DPV
 */
@WebServlet(name = "LoginController", urlPatterns = {"/view/login"})
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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
        User account = (User) request.getSession().getAttribute("account");
        if (service != null) {
            if (service.equalsIgnoreCase("logout")) {
                request.getSession().removeAttribute("account");
                response.sendRedirect("../view/home");
                return;
            }
        }
        response.sendRedirect("../view/home");
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

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO userDao = new UserDAO();
        User account = userDao.checkLogin(email, password);

        if (userDao.checkUser(email) == null) {
            request.setAttribute("falseEmail", "falseEmail");
            request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            return;
        }
        if (account != null) {
            if (account.isStatus() == false) {
                request.setAttribute("falseActivated", "falseActivated");
                request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            } else {
                if (account.getRoleId() == 5) {
                    HttpSession session = request.getSession();
                    session.setAttribute("account", account);
//                request.setAttribute("loginSuccess", "loginSuccess");
                    request.getRequestDispatcher("Dashboard").forward(request, response);
               
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("account", account);
//                request.setAttribute("loginSuccess", "loginSuccess");
                    request.getRequestDispatcher("/view/home.jsp").forward(request, response);
                }
            }
        } else {
            request.setAttribute("falseOldPassword", "falseOldPassword");
            request.getRequestDispatcher("/view/home.jsp").forward(request, response);
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
