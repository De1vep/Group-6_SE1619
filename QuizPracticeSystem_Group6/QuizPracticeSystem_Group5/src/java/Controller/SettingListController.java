/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.DimensionTypeDAO;
import dal.LessonTypeDAO;
import dal.PostCategoryDAO;
import dal.QuizLevelDAO;
import dal.SubjectCategoryDAO;
import dal.TestTypeDAO;
import dal.UserRoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DimensionType;
import model.LessonType;
import model.PostCategory;
import model.QuizLevel;
import model.SubjectCategory;
import model.TestType;
import model.User;
import model.UserRole;
import model.settingList;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SettingListController", urlPatterns = {"/view/settingList"})
public class SettingListController extends HttpServlet {

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
            String service = request.getParameter("service").trim();
            //declare all DAO
            UserRoleDAO userRoleDAO = new UserRoleDAO();
            PostCategoryDAO postCateDAO = new PostCategoryDAO();
            SubjectCategoryDAO subjectCateDAO = new SubjectCategoryDAO();
            TestTypeDAO testTypeDAO = new TestTypeDAO();
            QuizLevelDAO quizLevelDAO = new QuizLevelDAO();
            LessonTypeDAO lessonTypeDAO = new LessonTypeDAO();
            DimensionTypeDAO dimensionTypeDAO = new DimensionTypeDAO();

            /**
             * get information to display in the setting list page
             */
            if (service.equalsIgnoreCase("getInformation")) {
                ArrayList<settingList> settingLists = new ArrayList<>();
                //get all user's roles
                ArrayList<UserRole> userRoleList = userRoleDAO.getAllStatusUserRole();
                for (UserRole userRole : userRoleList) {
                    settingLists.add(new settingList(userRole.getUserRoleId(), "User Roles", userRole.getUserRoleName(), userRole.isStatus()));
                }
                //get all post's categories
                ArrayList<PostCategory> postCateList = postCateDAO.getAllStatusPostCates();
                for (PostCategory postCategory : postCateList) {
                    settingLists.add(new settingList(postCategory.getPostCateId(), "Post Category", postCategory.getPostCateName(), postCategory.isStatus()));
                }
                //get all subject's categories
                ArrayList<SubjectCategory> subjectCateList = subjectCateDAO.getAllStatusSubjectCates();
                for (SubjectCategory subjectCategory : subjectCateList) {
                    settingLists.add(new settingList(subjectCategory.getSubjectCateId(), "Subject Category", subjectCategory.getSubjectCateName(), subjectCategory.isStatus()));
                }
                //get all test's type
                ArrayList<TestType> testTypeList = testTypeDAO.getAllStatusTestTypes();
                for (TestType testType : testTypeList) {
                    settingLists.add(new settingList(testType.getTestTypeId(), "Test Type", testType.getTestTypeName(), testType.isStatus()));
                }
                //get all quiz's level
                ArrayList<QuizLevel> quizLevelList = quizLevelDAO.getAllStatusQuizLevel();
                for (QuizLevel quizLevel : quizLevelList) {
                    settingLists.add(new settingList(quizLevel.getQuizLevelId(), "Quiz Level", quizLevel.getQuizLevelName(), quizLevel.isStatus()));
                }
                //get all lesson's type
                ArrayList<LessonType> lessonTypeList = lessonTypeDAO.getAllStatusLessonType();
                for (LessonType lessonType : lessonTypeList) {
                    settingLists.add(new settingList(lessonType.getLessonTypeId(), "Lesson Type", lessonType.getLessonTypeName(), lessonType.isStatus()));
                }
                //get all dimesion's type
                ArrayList<DimensionType> dimensionTypeList = dimensionTypeDAO.getAllStatusDimensionTypes();
                for (DimensionType dimensionType : dimensionTypeList) {
                    settingLists.add(new settingList(dimensionType.getDimensionTypeId(), "Dimension Type", dimensionType.getDimensionTypeName(), dimensionType.isStatus()));
                }
                String message = request.getParameter("message");
                if (message != null) {//if there is a message
                    request.setAttribute("message", message);
                }

                int page = 1;
                int page_size = 5;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                int countSetting = settingLists.size();
                int totalPage = countSetting / page_size;
                if (countSetting % page_size != 0) {
                    totalPage += 1;
                }

                String previousLink = "settingList?service=getInformation&page=";

                request.setAttribute("previousLink", previousLink);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                if (page <= totalPage - 1) {
                    request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, page * page_size));
                } else if (page == totalPage) {
                    request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, countSetting));

                }
                request.getRequestDispatcher("../view/settingList.jsp").forward(request, response);
            }

            /**
             * Filter setting list by type of system setting.
             */
            if (service.equalsIgnoreCase("filter")) {
                ArrayList<settingList> settingLists = new ArrayList<>();
                String field = request.getParameter("field").trim();
                if (field.equalsIgnoreCase("userRole")) { // if admin  want to get all user role list

                    ArrayList<UserRole> userRoleList = userRoleDAO.getAllStatusUserRole();
                    for (UserRole userRole : userRoleList) {
                        settingLists.add(new settingList(userRole.getUserRoleId(), "User Roles", userRole.getUserRoleName(), userRole.isStatus()));
                    }
                    int page = 1;
                    int page_size = 5;
                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    int countSetting = settingLists.size();
                    int totalPage = countSetting / page_size;
                    if (countSetting % page_size != 0) {
                        totalPage += 1;
                    }

                    String previousLink = "settingList?service=filter&field=userRole&page=";

                    request.setAttribute("previousLink", previousLink);
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    if (page <= totalPage - 1) {
                        request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, page * page_size));
                    } else if (page == totalPage) {
                        request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, countSetting));

                    }
                    request.getRequestDispatcher("../view/settingList.jsp").forward(request, response);

                } else if (field.equalsIgnoreCase("postCate")) {// if admin only want to get all post category list
                    ArrayList<PostCategory> postCateList = postCateDAO.getAllStatusPostCates();
                    for (PostCategory postCategory : postCateList) {
                        settingLists.add(new settingList(postCategory.getPostCateId(), "Post Category", postCategory.getPostCateName(), postCategory.isStatus()));
                    }
                    int page = 1;
                    int page_size = 5;
                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    int countSetting = settingLists.size();
                    int totalPage = countSetting / page_size;
                    if (countSetting % page_size != 0) {
                        totalPage += 1;
                    }

                    String previousLink = "settingList?service=filter&field=postCate&page=";

                    request.setAttribute("previousLink", previousLink);
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    if (page <= totalPage - 1) {
                        request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, page * page_size));
                    } else if (page == totalPage) {
                        request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, countSetting));

                    }
                    request.getRequestDispatcher("../view/settingList.jsp").forward(request, response);

                } else if (field.equalsIgnoreCase("subjectCate")) {// if admin only want to get all subject category list
                    ArrayList<SubjectCategory> subjectCateList = subjectCateDAO.getAllStatusSubjectCates();
                    for (SubjectCategory subjectCategory : subjectCateList) {
                        settingLists.add(new settingList(subjectCategory.getSubjectCateId(), "Subject Category", subjectCategory.getSubjectCateName(), subjectCategory.isStatus()));
                    }
                    int page = 1;
                    int page_size = 5;
                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    int countSetting = settingLists.size();
                    int totalPage = countSetting / page_size;
                    if (countSetting % page_size != 0) {
                        totalPage += 1;
                    }

                    String previousLink = "settingList?service=filter&field=subjectCate&page=";

                    request.setAttribute("previousLink", previousLink);
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    if (page <= totalPage - 1) {
                        request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, page * page_size));
                    } else if (page == totalPage) {
                        request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, countSetting));

                    }
                    request.getRequestDispatcher("../view/settingList.jsp").forward(request, response);
                } else if (field.equalsIgnoreCase("testType")) {// if admin only want to get all test type list
                    ArrayList<TestType> testTypeList = testTypeDAO.getAllStatusTestTypes();
                    for (TestType testType : testTypeList) {
                        settingLists.add(new settingList(testType.getTestTypeId(), "Test Type", testType.getTestTypeName(), testType.isStatus()));
                    }
                    int page = 1;
                    int page_size = 5;
                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    int countSetting = settingLists.size();
                    int totalPage = countSetting / page_size;
                    if (countSetting % page_size != 0) {
                        totalPage += 1;
                    }

                    String previousLink = "settingList?service=filter&field=testType&page=";

                    request.setAttribute("previousLink", previousLink);
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    if (page <= totalPage - 1) {
                        request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, page * page_size));
                    } else if (page == totalPage) {
                        request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, countSetting));

                    }
                    request.getRequestDispatcher("../view/settingList.jsp").forward(request, response);
                } else if (field.equalsIgnoreCase("quizLevel")) {// if admin only want to get all quiz level list
                    ArrayList<QuizLevel> quizLevelList = quizLevelDAO.getAllStatusQuizLevel();
                    for (QuizLevel quizLevel : quizLevelList) {
                        settingLists.add(new settingList(quizLevel.getQuizLevelId(), "Quiz Level", quizLevel.getQuizLevelName(), quizLevel.isStatus()));
                    }
                    int page = 1;
                    int page_size = 5;
                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    int countSetting = settingLists.size();
                    int totalPage = countSetting / page_size;
                    if (countSetting % page_size != 0) {
                        totalPage += 1;
                    }

                    String previousLink = "settingList?service=filter&field=quizLevel&page=";

                    request.setAttribute("previousLink", previousLink);
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    if (page <= totalPage - 1) {
                        request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, page * page_size));
                    } else if (page == totalPage) {
                        request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, countSetting));

                    }
                    request.getRequestDispatcher("../view/settingList.jsp").forward(request, response);
                } else if (field.equalsIgnoreCase("lessonType")) {// if admin only want to get all lesson type list
                    ArrayList<LessonType> lessonTypeList = lessonTypeDAO.getAllStatusLessonType();
                    for (LessonType lessonType : lessonTypeList) {
                        settingLists.add(new settingList(lessonType.getLessonTypeId(), "Lesson Type", lessonType.getLessonTypeName(), lessonType.isStatus()));
                    }
                    int page = 1;
                    int page_size = 5;
                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    int countSetting = settingLists.size();
                    int totalPage = countSetting / page_size;
                    if (countSetting % page_size != 0) {
                        totalPage += 1;
                    }

                    String previousLink = "settingList?service=filter&field=lessonType&page=";

                    request.setAttribute("previousLink", previousLink);
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    if (page <= totalPage - 1) {
                        request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, page * page_size));
                    } else if (page == totalPage) {
                        request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, countSetting));

                    }
                    request.getRequestDispatcher("../view/settingList.jsp").forward(request, response);
                } else if (field.equalsIgnoreCase("dimensionType")) {// if admin only want to get all dimension type list
                    ArrayList<DimensionType> dimensionTypeList = dimensionTypeDAO.getAllStatusDimensionTypes();
                    for (DimensionType dimensionType : dimensionTypeList) {
                        settingLists.add(new settingList(dimensionType.getDimensionTypeId(), "Dimension Type", dimensionType.getDimensionTypeName(), dimensionType.isStatus()));
                    }
                    int page = 1;
                    int page_size = 5;
                    String pageStr = request.getParameter("page");
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    int countSetting = settingLists.size();
                    int totalPage = countSetting / page_size;
                    if (countSetting % page_size != 0) {
                        totalPage += 1;
                    }

                    String previousLink = "settingList?service=filter&field=dimensionType&page=";

                    request.setAttribute("previousLink", previousLink);
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    if (page <= totalPage - 1) {
                        request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, page * page_size));
                    } else if (page == totalPage) {
                        request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, countSetting));

                    }
                    request.getRequestDispatcher("../view/settingList.jsp").forward(request, response);
                } else if (field.equalsIgnoreCase("all")) {
                    response.sendRedirect("settingList?service=getInformation");
                }
                //request.getRequestDispatcher("jsp/settingList.jsp").forward(request, response);
            }

            if (service.equals("sort")) {
                ArrayList<settingList> settingLists = new ArrayList<>();
                //get all user's roles
                ArrayList<UserRole> userRoleList = userRoleDAO.getAllStatusUserRole();
                for (UserRole userRole : userRoleList) {
                    settingLists.add(new settingList(userRole.getUserRoleId(), "User Roles", userRole.getUserRoleName(), userRole.isStatus()));
                }
                //get all post's categories
                ArrayList<PostCategory> postCateList = postCateDAO.getAllStatusPostCates();
                for (PostCategory postCategory : postCateList) {
                    settingLists.add(new settingList(postCategory.getPostCateId(), "Post Category", postCategory.getPostCateName(), postCategory.isStatus()));
                }
                //get all subject's categories
                ArrayList<SubjectCategory> subjectCateList = subjectCateDAO.getAllStatusSubjectCates();
                for (SubjectCategory subjectCategory : subjectCateList) {
                    settingLists.add(new settingList(subjectCategory.getSubjectCateId(), "Subject Category", subjectCategory.getSubjectCateName(), subjectCategory.isStatus()));
                }
                //get all test's type
                ArrayList<TestType> testTypeList = testTypeDAO.getAllStatusTestTypes();
                for (TestType testType : testTypeList) {
                    settingLists.add(new settingList(testType.getTestTypeId(), "Test Type", testType.getTestTypeName(), testType.isStatus()));
                }
                //get all quiz's level
                ArrayList<QuizLevel> quizLevelList = quizLevelDAO.getAllStatusQuizLevel();
                for (QuizLevel quizLevel : quizLevelList) {
                    settingLists.add(new settingList(quizLevel.getQuizLevelId(), "Quiz Level", quizLevel.getQuizLevelName(), quizLevel.isStatus()));
                }
                //get all lesson's type
                ArrayList<LessonType> lessonTypeList = lessonTypeDAO.getAllStatusLessonType();
                for (LessonType lessonType : lessonTypeList) {
                    settingLists.add(new settingList(lessonType.getLessonTypeId(), "Lesson Type", lessonType.getLessonTypeName(), lessonType.isStatus()));
                }
                //get all dimesion's type
                ArrayList<DimensionType> dimensionTypeList = dimensionTypeDAO.getAllStatusDimensionTypes();
                for (DimensionType dimensionType : dimensionTypeList) {
                    settingLists.add(new settingList(dimensionType.getDimensionTypeId(), "Dimension Type", dimensionType.getDimensionTypeName(), dimensionType.isStatus()));
                }
                String message = request.getParameter("message");
                if (message != null) {//if there is a message
                    request.setAttribute("message", message);
                }

                String type1 = request.getParameter("type1");
                String type2 = request.getParameter("type2");

                //sort ID
                if (type1.equals("1") && type2.equals("1")) {
                    Collections.sort(settingLists, new Comparator<settingList>() {
                        @Override
                        public int compare(settingList t, settingList t1) {
                            if (t.getId() == t1.getId()) {
                                return t.getSettingType().compareTo(t1.getSettingType());
                            }
                            return t.getId() - t1.getId();
                        }

                    });
                }
                if (type1.equals("1") && type2.equals("0")) {
                    Collections.sort(settingLists, new Comparator<settingList>() {
                        @Override
                        public int compare(settingList t, settingList t1) {
                            if (t.getId() == t1.getId()) {
                                return t1.getSettingType().compareTo(t.getSettingType());
                            }
                            return t1.getId() - t.getId();
                        }

                    });
                }

                //sort type
                if (type1.equals("0") && type2.equals("1")) {
                    Collections.sort(settingLists, new Comparator<settingList>() {
                        @Override
                        public int compare(settingList t, settingList t1) {
                            if (t.getSettingType().equalsIgnoreCase(t1.getSettingType())) {
                                return t.getName().compareTo(t1.getName());
                            }
                            return t.getSettingType().compareTo(t1.getSettingType());
                        }

                    });
                }
                if (type1.equals("0") && type2.equals("0")) {
                    Collections.sort(settingLists, new Comparator<settingList>() {
                        @Override
                        public int compare(settingList t, settingList t1) {
                            if (t1.getSettingType().equalsIgnoreCase(t.getSettingType())) {
                                return t1.getName().compareTo(t.getName());
                            }
                            return t1.getSettingType().compareTo(t.getSettingType());
                        }

                    });
                }

                //sort name
                if (type1.equals("-1") && type2.equals("1")) {
                    Collections.sort(settingLists, new Comparator<settingList>() {
                        @Override
                        public int compare(settingList t, settingList t1) {
                            if (t.getName().equalsIgnoreCase(t1.getName())) {
                                return t.getSettingType().compareTo(t1.getSettingType());
                            }
                            return t.getName().compareTo(t1.getName());
                        }

                    });
                }
                if (type1.equals("-1") && type2.equals("0")) {
                    Collections.sort(settingLists, new Comparator<settingList>() {
                        @Override
                        public int compare(settingList t, settingList t1) {
                            if (t1.getName().equalsIgnoreCase(t.getName())) {
                                return t1.getSettingType().compareTo(t.getSettingType());
                            }
                            return t1.getName().compareTo(t.getName());
                        }

                    });
                }

                int page = 1;
                int page_size = 5;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                int countSetting = settingLists.size();
                int totalPage = countSetting / page_size;
                if (countSetting % page_size != 0) {
                    totalPage += 1;
                }

                String previousLink = "settingList?service=sort&type1=" + type1 + "&type2=" + type2 + "&page=";

                request.setAttribute("t1", type1);
                request.setAttribute("t2", type2);
                request.setAttribute("previousLink", previousLink);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                if (page <= totalPage - 1) {
                    request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, page * page_size));
                } else if (page == totalPage) {
                    request.setAttribute("settingLists", settingLists.subList((page - 1) * page_size, countSetting));

                }
                request.getRequestDispatcher("../view/settingList.jsp").forward(request, response);

            }

            if (service.equalsIgnoreCase("loadSetting")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String type = request.getParameter("type");
                //User Roles
                if ("User Roles".startsWith(type)) {
                    UserRoleDAO ud = new UserRoleDAO();
                    UserRole userole = ud.getUserRoleByIdLoad(id);
                    settingList setting = new settingList();
                    setting.setId(id);
                    setting.setSettingType("User Roles");
                    setting.setName(userole.getUserRoleName());
                    setting.setStatus(userole.isStatus());
                    request.setAttribute("setting", setting);
                    request.getRequestDispatcher("../view/LoadSettingList.jsp").forward(request, response);
                }

                //Post Category
                if ("Post Category".startsWith(type)) {
                    PostCategoryDAO pd = new PostCategoryDAO();
                    PostCategory postCategory = pd.getPostCategoryByIdLoad(id);
                    settingList setting = new settingList();
                    setting.setId(id);
                    setting.setSettingType("Post Category");
                    setting.setName(postCategory.getPostCateName());
                    setting.setStatus(postCategory.isStatus());
                    request.setAttribute("setting", setting);
                    request.getRequestDispatcher("../view/LoadSettingList.jsp").forward(request, response);
                }

                //Subject Category
                if ("Subject Category".startsWith(type)) {
                    SubjectCategoryDAO sd = new SubjectCategoryDAO();
                    SubjectCategory subjectCategory = sd.getSubjectCategoryByIdLoad(id);
                    settingList setting = new settingList();
                    setting.setId(id);
                    setting.setSettingType("Subject Category");
                    setting.setName(subjectCategory.getSubjectCateName());
                    setting.setStatus(subjectCategory.isStatus());
                    request.setAttribute("setting", setting);
                    request.getRequestDispatcher("../view/LoadSettingList.jsp").forward(request, response);
                }

                //Test Type
                if ("Test Type".startsWith(type)) {
                    TestTypeDAO td = new TestTypeDAO();
                    TestType testType = td.getTestTypeByIdLoad(id);
                    settingList setting = new settingList();
                    setting.setId(id);
                    setting.setSettingType("Test Type");
                    setting.setName(testType.getTestTypeName());
                    setting.setStatus(testType.isStatus());
                    request.setAttribute("setting", setting);
                    request.getRequestDispatcher("../view/LoadSettingList.jsp").forward(request, response);
                }

                //Quiz Level
                if ("Quiz Level".startsWith(type)) {
                    QuizLevelDAO qd = new QuizLevelDAO();
                    QuizLevel quizLevel = qd.getQuizLevelByIdLoad(id);
                    settingList setting = new settingList();
                    setting.setId(id);
                    setting.setSettingType("Quiz Level");
                    setting.setName(quizLevel.getQuizLevelName());
                    setting.setStatus(quizLevel.isStatus());
                    request.setAttribute("setting", setting);
                    request.getRequestDispatcher("../view/LoadSettingList.jsp").forward(request, response);
                }

                //Lesson Type
                if ("Lesson Type".startsWith(type)) {
                    LessonTypeDAO ld = new LessonTypeDAO();
                    LessonType lessonType = ld.getLessonTypeByIdLoad(id);
                    settingList setting = new settingList();
                    setting.setId(id);
                    setting.setSettingType("Lesson Type");
                    setting.setName(lessonType.getLessonTypeName());
                    setting.setStatus(lessonType.isStatus());
                    request.setAttribute("setting", setting);
                    request.getRequestDispatcher("../view/LoadSettingList.jsp").forward(request, response);
                }

                //Dimension Type
                if ("Dimension Type".startsWith(type)) {
                    DimensionTypeDAO dd = new DimensionTypeDAO();
                    DimensionType dimensionType = dd.getDimensionTypeByIdLoad(id);
                    settingList setting = new settingList();
                    setting.setId(id);
                    setting.setSettingType("Dimension Type");
                    setting.setName(dimensionType.getDimensionTypeName());
                    setting.setStatus(dimensionType.isStatus());
                    request.setAttribute("setting", setting);
                    request.getRequestDispatcher("../view/LoadSettingList.jsp").forward(request, response);
                }

            }

            if (service.equals("editSetting")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String type = request.getParameter("settingtype");
                String name = request.getParameter("settingName");
                int status = Integer.parseInt(request.getParameter("status"));
                boolean statuss = true;
                if (status == 0) {
                    statuss = false;
                }
                //User Roles
                if ("User Roles".startsWith(type)) {
                    UserRoleDAO ud = new UserRoleDAO();
                    ud.UpDateUserRolesSetting(id, name, statuss);
                    request.getRequestDispatcher("settingList?service=getInformation").forward(request, response);
                }

                //Post Category
                if ("Post Category".startsWith(type)) {
                    PostCategoryDAO pd = new PostCategoryDAO();
                    pd.UpDatePostCategorySetting(id, name, statuss);
                    request.getRequestDispatcher("settingList?service=getInformation").forward(request, response);
                }

                //Subject Category
                if ("Subject Category".startsWith(type)) {
                    SubjectCategoryDAO sd = new SubjectCategoryDAO();
                    sd.UpDateSubjectCategorySetting(id, name, statuss);
                    request.getRequestDispatcher("settingList?service=getInformation").forward(request, response);
                }

                //Test Type
                if ("Test Type".startsWith(type)) {
                    TestTypeDAO td = new TestTypeDAO();
                    td.UpDateTestTypeSetting(id, name, statuss);
                    request.getRequestDispatcher("settingList?service=getInformation").forward(request, response);
                }

                //Quiz Level
                if ("Quiz Level".startsWith(type)) {
                    QuizLevelDAO qd = new QuizLevelDAO();
                    qd.UpDateQuizLevelSetting(id, name, statuss);
                    request.getRequestDispatcher("settingList?service=getInformation").forward(request, response);
                }

                //Lesson Type
                if ("Lesson Type".startsWith(type)) {
                    LessonTypeDAO ld = new LessonTypeDAO();
                    ld.UpDateLessonTypeSetting(id, name, statuss);
                    request.getRequestDispatcher("settingList?service=getInformation").forward(request, response);
                }

                //Dimension Type
                if ("Dimension Type".startsWith(type)) {
                    DimensionTypeDAO dd = new DimensionTypeDAO();
                    dd.UpDateDimensionTypeSetting(id, name, statuss);
                    request.getRequestDispatcher("settingList?service=getInformation").forward(request, response);
                }
            }

            if (service.equalsIgnoreCase("addLoad")) {
                request.getRequestDispatcher("../view/AddSettingDetail.jsp").forward(request, response);
            }

            if (service.equalsIgnoreCase("AddSetting")) {
                String type = request.getParameter("type");
                String name = request.getParameter("settingName");
                String status = request.getParameter("status");
                boolean statuss = true;
                if (status.equals("0")) {
                    statuss = false;
                }
                
                //User Roles
                if (type.equalsIgnoreCase("1")) {
                    UserRoleDAO ud = new UserRoleDAO();
                    ud.InsertUserRolesSetting(name, statuss);
                    request.getRequestDispatcher("settingList?service=getInformation").forward(request, response);
                }

                //Post Category
                if (type.equalsIgnoreCase("2")) {
                    PostCategoryDAO pd = new PostCategoryDAO();
                    pd.InsertPostCategorySetting(name, statuss);
                    request.getRequestDispatcher("settingList?service=getInformation").forward(request, response);
                }

                //Subject Category
                if (type.equalsIgnoreCase("3")) {
                    SubjectCategoryDAO sd = new SubjectCategoryDAO();
                    sd.InsertSubjectCategorySetting(name, statuss);
                    request.getRequestDispatcher("settingList?service=getInformation").forward(request, response);
                }

                //Test Type
                if (type.equalsIgnoreCase("4")) {
                    TestTypeDAO td = new TestTypeDAO();
                    td.InsertTestTypeSetting(name, statuss);
                    request.getRequestDispatcher("settingList?service=getInformation").forward(request, response);
                }

                //Quiz Level
                if (type.equalsIgnoreCase("5")) {
                    QuizLevelDAO qd = new QuizLevelDAO();
                    qd.InsertQuizLevelSetting(name, statuss);
                    request.getRequestDispatcher("settingList?service=getInformation").forward(request, response);
                }

                //Lesson Type
                if (type.equalsIgnoreCase("6")) {
                    LessonTypeDAO ld = new LessonTypeDAO();
                    ld.InsertLessonTypeSetting( name, statuss);
                    request.getRequestDispatcher("settingList?service=getInformation").forward(request, response);
                }

                //Dimension Type
                if (type.equalsIgnoreCase("7")) {
                    DimensionTypeDAO dd = new DimensionTypeDAO();
                    dd.InsertDimensionTypeSetting( name, statuss);
                    request.getRequestDispatcher("settingList?service=getInformation").forward(request, response);
                }
            }


        } catch (Exception ex) {
            Logger.getLogger(SettingListController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMess", ex.toString());
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
