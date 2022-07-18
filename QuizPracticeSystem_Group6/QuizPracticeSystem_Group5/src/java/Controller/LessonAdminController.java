/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.LessonDAO;
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
import model.Lesson;
import model.LessonAdmin;
import model.Slider;

/**
 *
 * @author 84915
 */
@WebServlet(name = "LessonAdminController", urlPatterns = {"/view/LessonAdminController"})
public class LessonAdminController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    LessonDAO dbLess = new LessonDAO();
    ArrayList<LessonAdmin> listLesson;
    int maxPage = 1;
    ArrayList<LessonAdmin> listAll;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = "";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        int page = 1;
        int filter = 0;

        switch (action) {
            case "detail":
                int id = Integer.parseInt(request.getParameter("id"));
                LessonAdmin s = listAll.get(id);
                request.setAttribute("lesson", s);
                request.getRequestDispatcher("../view/LessonDetail.jsp").forward(request, response);
                break;
            case "Update":
                update(request, response);
                list(request, response, 1, 0);
                break;
            case "filter":
                try {
                //page = Integer.parseInt(request.getParameter("page"));
                filter = Integer.parseInt(request.getParameter("filterType"));
                //update(request, response);
            } catch (Exception e) {
                filter = 0;
            }
            list(request, response, 1, filter);
            break;
            case "changePage":
                try {
                page = Integer.parseInt(request.getParameter("page"));
                //update(request, response);
            } catch (Exception e) {
            }
            changePage(request, response, page);
            break;
            default:
                list(request, response, 1, 0);
                break;
        }
    }

    protected void list(HttpServletRequest request, HttpServletResponse response, int page, int filter) {
        try {
            listAll = new ArrayList<>();
            listLesson = new ArrayList<>();
            listAll = dbLess.getAllLesson();
            if (filter == 1) {
                String sub = request.getParameter("filterSubject");
                for (LessonAdmin item : listAll) {
                    if (item.getSubject().getSubjectName().toLowerCase().contains(sub.toLowerCase())) {
                        listLesson.add(item);
                    }
                }
            } else if (filter == 2) {
                String name = request.getParameter("filterName");
                for (LessonAdmin item : listAll) {
                    if (item.getLessonName().toLowerCase().contains(name.toLowerCase())) {
                        listLesson.add(item);
                    }
                }
            } else if (filter == 3) {
                int order = Integer.parseInt(request.getParameter("filterOrder"));
                for (LessonAdmin item : listAll) {
                    if (item.getLessonOrder() == order) {
                        listLesson.add(item);
                    }
                }
            } else if (filter == 4) {
                int type = Integer.parseInt(request.getParameter("typeFilter"));
                int blogStatus = Integer.parseInt(request.getParameter("statusFilter"));
                boolean status = true;
                if (blogStatus == 2) {
                    status = false;
                }
                if (type > 0 && blogStatus > 0) {
                    for (LessonAdmin item : listAll) {
                        if (item.getLessonType().getLessonTypeId() == type && item.isStatus() == status) {
                            listLesson.add(item);
                        }
                    }
                } else if (type > 0 && blogStatus < 0) {
                    for (LessonAdmin item : listAll) {
                        if (item.getLessonType().getLessonTypeId() == type) {
                            listLesson.add(item);
                        }
                    }
                } else if (type < 0 && blogStatus > 0) {
                    for (LessonAdmin item : listAll) {
                        if (item.isStatus() == status) {
                            listLesson.add(item);
                        }
                    }
                } else {
                    listLesson = listAll;
                }

            } else {
                listLesson = listAll;
            }

            ArrayList<LessonAdmin> listLessPerPage = new ArrayList<>();
            maxPage = listLesson.size() / 5;
            if (listLesson.size() % 5 != 0) {
                maxPage += 1;
            }
            if (listLesson.isEmpty()) {
                maxPage = 1;
            }
            for (int i = (page - 1) * 5; i < (page * 5); i++) {
                if (i < listLesson.size()) {
                    listLessPerPage.add(listLesson.get(i));
                }
            }

            reDirect(request, response, listLessPerPage, page, maxPage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void changePage(HttpServletRequest request, HttpServletResponse response, int page) {
        ArrayList<LessonAdmin> listLessPerPage = new ArrayList<>();
        for (int i = (page - 1) * 5; i < (page * 5); i++) {
            if (i < listLesson.size()) {
                listLessPerPage.add(listLesson.get(i));
            }
        }
        reDirect(request, response, listLessPerPage, page, maxPage);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        int lesId = Integer.parseInt(request.getParameter("lesId"));
        String lesSub = request.getParameter("lesSub");
        String lesName = request.getParameter("lesName");
        int lesOrder = Integer.parseInt(request.getParameter("lesOrder"));
        String lesLink = request.getParameter("lesLink");
        String lesContent = request.getParameter("lesContent");
        int lesType = Integer.parseInt(request.getParameter("lesType"));
        boolean lesStatus = false;
        if (request.getParameter("lesStatus").equals("1")) {
            lesStatus = true;
        }

        int row_effects = dbLess.updateLess(lesId, lesSub, lesName, lesOrder, lesLink, lesContent, lesType, lesStatus);
    }

    protected void reDirect(HttpServletRequest request, HttpServletResponse response, ArrayList<LessonAdmin> listShow, int page, int maxpage) {
        request.setAttribute("listLesson", listShow);
        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxpage);
        try {
            request.getRequestDispatcher("../view/LessonAdmin.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(BlogAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BlogAdminController.class.getName()).log(Level.SEVERE, null, ex);
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
