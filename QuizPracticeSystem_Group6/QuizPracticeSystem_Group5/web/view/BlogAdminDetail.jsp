<%-- 
    Document   : BlogAdminDetail
    Created on : Jun 6, 2022, 9:21:10 PM
    Author     : 84915
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Blog"%>
<!DOCTYPE html>
<%
    Blog s = (Blog) request.getAttribute("blog");
%>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Blog Detail</title>
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

                        <form action="BlogAdminController" method="POST" >
                            <input type="hidden" name="blogId" value="<%= s.getBlogId()%>" />
                            <div class="row">
                                <div class="col-md-3"></div>
                                <div class="col-md-6">
                                    <div>
                                        <h5 style="color:red">  </h5>
                                    </div>
                                    <br>
                                    <h1>Blog Detail</h1>
                                    <label class="label control-label">Blog Title</label>
                                    <div class="form-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input class="form-control" type="text" name="blogTitle" required value="<%= s.getBlogTitle()%>" maxlength="100">
                                    </div>

                                    <label class="label control-label">Date created</label>
                                    <div class="form-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input class="form-control" type="date" name="blogCreated" required value="<%= s.getCreated()%>">
                                    </div>

                                    <label class="label control-label">Last edit</label>
                                    <div class="form-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input class="form-control" type="date" name="blogLastEdit" required value="<%= s.getLastEdited()%>">
                                    </div>

                                    <label class="label control-label">Author</label>
                                    <div class="form-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input readonly="true" class="form-control" type="text" name="blogAuthor" required value="<%= s.getAuthor().getUserName().toString()%>">
                                    </div>

                                    <label class="label control-label">Detail</label>
                                    <div class="form-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <textarea class="form-control" name="blogDetail" required rows="4" cols="40" maxlength="1000"><%= s.getDetail()%></textarea>
                                    </div>

                                    <div  class="wrapper">
                                        <label class="label control-label">Thumbnail</label>
                                        <div class="form-group">
                                            <div style="width: 100%;border: dashed green;">
                                                <img id="img" style="width: 100%;" src="../assets/blog/<%= s.getThumbnail()%>" alt="Thumbnail image" >
                                            </div>                                            
                                        </div>
                                        <br>
                                        <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-user"></span>
                                            </span>
                                            <input id="file" class="form-control" required type="file" name="thumnail" onchange="readURL(this);">
                                    </div>
                                                   
                                    <label class="label control-label " style="margin: 5px auto" >Category:</label>
                                    <select name="blogCate">
                                        <option value="1" <%= (s.getPostCategory().getPostCateId() == 1) ? "selected" : ""%>>News</option>
                                        <option value="2" <%= (s.getPostCategory().getPostCateId() == 2) ? "selected" : ""%>>Engineering</option>
                                        <option value="3" <%= (s.getPostCategory().getPostCateId() == 3) ? "selected" : ""%>>Design</option>
                                        <option value="4" <%= (s.getPostCategory().getPostCateId() == 4) ? "selected" : ""%>>Rest and Relax</option>
                                    </select>  
                                    </br>
                                    <label class="label control-label">Status:</label>
                                    <select name="blogStatus">
                                        <option value="0" <%= (!s.getStatus()) ? "selected" : ""%>>Hide</option>
                                        <option value="1" <%= (s.getStatus()) ? "selected" : ""%>>Show</option>
                                    </select>                                  
                                    <br>
                                    <div class="input-group " style="margin: 10px auto" >
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
