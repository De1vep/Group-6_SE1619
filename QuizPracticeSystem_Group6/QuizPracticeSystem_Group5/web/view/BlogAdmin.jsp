<%-- 
    Document   : BlogAdmin
    Created on : Jun 5, 2022, 9:30:50 PM
    Author     : 84915
--%>

<%@page import="model.Blog"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Blog Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="../css/AdminStyle/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <c:set var="sort" value="${sort}"/>
        <c:set var="sortCriteria" value="${sortCriteria}"/>
        <c:if test="${empty page}"><c:set var="page" value="1"/></c:if>

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

                        <div  class="container-fluid px-4">
                            <h1 style="margin: 10px">Blog List</h1>
                            <div class="row " >
                                </br>
                                <div class="col-md-3">
                                    <div class="position-sticky borderQuestion px-2 py-2" style="top: 5rem; height: 500px; ">
                                        <h2 class="text-center">Search</h2>
                                        <div class="mb-4">
                                        <%-- Start search form --%>
                                        <form action = "BlogAdminController" class="navbar-form">
                                            <div class="input-group">
                                                <input  class="form-control" type="text" id="content" placeholder="Search By Blog title..." name="filterTitle" value="${tilte}" style="display: inline-block ; font-size:15px ">
                                                <span class="input-group-btn">
                                                    <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                                </span>
                                                <input type="hidden" name="action" value="filter">
                                                <input type="hidden" name="filterType" value="1">
                                            </div>
                                        </form>
                                        <form action = "BlogAdminController" class="navbar-form">
                                            <label>Search By Blog Date Create</label><br>
                                            <div class="input-group">
                                                <input  class="form-control" type="date" id="content"name="filterCreateAfter" value="${blogCreatedAfter}" style="display: inline-block">
                                                <p>&nbsp; to &nbsp;</p>
                                                <input  class="form-control" type="date" id="content"name="filterCreateBefore" value="${blogCreatedBefore}" style="display: inline-block">
                                                <span class="input-group-btn">
                                                    <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                                </span>
                                                <input type="hidden" name="action" value="filter">
                                                <input type="hidden" name="filterType" value="2">
                                            </div>
                                        </form>
                                        <form action = "BlogAdminController" class="navbar-form">
                                            <label>Search By Blog Last Edit</label><br>
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

                                        <form action = "BlogAdminController" class="navbar-form">
                                            <br>
                                            <div class="input-group">
                                                <input  class="form-control" type="text" id="content" placeholder="Search By Blog Author..." name="filterAuthor" value="${blogAuthor}" style="display: inline-block ; font-size:15px">
                                                <span class="input-group-btn">
                                                    <button type="submit" class="btn btn-primary"><span class="fas fa-search"></span></button>  
                                                </span>
                                                <input type="hidden" name="action" value="filter">
                                                <input type="hidden" name="filterType" value="4">
                                            </div>
                                        </form>
                                    </div>
                                    <h2 class="text-center">Filter</h2>
                                    <%--Filter--%>
                                    <form action = "BlogAdminController" class="filterForm" method="POST">
                                        <div class="row input-group" class="filter-sellection">
                                            <div class="col-md-6"><label>By category</label></div>
                                            <div class="col-md-6">
                                                <select name="cateFilter">
                                                    <option value="-1" ${(cate == -1)?"selected = \"selected\"":""}>All</option>
                                                    <option value="1" ${(cate == 1)?"selected = \"selected\"":""}>News</option>
                                                    <option value="2" ${(cate == 2)?"selected = \"selected\"":""}>Engineering</option>
                                                    <option value="3" ${(cate == 3)?"selected = \"selected\"":""}>Design</option>
                                                    <option value="4" ${(cate == 4)?"selected = \"selected\"":""}>Rest and Relax</option>
                                                </select>
                                            </div>                                        
                                        </div>
                                        </br>
                                        <%--Status--%>
                                        <div class="row input-group" class="filter-sellection">
                                            <div class="col-md-6"><label>By Status</label></div>
                                            <div class="col-md-6">
                                                <select name="statusFilter">
                                                    <option id="status-1" value="-1" ${(blogStatus == -1)?"selected = \"selected\"":""}>All</option>
                                                    <option id="status1" value="1" ${(blogStatus == 1)?"selected = \"selected\"":""}>Show</option>
                                                    <option id="status0" value="2" ${(blogStatus == 2)?"selected = \"selected\"":""}>Hide</option>
                                                </select>
                                            </div>
                                            <div class="input-group">
                                                <input type="hidden" name="action" value="filter">
                                                <input type="hidden" name="filterType" value="5">
                                                <input type="submit" value="Filter" class="btn btn-primary" style="margin: 10px auto;">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>


                            <div class="col-md-9">
                                <div class="container-fluid borderQuestion px-2 py-2 mb-3" >
                                    <div class="container" >
                                        <table id="table-id" class="table table-bordered table-hover table-striped mt-3" >
                                            <thead>
                                                <%-- Headers of Table--%>
                                                <tr style="background-color: #F0D8D5;">
                                                    <th onclick="sortTable(0)">ID</th>
                                                    <th onclick="sortTable(1)">Title</th>
                                                    <th onclick="sortTable(2)">Thumbnail</th>
                                                    <th onclick="sortTable(3)">Create</th>
                                                    <th onclick="sortTable(4)">Last Edit</th>
                                                    <th onclick="sortTable(5)">Author</th>
                                                    <th onclick="sortTable(6)">Detail</th>
                                                    <th onclick="sortTable(7)">Category</th>
                                                    <th onclick="sortTable(8)">Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:choose>
                                                    <c:when test="${empty listBlog}">
                                                        <tr style="color: red"><td colspan="8">No Blog Found</td></tr>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:forEach items="${listBlog}" var="blog">
                                                            <tr>                                                       
                                                                <td><c:out value="${blog.getBlogId()}"/></td>
                                                                <td><c:out value="${blog.getBlogTitle()}"/></td>
                                                                <td><img width="100%" src="../assets/blog/<c:out value="${blog.getThumbnail()}"/>" ></td>
                                                                <td><c:out value="${blog.getCreated()}"/></td>
                                                                <td><c:out value="${blog.getLastEdited()}"/></td>
                                                                <td><c:out value="${blog.getAuthor().getUserName()}"/></td>
                                                                <td><c:out value="${blog.getDetail().substring(0,120)}"/>...</td>
                                                                <td><c:out value="${blog.getPostCategory().getPostCateName()}"/></td>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${blog.getStatus() == true}">
                                                                            Show
                                                                        </c:when>
                                                                        <c:when test="${blog.getStatus() == false}">
                                                                            Hide
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            NULL
                                                                        </c:otherwise>
                                                                    </c:choose>                
                                                                </td>
                                                                <td>
                                                                    <a href="./BlogAdminController?action=detail&id=${blog.getBlogId()}"><div class="btn btn-success">Edit</div></a>
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
                                                        <li class="page-item" id="page${pageNumber}"><a class="page-link" href="./BlogAdminController?page=${pageNumber}&action=changePage">${pageNumber}</a></li>
                                                        </c:forEach>
                                                        <%--Active page--%>
                                                    <li class="page-item active" id="page${page}"><a class="page-link" href="#">${page}</a></li>
                                                        <%--2 buttons after active page--%>


                                                    <c:forEach var="pageNumber" begin="1" end="${maxPage - page}">
                                                        <li class="page-item" id="page${page+pageNumber}"><a class="page-link" href="./BlogAdminController?page=${page+pageNumber}&action=changePage">${page+pageNumber}</a></li>
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
