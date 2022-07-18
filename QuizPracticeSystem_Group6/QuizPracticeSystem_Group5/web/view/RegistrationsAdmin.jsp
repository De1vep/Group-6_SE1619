<%-- 
    Document   : LessonAdmin
    Created on : Jun 13, 2022, 7:01:18 AM
    Author     : 84915
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.RegistrationAdmin"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Registrations  Admin</title>
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
                    <style>
                        .borderQuestion{
                            background: whitesmoke;
                            border-radius: 10px;
                            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
                            border: solid #0075ff 1px;
                        }
                    </style>
                    <%--<c:set var="urlAddOn" value="&sort=${sort}&sortCriteria=${sortCriteria}&criteriaType=${criteriaType}&criteria=${criteria}&genderFilter=${genderFilter}&roleFilter=${roleFilter}&statusFilter=${statusFilter}&service=${service}"/>--%>
                    <%--If page is null, set default is 1--%>
                    <c:if test="${empty page}"><c:set var="page" value="1"/></c:if>

                        <div class="container-fluid px-4">
                            <h1 style="margin: 10px">Registrations List</h1>
                            <div class="row " >
                                </br>
                                <div class="col-md-3">
                                    <div class="position-sticky borderQuestion px-2 py-2" style="top: 5rem; height: 400px; ">
                                    <%-- Start search form --%>
                                    <h2 class="text-center">Search</h2>
                                    <div class="mb-4">
                                        <form action = "RegistrationAdminController" class="navbar-form">
                                            <div class="input-group">
                                                <input  class="form-control" type="text" id="content" placeholder="Search By Subject..." name="filterSubject" value="${sub}" style="display: inline-block ; font-size: 15px ">
                                                <span class="input-group-btn">
                                                    <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                                </span>
                                                <input type="hidden" name="action" value="filter">
                                                <input type="hidden" name="filterType" value="1">
                                            </div>
                                        </form>

                                        <form action = "RegistrationAdminController" class="navbar-form">
                                            <br>
                                            <div class="input-group">
                                                <input  class="form-control" type="text" id="content" placeholder="Search By Email..." name="filterEmail" value="${email}" style="display: inline-block ; font-size: 15px">
                                                <span class="input-group-btn">
                                                    <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                                </span>
                                                <input type="hidden" name="action" value="filter">
                                                <input type="hidden" name="filterType" value="2">
                                            </div>
                                        </form> 

                                        <form action = "RegistrationAdminController" class="navbar-form">
                                            <label>Search By Registration time </label><br>
                                            <div class="input-group">
                                                <input  class="form-control" type="date" id="content"name="filterLastEditAfter" value="${blogLastEditAfterStr}" style="display: inline-block">
                                                <p>&nbsp; to &nbsp;</p>
                                                <input  class="form-control" type="date" id="content"name="filterLastEditBefore" value="${blogLastEditBeforeStr}" style="display: inline-block">
                                                <span class="input-group-btn">
                                                    <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                                </span>
                                                <input type="hidden" name="action" value="filter">
                                                <input type="hidden" name="filterType" value="3">
                                            </div>
                                        </form>

                                    </div>
                                    <h2 class="text-center" >Filter</h2>
                                    <%--Filter--%>
                                    <form action = "RegistrationAdminController" class="filterForm" method="POST">
                                        <%--Status--%>
                                        <div class="row input-group" class="filter-sellection">
                                            <div class="col-md-6"><label>By Status</label></div>
                                            <div class="col-md-6">
                                                <select name="statusFilter" value="${blogStatusStr}">
                                                    <option id="status-1" value="-1" ${(blogStatus == -1)?"selected = \"selected\"":""}>All</option>
                                                    <option id="status1" value="1" ${(blogStatus == 1)?"selected = \"selected\"":""}>Acceptance</option>
                                                    <option id="status0" value="2" ${(blogStatus == 2)?"selected = \"selected\"":""}>Pending</option>
                                                </select>
                                            </div>
                                            <div class="input-group">
                                                <input type="hidden" name="action" value="filter">
                                                <input type="hidden" name="filterType" value="4">
                                                <input type="submit" value="Filter" class="btn btn-primary" style="margin: 10px auto;">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>

                            <div class="col-md-9">
                                <div class="container-fluid borderQuestion px-2 py-2 mb-3" >
                                    <div class="form-group">
                                        <a href=""><button class="btn btn-success" style="float: right; margin: 5px ">New Registration</button></a>
                                    </div>
                                    <div class="container" >
                                        <table id="table-id" class="table table-bordered table-hover table-striped mt-3">
                                            <thead>
                                                <%-- Headers of Table--%>
                                                <tr style="background-color: #F0D8D5;">
                                                    <th onclick="sortTable(0)">ID</th>
                                                    <th onclick="sortTable(1)">Email</th>
                                                    <th onclick="sortTable(2)">Registration time</th>
                                                    <th onclick="sortTable(3)">Subject</th>
                                                    <th onclick="sortTable(4)">Package</th>
                                                    <th onclick="sortTable(5)">Total cost</th>
                                                    <th onclick="sortTable(6)">Valid from</th>
                                                    <th onclick="sortTable(7)">Valid to</th>
                                                    <th onclick="sortTable(8)">Last updated by</th>
                                                    <th onclick="sortTable(9)">Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:choose>
                                                    <c:when test="${empty listReg}">
                                                        <tr style="color: red"><td colspan="8">No Registration  Found</td></tr>
                                                    </c:when>
                                                    <c:otherwise>                                               
                                                        <c:forEach items="${listReg}" var="reg">
                                                            <tr>                                                       
                                                                <td><c:out value="${reg.getRegId()}"/></td>
                                                                <td><c:out value="${reg.getUser().getEmail()}"/></td>
                                                                <td><c:out value="${reg.getRegTime()}"/></td>
                                                                <td><c:out value="${reg.getSubject().getSubjectName()}"/></td>
                                                                <td><c:out value="${reg.getPack().getPackName()}"/></td> 
                                                                <td><c:out value="${reg.getCost()}"/>$</td>
                                                                <td><c:out value="${reg.getValidFrom()}"/></td>
                                                                <td><c:out value="${reg.getValidTo()}"/></td> 
                                                                <td><c:out value="${reg.getLastUpdatedBy().getUserName()}"/></td> 
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${reg.isStatus() == true}">
                                                                            Acceptance
                                                                        </c:when>
                                                                        <c:when test="${reg.isStatus() == false}">
                                                                            Pending
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            NULL
                                                                        </c:otherwise>
                                                                    </c:choose>                
                                                                </td>
                                                                <td>
                                                                    <a href="./RegistrationAdminController?action=detail&id=${reg.getRegId()}"><div class="btn btn-success">Edit</div></a>
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
                                        <div class='pagination-container row'>
                                            <nav aria-label="Page navigation">
                                                <ul class="pagination justify-content-center">                                           
                                                    <%--2 buttons before active page--%>                                            
                                                    <c:forEach var="pageNumber" begin="1" end="${page-1}">
                                                        <li class="page-item" id="page${pageNumber}"><a class="page-link" href="./RegistrationAdminController?page=${pageNumber}&action=changePage">${pageNumber}</a></li>
                                                        </c:forEach>
                                                        <%--Active page--%>
                                                    <li class="page-item active" id="page${page}"><a class="page-link" href="#">${page}</a></li>
                                                        <%--2 buttons after active page--%>

                                                    <c:forEach var="pageNumber" begin="1" end="${maxPage - page}">
                                                        <li class="page-item" id="page${page+pageNumber}"><a class="page-link" href="./RegistrationAdminController?page=${page+pageNumber}&action=changePage">${page+pageNumber}</a></li>
                                                        </c:forEach>
                                                </ul>
                                            </nav>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <%@include file="../components/footerAdmin.jsp" %>
            </div>
        </div>
        <script>
            function sortTable(n) {
                var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
                table = document.getElementById("table-id");
                switching = true;
                //Set the sorting direction to ascending:
                dir = "asc";
                /*Make a loop that will continue until
                 no switching has been done:*/
                while (switching) {
                    //start by saying: no switching is done:
                    switching = false;
                    rows = table.rows;
                    /*Loop through all table rows (except the
                     first, which contains table headers):*/
                    for (i = 1; i < (rows.length - 1); i++) {
                        //start by saying there should be no switching:
                        shouldSwitch = false;
                        /*Get the two elements you want to compare,
                         one from current row and one from the next:*/
                        x = rows[i].getElementsByTagName("TD")[n];
                        y = rows[i + 1].getElementsByTagName("TD")[n];
                        /*check if the two rows should switch place,
                         based on the direction, asc or desc:*/
                        if (dir == "asc") {
                            if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                                //if so, mark as a switch and break the loop:
                                shouldSwitch = true;
                                break;
                            }
                        } else if (dir == "desc") {
                            if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                                //if so, mark as a switch and break the loop:
                                shouldSwitch = true;
                                break;
                            }
                        }
                    }
                    if (shouldSwitch) {
                        /*If a switch has been marked, make the switch
                         and mark that a switch has been done:*/
                        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                        switching = true;
                        //Each time a switch is done, increase this count by 1:
                        switchcount++;
                    } else {
                        /*If no switching has been done AND the direction is "asc",
                         set the direction to "desc" and run the while loop again.*/
                        if (switchcount == 0 && dir == "asc") {
                            dir = "desc";
                            switching = true;
                        }
                    }
                }
            }
        </script>       
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="../assets/demo/chart-area-demo.js"></script>
        <script src="../assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="../js/datatables-simple-demo.js"></script>
    </body>
</html>
