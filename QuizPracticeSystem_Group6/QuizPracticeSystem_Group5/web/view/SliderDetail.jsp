<%-- 
    Document   : SliderDetail
    Created on : May 31, 2022, 5:26:30 PM
    Author     : 84915
--%>

<%@page import="model.Slider"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    //lấy slider đã được set attribute bên controller
    Slider s = (Slider) request.getAttribute("slider");
%>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Slider Detail</title>
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

                        <form action="slider" method="POST" >
                            <input type="hidden" name="sliderId" value="<%= s.getSliderId() %>" />
                            <div class="row">
                                <div class="col-md-3"></div>
                                <div class="col-md-6">
                                    <div>
                                        <h5 style="color:red">  </h5>
                                    </div>
                                    <br>
                                    <h1>Slider Detail</h1>
                                    <label class="label control-label">Slider Title</label>
                                    <div class="form-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input class="form-control" type="text" name="sliderTitle" required value="<%= s.getSliderTitle() %>" maxlength="100">
                                    </div>

                                    <div  class="wrapper">
                                        <label class="label control-label">Image</label>
                                        <div class="form-group">
                                            <div style="width: 100%;border: dashed green;">
                                                <img id="img" style="width: 100%;" src="../assets/<%= s.getImage() %>" alt="Thumbnail image" >
                                            </div>
                                            <br>
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-user"></span>
                                            </span>
                                            <input id="file" class="form-control" required type="file" name="thumnail" onchange="readURL(this);">
                                        </div>
                                    </div>


                                    <label class="label control-label">BackLink</label>
                                    <div class="form-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input class="form-control" type="text" name="sliderLink" required value="<%= s.getLink() %>" maxlength="100">
                                    </div>

                                    <label class="label control-label" style="margin: 5px auto">Status:</label>
                                    <select name="sliderStatus">
                                        <option value="0" <%= (!s.isStatus()) ? "selected" : "" %>>Hide</option>
                                        <option value="1" <%= (s.isStatus()) ? "selected" : "" %>>Show</option>
                                    </select>                                  
                                    <br>


                                    <label class="label control-label" >Note</label>
                                    <div class="form-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <textarea class="form-control" name="sliderNote" required rows="4" cols="40" maxlength="1000"><%= s.getNote() %></textarea>
                                    </div>


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

