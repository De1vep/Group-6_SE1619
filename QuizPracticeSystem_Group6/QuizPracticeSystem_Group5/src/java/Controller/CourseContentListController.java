/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dal.DimensionDAO;
import dal.PricePackageDAO;
import dal.SubjectCategoryDAO;
import dal.SubjectDAO;
import dal.UserDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Subject;
import model.SubjectCategory;
import model.User;

/**
 *
 * @author Minh-PC
 */
@WebServlet(name = "CourseContentListController", urlPatterns = {"/view/CourseContentList"})
public class CourseContentListController extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            SubjectDAO subjectDAO = new SubjectDAO();
            User account = (User) request.getSession().getAttribute("account");
            SubjectCategoryDAO SCDAO = new SubjectCategoryDAO();
            ArrayList<SubjectCategory> listSC = SCDAO.GetAllSubjectCategory();
            request.setAttribute("listSC", listSC);
            UserDAO userDAO = new UserDAO();
            String keyword = request.getParameter("keyword");
            if (service != null) {
                if (service.equalsIgnoreCase("CourseContentList")) {

                    if (account == null || ((account.getRoleId() != 5)
                            && (account.getRoleId() != 4))) {
                        sendDispatcher(request, response, "error.jsp");
                    } else if (account.getRoleId() == 5) {
                        int page = 1;
                        int page_size = 2;
                        String pageStr = request.getParameter("page");
                        if (pageStr != null) {
                            page = Integer.parseInt(pageStr);
                        }

                        /*url to page=?, with service=SubjectList*/
                        String previousLink = "../view/CourseContentList?service=CourseContentList&&page=";
                        /*get list subject by page*/
                        ArrayList<Subject> listSubjectByPagination = new ArrayList<>();
                        ArrayList<Subject> getallSubject = new ArrayList<>();
                        getallSubject = subjectDAO.getAllSubject();
                        listSubjectByPagination = subjectDAO.getListSubjectByKeyword(page, page_size, getallSubject);
                        int countSubject = getallSubject.size();
                        int totalPage = countSubject / page_size;
                        if (countSubject % page_size != 0) {
                            totalPage += 1;
                        }
                        /*send page now, toltal page and url to page=? */

                        request.setAttribute("page", page);
                        request.setAttribute("totalPage", totalPage);
                        request.setAttribute("previousLink", previousLink);
                        /*send list latest blog and list blog*/
                        request.setAttribute("listSubject", listSubjectByPagination);
                        sendDispatcher(request, response, "CourseContentList.jsp");
                        return;
                    } else if (account.getRoleId() == 4) {
                        int page = 1;
                        int page_size = 2;
                        String pageStr = request.getParameter("page");
                        if (pageStr != null) {
                            page = Integer.parseInt(pageStr);
                        }
                        String previousLink = "../view/CourseContentList?service=CourseContentList&&page=";
                        /*get list subject by page*/
                        ArrayList<Subject> listSubjectByPagination = new ArrayList<>();
                        listSubjectByPagination = subjectDAO.getListSubjectByPaginationEX(page, page_size, account.getUserId());
                        int countSubject = listSubjectByPagination.size();
                        int totalPage = countSubject / page_size;
                        if (countSubject % page_size != 0) {
                            totalPage += 1;
                        }
                        /*url to page=?, with service=SubjectList*/

 /*send page now, toltal page and url to page=? */
                        request.setAttribute("page", page);
                        request.setAttribute("totalPage", totalPage);
                        request.setAttribute("previousLink", previousLink);
                        /*send list latest blog and list blog*/
                        request.setAttribute("listSubject", listSubjectByPagination);
                        sendDispatcher(request, response, "CourseContentList.jsp");
                        return;
                    } else {
                        sendDispatcher(request, response, "home.jsp");
                    }
                }
                if (service.equalsIgnoreCase("search")) {
                    if (account == null || ((account.getRoleId() != 5)
                            && (account.getRoleId() != 4))) {
                        sendDispatcher(request, response, "error.jsp");
                    } else if (account.getRoleId() == 5) {

                        /*url to page=?, with service=search*/
                        String previousLink = "../view/CourseContentList?service=search&keyword=" + keyword;

                        previousLink += "&&page=";

                        /*Process pagination*/
                        int page = 1;
                        int page_size = 2;
                        String pageStr = request.getParameter("page");
                        if (pageStr != null) {
                            page = Integer.parseInt(pageStr);
                        }
                        ArrayList<Subject> ListSubjectByKeyword = new ArrayList<>();
                        ArrayList<Subject> listSubjectByPagination = new ArrayList<>();
                        if (keyword.isEmpty()) {
                            try {
                                ListSubjectByKeyword = subjectDAO.getAllSubject();
                            } catch (Exception ex) {
                                Logger.getLogger(CourseContentListController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            ListSubjectByKeyword = subjectDAO.getListSubjectByKeyword(keyword);
                        }
                        listSubjectByPagination = subjectDAO.getListSubjectByKeyword(page, page_size, ListSubjectByKeyword);
                        int countSubject = ListSubjectByKeyword.size();
                        int totalPage = countSubject / page_size;
                        if (countSubject % page_size != 0) {
                            totalPage += 1;
                        }
                        request.setAttribute("page", page);
                        request.setAttribute("totalPage", totalPage);
                        request.setAttribute("keyword", keyword);
                        request.setAttribute("previousLink", previousLink);
                        /*send list blog by page*/
                        request.setAttribute("listSubject", listSubjectByPagination);
                        sendDispatcher(request, response, "CourseContentList.jsp");
                        return;
                    } else if (account.getRoleId() == 4) {
//                        String keyword = request.getParameter("keyword");
                        /*url to page=?, with service=search*/
                        String previousLink = "../view/CourseContentList?service=search&keyword=" + keyword;

                        previousLink += "&&page=";

                        /*Process pagination*/
                        int page = 1;
                        int page_size = 2;
                        String pageStr = request.getParameter("page");
                        if (pageStr != null) {
                            page = Integer.parseInt(pageStr);
                        }
                        ArrayList<Subject> ListSubjectByKeywordE = new ArrayList<>();
                        ArrayList<Subject> listSubjectByPagination = new ArrayList<>();
                        if (keyword.isEmpty()) {
                            ListSubjectByKeywordE = subjectDAO.getListSubjectByKeywordE(account.getUserId());
                        } else {
                            ListSubjectByKeywordE = subjectDAO.getListSubjectByKeywordE(keyword, account.getUserId());
                        }

                        listSubjectByPagination = subjectDAO.getListSubjectByKeyword(page, page_size, ListSubjectByKeywordE);
                        int countSubject = ListSubjectByKeywordE.size();
                        int totalPage = countSubject / page_size;
                        if (countSubject % page_size != 0) {
                            totalPage += 1;
                        }
                        request.setAttribute("page", page);
                        request.setAttribute("totalPage", totalPage);
                        request.setAttribute("keyword", keyword);
                        request.setAttribute("previousLink", previousLink);
                        /*send list blog by page*/
                        request.setAttribute("listSubject", listSubjectByPagination);
                        sendDispatcher(request, response, "CourseContentList.jsp");
                        return;
                    } else {
                        sendDispatcher(request, response, "home.jsp");
                    }
                }
                if (service.equalsIgnoreCase("FilterByCategotyAndStatus")) {
                    if (account == null || ((account.getRoleId() != 5)
                            && (account.getRoleId() != 4))) {
                        sendDispatcher(request, response, "error.jsp");
                    } else if (account.getRoleId() == 5) {

                        int subjectCateId = Integer.parseInt(request.getParameter("subjectCateId"));
                        int status = Integer.parseInt(request.getParameter("status"));
                        String previousLink = "../view/CourseContentList?service=FilterByCategotyAndStatus&&page=";
                        int page = 1;
                        int page_size = 2;
                        String pageStr = request.getParameter("page");
                        if (pageStr != null) {
                            page = Integer.parseInt(pageStr);
                        }

                        ArrayList<Subject> listSubjectByCategoryAndStatus = subjectDAO.getFiterByCategoryAndStatus(subjectCateId, status);
                        ArrayList<Subject> listSubjectByPagination = subjectDAO.getListSubjectByKeyword(page, page_size, listSubjectByCategoryAndStatus);
                        int countSubject = listSubjectByCategoryAndStatus.size();
                        int totalPage = countSubject / page_size;
                        if (countSubject % page_size != 0) {
                            totalPage += 1;
                        }

                        request.setAttribute("page", page);
                        request.setAttribute("totalPage", totalPage);
                        request.setAttribute("previousLink", previousLink);
                        request.setAttribute("listSubject", listSubjectByPagination);
                        sendDispatcher(request, response, "CourseContentList.jsp");
                        return;
                    } else if (account.getRoleId() == 4) {
                        int subjectCateId = Integer.parseInt(request.getParameter("subjectCateId"));
                        int status = Integer.parseInt(request.getParameter("status"));
                        String previousLink = "../view/CourseContentList?service=FilterByCategotyAndStatus&&page=";
                        int page = 1;
                        int page_size = 2;
                        String pageStr = request.getParameter("page");
                        if (pageStr != null) {
                            page = Integer.parseInt(pageStr);
                        }
                        ArrayList<Subject> listSubjectByCategoryAndStatus = subjectDAO.getFiterByCategoryAndStatus(subjectCateId, status, account.getUserId());
                        ArrayList<Subject> listSubjectByPagination = subjectDAO.getListSubjectByKeyword(page, page_size, listSubjectByCategoryAndStatus);
                        int countSubject = listSubjectByCategoryAndStatus.size();
                        int totalPage = countSubject / page_size;
                        if (countSubject % page_size != 0) {
                            totalPage += 1;
                        }
                        request.setAttribute("page", page);
                        request.setAttribute("totalPage", totalPage);
                        request.setAttribute("previousLink", previousLink);
                        request.setAttribute("listSubject", listSubjectByPagination);
                        sendDispatcher(request, response, "CourseContentList.jsp");
                        return;
                    } else {
                        sendDispatcher(request, response, "error.jsp");
                    }
                }
                if (service.equalsIgnoreCase("deletesubject")) {
                    int subjectId = Integer.parseInt(request.getParameter("subjectId"));
                    if (account == null || ((account.getRoleId() != 5)
                            && (account.getRoleId() != 4))) {
                        sendDispatcher(request, response, "error.jsp");
                    }else if(account.getRoleId()==5){
                        DimensionDAO DDAO= new DimensionDAO();
                        PricePackageDAO PPDAO = new PricePackageDAO();
                        PPDAO.deletePricePackageBySubId(subjectId);
                        DDAO.deleteDimensionbySubID(subjectId);
                        userDAO.deleteSubjectExpert(subjectId);
                        SCDAO.deleteSubjectCate(subjectId);
                        subjectDAO.deleteSubject(subjectId);
                        sendDispatcher(request, response, "CourseContentList?service=CourseContentList");
                    }else{
                        sendDispatcher(request, response, "error.jsp");
                    }

                }
            }
        }
    }

    public void sendDispatcher(HttpServletRequest request, HttpServletResponse response, String path) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.forward(request, response);

        } catch (ServletException | IOException ex) {
            Logger.getLogger(SubjectController.class
                    .getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CourseContentListController.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CourseContentListController.class.getName()).log(Level.SEVERE, null, ex);
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
