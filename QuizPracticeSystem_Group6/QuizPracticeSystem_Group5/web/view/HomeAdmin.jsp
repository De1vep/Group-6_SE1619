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
        <%@include file="../components/navbarAdmin.jsp" %>
        <div id="layoutSidenav">
            <%@include file="../components/leftAdmin.jsp" %>
            <div id="layoutSidenav_content">

                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4"></h1>

                        <div class="row">
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-primary text-white mb-4">
                                    <div class="card-body">TOTAL SUBJECTS</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a>20</a>

                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-warning text-white mb-4">
                                    <div class="card-body">TOTAL REGISTRATIONS</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a>30</a>

                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-success text-white mb-4">
                                    <div class="card-body">TOTAL USER</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a>50</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-danger text-white mb-4">
                                    <div class="card-body">TOTAL VIEW</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a>100</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-area me-1"></i>
                                        Marketing Sales
                                    </div>
                                    <div class="card-body"><canvas id="myAreaChart" width="100%" height="40"></canvas></div>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-bar me-1"></i>
                                        Marketing Statistics
                                    </div>
                                    <div class="card-body"><canvas id="myBarChart" width="100%" height="40"></canvas></div>
                                </div>
                            </div>
                        </div>

                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                All users
                            </div>
                            <div class="card-body">
                                <c:if test="${users!=null}" >
                                    <table id="datatablesSimple">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Role</th>
                                                <th>Image</th>
                                                <th>Email</th>
                                                <th>Gender</th>
                                                <th>Phone</th>
                                                <th>Status</th>
                                            </tr>
                                        </thead>

                                        <%

                                            ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");

                                        %>

                                        <tbody>
                                            <%                                            for (User sl : users) {
                                            %>
                                            <tr>
                                                <td><%= sl.getUserId()%></td>
                                                <td><%= sl.getUserName()%></td>
                                                <td><%= sl.getRoleId()%></td>
                                                <td><%= sl.getImage()%> </td>
                                                <td><%= sl.getEmail()%> </td>

                                                <td>
                                                    <%if (sl.isGender() == true) {
                                                    %>
                                                    Male
                                                    <%
                                                    } else if (sl.isGender() == false) {
                                                    %>
                                                    Female
                                                    <%
                                                    } else {
                                                    %>
                                                    Else
                                                    <%
                                                        }
                                                    %>
                                                </td>

                                                <td><%= sl.getPhone()%> </td>

                                                <td>
                                                    <%if (sl.isStatus() == true) {
                                                    %>
                                                    Active
                                                    <%
                                                    } else if (sl.isStatus() == false) {
                                                    %>
                                                    Deny
                                                    <%
                                                    } else {
                                                    %>
                                                    Else
                                                    <%
                                                        }
                                                    %>
                                                </td>
                                                <td>
                                                    <!--                                                <a href="CRUD?id=&type=edit&manage=KH" class="btn btn-info btn-circle">
                                                                                                        <i class="fas fa-pen"></i>
                                                                                                    </a>-->
                                                </td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </main>

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
