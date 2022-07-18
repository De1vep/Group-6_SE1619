/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.CustomerQuizDAO;
import dal.DimensionDAO;
import dal.QuestionDAO;
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
import model.CustomerQuiz;
import model.Dimension;
import model.Question;
import model.Quiz;
import model.QuizQuizHandle;
import model.Subject;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "PracticeListController", urlPatterns = {"/view/PracticeList"})
public class PracticeListController extends HttpServlet {

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

        String service = request.getParameter("service");

        //Get all information to display in the practiceDetail page
        if (service.equalsIgnoreCase("getPracticeDetail")) {
            try {
                //User Account = (User) request.getSession().getAttribute("account");
                SubjectDAO subjectDAO = new SubjectDAO();
                DimensionDAO dimensionDAO = new DimensionDAO();
                //Get all subject that user have registed
                ArrayList<Subject> registedSubject = subjectDAO.getAllSubject();
                ArrayList<Dimension> dimension = dimensionDAO.getAllDimension();
                request.getSession().setAttribute("registedSubject", registedSubject);
                request.getSession().setAttribute("dimensionTypes", dimension);
                request.getRequestDispatcher("../view/practiceDetail.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(PracticeListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //create quiz that meet user's requirement
        if (service.equalsIgnoreCase("createPractice")) {
            try {
                //Get user's input values
                int subjectId = Integer.parseInt(request.getParameter("subject"));
                int numberOfQuestion = Integer.parseInt(request.getParameter("numberOfQuestion"));
                int dimensionId = Integer.parseInt(request.getParameter("dimension"));
                int duration = Integer.parseInt(request.getParameter("duration"));
                QuestionDAO questionDAO = new QuestionDAO();
                QuizDAO quizDAO = new QuizDAO();
                SubjectDAO subjectDAO = new SubjectDAO();
                //select question that meet user requirement
                ArrayList<Question> questionList = questionDAO.getQuestionForCreateQuiz(numberOfQuestion, subjectId, dimensionId);

                // if there isn't any question meet user requirement then display message
                if (questionList.size() == 0) {
                    String mess = "There isn't any question that meet your require!";
                    request.setAttribute("message", mess);
                    request.getRequestDispatcher("jsp/practiceDetail.jsp").forward(request, response);
                    return;
                }

                //setup quiz information to create new ppractice
                Quiz quiz = new Quiz();
                Subject subject = subjectDAO.getSubjectbyId(subjectId);
                quiz.setQuizName("Practice Quiz");
                quiz.setSubject(subject);
                quiz.setQuizDuration(duration * 60);
                quiz.setTestTypeId(3);
                quiz.setNumberQuestion(questionList.size());
                quiz.setDimensionTypeId(dimensionId);
                quiz.setStatus(true);
                quizDAO.addQuiz(quiz);// add practice
                // add practice questions
                Quiz practice = quizDAO.getQuizById(quizDAO.getQuizIdCreated(quiz));
                for (Question question : questionList) {
                    quizDAO.addQuizQuestion(practice.getQuizId(), question.getQuestionId());
                }
                response.getWriter().print("aaaa");
//                response.sendRedirect("quizHandleController?service=quizEntrance&quizId=" + practice.getQuizId());
            } catch (Exception ex) {
                Logger.getLogger(PracticeListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Get information to display in the practiceList
        if (service.equalsIgnoreCase("getPracticeListInformation")) {
            try {
                User Account = (User) request.getSession().getAttribute("account");
                registrationDBcontext registrationDAO = new registrationDBcontext();
                CustomerQuizDAO customerQuizDAO = new CustomerQuizDAO();
                QuizDAO quizDAO = new QuizDAO();
                ArrayList<Subject> registedSubject = registrationDAO.getRegistedSubject(1);

                final int PAGE_SIZE = 5;
                int page = 1;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }

                String previousLink = "PracticeList?service=getPracticeListInformation&&page=";
                request.setAttribute("previousLink", previousLink);

                ArrayList<CustomerQuiz> customerQuizs = customerQuizDAO.getQuizByUserBypageing(Account.getUserId(), page, PAGE_SIZE);
                int totalcustomerQuiz = customerQuizDAO.CountCustomerQuizByUserId(Account.getUserId());
                int totalPage = totalcustomerQuiz / PAGE_SIZE;
                if (totalcustomerQuiz % PAGE_SIZE != 0) {
                    totalPage += 1;
                }

                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("registedSubject", registedSubject);
                request.setAttribute("customerQuizs", customerQuizs);

                Object object = request.getSession().getAttribute("doingQuiz");
                if (object != null) {
                    QuizQuizHandle doingQuiz = (QuizQuizHandle) object;
                    Quiz doingQuizInfo = quizDAO.getQuizById(doingQuiz.getQuiz().getQuizId());
                    request.setAttribute("doingQuiz", doingQuizInfo);
                }

                request.getRequestDispatcher("../view/practiceList.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(PracticeListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //update practice list information
        if (service.equalsIgnoreCase("filterPracticeListInformation")) {
            try {
                User Account = (User) request.getSession().getAttribute("account");
                int subjectId = Integer.parseInt(request.getParameter("subjectId"));
                if (subjectId == 0) {
                    try {
                        
                        registrationDBcontext registrationDAO = new registrationDBcontext();
                        CustomerQuizDAO customerQuizDAO = new CustomerQuizDAO();
                        QuizDAO quizDAO = new QuizDAO();
                        ArrayList<Subject> registedSubject = registrationDAO.getRegistedSubject(1);

                        final int PAGE_SIZE = 5;
                        int page = 1;
                        String pageStr = request.getParameter("page");
                        if (pageStr != null) {
                            page = Integer.parseInt(pageStr);
                        }

                        String previousLink = "PracticeList?service=getPracticeListInformation&&page=";
                        request.setAttribute("previousLink", previousLink);

                        ArrayList<CustomerQuiz> customerQuizs = customerQuizDAO.getQuizByUserBypageing(Account.getUserId(), page, PAGE_SIZE);
                        int totalcustomerQuiz = customerQuizDAO.CountCustomerQuizByUserId(Account.getUserId());
                        int totalPage = totalcustomerQuiz / PAGE_SIZE;
                        if (totalcustomerQuiz % PAGE_SIZE != 0) {
                            totalPage += 1;
                        }

                        request.setAttribute("page", page);
                        request.setAttribute("totalPage", totalPage);
                        request.setAttribute("registedSubject", registedSubject);
                        request.setAttribute("customerQuizs", customerQuizs);

                        Object object = request.getSession().getAttribute("doingQuiz");
                        if (object != null) {
                            QuizQuizHandle doingQuiz = (QuizQuizHandle) object;
                            Quiz doingQuizInfo = quizDAO.getQuizById(doingQuiz.getQuiz().getQuizId());
                            request.setAttribute("doingQuiz", doingQuizInfo);
                        }

                        request.getRequestDispatcher("../view/practiceList.jsp").forward(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(PracticeListController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    registrationDBcontext registrationDAO = new registrationDBcontext();
                    CustomerQuizDAO customerQuizDAO = new CustomerQuizDAO();
                    QuizDAO quizDAO = new QuizDAO();
                    ArrayList<CustomerQuiz> filteredCustomerQuizs = new ArrayList<>();
                    //get all quiz of the user  

                    final int PAGE_SIZE = 5;
                    int page = 1;
                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }

                    String previousLink = "PracticeList?service=filterPracticeListInformation&&subjectId=" + subjectId + "&&page=";
                    request.setAttribute("previousLink", previousLink);

                    ArrayList<CustomerQuiz> customerQuizs = customerQuizDAO.getQuizByUserBySubjectIdBypageing(Account.getUserId(), subjectId, page, PAGE_SIZE);
                    int totalcustomerQuiz = customerQuizDAO.CountCustomerQuizByUserIdBySubjectId(Account.getUserId(), subjectId);
                    int totalPage = totalcustomerQuiz / PAGE_SIZE;
                    if (totalcustomerQuiz % PAGE_SIZE != 0) {
                        totalPage += 1;
                    }

                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);

                    ArrayList<Subject> registedSubject = registrationDAO.getRegistedSubject(1);
                    request.setAttribute("registedSubject", registedSubject);

                    request.setAttribute("subjectId", subjectId);
                    request.setAttribute("customerQuizs", customerQuizs);
                    request.getRequestDispatcher("../view/practiceList.jsp").forward(request, response);
                }

            } catch (Exception ex) {
                Logger.getLogger(PracticeListController.class.getName()).log(Level.SEVERE, null, ex);
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
