<%-- 
    Document   : ListSliders
    Created on : May 30, 2022, 8:28:23 PM
    Author     : 84915
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page import="javax.xml.ws.Holder"%>--%>
<%@page import="model.Slider"%>
<%@page import="java.util.ArrayList"%>

<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Slider Admin</title>
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
                            <h1 style="margin: 10px">Slider List</h1>
                            <div class="row " >
                                </br>

                                <div class="col-md-3">
                                    <div class="position-sticky borderQuestion px-2 py-2" style="top: 5rem; height: 400px; ">
                                        <h2 class="text-center">Search</h2>
                                        <div class="mb-4">
                                        <%-- Start search form --%>
                                        <%--By name--%>
                                        <form action = "slider" class="navbar-form">
                                            <div class="input-group">
                                                <input  class="form-control" type="text" id="content" placeholder="Search By Title..." name="filterTitle"  style="display: inline-block ; font-size: 10px">
                                                <span class="input-group-btn">
                                                    <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                                </span>
                                                <input type="hidden" name="action" value="filter">
                                                <input type="hidden" name="filterType" value="1">
                                            </div>
                                        </form>   

                                        </br>

                                        <form action = "slider" class="navbar-form">
                                            <div class="input-group">
                                                <input  class="form-control" type="text" id="content" placeholder="Search By Backlink..." name="filterBacklink"  style="display: inline-block ; font-size: 10px">
                                                <span class="input-group-btn">
                                                    <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                                </span>
                                                <input type="hidden" name="action" value="filter">
                                                <input type="hidden" name="filterType" value="2">
                                            </div>
                                        </form>                              
                                        <br>
                                    </div>
                                    <h2 class="text-center">Filter</h2>
                                    <%--Filter--%>
                                    <form action = "slider" class="filterForm" method="POST">

                                        <%--Status--%>
                                        <div class="row input-group" class="filter-sellection">
                                            <div class="col-md-6"><label>By Status</label></div>
                                            <div class="col-md-6">
                                                <select name="statusFilter">
                                                    <option id="status-1" value="-1">All</option>
                                                    <option id="status1" value="1">Show</option>
                                                    <option id="status0" value="0">Hide</option>
                                                </select>
                                            </div>
                                            <div class="input-group">
                                                <input type="hidden" name="action" value="filter">
                                                <input type="hidden" name="filterType" value="3">
                                                <input type="submit" value="Filter" class="btn btn-primary" style="margin: 5px auto;">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="col-md-9">
                                <div class="container-fluid borderQuestion px-2 py-2 mb-3" >
                                    <div class="container" >
                                        <table id="table-id" class="table table-bordered table-hover table-striped mt-3">
                                            <thead>
                                                <%-- Headers of Table--%>
                                                <tr style="background-color: #F0D8D5;">
                                                    <th onclick="sortTable(0)">ID</th>
                                                    <th onclick="sortTable(1)">Title</th>
                                                    <th onclick="sortTable(2)">Image</th>
                                                    <th onclick="sortTable(3)">Backlink</th>
                                                    <th onclick="sortTable(4)">Note</th>
                                                    <th onclick="sortTable(5)">Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:choose>
                                                    <c:when test="${empty sliders}">
                                                        <tr style="color: red"><td colspan="8">No User Found</td></tr>
                                                    </c:when>
                                                    <c:otherwise>                                               
                                                        <c:forEach items="${sliders}" var="slider">
                                                            <tr>                                                       
                                                                <td><c:out value="${slider.getSliderId()}"/></td>
                                                                <td><c:out value="${slider.getSliderTitle()}"/></td>
                                                                <td><img width="30%" src="../assets/<c:out value="${slider.getImage()}"/>" ></td>
                                                                <td><c:out value="${slider.getLink()}"/></td>
                                                                <td><c:out value="${slider.getNote()}"/></td>                                                       
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${slider.isStatus() == true}">
                                                                            Show
                                                                        </c:when>
                                                                        <c:when test="${slider.isStatus() == false}">
                                                                            Hide
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            NULL
                                                                        </c:otherwise>
                                                                    </c:choose>                
                                                                </td>
                                                                <td>
                                                                    <a href="./slider?action=detail&id=${slider.getSliderId()-1}"><div class="btn btn-success">Edit</div></a>
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
                                                        <li class="page-item" id="page${pageNumber}"><a class="page-link" href="./slider?page=${pageNumber}&action=changePage">${pageNumber}</a></li>
                                                        </c:forEach>
                                                        <%--Active page--%>
                                                    <li class="page-item active" id="page${page}"><a class="page-link" href="#">${page}</a></li>
                                                        <%--2 buttons after active page--%>
                                                        <c:forEach var="pageNumber" begin="1" end="${maxPage - page}">
                                                        <li class="page-item" id="page${page+pageNumber}"><a class="page-link" href="./slider?page=${page+pageNumber}&action=changePage">${page+pageNumber}</a></li>
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
