<script>
    validateCreateSubject();
    function getImgs(event) {
        var image = URL.createObjectURL(event.target.files[0]);
        var imagediv = document.getElementById("nimage");
        imagediv.setAttribute('src', image);
    }
</script>
<%-- 
    Document   : navbarAdmin
    Created on : Jun 10, 2022, 12:23:35 AM
    Author     : Minh-PC
--%>

<%-- 
    Document   : HomeAdmin
    Created on : May 31, 2022, 4:43:52 PM
    Author     : 84915
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page  import="javax.xml.ws.Holder"%>
<%@page isErrorPage="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
       <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <c:if test="${sessionScope.account.getRoleId()==5}">
            <title>Admin</title>

        </c:if>
        <c:if test="${sessionScope.account.getRoleId()==4}">
            <title>QPS - Quiz Practice System</title>
        </c:if>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="../css/AdminStyle/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <link href="css/styles.css" rel="stylesheet" />

    </head>
    <body class="sb-nav-fixed">
        <%@include file="../components/navbarAdmin.jsp" %>
        <div id="layoutSidenav">
            <%@include file="../components/leftAdmin.jsp" %>
            <div id="layoutSidenav_content">
                <div class="modal-dialog" >
                    <div class="modal-content" >
                        <div class="modal-header">
                            <h5 class="modal-title text-center container-fluid" id="exampleModalLabel">New Subject</h5>
                        </div>
                        <div class="row modal-body">
                            <div class=" text-center">                             
                                <img  style="width: 200px; height: 230px; object-fit:cover; border-radius:50%;" src="../assets/avataSubjectConst.jpg" alt="...">                                   
                            </div>
                            <form action="CreateSubject" method="POST" enctype="multipart/form-data">
                                <div>
                                    <div class="mb-3">
                                        <label class="form-label">Thumbnail</label>    
                                        <input type="file" onchange="getImgs(event)" class="form-control" name="nimage" id="nimage" required>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label">Subject Name</label>
                                        <input type="text" class="form-control" name="subname" required/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Description</label>
                                        <input type="text" class="form-control"  name="subdescription"  required/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Owner</label>
                                        <select class="form-select" aria-label="Default select example" name="ExpertId">  
                                            <option value="-1">Choose..</option>
                                            <c:if test="${!empty listuser}">
                                                <c:forEach items="${listuser}" var="listuser">
                                                    <option id="${listuser.getUserId()}" value="${listuser.getUserId()}"><c:out value="${listuser.getUserName()}"/></option>
                                                </c:forEach>
                                            </c:if>                                                    
                                        </select>
                                    </div>
                                    <div class="mb-3" >
                                        <label class="form-label">Subject Category</label>
                                        <select class="form-select" aria-label="Default select example" name="subcateId" >   
                                            <option value="-1">Choose..</option>
                                            <c:if test="${!empty listSC}">
                                                <c:forEach items="${listSC}" var="listSC">
                                                    <option id="${listSC.getSubjectCateId()}" value="${listSC.getSubjectCateId()}"><c:out value="${listSC.getSubjectCateName()}"/></option>
                                                </c:forEach>
                                            </c:if>                                                               
                                        </select>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label">feature  Subject</label>
                                        <select class="form-select" aria-label="Default select example" name="feature">   
                                            <option value="-1">Choose..</option>
                                            <option value="true">On</option>
                                            <option value="false">Off</option>                                                   
                                        </select>
                                    </div>
                                    </br>
                                    <div class="d-block mx-4 mb-3 mb-lg-4 text-center">
                                        <button type="submit" class="btn btn-warning">Create Subject</button>
                                    </div>
                                </div>
                            </form>
                        </div>                   
                    </div>
                </div>


                <!-------------------------------------------------------------------------------->

                <%@include file="../components/footerAdmin.jsp" %>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="../assets/demo/chart-area-demo.js"></script>
        <script src="../assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="../js/datatables-simple-demo.js"></script>
    </body>
</html>


