/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.AnswerDAO;
import dal.DimensionDAO;
import dal.LessonDAO;
import dal.QuestionDAO;
import dal.SubjectDAO;
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
import model.Answer;
import model.Dimension;
import model.Lesson;
import model.Question;
import model.QuestionManage;
import model.Subject;
import model.User;

/**
 *
 * @author DPV
 */
@WebServlet(name = "QuestionController", urlPatterns = {"/view/question"})
public class QuestionController extends HttpServlet {

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
            out.println("<title>Servlet QuestionController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuestionController at " + request.getContextPath() + "</h1>");
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
        User account = (User) request.getSession().getAttribute("account");
        
        String service = request.getParameter("service");
        QuestionDAO questionDao = new QuestionDAO();
        SubjectDAO subjectDao = new SubjectDAO();
        LessonDAO lessonDao = new LessonDAO();
        DimensionDAO dimensionDao = new DimensionDAO();
        if (service != null) {
            if (service.equals("questionList")) {
                int page = 1;
                int page_size = 5;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                int countQuestion = questionDao.countQuestion();
                int totalPage = countQuestion / page_size;
                if (countQuestion % page_size != 0) {
                    totalPage += 1;
                }
                String previousLink = "../view/question?service=questionList&page=";
                ArrayList<Question> listQuestion = questionDao.getListQuestionByPaginationDPV(page, page_size);
                ArrayList<Subject> listSubject = subjectDao.getAllSubjectDPV();
                ArrayList<Lesson> listLesson = lessonDao.getAllLessonDPV();
                ArrayList<Dimension> listDimension = dimensionDao.getAllDimensionDPV();

                request.setAttribute("previousLink", previousLink);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listQuestion", listQuestion);

                request.getSession().setAttribute("listSubject", listSubject);
                request.getSession().setAttribute("listLesson", listLesson);
                request.getSession().setAttribute("listDimension", listDimension);
                request.getRequestDispatcher("../view/QuestionList.jsp").forward(request, response);
                return;
            }
            if (service.equals("search")) {
                String content = request.getParameter("content");
                String previousLink = "../view/question?service=search&content=" + content;
                int page = 1;
                int page_size = 5;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                int countQuestionSearch = questionDao.countQuestionSearch(content);
                int totalPage = countQuestionSearch / page_size;
                if (countQuestionSearch % page_size != 0) {
                    totalPage += 1;
                }

                ArrayList<Question> listQuestion = questionDao.getListQuestionSearchDPV(page, page_size, content);
                previousLink += "&page=";

                request.setAttribute("previousLink", previousLink);
                request.setAttribute("content", content);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listQuestion", listQuestion);
                request.getRequestDispatcher("../view/QuestionList.jsp").forward(request, response);
                return;
            }
            if (service.equals("filter")) {
                int subjectId = Integer.parseInt(request.getParameter("subjectId"));
                int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
                int lessonId = Integer.parseInt(request.getParameter("lessonId"));
                int status = Integer.parseInt(request.getParameter("status"));

                String previousLink = "../view/question?service=filter";
                int page = 1;
                int page_size = 5;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                int countQuestionFilter = questionDao.countQuestionFilter(subjectId, dimensionId, lessonId);
                int totalPage = countQuestionFilter / page_size;
                if (countQuestionFilter % page_size != 0) {
                    totalPage += 1;
                }
                ArrayList<Question> listQuestion = questionDao.getListQuestionFilterDPV(page, page_size, subjectId, dimensionId, lessonId, status);

                previousLink += "&subjectId=" + subjectId;
                previousLink += "&dimensionId=" + dimensionId;
                previousLink += "&lessonId=" + lessonId;
                previousLink += "&status=" + status;
                previousLink += "&page=";

                request.setAttribute("previousLink", previousLink);
                request.setAttribute("subjectId", subjectId);
                request.setAttribute("dimensionId", dimensionId);
                request.setAttribute("lessonId", lessonId);
                request.setAttribute("status", status);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listQuestion", listQuestion);
                request.getRequestDispatcher("../view/QuestionList.jsp").forward(request, response);
                return;
            }
            if (service.equals("questionDetails")) {
                int questionId = Integer.parseInt(request.getParameter("questionId"));
                Question question = questionDao.getQuestionByIdDPV(questionId);

                String media = question.getMedia();
                int VIDEO_MEDIA_TYPE = 1;
                int IMAGE_MEDIA_TYPE = 2;
                if (media != null) {
                    int mediaType = VIDEO_MEDIA_TYPE;
                    String[] imageExtensions = {".jpg", ".gif", ".jpeg", ".jfif", ".pjpeg", ".png", ".pjps"};
                    for (String extension : imageExtensions) {
                        if (media.contains(extension)) {
                            mediaType = IMAGE_MEDIA_TYPE;
                        }
                    }
                    request.setAttribute("mediaType", mediaType);
                }

                ArrayList<Answer> answerList = question.getAnswers();
                Answer trueAnswer = new Answer();
                ArrayList<Answer> falseAnswer = new ArrayList<>();
                for (Answer answer : answerList) {
                    if (answer.isIsCorrect()) {
                        trueAnswer = answer;
                    } else {
                        falseAnswer.add(answer);
                    }
                }

                request.setAttribute("question", question);
                request.setAttribute("trueAnswer", trueAnswer);
                request.setAttribute("falseAnswer", falseAnswer);
                request.getRequestDispatcher("../view/QuestionDetails.jsp").forward(request, response);
                return;
            }
            if (service.equals("addQuestion")) {
                request.getRequestDispatcher("../view/AddQuestion.jsp").forward(request, response);
                return;
            }
            if (service.equals("questionImport")) {
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
        QuestionDAO questionDao = new QuestionDAO();
        SubjectDAO subjectDao = new SubjectDAO();
        LessonDAO lessonDao = new LessonDAO();
        DimensionDAO dimensionDao = new DimensionDAO();
        AnswerDAO answerDao = new AnswerDAO();
        if (service != null) {
            if (service.equals("questionUpdate")) {
                int questionId = Integer.parseInt(request.getParameter("questionId"));
                int subjectId = Integer.parseInt(request.getParameter("subject"));
                int dimensionId = Integer.parseInt(request.getParameter("dimension"));
                int lessonId = Integer.parseInt(request.getParameter("lesson"));
                boolean status = request.getParameter("questionStatus").equals("1");
                String content = request.getParameter("content").trim();
                String media = request.getParameter("media").trim();
                String explanation = request.getParameter("explanation").trim();

                String trueAnswerContent = request.getParameter("trueAnswer").trim();
                String wrongAnswer1Content = request.getParameter("wrongAnswer1").trim();
                String wrongAnswer2Content = request.getParameter("wrongAnswer2").trim();
                String wrongAnswer3Content = request.getParameter("wrongAnswer3").trim();

                int trueAnswerId = Integer.parseInt(request.getParameter("trueAnswerId"));
                int wrongAnswer1Id = Integer.parseInt(request.getParameter("wrongAnswer1Id"));
                int wrongAnswer2Id = Integer.parseInt(request.getParameter("wrongAnswer2Id"));
                int wrongAnswer3Id = Integer.parseInt(request.getParameter("wrongAnswer3Id"));

                Question questionUpdate = new Question();

                questionUpdate.setSubjectId(subjectId);
                questionUpdate.setQuestionId(questionId);
                questionUpdate.setDimensionId(dimensionId);
                questionUpdate.setLessonId(lessonId);
                questionUpdate.setContent(content);
                questionUpdate.setMedia(media);
                questionUpdate.setStatus(status);
                questionUpdate.setExplanation(explanation);

                questionDao.updateQuestionDPV(questionUpdate);

                answerDao.updateAnswerDPV(trueAnswerId, trueAnswerContent);
                answerDao.updateAnswerDPV(wrongAnswer1Id, wrongAnswer1Content);
                answerDao.updateAnswerDPV(wrongAnswer2Id, wrongAnswer2Content);
                answerDao.updateAnswerDPV(wrongAnswer3Id, wrongAnswer3Content);

                Question question = questionDao.getQuestionByIdDPV(questionId);

                String mediaNew = question.getMedia();
                int VIDEO_MEDIA_TYPE = 1;
                int IMAGE_MEDIA_TYPE = 2;
                if (mediaNew != null) {
                    int mediaType = VIDEO_MEDIA_TYPE;
                    String[] imageExtensions = {".jpg", ".gif", ".jpeg", ".jfif", ".pjpeg", ".png", ".pjps"};
                    for (String extension : imageExtensions) {
                        if (mediaNew.contains(extension)) {
                            mediaType = IMAGE_MEDIA_TYPE;
                        }
                    }
                    request.setAttribute("mediaType", mediaType);
                }

                ArrayList<Answer> answerList = question.getAnswers();
                Answer trueAnswer = new Answer();
                ArrayList<Answer> falseAnswer = new ArrayList<>();
                for (Answer answer : answerList) {
                    if (answer.isIsCorrect()) {
                        trueAnswer = answer;
                    } else {
                        falseAnswer.add(answer);
                    }
                }
                String notification = "The question has been updated.";
                request.setAttribute("notification", notification);
                request.setAttribute("question", question);
                request.setAttribute("trueAnswer", trueAnswer);
                request.setAttribute("falseAnswer", falseAnswer);
                request.getRequestDispatcher("../view/QuestionDetails.jsp").forward(request, response);
                return;
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
