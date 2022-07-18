/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import dal.UserDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.User;

/**
 *
 * @author Admin
 */

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name="userProfileController", urlPatterns={"/view/userProfile"})
public class userProfileController extends HttpServlet {
   
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
        int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String nusername = request.getParameter("nusername");
        String nphone = request.getParameter("nphone");
        //  khai bao file
        Part nimage = request.getPart("nimage");
        String ngender = request.getParameter("ngender");
        boolean ngenders = Boolean.parseBoolean(ngender);
        long millis = System.currentTimeMillis();
        // change profile
        if (ngenders == true) {
            ngender = "Male";
        } else {
            ngender = "Female";
        }
        ;
        // file name trong file  -- getSubmittedFileName() :Gets the file name specified by the client
        String rawfilename = Paths.get(nimage.getSubmittedFileName()).getFileName().toString();
        String filename;
        if (rawfilename == null || rawfilename.length() == 0) {
            filename = request.getParameter("oldimage");// khong cap nhat avater , su dung anh cu
        } else {
            // getRealPath to return location
            String raw_realPath = request.getServletContext().getRealPath("");
            // xu ly  file , bo qua forder build
            String realPath = raw_realPath.substring(0, raw_realPath.indexOf("build")) + "web\\assets";
            //  xu ly lai file name moi  
            int index = rawfilename.lastIndexOf('.');
            String typeFile = "";
            if (index > 0) {
                typeFile = rawfilename.substring(index + 1);
            }
            filename = id + "userimage" + millis + "." + typeFile;
            // Save file at location 
            InputStream is = nimage.getInputStream();
            // File Separator :  ký tự được sử dụng để phân tách các tên thư mục tạo nên đường dẫn đến một vị trí cụ thể.
            // StandardCopyOption.REPLACE_EXISTING : di chuyen file , ghi de len file neu file do da ton tai
            Files.copy(is, Paths.get(realPath + File.separator + filename));

        }
        UserDAO db = new UserDAO();
        User acc = new User();
        acc.setUserName(nusername);
        acc.setGender(ngenders);
        acc.setImage(filename);
        acc.setEmail(email);
        acc.setPhone(nphone);
        db.userUpdate(acc);
        HttpSession session = request.getSession();
        User u = db.checkUser(email);
        session.setAttribute("account", u);
        response.sendRedirect("../view/home");
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
