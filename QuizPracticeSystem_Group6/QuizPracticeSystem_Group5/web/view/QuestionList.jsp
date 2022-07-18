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
    <body class="sb-nav-fixed">
        <%@include file="../components/navbarAdmin.jsp" %>
        <div id="layoutSidenav">
            <%@include file="../components/leftAdmin.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Question List</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="Dashboard" class="text-decoration-none">Dashboard</a></li>
                            <li class="breadcrumb-item active">Question List</li>
                        </ol>
                        <div class="row">
                            <div class="col-md-2">
                                <div class="position-sticky borderQuestion px-2 py-2" style="top: 5rem; height: 500px;">
                                    <h2 class="text-center">Filter</h2>
                                    <div class="mb-4">
                                        <%-- Start search form --%>
                                        <form action = "question" class="navbar-form" method="get">
                                            <div class="input-group">
                                                <input type="hidden" name="service" value="search">
                                                <input  class="form-control" type="text" id="content" placeholder="Content... " name="content" value="${content}" style="display: inline-block">
                                                <span class="input-group-btn">
                                                    <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                                </span>
                                            </div>
                                        </form>                     
                                    </div>
                                    <%-- Start filter form --%>
                                    <form action="question" method="GET">
                                        <input type="hidden" name="service" value="filter">
                                        <div class="form-group">
                                            <h5>Filter by Subject</h5>
                                            <%-- Choose Subject Filter --%>
                                            <select class="form-control" name="subjectId">                                
                                                <option value="0" selected="">Choose...</option>
                                                <c:forEach items="${listSubject}" var="subject">
                                                    <option value="${subject.getSubjectId()}" ${subject.getSubjectId() == subjectId ? "selected":""} ><c:out value="${subject.getSubjectName()}" /></option>                          
                                                </c:forEach>                          
                                            </select>
                                            <h5>Filter by Dimension</h5>
                                            <%-- Choose Dimension Filter --%>
                                            <select class="form-control" name="dimensionId">
                                                <option value="0" selected="">Choose...</option>
                                                <c:forEach items="${listDimension}" var="dimension">
                                                    <option value="${dimension.getDimensionId()}" ${dimension.getDimensionId() == dimensionId ? "selected":""}><c:out value="${dimension.getDimensionName()}" /></option>                          
                                                </c:forEach>                          
                                            </select>
                                            <h5>Filter by Lesson</h5>
                                            <%-- Choose Lesson Filter --%>
                                            <select class="form-control" name="lessonId">
                                                <option value="0" selected="">Choose...</option>
                                                <c:forEach items="${listLesson}" var="lesson">
                                                    <option value="${lesson.getLessonId()}" ${lesson.getLessonId() == lessonId ? "selected":""}><c:out value="${lesson.getLessonName()}" /></option>                          
                                                </c:forEach>                          
                                            </select>
                                            <h5>Filter by Status</h5>
                                            <%-- Choose Status Filter --%>
                                            <select class="form-control" name="status">
                                                <option value="-1" selected="">Choose...</option>
                                                <option value="1">Activity</option>
                                                <option value="0">Non-Activity</option>
                                            </select>
                                        </div>
                                        <div class="mt-4 mb-5">
                                            <button type="submit" id="submit" class="btn btn-success" style="width: 100%">Filter</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="col-md-10">
                                <div class="container-fluid borderQuestion px-2 py-2 mb-3" style="height: 500px;">
                                    <%-- Table Container --%>
                                    <div class="form-group">                           
<!--                                        <a href="../view/question?service=addQuestion"><button class="btn btn-success" style="float:right;margin: 5px">Add Question</button></a>
                                        <a data-bs-toggle="modal" data-bs-target="#importQuestionModal" ><button class="btn btn-danger" style="float: right; margin: 5px">Import Question</button></a>-->
                                    </div>  

                                    <%-- Table of QuestionList--%>
                                    <table id="table-id" class="table table-bordered table-hover table-striped mt-3">
                                        <thead>
                                            <%-- Headers of Table--%>
                                            <tr class="text-center" style="background-color: #F0D8D5;">
                                                <th style="width: 10%">Question ID</th>
                                                <th style="width: 35%">Content</th>
                                                <th>Subject</th>
                                                <th>Lesson</th>
                                                <th>Dimension</th>
                                                <th>Status</th>
                                                <th>Action</th></tr>
                                        </thead> 
                                        <tbody>
                                            <c:choose>
                                                <c:when test="${empty listQuestion}">
                                                    <tr style="color: red"><td colspan="8">No Question Available</td></tr>
                                                </c:when>                               
                                                <%-- Check if listQuestionManage not null then display listQuestionManage --%>
                                                <c:otherwise>
                                                    <c:forEach items="${listQuestion}" var="questionList">
                                                        <tr class="text-center">
                                                            <td>${questionList.getQuestionId()}</td>
                                                            <td>${questionList.getContent()}</td>
                                                            <td>
                                                                <c:forEach items="${listSubject}" var="subject">
                                                                    <c:if test="${subject.getSubjectId() == questionList.getSubjectId()}">
                                                                        ${subject.getSubjectName()}
                                                                    </c:if>
                                                                </c:forEach> 
                                                            </td>
                                                            <td>
                                                                <c:forEach items="${listLesson}" var="lesson">
                                                                    <c:if test="${lesson.getLessonId() == questionList.getLessonId()}">
                                                                        ${lesson.getLessonName()}
                                                                    </c:if>
                                                                </c:forEach>
                                                            </td>
                                                            <td>
                                                                <c:forEach items="${listDimension}" var="dimension">
                                                                    <c:if test="${dimension.getDimensionId() == questionList.getDimensionId()}">
                                                                        ${dimension.getDimensionName()}
                                                                    </c:if>
                                                                </c:forEach>
                                                            </td>
                                                            <%-- Check if questionList status is available or not--%>
                                                            <td><c:if test="${questionList.isStatus()}">
                                                                    Available
                                                                </c:if>
                                                                <c:if test="${!questionList.isStatus()}">
                                                                    Not Available
                                                                </c:if>
                                                            </td>
                                                            <td><a href="../view/question?service=questionDetails&questionId=${questionList.getQuestionId()}"><div class="btn btn-success">EDIT</div></a></td>
                                                        </tr>
                                                    </c:forEach> 
                                                </c:otherwise>
                                            </c:choose>
                                            <c:if test="${message != null}">
                                                <tr style="color: red"><td colspan="8"><c:out value="${message}" /></td></tr>
                                                </c:if>
                                        </tbody>
                                    </table>
                                    <%--Import Question --%>
                                    <!-- Modal -->
                                    <div class="modal fade" id="importQuestionModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog modal-xl">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Question Import</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    ...
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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

