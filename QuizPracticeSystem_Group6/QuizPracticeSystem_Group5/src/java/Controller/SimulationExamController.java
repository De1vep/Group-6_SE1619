/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import dal.QuizDAO;
import dal.SubjectDAO;
import dal.registrationDBcontext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Quiz;
import model.QuizQuizHandle;
import model.Subject;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name="SimulationExamController", urlPatterns={"/view/simulationExam"})
public class SimulationExamController extends HttpServlet {
   
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
        try {
            HttpSession session = request.getSession();
            QuizQuizHandle doingQuiz = (QuizQuizHandle) session.getAttribute("doingQuiz");
            //check if user currently taking a quiz
            if (doingQuiz != null) {
                request.setAttribute("doingQuiz", doingQuiz);
            }

            //information of simulation exam
            registrationDBcontext IRegistration = new registrationDBcontext();
            SubjectDAO ISubject = new SubjectDAO();
            User currUser = (User) session.getAttribute("account");

            String subjectSearchIdRaw = request.getParameter("subjectSearchId");
            int subjectSearchId = 0;
            if (subjectSearchIdRaw != null && !subjectSearchIdRaw.equalsIgnoreCase("")) {
                subjectSearchId = Integer.parseInt(subjectSearchIdRaw);
                request.setAttribute("subjectSearchId", subjectSearchId);
                request.setAttribute("subjectSearchName", ISubject.getSubjectbyId(subjectSearchId).getSubjectName());
            }

            String searchQuizName = request.getParameter("searchQuizName");
            if (searchQuizName != null && searchQuizName.length() > 100) {
                request.setAttribute("errorMess", "invalid length");
            }
            request.setAttribute("searchQuizName", searchQuizName);

            QuizDAO quizInterface = new QuizDAO();
            ArrayList<Subject> subjectList = IRegistration.getRegistedSubject(currUser.getUserId());
            ArrayList<Quiz> simulationList = quizInterface.getAllSimulationQuizByUser(currUser.getUserId(), subjectSearchId, searchQuizName);
            request.setAttribute("subjectList", subjectList);
            request.setAttribute("simulationList", simulationList);
            request.getRequestDispatcher("../view/simulationExam.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMess", ex.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);

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
