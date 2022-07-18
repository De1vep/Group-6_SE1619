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
        function dobackSetting() {
            window.location.href = "settingList?service=getInformation";
        }

    </script>
    <body class="sb-nav-fixed">
        <%@include file="../components/navbarAdmin.jsp" %>
        <div id="layoutSidenav">
            <%@include file="../components/leftAdmin.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4 mt-5">
                        <div class="row">
                            <div class="col-md-11">
                                <div class="container-fluid borderQuestion px-2 py-2 mb-3" style="height: 500px;">
                                    <h2 class="text-center" onclick="dobackSetting()">Setting List</h2>
                                    <%-- Table Container --%>
                                    <div class="form-group">                           

                                        <div style="float: left; margin-top: 15px;margin-bottom: auto" class="pb-2">
                                            <form action="settingList?service=sort" method="POST" >
                                                Sort By
                                                <select style="height: 30px" name="type1">
                                                    <option value="1" ${(t1.equals("1"))?"selected = \"selected\"":""}>Id</option>
                                                    <option value="0" ${(t1.equals("0"))?"selected = \"selected\"":""}>Setting Type</option>
                                                    <option value="-1" ${(t1.equals("-1"))?"selected = \"selected\"":""}>Name</option>                                                  
                                                </select >
                                                <select style="height: 30px" name="type2">
                                                    <option value="1" ${(t1.equals("1"))?"selected = \"selected\"":""}>Ascending</option>
                                                    <option value="0" ${(t2.equals("0"))?"selected = \"selected\"":""}>Descending</option>
                                                </select>
                                                <input type="submit" value="Sort">
                                            </form>
                                        </div>

                                        <div class="dropdown">
                                            <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false" style="float: right; margin-top: auto;margin-bottom: auto">
                                                Filter by
                                            </button>
                                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                                <li><a class="dropdown-item" href="settingList?service=filter&field=all">All</a></li>
                                                <li><a class="dropdown-item" href="settingList?service=filter&field=userRole">User Role</a></li>
                                                <li><a class="dropdown-item" href="settingList?service=filter&field=postCate">Post Category</a></li>
                                                <li><a class="dropdown-item" href="settingList?service=filter&field=subjectCate">Subject Category</a></li>
                                                <li><a class="dropdown-item" href="settingList?service=filter&field=testType">Test Type</a></li>
                                                <li><a class="dropdown-item" href="settingList?service=filter&field=quizLevel">Quiz Level</a></li>
                                                <li><a class="dropdown-item" href="settingList?service=filter&field=lessonType">Lesson Type</a></li>
                                                <li><a class="dropdown-item" href="settingList?service=filter&field=dimensionType">Dimension Type</a></li>
                                            </ul>
                                        </div>
                                        <a href="settingList?service=addLoad">
                                            <button type="button" class="btn btn-primary mb-2" style="float: right; margin-top: auto;margin-bottom: auto;margin-right: 10px;">
                                                Add setting
                                            </button>
                                        </a>
                                    </div>  

                                    <%-- Table of QuestionList--%>
                                    <table id="table-id" class="table table-bordered table-hover table-striped mt-3">
                                        <thead>
                                            <tr style="background-color: #F0D8D5;">
                                                <th onclick="sortTable(0)">Id</th>
                                                <th onclick="sortTable(1)">Setting Type</th>
                                                <th onclick="sortTable(2)">Name</th>
                                                <th onclick="sortTable(3)">Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead> 
                                        <tbody>
                                            <%-- user role list --%>
                                            <c:forEach items="${settingLists}" var="s">
                                                <tr>
                                                    <td><c:out value="${s.getId()}"/></td>
                                                    <td><c:out value="${s.getSettingType()}"/></td>
                                                    <td><c:out value="${s.getName()}"/></td>
                                                    <td><c:if test="${s.isStatus()}">
                                                            Active
                                                        </c:if>
                                                        <c:if test="${!s.isStatus()}">
                                                            Inactive
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <a href=settingList?service=loadSetting&&id=${s.getId()}&&type=${s.getSettingType()}" ><button  class="btn btn-success">Edit</button></a>
                                                    </td>
                                                </tr>
                                            </c:forEach>


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


