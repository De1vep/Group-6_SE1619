/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.LessonDAO;
import dal.PricePackageDAO;
import dal.SubjectCategoryDAO;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Lesson;
import model.LessonType;
import model.PricePackage;
import model.Subject;
import model.SubjectCategory;
import model.User;

/**
 *
 * @author DPV
 */
@WebServlet(name = "SubjectController", urlPatterns = {"/view/subject"})
public class SubjectController extends HttpServlet {

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
            out.println("<title>Servlet SubjectController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectController at " + request.getContextPath() + "</h1>");
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

        SubjectDAO subjectDao = new SubjectDAO();
        LessonDAO lessonDao = new LessonDAO();
        SubjectCategoryDAO subjectCategoryDao = new SubjectCategoryDAO();
        PricePackageDAO pricePackageDao = new PricePackageDAO();
        String service = request.getParameter("service");
        String sortBy = request.getParameter("sortBy");

        if (service != null) {
            if (service.equals("subjectList")) {

                int page = 1;
                int page_size = 4;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                int countSubject = subjectDao.countSubjectDPV();
                int totalPage = countSubject / page_size;
                if (countSubject % page_size != 0) {
                    totalPage += 1;
                }

                String previousLink = "../view/subject?service=subjectList";
                ArrayList<Subject> listSubject = subjectDao.getListSubjectByPaginationDPV(page, page_size);
                ArrayList<Subject> listFeatureSubject = subjectDao.getFeatureSubjectDPV();
                ArrayList<SubjectCategory> listSubjectCategory = subjectCategoryDao.getAllSubjectCategoryDPV();

                if (sortBy != null) {
                    if (sortBy.equals("price-asc")) {
                        Collections.sort(listSubject, new Comparator<Subject>() {
                            @Override
                            public int compare(Subject o1, Subject o2) {
                                if ((o1.getPricePackage().get(0).getSalePrice() - o2.getPricePackage().get(0).getSalePrice()) > 0) {
                                    return 1;
                                } else if (o1.getPricePackage().get(0).getSalePrice() - o2.getPricePackage().get(0).getSalePrice() < 0) {
                                    return -1;
                                } else {
                                    return 0;
                                }
                            }
                        });
                    }
                    if (sortBy.equals("price-desc")) {
                        Collections.sort(listSubject, new Comparator<Subject>() {
                            @Override
                            public int compare(Subject o1, Subject o2) {
                                if ((o2.getPricePackage().get(0).getSalePrice() - o1.getPricePackage().get(0).getSalePrice()) > 0) {
                                    return 1;
                                } else if (o2.getPricePackage().get(0).getSalePrice() - o1.getPricePackage().get(0).getSalePrice() < 0) {
                                    return -1;
                                } else {
                                    return 0;
                                }
                            }
                        });
                    }
                }

                previousLink += "&page=";

                request.setAttribute("previousLink", previousLink);
                request.setAttribute("sortBy", sortBy);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listSubject", listSubject);
                request.setAttribute("listFeatureSubject", listFeatureSubject);
                request.setAttribute("listSubjectCategory", listSubjectCategory);
                request.getRequestDispatcher("../view/subject.jsp").forward(request, response);
                return;
            }

            if (service.equals("search")) {
                String keyword = request.getParameter("keyword");
                String cateId[] = request.getParameterValues("cateId");
                String previousLink = "../view/subject?service=search&keyword=" + keyword;

                if (cateId != null) {
                    for (int i = 0; i < cateId.length; i++) {
                        previousLink += "&cateId=" + cateId[i];
                    }
                }
                int page = 1;
                int page_size = 4;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                int countSubject = subjectDao.countSubjectByKeywordAndCateIdDPV(cateId, keyword);
                int totalPage = countSubject / page_size;
                if (countSubject % page_size != 0) {
                    totalPage += 1;
                }
                ArrayList<Subject> listSubject = subjectDao.getListSubjectByKeywordAndCateIdDPV(cateId, keyword, page, page_size);
                ArrayList<Subject> listFeatureSubject = subjectDao.getFeatureSubjectDPV();
                ArrayList<SubjectCategory> listSubjectCategory = subjectCategoryDao.getAllSubjectCategoryDPV();
                if (sortBy != null) {
                    if (sortBy.equals("price-asc")) {
                        Collections.sort(listSubject, new Comparator<Subject>() {
                            @Override
                            public int compare(Subject o1, Subject o2) {
                                if ((o1.getPricePackage().get(0).getSalePrice() - o2.getPricePackage().get(0).getSalePrice()) > 0) {
                                    return 1;
                                } else if (o1.getPricePackage().get(0).getSalePrice() - o2.getPricePackage().get(0).getSalePrice() < 0) {
                                    return -1;
                                } else {
                                    return 0;
                                }
                            }
                        });
                    }
                    if (sortBy.equals("price-desc")) {
                        Collections.sort(listSubject, new Comparator<Subject>() {
                            @Override
                            public int compare(Subject o1, Subject o2) {
                                if ((o2.getPricePackage().get(0).getSalePrice() - o1.getPricePackage().get(0).getSalePrice()) > 0) {
                                    return 1;
                                } else if (o2.getPricePackage().get(0).getSalePrice() - o1.getPricePackage().get(0).getSalePrice() < 0) {
                                    return -1;
                                } else {
                                    return 0;
                                }
                            }
                        });
                    }
                }
                previousLink += "&page=";

                request.setAttribute("previousLink", previousLink);
                request.setAttribute("sortBy", sortBy);
                request.setAttribute("keyword", keyword);
                request.setAttribute("cateId", cateId);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listSubject", listSubject);
                request.setAttribute("listFeatureSubject", listFeatureSubject);
                request.setAttribute("listSubjectCategory", listSubjectCategory);
                request.getRequestDispatcher("../view/subject.jsp").forward(request, response);
                return;
            }
            if (service.equals("subjectDetails")) {
                int subjectId = Integer.parseInt(request.getParameter("subjectId"));

                Subject subject = subjectDao.getSubjectByIdDPV(subjectId);
                User subjectExpert = subjectDao.getSubjectExpertDPV(subjectId);
                int countQuestionInSubject = subjectDao.countQuestionInSubjectDPV(subjectId);
                ArrayList<Subject> listFeatureSubject = subjectDao.getFeatureSubjectDPV();
                ArrayList<SubjectCategory> listSubjectCategory = subjectCategoryDao.getAllSubjectCategoryDPV();
                ArrayList<Lesson> listLessonById = lessonDao.getLessonByIdDPV(subjectId);
                ArrayList<LessonType> listLessonType = lessonDao.getLessonTypeDPV();
                        
                request.setAttribute("subject", subject);
                request.setAttribute("subjectExpert", subjectExpert);
                request.setAttribute("countQuestionInSubject", countQuestionInSubject);
                request.setAttribute("listFeatureSubject", listFeatureSubject);
                request.setAttribute("listSubjectCategory", listSubjectCategory);
                request.setAttribute("listLessonById", listLessonById);
                request.setAttribute("listLessonType", listLessonType);
                request.getRequestDispatcher("../view/subject-details.jsp").forward(request, response);
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
