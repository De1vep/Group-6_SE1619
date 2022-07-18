/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dal.LessonDAO;
import dal.RegistrationAdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.LessonAdmin;
import model.Registration;
import model.RegistrationAdmin;
import model.RegistrationManage;
import model.Slider;

/**
 *
 * @author 84915
 */
@WebServlet(name = "RegistrationAdminController", urlPatterns = {"/view/RegistrationAdminController"})
public class RegistrationAdminController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    RegistrationAdminDAO dbReg = new RegistrationAdminDAO();
    ArrayList<RegistrationAdmin> listReg;
    int maxPage = 1;
    ArrayList<RegistrationAdmin> listAll;

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
//                RegistrationAdmin s = listAll.get(id);
//                request.setAttribute("reg", s);
//                request.getRequestDispatcher("../view/LessonDetail.jsp").forward(request, response);
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
            listReg = new ArrayList<>();
            listAll = dbReg.getAllRegistration();
            if (filter == 1) {
                String sub = request.getParameter("filterSubject");
                for (RegistrationAdmin item : listAll) {
                    if (item.getSubject().getSubjectName().toLowerCase().contains(sub.toLowerCase())) {
                        listReg.add(item);
                    }
                }
                request.setAttribute("sub", sub);
            } else if (filter == 2) {
                String email = request.getParameter("filterEmail");
                for (RegistrationAdmin item : listAll) {
                    if (item.getUser().getEmail().toLowerCase().contains(email.toLowerCase())) {
                        listReg.add(item);
                    }
                }
                request.setAttribute("email", email);
            } else if (filter == 3) {

                String blogLastEditAfterStr = request.getParameter("filterLastEditAfter");
                String blogLastEditBeforeStr = request.getParameter("filterLastEditBefore");
                if (!blogLastEditAfterStr.isEmpty() && !blogLastEditBeforeStr.isEmpty()) {
                    Date blogLastAfter = Date.valueOf(blogLastEditAfterStr);
                    Date blogLastBefore = Date.valueOf(blogLastEditBeforeStr);
                    for (RegistrationAdmin item : listAll) {
                        if (item.getRegTime().after(blogLastAfter) && item.getRegTime().before(blogLastBefore)) {
                            listReg.add(item);
                        }
                    }
                    request.setAttribute("blogLastEditAfterStr", blogLastAfter);
                    request.setAttribute("blogLastEditBeforeStr", blogLastBefore);
                }
            } else if (filter == 4) {

                String blogStatusStr = request.getParameter("statusFilter");
                if (!blogStatusStr.isEmpty()) {
                    int blogStatus = Integer.parseInt(blogStatusStr);
                    if (blogStatus == 1) {
                        for (RegistrationAdmin item : listAll) {
                            if (item.isStatus()) {
                                listReg.add(item);
                            }
                        }
                    } else if (blogStatus == 2) {
                        for (RegistrationAdmin item : listAll) {
                            if (!item.isStatus()) {
                                listReg.add(item);
                            }
                        }
                    } else {
                        listReg = listAll;
                    }
                    request.setAttribute("blogStatus", blogStatus);
                }

            } else {
                listReg = listAll;
            }
            ArrayList<RegistrationAdmin> listRegPerPage = new ArrayList<>();
            maxPage = listReg.size() / 5;
            if (listReg.size() % 5 != 0) {
                maxPage += 1;
            }
            if (listReg.isEmpty()) {
                maxPage = 1;
            }
            for (int i = (page - 1) * 5; i < (page * 5); i++) {
                if (i < listReg.size()) {
                    listRegPerPage.add(listReg.get(i));
                }
            }

            reDirect(request, response, listRegPerPage, page, maxPage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void changePage(HttpServletRequest request, HttpServletResponse response, int page) {
        ArrayList<RegistrationAdmin> listRegPerPage = new ArrayList<>();
        for (int i = (page - 1) * 5; i < (page * 5); i++) {
            if (i < listReg.size()) {
                listRegPerPage.add(listReg.get(i));
            }
        }
        reDirect(request, response, listRegPerPage, page, maxPage);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        int RegId = Integer.parseInt(request.getParameter("RegId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int packId = Integer.parseInt(request.getParameter("packId"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        Date validateFrom = Date.valueOf(request.getParameter("validateFrom"));
        Date validateTo = Date.valueOf(request.getParameter("validateTo"));
        String note = request.getParameter("note");
        Date lastUpdatedBy = new Date(new java.util.Date().getTime());
        String statusStr = request.getParameter("status");
        boolean status = statusStr.equals("true");
        Registration r = new Registration(RegId, userId, lastUpdatedBy, packId, cost, validateFrom, validateTo, userId, note, status);
        new RegistrationAdminDAO().Update(r);
    }

    protected void reDirect(HttpServletRequest request, HttpServletResponse response, ArrayList<RegistrationAdmin> listShow, int page, int maxpage) {
        request.setAttribute("listReg", listShow);
        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxpage);
        try {
            request.getRequestDispatcher("../view/RegistrationsAdmin.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(RegistrationAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationAdminController.class.getName()).log(Level.SEVERE, null, ex);
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
