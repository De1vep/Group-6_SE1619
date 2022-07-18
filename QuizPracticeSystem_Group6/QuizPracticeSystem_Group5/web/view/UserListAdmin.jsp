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
        
        function doEdit(id){
            window.location.href = "userList?service=loadUser&&id="+id;
        }
    </script>
    <body class="sb-nav-fixed">
        <%@include file="../components/navbarAdmin.jsp" %>
        <div id="layoutSidenav">
            <%@include file="../components/leftAdmin.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4" onclick="doBackUserList()">User List</h1>
                        <ol class="breadcrumb mb-4">


                        </ol>
                        <div class="row">
                            <div class="col-md-2">
                                <div class="position-sticky borderQuestion px-2 py-2" style="top: 5rem; height: 500px;">
                                    <h2 class="text-center">Filter</h2>
                                    <div class="mb-4">
                                        <%-- Start search form --%>
                                        <form action = "userList" class="navbar-form" method="get">
                                            <label>By Name</label>
                                            <div class="input-group">
                                                <input type="hidden" name="service" value="search">


                                                <input  class="form-control" type="text" id="Name" placeholder="Name... " name="Name" value="${Names}" style="display: inline-block">
                                                <span class="input-group-btn">
                                                    <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                                </span>
                                            </div>
                                        </form>   
                                        <form action = "userList" class="navbar-form" method="get">
                                            <label >By Mail</label>
                                            <div class="input-group">
                                                <input type="hidden" name="service" value="search">


                                                <input  class="form-control" type="text" id="Mail" placeholder="Mail... " name="Mail" value="${Mails}" style="display: inline-block">
                                                <span class="input-group-btn">
                                                    <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                                </span>
                                            </div>
                                        </form> 
                                        <form action = "userList" class="navbar-form" method="get">
                                            <label>By Mobile</label>
                                            <div class="input-group">
                                                <input type="hidden" name="service" value="search">


                                                <input  class="form-control" type="text" id="Mobile" placeholder="Mobile... " name="Mobile" value="${Mobiles}" style="display: inline-block">
                                                <span class="input-group-btn">
                                                    <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                                </span>
                                            </div>
                                        </form> 
                                    </div>
                                    <%-- Start filter form --%>
                                    <form action = "userList" class="filterForm" method="POST">
                                        <%--Gender--%>
                                        <div class="row input-group mb-2">
                                            <div class="col-md-6"><label>By gender</label></div>
                                            <div class="col-md-6">
                                                <select name="genderFilter" class="filter-sellection">
                                                    <option id="gender-1" value="-1" ${(genderFilter == -1)?"selected = \"selected\"":""}>Not Specify</option>
                                                    <option id="gender1" value="1" ${(genderFilter == 1)?"selected = \"selected\"":""}>Male</option>
                                                    <option id="gender0" value="0" ${(genderFilter == 0)?"selected = \"selected\"":""}>Female</option>
                                                </select>
                                            </div>
                                        </div>
                                        <%--Role--%>
                                        <div class="row input-group mb-2" class="filter-sellection">
                                            <div class="col-md-6"><label>By Role</label></div>
                                            <div class="col-md-6">
                                                <select name="roleFilter">
                                                    <option value="-1">Not Specify</option>
                                                    <c:if test="${!empty userRoleList}">
                                                        <c:forEach items="${userRoleList}" var="role">
                                                            <option id="role${role.getUserRoleId()}" value="${role.getUserRoleId()}" ${(roleFilter == role.getUserRoleId())?"selected = \"selected\"":""}><c:out value="${role.getUserRoleName()}"/></option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </div>
                                        <%--Status--%>
                                        <div class="row input-group mb-2" class="filter-sellection">
                                            <div class="col-md-6"><label>By Status</label></div>
                                            <div class="col-md-6">
                                                <select name="statusFilter">
                                                    <option id="status-1" value="-1" ${(statusFilter == -1)?"selected = \"selected\"":""}>Not Specify</option>
                                                    <option id="status1" value="1" ${(statusFilter == 1)?"selected = \"selected\"":""}>Available</option>
                                                    <option id="status0" value="0" ${(statusFilter == 0)?"selected = \"selected\"":""}>Disabled</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="input-group">
                                            <input type="hidden" name="service" value="filter">
                                            <input type="submit" value="Filter" class="btn btn-primary" style="margin: 0px auto;">
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="col-md-10">
                                <div class="container-fluid borderQuestion px-2 py-2 mb-3" style="height: 500px;">
                                    <%-- Table Container --%>
                                    <div class="form-group">                           

                                        <a href="userList?service=createUser"><button class="btn btn-danger" style="float: right; margin: 5px">Create User</button></a>
                                    </div>  

                                    <%-- Table of QuestionList--%>
                                    <table id="table-id" class="table table-bordered table-hover table-striped mt-3">
                                        <thead>
                                            <%-- Headers of Table--%>
                                            <tr class="text-center" style="background-color: #F0D8D5;">
                                                <th>User Id</th>
                                                <th>User Name</th>
                                                <th>Gender</th>
                                                <th>Email</th>
                                                <th>Mobile</th>
                                                <th>Role</th>
                                                <th>Status</th>   
                                                <th>Action</th>
                                        </thead> 
                                        <tbody>
                                            <c:choose>
                                                <c:when test="${empty users}">
                                                    <tr style="color: red"><td colspan="8">No User Available</td></tr>
                                                </c:when>                               
                                                <%-- Check if listQuestionManage not null then display listQuestionManage --%>
                                                <c:otherwise>
                                                    <c:forEach items="${users}" var="u">
                                                        <tr class="text-center">
                                                            <td>${u.getUserId()}</td>
                                                            <td>${u.getUserName()}</td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${u.isGender()}">
                                                                        Male
                                                                    </c:when>    
                                                                    <c:otherwise>
                                                                        Female
                                                                    </c:otherwise>
                                                                </c:choose>

                                                            </td>
                                                            <td>${u.getEmail()}</td>
                                                            <td>${u.getPhone()}</td>
                                                            <td>${u.getUserRole().getUserRoleName()}</td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${u.isStatus()}">
                                                                        Available
                                                                    </c:when>    
                                                                    <c:otherwise>
                                                                        UnAvailable
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                <button type="button" class="btn btn-success" onclick="doEdit(${u.getUserId()})">Edit</button>
                                                            </td>
                                                        </tr>
                                                    </c:forEach> 
                                                </c:otherwise>
                                            </c:choose>
                                            <c:if test="${message != null}">
                                                <tr style="color: red"><td colspan="8"><c:out value="${message}" /></td></tr>
                                                </c:if>
                                        </tbody>
                                    </table>
                                    <%--Start Pagination --%>
                                    <c:if test="${totalPage == 2}">
                                        <div class="d-grid gap-2 col-md-12 mx-auto">
                                            <nav aria-label="Page navigation example">
                                                <ul class="pagination justify-content-center">
                                                    <c:if test="${page == 1}">
                                                        <li class="page-item disabled">
                                                            <a class="page-link" href="#" aria-label="Previous">
                                                                <span aria-hidden="true">Previous</span>
                                                            </a>
                                                        </li>
                                                        <li class="page-item active"><a class="page-link">1</a></li>
                                                        <li class="page-item"><a class="page-link" href="${previousLink}${page+1}">2</a></li>

                                                        <li class="page-item">
                                                            <a class="page-link" href="${previousLink}${page+1}" aria-label="Next">
                                                                <span aria-hidden="true">Next</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:if test="${page == totalPage}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="${previousLink}${page-1}" aria-label="Previous">
                                                                <span aria-hidden="true">Previous</span>
                                                            </a>
                                                        </li>
                                                        <li class="page-item"><a class="page-link" href="${previousLink}${page-1}">1</a></li>
                                                        <li class="page-item active"><a class="page-link">2</a></li>

                                                        <li class="page-item disabled">
                                                            <a class="page-link" href="${previousLink}${page+2}" aria-label="Next">
                                                                <span aria-hidden="true">Next</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </nav>
                                        </div>
                                    </c:if>
                                    <c:if test="${totalPage > 2}">
                                        <div class="d-grid gap-2 col-md-12 mx-auto">
                                            <nav aria-label="Page navigation example">
                                                <ul class="pagination justify-content-center">
                                                    <c:if test="${page == 1}">
                                                        <li class="page-item disabled">
                                                            <a class="page-link" href="#" aria-label="Previous">
                                                                <span aria-hidden="true">Previous</span>
                                                            </a>
                                                        </li>

                                                        <li class="page-item active"><a class="page-link">${page}</a></li>
                                                        <li class="page-item"><a class="page-link" href="${previousLink}${page+1}">${page+1}</a></li>
                                                        <li class="page-item"><a class="page-link" href="${previousLink}${page+2}">${page+2}</a></li>

                                                        <li class="page-item">
                                                            <a class="page-link" href="${previousLink}${page+1}" aria-label="Next">
                                                                <span aria-hidden="true">Next</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:if test="${page > 1 && page < totalPage}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="${previousLink}${page-1}" aria-label="Previous">
                                                                <span aria-hidden="true">Previous</span>
                                                            </a>
                                                        </li>

                                                        <li class="page-item"><a class="page-link" href="${previousLink}${page-1}">${page-1}</a></li>
                                                        <li class="page-item active"><a class="page-link">${page}</a></li>
                                                        <li class="page-item"><a class="page-link" href="${previousLink}${page+1}">${page+1}</a></li>

                                                        <li class="page-item">
                                                            <a class="page-link" href="${previousLink}${page+1}" aria-label="Next">
                                                                <span aria-hidden="true">Next</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:if test="${page == totalPage}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="${previousLink}${page-1}" aria-label="Previous">
                                                                <span aria-hidden="true">Previous</span>
                                                            </a>
                                                        </li>
                                                        <li class="page-item"><a class="page-link" href="${previousLink}${page-2}">${page-2}</a></li>
                                                        <li class="page-item"><a class="page-link" href="${previousLink}${page-1}">${page-1}</a></li>
                                                        <li class="page-item active"><a class="page-link">${page}</a></li>

                                                        <li class="page-item disabled">
                                                            <a class="page-link" aria-label="Next">
                                                                <span aria-hidden="true">Next</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </nav>
                                        </div>
                                    </c:if>
                                </div>
                            </div>

                        </div>

                    </div>
                </main>
                <%@include file="../components/footerAdmin.jsp" %>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="../js/datatables-simple-demo.js" type="text/javascript"></script>
    </body>
</html>


