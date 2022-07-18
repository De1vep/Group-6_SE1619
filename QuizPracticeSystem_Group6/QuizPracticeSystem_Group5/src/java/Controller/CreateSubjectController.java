/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dal.SubjectCategoryDAO;
import dal.SubjectDAO;
import dal.UserDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Subject;
import model.SubjectCategory;
import model.User;

/**
 *
 * @author Minh-PC
 */
@MultipartConfig
//        fileSizeThreshold = 1024 * 1024,
//        maxFileSize = 1024 * 1024 * 5,
//        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "CreateSubjectController", urlPatterns = {"/view/CreateSubject"})
public class CreateSubjectController extends HttpServlet {

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
//        response.setContentType("multipart/form-data");
        HttpSession session = request.getSession();
        User account = (User) request.getSession().getAttribute("account");
        SubjectCategoryDAO SCDAO = new SubjectCategoryDAO();
        ArrayList<SubjectCategory> listSC = SCDAO.GetAllSubjectCategory();
        request.setAttribute("listSC", listSC);
        /* If user is not logged in, or not admin/expert, redirect to index */
        if (account == null || ((account.getRoleId() != 5)
                && (account.getRoleId() != 4))) {
            sendDispatcher(request, response, "error.jsp");
        }
        int subcateId = Integer.parseInt(request.getParameter("subcateId"));
        int ExpertId = Integer.parseInt(request.getParameter("ExpertId"));
        String subname = request.getParameter("subname");
        String subdescription = request.getParameter("subdescription");
//        String subOwner = request.getParameter("subOwner");
        //  khai bao file
        Part nimage = request.getPart("nimage");
        String feature = request.getParameter("feature");
        String status = request.getParameter("status");
        boolean statuss = Boolean.parseBoolean(status);
        boolean features = Boolean.parseBoolean(feature);
        if (features == true) {
            feature = "1";
        } else {
            feature = "0";
        }
        if (statuss == true) {
            status = "1";
        } else {
            status = "0";
        }
        

        long millis = System.currentTimeMillis();
        java.sql.Date edate =new java.sql.Date(millis);  
        
        // file name trong file  -- getSubmittedFileName() :Gets the file name specified by the client
        String rawfilename = Paths.get(nimage.getSubmittedFileName()).getFileName().toString();
        String filename;
        if (rawfilename == null || rawfilename.length() == 0) {
            filename = request.getParameter("oldimage");// khong cap nhat avater , su dung anh cu
        } else {
            // getRealPath to return location
            String raw_realPath = request.getServletContext().getRealPath("");
            // xu ly  file , bo qua forder build
            String realPath = raw_realPath.substring(0, raw_realPath.indexOf("build")) + "web\\assets//subject";
            //  xu ly lai file name moi  
            int index = rawfilename.lastIndexOf('.');
            String typeFile = "";
            if (index > 0) {
                typeFile = rawfilename.substring(index + 1);
            }
            filename = "SubjectIMG" + millis + "." + typeFile;
            // Save file at location 
            InputStream is = nimage.getInputStream();
            // File Separator :  ký tự được sử dụng để phân tách các tên thư mục tạo nên đường dẫn đến một vị trí cụ thể.
            // StandardCopyOption.REPLACE_EXISTING : di chuyen file , ghi de len file neu file do da ton tai
            Files.copy(is, Paths.get(realPath + File.separator + filename));

        }
        SubjectDAO SubjectDAO = new SubjectDAO();
        Subject s = new Subject();
        SubjectDAO.insertSubject(subname, subdescription, filename, features, false,edate);
        ArrayList<Subject> ls;
        int sid = 0;
        try {
            ls = SubjectDAO.getAllSubject();
            sid = ls.get(ls.size() - 1).getSubjectId();
        } catch (Exception ex) {
            Logger.getLogger(CreateSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SubjectDAO.insertSubjectExpert(sid,ExpertId,true);
        SubjectDAO.insertCateSubject(sid, subcateId);
        sendDispatcher(request, response, "CourseContentList?service=CourseContentList");
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
        UserDAO userDAO = new UserDAO();
        ArrayList<User> listuser = userDAO.GetAllExpert();
        SubjectCategoryDAO SCDAO = new SubjectCategoryDAO();
        ArrayList<SubjectCategory> listSC = SCDAO.GetAllSubjectCategory();
        request.setAttribute("listSC", listSC);
        request.setAttribute("listuser", listuser);
        request.getRequestDispatcher("/view/CreateSubject.jsp").forward(request, response);
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
