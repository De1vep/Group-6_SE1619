/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.SubjectCategoryDAO;
import dal.registrationDBcontext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PricePackage;
import model.RegistrationManage;
import model.SubjectCategory;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "MyRegistrationController", urlPatterns = {"/view/MyRegistration"})
public class MyRegistrationController extends HttpServlet {

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
            out.println("<title>Servlet MyRegistrationController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyRegistrationController at " + request.getContextPath() + "</h1>");
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
        
        User account = (User)request.getSession().getAttribute("account");
        String service = request.getParameter("service");
        registrationDBcontext rdb = new registrationDBcontext();
        SubjectCategoryDAO sdb = new SubjectCategoryDAO();
        if (service != null) {
            if (service.equalsIgnoreCase("listRegistration")) {
                final int PAGE_SIZE = 10;
                int page = 1;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                
                String previousLink = "MyRegistration?service=listRegistration&&page=";
                request.setAttribute("previousLink", previousLink);
                
                ArrayList<RegistrationManage> Registrations = rdb.GetAllRegistrationByUserIdWithPage(account.getUserId(), page, PAGE_SIZE);//theo userId tam thoi fix cung
                int totalProducts = rdb.CountRegistrationByUserId(account.getUserId());
                int totalPage = totalProducts / PAGE_SIZE;
                if (totalProducts % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                
                ArrayList<PricePackage> PricePackages = rdb.getAllPricePackage();
                request.setAttribute("PricePackages", PricePackages);
                
                
                ArrayList<SubjectCategory> SubjectCategorys = sdb.GetAllSubjectCategory();
                request.setAttribute("SubjectCategorys", SubjectCategorys);
                
                request.setAttribute("page", page);
                request.getSession().setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("Registrations", Registrations);
                request.getRequestDispatcher("../view/myRegistration.jsp").forward(request, response);
                return;
            }
            
            if (service.equalsIgnoreCase("search")) {
                String subjectCategorySearch = request.getParameter("subjectCategorySearch").trim();
                if (subjectCategorySearch != null) {
                    int subjectCategoryId = Integer.parseInt(subjectCategorySearch);
                    final int PAGE_SIZE = 10;
                    int page = 1;
                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    ArrayList<RegistrationManage> Registrations = rdb.GetAllRegistrationByUserNameWithPage(subjectCategoryId, account.getUserId(), page, PAGE_SIZE);//theo userId tam thoi fix cung
                    int totalProducts = rdb.CountRegistrationByUserName(subjectCategoryId, account.getUserId());
                    int totalPage = totalProducts / PAGE_SIZE;
                    if (totalProducts % PAGE_SIZE != 0) {
                        totalPage += 1;
                    }
                    
                    String previousLink = "MyRegistration?service=search&subjectCategorySearch="+subjectCategoryId+"&&page=";
                    request.setAttribute("previousLink", previousLink);
                    
                     ArrayList<SubjectCategory> SubjectCategorys = sdb.GetAllSubjectCategory();
                    request.setAttribute("SubjectCategorys", SubjectCategorys);
                    
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    request.setAttribute("Registrations", Registrations);
                    request.setAttribute("subjectCategoryId", subjectCategoryId);
                    
                    request.getRequestDispatcher("../view/myRegistration.jsp").forward(request, response);
                } else {

                }
            }
            
            if (service.equalsIgnoreCase("deleteRegistration")) {
                int regId = Integer.parseInt(request.getParameter("regId"));
                rdb.deleteRegistration(regId);
                response.sendRedirect("MyRegistration?service=listRegistration");
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
        User account = (User) request.getSession().getAttribute("account");
        registrationDBcontext rdb = new registrationDBcontext();
        SubjectCategoryDAO sdb = new SubjectCategoryDAO();
        if (service != null) {
            if (service.equalsIgnoreCase("editRegistration")) {
                int page = (int) request.getSession().getAttribute("page");
                int regId = Integer.parseInt(request.getParameter("regId"));
                int packageId = Integer.parseInt(request.getParameter("package"));
                String note = request.getParameter("note");
                rdb.upDateRegistration(packageId, 5, note, regId);
                response.sendRedirect("MyRegistration?service=listRegistration&&page="+page);
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
