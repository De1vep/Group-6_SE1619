<%-- 
    Document   : LessonDetail
    Created on : Jun 13, 2022, 11:58:58 PM
    Author     : HUY
--%>

<%@page import="model.LessonAdmin"%>
<%@page import="model.Subject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    LessonAdmin s = (LessonAdmin) request.getAttribute("lesson");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Lesson Detail</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="../css/AdminStyle/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <%@include file="../components/navbarAdmin.jsp" %>
        <div id="layoutSidenav">
            <%@include file="../components/leftAdmin.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container" style="align-self: center; min-height: 50vh">

                        <form action="LessonAdminController" method="POST" >
                            <input type="hidden" name="lesId" value="<%= s.getLessonId()%>" />
                            <div class="row">
                                <div class="col-md-3"></div>
                                <div class="col-md-6">
                                    <div>
                                        <h5 style="color:red">  </h5>
                                    </div>
                                    <br>
                                    <h1>Lesson Detail</h1>
                                    <label class="label control-label">Lesson Subject</label>
                                    <div class="form-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input readonly="true" class="form-control" type="text" name="lesSub" required value="<%= s.getSubject().getSubjectName()%>" maxlength="100">
                                    </div>
                                    <label class="label control-label">Lesson Name</label>
                                    <div class="form-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input class="form-control" type="text" name="lesName" required value="<%= s.getLessonName()%>">
                                    </div>

                                    <label class="label control-label">Lesson order</label>
                                    <div class="form-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input class="form-control" type="number" name="lesOrder" required value="<%= s.getLessonOrder()%>">
                                    </div>
                                    
                                    <label class="label control-label">Lesson video link</label>
                                    <div class="form-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input class="form-control" type="text" name="lesLink" required value="<%= s.getVideoLink()%>">
                                    </div>

                                    <label class="label control-label">Lesson content</label>
                                    <div class="form-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <textarea class="form-control" name="lesContent" required rows="4" cols="40" maxlength="1000"><%= s.getContent()%></textarea>
                                    </div>
                                                   
                                    <label class="label control-label">Lesson type</label>
                                    <select name="lesType">
                                        <option value="1" <%= (s.getLessonType().getLessonTypeId()== 1) ? "selected" : ""%>>Subject-Topic</option>
                                        <option value="2" <%= (s.getLessonType().getLessonTypeId() == 2) ? "selected" : ""%>>Lesson</option>
                                        <option value="3" <%= (s.getLessonType().getLessonTypeId() == 3) ? "selected" : ""%>>Quiz</option>
                                    </select>  

                                    <label class="label control-label">Status:</label>
                                    <select name="lesStatus">
                                        <option value="0" <%= (!s.isStatus()) ? "selected" : ""%>>Hide</option>
                                        <option value="1" <%= (s.isStatus()) ? "selected" : ""%>>Show</option>
                                    </select>                                  
                                    <br>

                                    <div class="input-group" style="margin: 10px auto">
                                        <input type="submit" value="Update" name="action" style="margin-left: auto; margin-right: auto" class="btn btn-success" />
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </main>
                <%@include file="../components/footerAdmin.jsp" %>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
        <script>
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $('#img').attr('src', e.target.result);
                    };
                    reader.readAsDataURL(input.files[0]);
                }
            }
        </script>
    </body>
</html>
