/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.DimensionDAO;
import dal.DimensionTypeDAO;
import dal.PricePackageDAO;
import dal.SubjectCategoryDAO;
import dal.SubjectDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dimension;
import model.DimensionType;
import model.Lesson;
import model.PricePackage;
import model.Subject;
import model.SubjectCategory;
import model.User;

/**
 *
 * @author Minh-PC
 */
@WebServlet(name = "CourseContentDetail", urlPatterns = {"/view/CourseContentDetail"})
public class CourseContentDetail extends HttpServlet {

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

        SubjectDAO subjectDAO = new SubjectDAO();
        User account = (User) request.getSession().getAttribute("account");
        SubjectCategoryDAO SCDAO = new SubjectCategoryDAO();
        DimensionDAO DDAO = new DimensionDAO();
        DimensionTypeDAO DTDAO = new DimensionTypeDAO();
        PricePackageDAO PPDAO = new PricePackageDAO();
        String service = request.getParameter("service");
        int page = 1;
        int page_size = 5;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        int page1 = 1;
        int page_size1 = 5;
        String pageStr1 = request.getParameter("page1");
        if (pageStr1 != null) {
            page1 = Integer.parseInt(pageStr1);
        }
        if (service != null) {
            if (service.equalsIgnoreCase("viewDetail")) {
                if (account == null || ((account.getRoleId() != 5)
                        && (account.getRoleId() != 4))) {
                    sendDispatcher(request, response, "error.jsp");
                } else {

                    int subjectId = Integer.parseInt(request.getParameter("subjectId"));
                    // subject by id
                    String previousLink = "../view/CourseContentDetail?service=viewDetail&displayTab=dimension&subjectId=" + subjectId;
                    previousLink += "&page=";
                    Subject courseContent = subjectDAO.getSubjectbyIdMinh(subjectId);
                    request.setAttribute("subject", courseContent);
                    //pagination by dimension
                    ArrayList<Dimension> ListDimensionByPagination = DDAO.getListSubjectByKeyword(page,page_size, courseContent.getDimensions());
                    int countDimension = courseContent.getDimensions().size();
                    int totalPage = countDimension / page_size;
                    if (countDimension % page_size != 0) {
                        totalPage += 1;
                    }
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    request.setAttribute("previousLink", previousLink);
                    /*send list latest blog and list blog*/
                    request.setAttribute("ListDimension", ListDimensionByPagination);
                    //price package
                    String previousLink1 = "../view/CourseContentDetail?service=viewDetail&displayTab=pricePackage&subjectId=" + subjectId;
                    previousLink1 += "&page1=";
                    ArrayList<PricePackage> ListPricePackageByPagination = PPDAO.getListSubjectByKeyword(page1, page_size1, courseContent.getPricePackage());
                    int countPricePackage = courseContent.getPricePackage().size();
                    int totalPage1 = countPricePackage / page_size1;
                    if (countPricePackage % page_size1 != 0) {
                        totalPage1 += 1;
                    }
                    request.setAttribute("page1", page1);
                    request.setAttribute("totalPage1", totalPage1);
                    request.setAttribute("previousLink1", previousLink1);
                    /*send list latest blog and list blog*/
                    request.setAttribute("ListPricePackage", ListPricePackageByPagination);
                    // list cate by subject
                    ArrayList<SubjectCategory> categoryList = SCDAO.getSubjectCateBySubject(subjectId);
                    request.setAttribute("categoryList", categoryList);
                    ArrayList<SubjectCategory> categoryRemainList = SCDAO.getRemainSubjectCateBySubject(subjectId);
                    request.setAttribute("categoryRemainList", categoryRemainList);
                    ArrayList<DimensionType> dimensionTypes = DTDAO.getAllDimensionTypes();
                    request.setAttribute("dimensionTypes", dimensionTypes);
                    request.setAttribute("displayTab", "overview");
                    sendDispatcher(request, response, "CourseContentDetail.jsp");
                }
            }
            if (service.equalsIgnoreCase("updateSubject")) {
                if (account == null || ((account.getRoleId() != 5)
                        && (account.getRoleId() != 4))) {
                    sendDispatcher(request, response, "error.jsp");
                } else {
                    int subjectId = Integer.parseInt(request.getParameter("subjectId").trim());
                    String subjectName = request.getParameter("subjectName").trim();
                    String subjectDescription = request.getParameter("subjectDescription").trim();
                    String subjectThumbnail = request.getParameter("subjectThumbnail");
                    boolean isFeatured = request.getParameter("isFeaturedSubject") != null;
                    boolean status = request.getParameter("subjectStatus").equals("1");
                    String[] updatedSubjectCateId = request.getParameterValues("subjectCategory");
                    /* Check boundaries */
                    String message = "";
                    if (subjectName == null || subjectName.length() == 0) {
                        message = "SubjectName can not be empty";
                    } else if (subjectName.length() > 255) {
                        message = "Subject Name is too long";
                    } else if (subjectDescription == null || subjectDescription.length() == 0) {
                        message = "Subject Description can not be empty";
                    } else if (subjectDescription.length() > 1023) {
                        message = "Subject Description is too long";
                    } else {
                        /* Perform the updates on subject basic data and categories */
                        Subject updateSubject = new Subject(subjectId, subjectName, subjectDescription, subjectThumbnail, isFeatured, status);
                        int updateNumber = 0;
                        updateNumber += subjectDAO.updateSubjectBasic(subjectId, updateSubject);
                        String[] currentCategorySubject = SCDAO.getSubjectCateIdBySubject(subjectId);
                        /* Terminates: eliminates all the unchanged tuples */
                        if (updatedSubjectCateId != null) {
                            /* If the updated subject category list is not null */
                            for (int i = 0; i < updatedSubjectCateId.length; i++) {
                                for (int j = 0; j < currentCategorySubject.length; j++) {
                                    if (updatedSubjectCateId[i].equals(currentCategorySubject[j])) {
                                        updatedSubjectCateId[i] = "-1";
                                        currentCategorySubject[j] = "-1";
                                    }
                                }
                            }
                            /* Add categories in the updated list */
                            for (String categoryId : updatedSubjectCateId) {
                                if (!categoryId.equals("-1")) {
                                    updateNumber += SCDAO.addCategorySubject(subjectId, Integer.parseInt(categoryId));
                                }
                            }
                            /* Delete categories in the current list */
                            for (String categoryId : currentCategorySubject) {
                                if (!categoryId.equals("-1")) {
                                    updateNumber += SCDAO.deteleCategorySubject(subjectId, Integer.parseInt(categoryId));
                                }
                            }
                        } else {
                            /* If the updated subject category list is null: delete all category */
                            for (String categoryId : currentCategorySubject) {
                                if (!categoryId.equals("-1")) {
                                    updateNumber += SCDAO.deteleCategorySubject(subjectId, Integer.parseInt(categoryId));
                                }
                            }
                        }
//                         subjectCateDAO.updateSubjectContentCate(subjectId, categoryId);
//                        int updateNumber = basicUpdate + categoryUpdate;
                        message = " Update successfully.";
                    }
                    /* Get the needed lists and redirect to the courseContentJsp */
                    String previousLink = "../view/CourseContentDetail?service=viewDetail&displayTab=dimension&subjectId=" + subjectId;
                    previousLink += "&page=";
                    Subject courseContent = subjectDAO.getSubjectbyIdMinh(subjectId);
                    request.setAttribute("subject", courseContent);
                    //pagination by dimension
                    ArrayList<Dimension> ListDimensionByPagination = DDAO.getListSubjectByKeyword(page,page_size, courseContent.getDimensions());
                    int countDimension = courseContent.getDimensions().size();
                    int totalPage = countDimension / page_size;
                    if (countDimension % page_size != 0) {
                        totalPage += 1;
                    }
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    request.setAttribute("previousLink", previousLink);
                    request.setAttribute("ListDimension", ListDimensionByPagination);
                    //price package
                    String previousLink1 = "../view/CourseContentDetail?service=viewDetail&displayTab=pricePackage&subjectId=" + subjectId;
                    previousLink1 += "&page1=";
                    ArrayList<PricePackage> ListPricePackageByPagination = PPDAO.getListSubjectByKeyword(page1, page_size1, courseContent.getPricePackage());
                    int countPricePackage = courseContent.getPricePackage().size();
                    int totalPage1 = countPricePackage / page_size1;
                    if (countPricePackage % page_size1 != 0) {
                        totalPage1 += 1;
                    }
                    request.setAttribute("page1", page1);
                    request.setAttribute("totalPage1", totalPage1);
                    request.setAttribute("previousLink1", previousLink1);
                    /*send list latest blog and list blog*/
                    request.setAttribute("ListPricePackage", ListPricePackageByPagination);
                    ArrayList<SubjectCategory> categoryList = SCDAO.getSubjectCateBySubject(subjectId);
                    request.setAttribute("categoryList", categoryList);
                    ArrayList<SubjectCategory> categoryRemainList = SCDAO.getRemainSubjectCateBySubject(subjectId);
                    request.setAttribute("categoryRemainList", categoryRemainList);
                    ArrayList<DimensionType> dimensionTypes = DTDAO.getAllDimensionTypes();
                    request.setAttribute("dimensionTypes", dimensionTypes);
                    request.setAttribute("detailMessage", message);
                    request.setAttribute("displayTab", "overview");
                    sendDispatcher(request, response, "CourseContentDetail.jsp");
                }
            }
            /**
             * Service course content detail: update subject dimension (edit and
             * delete)
             */
            if (service.equalsIgnoreCase("updateDimension")) {
                if (account == null || ((account.getRoleId() != 5)
                        && (account.getRoleId() != 4))) {
                    sendDispatcher(request, response, "error.jsp");
                } else {
                    int subjectId = Integer.parseInt(request.getParameter("subjectId").trim());
                    String message = "";

                    int dimensionId = Integer.parseInt(request.getParameter("dimensionId").trim());
                    /* Check if the sub-service is update or delete */
                    String subService = request.getParameter("subService").trim();
                    if (subService.equalsIgnoreCase("Delete")) {
                        /* Perform deletion on subject dimension */
                        int check = DDAO.deleteDimension(dimensionId);
                        if (check > 0) {

                            message = "Delete dimension successfully.";
                        } else {
                            message = "Delete dimension failed.";
                        }
                    } else if (subService.equalsIgnoreCase("Update")) {
                        /* Get parameters from jsp */
                        int dimensionTypeId = Integer.parseInt(request.getParameter("dimensionType").trim());
                        String dimensionName = request.getParameter("dimensionName").trim();
                        String description = request.getParameter("description").trim();
                        /* Check boundaries */

                        if (dimensionName == null || dimensionName.length() == 0) {
                            message = "Dimension Name can not be empty";
                        } else if (dimensionName.length() > 255) {
                            message = "Dimension Name is too long";
                        } else if (description.length() > 511) {
                            message = "Dimension Description is too long";
                        } else {

                            /* Perform the updates on subject dimension */
                            Dimension updateDimension = new Dimension(dimensionId, subjectId, dimensionTypeId, "", dimensionName, description, true);
                            int check = DDAO.editDimension(dimensionId, updateDimension);
                            if (check > 0) {

                                message = "Update dimension successfully.";
                            } else {
                                message = "Update dimension failed.";
                            }
                        }
                    }
                    /* Get the needed lists and redirect to the courseContentJsp */
                    String previousLink = "../view/CourseContentDetail?service=viewDetail&displayTab=dimension&subjectId=" + subjectId;
                    previousLink += "&page=";
                    Subject courseContent = subjectDAO.getSubjectbyIdMinh(subjectId);
                    request.setAttribute("subject", courseContent);
                    //pagination by dimension
                    ArrayList<Dimension> ListDimensionByPagination = DDAO.getListSubjectByKeyword(page,page_size, courseContent.getDimensions());
                    int countDimension = courseContent.getDimensions().size();
                    int totalPage = countDimension / page_size;
                    if (countDimension % page_size != 0) {
                        totalPage += 1;
                    }
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    request.setAttribute("previousLink", previousLink);
                    /*send list latest blog and list blog*/
                    request.setAttribute("ListDimension", ListDimensionByPagination);
                    //price package
                    String previousLink1 = "../view/CourseContentDetail?service=viewDetail&displayTab=pricePackage&subjectId=" + subjectId;
                    previousLink1 += "&page1=";
                    ArrayList<PricePackage> ListPricePackageByPagination = PPDAO.getListSubjectByKeyword(page1, page_size1, courseContent.getPricePackage());
                    int countPricePackage = courseContent.getPricePackage().size();
                    int totalPage1 = countPricePackage / page_size1;
                    if (countPricePackage % page_size1 != 0) {
                        totalPage1 += 1;
                    }
                    request.setAttribute("page1", page1);
                    request.setAttribute("totalPage1", totalPage1);
                    request.setAttribute("previousLink1", previousLink1);
                    /*send list latest blog and list blog*/
                    request.setAttribute("ListPricePackage", ListPricePackageByPagination);
                    ArrayList<SubjectCategory> categoryList = SCDAO.getSubjectCateBySubject(subjectId);
                    request.setAttribute("categoryList", categoryList);
                    ArrayList<SubjectCategory> categoryRemainList = SCDAO.getRemainSubjectCateBySubject(subjectId);
                    request.setAttribute("categoryRemainList", categoryRemainList);
                    ArrayList<DimensionType> dimensionTypes = DTDAO.getAllDimensionTypes();
                    request.setAttribute("dimensionTypes", dimensionTypes);
//                    ArrayList<Lesson> listLesson = lessonDAO.getAllLessonBySubjectId(subjectId);
//                    request.setAttribute("listLesson", listLesson);
//                    request.setAttribute("dimensionColor", color);
                    request.setAttribute("dimensionMessage", message);
                    request.setAttribute("displayTab", "dimension");
                    sendDispatcher(request, response, "CourseContentDetail.jsp");
                }
            }
            //ADD Dimension
            if (service.equalsIgnoreCase("addDimension")) {
                /* Get user and role on session scope */
                if (account == null || ((account.getRoleId() != 5)
                        && (account.getRoleId() != 4))) {
                    sendDispatcher(request, response, "error.jsp");
                } else {
                    int subjectId = Integer.parseInt(request.getParameter("subjectId").trim());
                    String message = "";
                    /* Get parameters from jsp */
                    int dimensionTypeId = Integer.parseInt(request.getParameter("dimensionType").trim());
                    String dimensionName = request.getParameter("dimensionName").trim();
                    String description = request.getParameter("description").trim();
                    /* Check boundaries */
                    if (dimensionName == null || dimensionName.length() == 0) {
                        message = "Dimension Name can not be empty";
                    } else if (dimensionName.length() > 255) {
                        message = "Dimension Name is too long";
                    } else if (description.length() > 511) {
                        message = "Dimension Description is too long";
                    } else {
                        /* Add new subject dimension */
                        Dimension updateDimension = new Dimension(0, subjectId, dimensionTypeId, "", dimensionName, description, true);
                        int check = DDAO.addDimension(updateDimension);
                        if (check > 0) {
                            message = "Add dimension successfully.";
                        } else {
                            message = "Add dimension failed.";
                        }
                    }
                    /* Get the needed lists and redirect to the courseContentJsp */
                    String previousLink = "../view/CourseContentDetail?service=viewDetail&displayTab=dimension&subjectId=" + subjectId;
                    previousLink += "&page=";
                    Subject courseContent = subjectDAO.getSubjectbyIdMinh(subjectId);
                    request.setAttribute("subject", courseContent);
                    //pagination by dimension
                    ArrayList<Dimension> ListDimensionByPagination = DDAO.getListSubjectByKeyword(page,page_size, courseContent.getDimensions());
                    int countDimension = courseContent.getDimensions().size();
                    int totalPage = countDimension / page_size;
                    if (countDimension % page_size != 0) {
                        totalPage += 1;
                    }
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    request.setAttribute("previousLink", previousLink);
                    /*send list latest blog and list blog*/
                    request.setAttribute("ListDimension", ListDimensionByPagination);
                    //price package
                    String previousLink1 = "../view/CourseContentDetail?service=viewDetail&displayTab=pricePackage&subjectId=" + subjectId;
                    previousLink1 += "&page1=";
                    ArrayList<PricePackage> ListPricePackageByPagination = PPDAO.getListSubjectByKeyword(page1, page_size1, courseContent.getPricePackage());
                    int countPricePackage = courseContent.getPricePackage().size();
                    int totalPage1 = countPricePackage / page_size1;
                    if (countPricePackage % page_size1 != 0) {
                        totalPage1 += 1;
                    }
                    request.setAttribute("page1", page1);
                    request.setAttribute("totalPage1", totalPage1);
                    request.setAttribute("previousLink1", previousLink1);
                    /*send list latest blog and list blog*/
                    request.setAttribute("ListPricePackage", ListPricePackageByPagination);
                    ArrayList<SubjectCategory> categoryList = SCDAO.getSubjectCateBySubject(subjectId);
                    request.setAttribute("categoryList", categoryList);
                    ArrayList<SubjectCategory> categoryRemainList = SCDAO.getRemainSubjectCateBySubject(subjectId);
                    request.setAttribute("categoryRemainList", categoryRemainList);
                    ArrayList<DimensionType> dimensionTypes = DTDAO.getAllDimensionTypes();
                    request.setAttribute("dimensionTypes", dimensionTypes);
                    request.setAttribute("dimensionMessage", message);
                    request.setAttribute("displayTab", "dimension");
                    sendDispatcher(request, response, "CourseContentDetail.jsp");
                }
            }
            if (service.equalsIgnoreCase("addPricePackage")) {
                if (account == null || ((account.getRoleId() != 5)
                        && (account.getRoleId() != 4))) {
                    sendDispatcher(request, response, "error.jsp");
                } else {
                    int subjectId = Integer.parseInt(request.getParameter("subjectId").trim());
                    String message = "";
                    /* Get parameters from jsp */
                    int duration = Integer.parseInt(request.getParameter("duration").trim());
                    Float listprice = Float.parseFloat(request.getParameter("listprice").trim());
                    Float saleprice = Float.parseFloat(request.getParameter("saleprice").trim());
                    String packName = request.getParameter("packName").trim();

                    /* Check boundaries */
                    if (packName == null || packName.length() == 0) {
                        message = "Price package Name can not be empty";
                    } else if (packName.length() > 255) {
                        message = "Price package Name is too long";
                    }else if(listprice < saleprice){
                         message = "Update Price package failed. because listprice < saleprice";
                    } else {
                        /* Add new subject dimension */
                        PricePackage updatePricePackage = new PricePackage(0, packName, subjectId, duration, listprice, saleprice, true);
                        int check = PPDAO.addPricePackage(updatePricePackage);
                        if (check > 0) {
                            message = "Add price package successfully.";
                        } else {
                            message = "Add price package failed.";
                        }
                    }
                    /* Get the needed lists and redirect to the courseContentJsp */
                    String previousLink = "../view/CourseContentDetail?service=viewDetail&displayTab=dimension&subjectId=" + subjectId;
                    previousLink += "&page=";
                    Subject courseContent = subjectDAO.getSubjectbyIdMinh(subjectId);
                    request.setAttribute("subject", courseContent);
                    //pagination by dimension
                    ArrayList<Dimension> ListDimensionByPagination = DDAO.getListSubjectByKeyword(page,page_size, courseContent.getDimensions());

                    int countDimension = courseContent.getDimensions().size();
                    int totalPage = countDimension / page_size;
                    if (countDimension % page_size != 0) {
                        totalPage += 1;
                    }
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    request.setAttribute("previousLink", previousLink);
                    request.setAttribute("ListDimension", ListDimensionByPagination);
                    //price package
                    String previousLink1 = "../view/CourseContentDetail?service=viewDetail&displayTab=pricePackage&subjectId=" + subjectId;
                    previousLink1 += "&page1=";
                    ArrayList<PricePackage> ListPricePackageByPagination = PPDAO.getListSubjectByKeyword(page1, page_size1, courseContent.getPricePackage());
                    int countPricePackage = courseContent.getPricePackage().size();
                    int totalPage1 = countPricePackage / page_size1;
                    if (countPricePackage % page_size1 != 0) {
                        totalPage1 += 1;
                    }
                    request.setAttribute("page1", page1);
                    request.setAttribute("totalPage1", totalPage1);
                    request.setAttribute("previousLink1", previousLink1);
                    /*send list latest blog and list blog*/
                    request.setAttribute("ListPricePackage", ListPricePackageByPagination);
                    ArrayList<SubjectCategory> categoryList = SCDAO.getSubjectCateBySubject(subjectId);
                    request.setAttribute("categoryList", categoryList);
                    ArrayList<SubjectCategory> categoryRemainList = SCDAO.getRemainSubjectCateBySubject(subjectId);
                    request.setAttribute("categoryRemainList", categoryRemainList);
                    ArrayList<DimensionType> dimensionTypes = DTDAO.getAllDimensionTypes();
                    request.setAttribute("dimensionTypes", dimensionTypes);
                    request.setAttribute("pricePackageMessage", message);
                    request.setAttribute("displayTab", "pricePackage");
                    sendDispatcher(request, response, "CourseContentDetail.jsp");
                }
            }
            if (service.equalsIgnoreCase("updatePricePackage")) {
                if (account == null || ((account.getRoleId() != 5)
                        && (account.getRoleId() != 4))) {
                    sendDispatcher(request, response, "error.jsp");
                } else {
                    int subjectId = Integer.parseInt(request.getParameter("subjectId").trim());
                    String message = "";

                    int packId = Integer.parseInt(request.getParameter("packId").trim());
                    /* Check if the sub-service is update or delete */
                    String subService = request.getParameter("subService").trim();
                    if (subService.equalsIgnoreCase("Delete")) {
                        /* Perform deletion on subject dimension */
                        int check = PPDAO.deletePricePackage(packId);
                        if (check > 0) {
                            message = "Delete dimension successfully.";
                        } else {
                            message = "Delete dimension failed.";
                        }
                    } else if (subService.equalsIgnoreCase("Update")) {
                        /* Get parameters from jsp */
                        int duration = Integer.parseInt(request.getParameter("duration").trim());
                        Float listprice = Float.parseFloat(request.getParameter("listprice").trim());
                        Float saleprice = Float.parseFloat(request.getParameter("saleprice").trim());
                        String packName = request.getParameter("packName").trim();
                        /* Check boundaries */

                        if (packName == null || packName.length() == 0) {
                            message = "Price package Name can not be empty";
                        } else if (packName.length() > 255) {
                            message = "Price package Name is too long";
                        }else if(listprice < saleprice){
                            message = "Update Price package failed. because listprice < saleprice";
                        } else {

                            /* Perform the updates on subject dimension */
                            PricePackage updatePricePackage = new PricePackage(packId, packName, subjectId, duration, listprice, saleprice, true);
                            int check = PPDAO.editPricePackage(packId, updatePricePackage);
                            if (check > 0) {

                                message = "Update Price package successfully.";
                            } else {
                                message = "Update Price package failed.";
                            }
                        }
                    }
                    /* Get the needed lists and redirect to the courseContentJsp */
                    String previousLink = "../view/CourseContentDetail?service=viewDetail&displayTab=dimension&subjectId=" + subjectId;
                    previousLink += "&page=";
                    Subject courseContent = subjectDAO.getSubjectbyIdMinh(subjectId);
                    request.setAttribute("subject", courseContent);
                    //pagination by dimension
                    ArrayList<Dimension> ListDimensionByPagination = DDAO.getListSubjectByKeyword(page,page_size, courseContent.getDimensions());
                    int countDimension = courseContent.getDimensions().size();
                    int totalPage = countDimension / page_size;
                    if (countDimension % page_size != 0) {
                        totalPage += 1;
                    }
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    request.setAttribute("previousLink", previousLink);
                    request.setAttribute("ListDimension", ListDimensionByPagination);
                    String previousLink1 = "../view/CourseContentDetail?service=viewDetail&displayTab=pricePackage&subjectId=" + subjectId;
                    previousLink1 += "&page1=";
                    ArrayList<PricePackage> ListPricePackageByPagination = PPDAO.getListSubjectByKeyword(page1, page_size1, courseContent.getPricePackage());
                    int countPricePackage = courseContent.getPricePackage().size();
                    int totalPage1 = countPricePackage / page_size1;
                    if (countPricePackage % page_size1 != 0) {
                        totalPage1 += 1;
                    }
                    request.setAttribute("page1", page1);
                    request.setAttribute("totalPage1", totalPage1);
                    request.setAttribute("previousLink1", previousLink1);
                    /*send list latest blog and list blog*/
                    request.setAttribute("ListPricePackage", ListPricePackageByPagination);
                    ArrayList<SubjectCategory> categoryList = SCDAO.getSubjectCateBySubject(subjectId);
                    request.setAttribute("categoryList", categoryList);
                    ArrayList<SubjectCategory> categoryRemainList = SCDAO.getRemainSubjectCateBySubject(subjectId);
                    request.setAttribute("categoryRemainList", categoryRemainList);
                    ArrayList<DimensionType> dimensionTypes = DTDAO.getAllDimensionTypes();
                    request.setAttribute("dimensionTypes", dimensionTypes);

                    request.setAttribute("pricePackageMessage", message);
                    request.setAttribute("displayTab", "pricePackage");
                    sendDispatcher(request, response, "CourseContentDetail.jsp");
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
            Logger.getLogger(CourseContentDetail.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CourseContentDetail.class.getName()).log(Level.SEVERE, null, ex);
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
