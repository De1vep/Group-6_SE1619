/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.SliderDAO;
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
import model.Blog;
import model.Slider;

/**
 *
 * @author 84915
 */
@WebServlet(name = "SliderController", urlPatterns = {"/view/slider"})
public class SliderController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    SliderDAO dbSlider = new SliderDAO();
    ArrayList<Slider> sliders = dbSlider.getAllSlider();
    int maxPage = 1;
    ArrayList<Slider> listAll;

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
                Slider s = listAll.get(id);
                request.setAttribute("slider", s);
                request.getRequestDispatcher("../view/SliderDetail.jsp").forward(request, response);
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

    protected void update(HttpServletRequest request, HttpServletResponse response) {
        int sliderId = Integer.parseInt(request.getParameter("sliderId"));
        String sliderTitle = request.getParameter("sliderTitle");
        String image = request.getParameter("thumnail");
        String sliderLink = request.getParameter("sliderLink");
        boolean sliderStatus = false;
        if (request.getParameter("sliderStatus").equals("1")) {
            sliderStatus = true;
        }
        String sliderNote = request.getParameter("sliderNote");
        Slider s = new Slider(sliderId, sliderTitle, image, sliderLink, sliderNote, sliderStatus);
        int row_effects = dbSlider.UpdateSlider(s);
        if (row_effects > 0) {
            System.out.println("slider updated");
        }
    }

    protected void list(HttpServletRequest request, HttpServletResponse response, int page, int filter) {
        try {
            listAll = new ArrayList<>();
            sliders = new ArrayList<>();
            listAll = dbSlider.getAllSlider();
            if (filter == 1) {
                String tilte = request.getParameter("filterTitle");
                for (Slider item : listAll) {
                    if (item.getSliderTitle().toLowerCase().contains(tilte.toLowerCase())) {
                        sliders.add(item);
                    }
                }
            } else if (filter == 2) {
                String backlink = request.getParameter("filterBacklink");
                for (Slider item : listAll) {
                    if (item.getLink().toLowerCase().equals(backlink.toLowerCase())) {
                        sliders.add(item);
                    }
                }
            } else if (filter == 3) {

                String blogStatusStr = request.getParameter("statusFilter");
                if (!blogStatusStr.isEmpty()) {
                    int blogStatus = Integer.parseInt(blogStatusStr);
                    if (blogStatus == 1) {
                        for (Slider item : listAll) {
                            if (item.isStatus()) {
                                sliders.add(item);
                            }
                        }
                    } else if (blogStatus == 0) {
                        for (Slider item : listAll) {
                            if (!item.isStatus()) {
                                sliders.add(item);
                            }
                        }
                    } else {
                        sliders = listAll;
                    }
                }

            } else {
                sliders = listAll;
            }

            ArrayList<Slider> listSliderPerPage = new ArrayList<>();
            maxPage = sliders.size() / 5;
            if (sliders.size() % 5 != 0) {
                maxPage += 1;
            }
            if (sliders.isEmpty()) {
                maxPage = 1;
            }
            for (int i = (page - 1) * 5; i < (page * 5); i++) {
                if (i < sliders.size()) {
                    listSliderPerPage.add(sliders.get(i));
                }
            }
            reDirect(request, response, listSliderPerPage, page, maxPage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void changePage(HttpServletRequest request, HttpServletResponse response, int page) {
        ArrayList<Slider> listSliderPerPage = new ArrayList<>();
        for (int i = (page - 1) * 5; i < (page * 5); i++) {
            if (i < sliders.size()) {
                listSliderPerPage.add(sliders.get(i));
            }
        }
        reDirect(request, response, listSliderPerPage, page, maxPage);
    }

    protected void reDirect(HttpServletRequest request, HttpServletResponse response, ArrayList<Slider> listShow, int page, int maxpage) {
        request.setAttribute("sliders", listShow);
        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxpage);
        try {
            request.getRequestDispatcher("../view/ListSliders.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(BlogAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BlogAdminController.class.getName()).log(Level.SEVERE, null, ex);
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
