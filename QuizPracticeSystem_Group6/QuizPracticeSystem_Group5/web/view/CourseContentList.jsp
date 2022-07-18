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
        <title>Admin</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="../css/AdminStyle/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <link href="css/styles.css" rel="stylesheet" />

    </head>
    <body class="sb-nav-fixed">
        <c:if test="${sessionScope.account.getRoleId()==5}">
            <%@include file="../components/navbarAdmin.jsp" %>
            <div id="layoutSidenav">
                <%@include file="../components/leftAdmin.jsp" %>
                <div id="layoutSidenav_content">
                    <script>
                        #form1{
                        background: whitesmoke;
                        border - radius: 15px;
                        box - shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
                        border: solid #00c6ff 1px;
                        margin: 10px;
                        }
                    </script>
                    <c:if test="${sessionScope.account.getRoleId()==4||sessionScope.account.getRoleId()==5}">

                        <div class="container mt-5"style="margin-top: 3rem">
                            <div class="row " >
                                </br>
                                <div class="col-md-1"></div>
                                <div class="col-md-3 border border-primary rounded"  id="form1" style="height: 650px;">
                                    <div class="position-sticky" style="margin-bottom: 20px;">
                                        </br>
                                        </br>

                                        <form action="CourseContentList" method="GET">    
                                            <input name="service" value="search" hidden="">
                                            <div class="d-flex">
                                                <input class="form-control" placeholder="Search by name" name="keyword" value="${keyword}">
                                                <span class="input-group-btn">
                                                    <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                                </span>
                                            </div>
                                        </form>

                                        <h3 class="mt-2 container-fluid text-center">Filter</h3>
                                        <form action="CourseContentList" method="POST">

                                            <div class="form-group">
                                                <h5>Filter by Category</h5>
                                                <%-- Choose Subject Filter --%>
                                                <select class="form-control" name="subjectCateId">                                
                                                    <option value="-1" selected="">Choose...</option>
                                                    <c:if test="${!empty listSC}">
                                                        <c:forEach items="${listSC}" var="listSC">
                                                            <option id="${listSC.getSubjectCateId()}" value="${listSC.getSubjectCateId()}"><c:out value="${listSC.getSubjectCateName()}"/></option>
                                                        </c:forEach>
                                                    </c:if>                         
                                                </select>
                                                <h5>Filter by Status</h5>
                                                <%-- Choose Dimension Filter --%>
                                                <select class="form-control" name="status">
                                                    <option value="-1" selected="">Choose...</option>
                                                    <option value="1">Activity</option>
                                                    <option value="0">Non-Activity</option>

                                                </select>
                                            </div>
                                            </br>
                                            <div class="input-group">
                                                <button type="submit" id="submit" class="btn btn-success" style="width: 100%">Filter</button>
                                                <input type="hidden" name="service" value="FilterByCategotyAndStatus">
                                            </div>
                                        </form>


                                    </div>
                                </div>
                                <div class="col-md-8 ">
                                    <div class="border border-primary rounded"  style="height: 650px;">
                                        <!-- Subject List -->
                                        <table id="table-id" class="table table-bordered table-striped "id="form1" style="min-height: 600px;">
                                            <c:if test="${sessionScope.account.getRoleId()==5}">
                                                <a href="CreateSubject"><button class="btn btn-success" style="float: right; margin: 5px">New Subject</button></a>
                                            </c:if>
                                            <thead>
                                                <%-- Headers of Table--%>
                                                <tr style="background-color: #F0D8D5;">
                                                    <th>Subject Id</th>
                                                    <th>Subject Name</th>
                                                    <th>Description</th>
                                                    <th style="width: 20%">Thumbnail</th>
                                                    <th>Status</th>
                                                    <th style="width: 20%">Manage</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:choose>
                                                    <c:when test="${empty listSubject}">
                                                        <tr style="color: red"><td colspan="8">No Subject Found</td></tr>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:forEach items="${listSubject}" var="subject">
                                                            <tr>
                                                                <td><c:out value="${subject.getSubjectId()}"/></td>
                                                                <td><c:out value="${subject.getSubjectName()}"/></td>                                       

                                                                <td><c:out value="${subject.getDescription()}"/></td>
                                                                <td><img style="width: 40%;"src="../assets/subject/${subject.getThumbnail()}"></td>
                                                                    <%--Check if user status--%>
                                                                <td><c:if test="${subject.isStatus()}">
                                                                        Available
                                                                    </c:if>
                                                                    <c:if test="${!subject.isStatus()}">
                                                                        Not Available
                                                                    </c:if>
                                                                </td>
                                                                <td><a href="CourseContentDetail?service=viewDetail&subjectId=${subject.getSubjectId()}"><div class="btn btn-success">Edit</div></a>
                                                                <a href="CourseContentList?service=deletesubject&subjectId=${subject.getSubjectId()}"><div  class="btn btn-danger">Delete</div></a></td>
                                                            </tr>
                                                        </c:forEach> 
                                                    </c:otherwise>
                                                </c:choose>

                                            </tbody>
                                        </table>
                                    </div>


                                    <!-- Pagination -->
                                    </br>
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
                                                            <a class="page-link" href="${previousLink}${page+1}" aria-label="Next">
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
                                                    <c:if test="${page >=2  && page < totalPage}">
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


                    </c:if>

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
        </c:if>
        <c:if test="${sessionScope.account.getRoleId()==4}">
            </br>
            <br>
            <%@include file="../components/navbar.jsp" %>

            <script>
                        #form1{
                        background: whitesmoke;
                        border - radius: 15px;
                        box - shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
                        border: solid #00c6ff 1px;
                        margin: 10px;
                        }
            </script>
            <c:if test="${sessionScope.account.getRoleId()==4||sessionScope.account.getRoleId()==5}">

                <div class="container mt-5"style="margin-top: 3rem">
                    <div class="row " >
                        </br>
                        <div class="col-md-1"></div>
                        <div class="col-md-3 border border-primary rounded"  id="form1" style="height: 600px;">
                            <div class="position-sticky" style="margin-bottom: 20px;">
                                </br>
                                </br>

                                <form action="CourseContentList" method="GET">    
                                    <input name="service" value="search" hidden="">
                                    <div class="d-flex">
                                        <input class="form-control" placeholder="Search by name" name="keyword" value="${keyword}">
                                        <span class="input-group-btn">
                                            <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                        </span>
                                    </div>
                                </form>

                                <h3 class="mt-2 container-fluid text-center">Filter</h3>
                                <form action="CourseContentList" method="POST">

                                    <div class="form-group">
                                        <h5>Filter by Category</h5>
                                        <%-- Choose Subject Filter --%>
                                        <select class="form-control" name="subjectCateId">                                
                                            <option value="-1" selected="">Choose...</option>
                                            <c:if test="${!empty listSC}">
                                                <c:forEach items="${listSC}" var="listSC">
                                                    <option id="${listSC.getSubjectCateId()}" value="${listSC.getSubjectCateId()}"><c:out value="${listSC.getSubjectCateName()}"/></option>
                                                </c:forEach>
                                            </c:if>                         
                                        </select>
                                        <h5>Filter by Status</h5>
                                        <%-- Choose Dimension Filter --%>
                                        <select class="form-control" name="status">
                                            <option value="-1" selected="">Choose...</option>
                                            <option value="1">Activity</option>
                                            <option value="0">Non-Activity</option>

                                        </select>
                                    </div>
                                    </br>
                                    <div class="input-group">
                                        <button type="submit" id="submit" class="btn btn-success" style="width: 100%">Filter</button>
                                        <input type="hidden" name="service" value="FilterByCategotyAndStatus">
                                    </div>
                                </form>


                            </div>
                        </div>
                        <div class="col-md-8 ">
                            <div class="border border-primary rounded"  style="height: 600px;">
                                <!-- Subject List -->
                                <table id="table-id" class="table table-bordered table-striped "id="form1" style="min-height: 600px;">
                                    <c:if test="${sessionScope.account.getRoleId()==5}">
                                        <a href="CreateSubject"><button class="btn btn-success" style="float: right; margin: 5px">New Subject</button></a>
                                    </c:if>
                                    <thead>
                                        <%-- Headers of Table--%>
                                        <tr style="background-color: #F0D8D5;">
                                            <th>Subject Id</th>
                                            <th>Subject Name</th>
                                            <th>Description</th>
                                            <th>Thumbnail</th>
                                            <th>Status</th>
                                            <th>Manage</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:choose>
                                            <c:when test="${empty listSubject}">
                                                <tr style="color: red"><td colspan="8">No Subject Found</td></tr>
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach items="${listSubject}" var="subject">
                                                    <tr>
                                                        <td><c:out value="${subject.getSubjectId()}"/></td>
                                                        <td><c:out value="${subject.getSubjectName()}"/></td>                                       

                                                        <td><c:out value="${subject.getDescription()}"/></td>
                                                        <td><img style="width: 40%;"src="../assets/subject/${subject.getThumbnail()}"></td>
                                                            <%--Check if user status--%>
                                                        <td><c:if test="${subject.isStatus()}">
                                                                Available
                                                            </c:if>
                                                            <c:if test="${!subject.isStatus()}">
                                                                Not Available
                                                            </c:if>
                                                        </td>
                                                        <td><a href="CourseContentDetail?service=viewDetail&subjectId=${subject.getSubjectId()}"><div class="btn btn-success">Edit</div></a></td>
                                                    </tr>
                                                </c:forEach> 
                                            </c:otherwise>
                                        </c:choose>

                                    </tbody>
                                </table>
                            </div>


                            <!-- Pagination -->
                            </br>
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
                                                    <a class="page-link" href="${previousLink}${page+1}" aria-label="Next">
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
                                            <c:if test="${page >=2  && page < totalPage}">
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


            </c:if>

            <%@include file="../components/footer.jsp" %>
        </c:if>
    </body>
</html>
