<%-- 
    Document   : QuestionList
    Created on : Jun 26, 2022, 7:39:19 PM
    Author     : DPV
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Question List</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="../css/AdminStyle/styles.css" rel="stylesheet" type="text/css"/>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <link rel="icon" type="image/x-icon" href="../assets/q-icon.png" />
    </head>
    <style>
        .borderQuestion{
            background: whitesmoke;
            border-radius: 10px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            border: solid #0075ff 1px;
        }
    </style>
    <script>
        function doBackUserList() {
            window.location.href = "userList?service=LoadUserList";
        }
    </script>
    <body class="sb-nav-fixed">
        <%@include file="../components/navbarAdmin.jsp" %>
        <div id="layoutSidenav">
            <%@include file="../components/leftAdmin.jsp" %>
            <div id="layoutSidenav_content">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">

                            <h5 class="modal-title text-center" id="exampleModalLabel">User Detail</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>

                        </div>
                        <div class="row modal-body">

                            <form action="userList?service=editUser" method="POST" >

                                <div class="container-fluid">
                                    <div class="row">
                                        <div class=" text-center">
                                            <c:choose>
                                                <c:when test="${empty user.image}">
                                                    <img  style="width: 200px; height: 230px; object-fit:cover; border-radius:50%;" src="../assets/userConst.png" alt="...">
                                                </c:when>
                                                <c:otherwise>
                                                    <img  style="width: 200px; height: 230px; object-fit:cover; border-radius:50%;" src="../assets/${user.image}" alt="...">   
                                                </c:otherwise>
                                            </c:choose>

                                        </div>
                                        <input type="hidden" class="form-control" name="id" value="${user.userId}"/>
                                        <div class="mb-3">
                                            <label class="form-label">User Name</label>
                                            <input type="text" class="form-control" id="username" name="username" value="${user.userName}" readonly="readonly" required/>
                                        </div>
                                        <div class="mb-3" >
                                            <label class="form-label">Gender</label>
                                            <input type="text" class="form-control" name="gender" value=
                                                   <c:if test="${user.gender==true}">
                                                       "Male"
                                                   </c:if>
                                                   <c:if test="${user.gender==false}">
                                                       "Female"
                                                   </c:if> readonly/>

                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Email Address</label>
                                            <input type="text" class="form-control" name="email" value="${user.email}" readonly="readonly"/>
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Phone</label>
                                            <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}" readonly="readonly" required/>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Role</label>

                                            <select class="form-select" name="roleId" >
                                                <c:forEach items="${userRoleList}" var="u">
                                                    <option ${(u.getUserRoleId() == user.getRoleId())?"selected":""}
                                                        value="${u.getUserRoleId()}" >${u.getUserRoleName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Status</label>
                                            <select class="form-select" aria-label="Default select example" name="status" >
                                                <option ${(user.isStatus())?"selected = \"selected\"":""}
                                                    value="1" >Available</option>
                                                <option ${!(user.isStatus())?"selected = \"selected\"":""}
                                                    value="0" >UnAvailable</option>
                                            </select>
                                        </div>
                                        </br>
                                        <div class="d-block mx-4 mb-3 mb-lg-4 text-center">
                                            <button type="submit" class="btn btn-warning">Save profile</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>                   
                    </div>
                </div>

                <%@include file="../components/footerAdmin.jsp" %>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="../js/datatables-simple-demo.js" type="text/javascript"></script>
    </body>
</html>


