/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Ultils.EmailUtils;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author DPV
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/view/register"})
public class RegisterController extends HttpServlet {

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
        if (service.equalsIgnoreCase("confirmAccount")) {
            UserDAO db = new UserDAO();
            String email = request.getParameter("email");
            System.out.println(email);
            User u = db.checkUser(email);
            db.changeStatus(u.getUserId(), true);
            request.setAttribute("ConfirmAccountSuccess", "ConfirmAccountSuccess");
            request.getRequestDispatcher("../view/home.jsp").forward(request, response);
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
        if (service.equalsIgnoreCase("register")) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            boolean gender = Boolean.parseBoolean(request.getParameter("gender")) ;
            String password = request.getParameter("password");
            String re_password = request.getParameter("repassword");

           
            UserDAO db = new UserDAO();

            if (db.checkUser(email) != null) {
                request.setAttribute("Eemail", "Eemmail");
                request.getRequestDispatcher("../view/home.jsp").forward(request, response);
            } else {
                if (!password.equals(re_password)) {
                    request.setAttribute("falseNewPassword", "falseNewPassword");
                    request.getRequestDispatcher("/view/home.jsp").forward(request, response);
                } else {

                    db.registerAccount(username, password, 1, email, gender, false, phone);
                    EmailUtils m = new EmailUtils();
                    String confirmLink = "http://localhost:56799/QuizPracticeSystem_Group5"
                            + "/view/register?service=confirmAccount&email="
                            + email;
                    m.mailConfirmAccount(email, confirmLink, username);
                    request.setAttribute("mess", "mess");
                    request.getRequestDispatcher("../view/home.jsp").forward(request, response);
                }
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
