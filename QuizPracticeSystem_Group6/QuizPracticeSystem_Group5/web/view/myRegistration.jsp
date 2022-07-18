<%-- 
    Document   : myRegistration
    Created on : May 30, 2022, 1:07:19 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>My Registration</title>

        <link href="../css/styles.css" rel="stylesheet" type="text/css"/>
        <script>
            function doDelete(id)
            {
                var c = confirm("Are you sure?");
                if (c)
                {
                    window.location.href = "MyRegistration?service=deleteRegistration&&regId=" + id;
                }
            }
        </script>
    </head>
    <body>

        <!--Notification-->
        <%@include file="../components/FalseUser.jsp" %>
        <!-- Navbar -->
        <%@include file="../components/navbar.jsp" %>
        <!-- Slider -->

        <div class="pt-5">
            <div class="container mt-5">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row bg-gradient" >
                            <div class="col-sm-8">
                                <a href="MyRegistration?service=listRegistration" class="text-decoration-none"><h2 class="text-black">My Registration</h2></a>
                            </div>
                            <div class="col-sm-4">
                                <form action="MyRegistration" method="get">
                                      <input name="service" value="search" hidden> 
                                    <button class="btn btn-outline-success text-black" type="submit" >Search</button>
                                    <select name = "subjectCategorySearch" class="btn btn-outline-success text-black">
                                        <c:forEach items="${SubjectCategorys}" var="s">
                                            <option ${(subjectCategoryId == s.getSubjectCateId())?"selected = \"selected\"":""}
                                                value="${s.getSubjectCateId()}">${s.getSubjectCateName()}</option>    
                                        </c:forEach>
                                    </select>
                                </form>
                            </div>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${empty Registrations}">
                            <h1>My Registration is empty</h1>
                        </c:when>    
                        <c:otherwise>
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>subject</th>
                                        <th>Registration Time</th>
                                        <th>Package</th>
                                        <th>Total Cost</th>
                                        <th>Valid From</th>
                                        <th>Valid To</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${Registrations}" var="r">
                                        <tr>

                                            <td>${r.getRegId()}</td>
                                            <td>${r.getSubjectName()}</td>
                                            <td>${r.getRegTime()}</td>
                                            <td>${r.getPackName()}</td>
                                            <td>${r.getCost()}</td>
                                            <td>${r.getValidFrom()}</td>
                                            <td>${r.getValidTo()}</td>
                                            <c:choose>
                                                <c:when test="${r.getStatus()==1}">
                                                    <td>
                                                        acceptance
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>
                                                        Pending
                                                    </td>
                                                </c:otherwise>
                                            </c:choose>


                                            <c:choose>
                                                <c:when test="${r.getStatus()==1}">
                                                    <td>
                                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" >
                                                            acceptance
                                                        </button>
                                                    </td>
                                                </c:when>
                                                <c:when test="${r.getStatus()==0}">
                                                    <td>
                                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" >
                                                            expired
                                                        </button>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>
                                                        <!-- Button trigger modal -->
                                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#EditMyRegistration${r.getRegId()}">
                                                            Edit
                                                        </button>
                                                        <!-- Modal -->
                                                        <div class="modal fade" id="EditMyRegistration${r.getRegId()}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content">
                                                                    <form action="MyRegistration?service=editRegistration&&regId=${r.getRegId()}" method="post">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title" id="exampleModalLabel">Edit Registration</h5>
                                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                        </div>
                                                                        <div class="modal-body">					
                                                                            <div class="form-group">
                                                                                <label>Rigistration ID: </label>
                                                                                <input name="rigistrationID" type="text" class="form-control" value="${r.getRegId()}" required readonly="readonly">
                                                                            </div>

                                                                            <div class="form-group">
                                                                                <label>Package:</label>
                                                                                <select name="package" class="form-select" aria-label="Default select example">
                                                                                    <c:forEach items="${PricePackages}" var="p">
                                                                                        <option ${r.getPackName().equals(p.getPackName()) && r.getSubjectName().equals(p.getSubjectName())?"selected = \"selected\"":""}
                                                                                            value="${p.getPackId()}">${p.getPackName()} - ${p.getSubjectName()}</option>
                                                                                    </c:forEach>
                                                                                </select>
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label>Registration Time: </label>
                                                                                <input name="registrationTime" type="text" value="${r.getRegTime()}" class="form-control" required readonly="readonly">
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label>Total Cost:</label>
                                                                                <input name="cost" type="text" value="${r.getCost()}" class="form-control" required readonly="readonly">
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label>Valid From</label>
                                                                                <input name="validFrom" type="text" value="${r.getValidFrom()}" class="form-control" required readonly="readonly">
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label>Valid To</label>
                                                                                <input name="validTo" type="text" value="${r.getValidTo()}" class="form-control" required readonly="readonly">
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label>Note:</label>
                                                                                <textarea name="note" class="form-control" required>${r.getNote()}</textarea>
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label>Status:</label>
                                                                                <input name="status" type="text" value="Pending" class="form-control" required readonly="readonly">
                                                                            </div>


                                                                        </div>
                                                                        <div class="modal-footer">

                                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                            <button type="submit" class="btn btn-primary">Save changes</button>
                                                                        </div>
                                                                    </form>

                                                                </div>
                                                            </div>
                                                        </div>
                                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" onclick="doDelete(${r.getRegId()})">
                                                            Cancel
                                                        </button>

                                                    </td>
                                                </c:otherwise>
                                            </c:choose>

                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                            <c:choose>
                                <c:when test="${Registrations==null || Registrations.size()==0}">
                                    Not founds
                                </c:when>
                                <c:otherwise>
                                    <nav aria-label="Page navigation example" class="d-flex justify-content-center">
                                        <ul class="pagination">
                                            <li class="page-item"><a class="page-link" href="${previousLink}${page-1}">Previous</a></li>
                                                <c:forEach begin="1" end="${totalPage}" var="i">
                                                <li class="page-item ${i == page?"active":""}"><a class="page-link" href="${previousLink}${i}">${i}</a></li>
                                                </c:forEach>
                                            <li class="page-item"><a class="page-link" href="${previousLink}${page+1}">Next</a></li>
                                        </ul>
                                    </nav>
                                </c:otherwise>
                            </c:choose>

                        </c:otherwise>
                    </c:choose>

                </div>
                <a href="#">
                    <button type="button" class="btn btn-primary" onclick="back()">Back to home</button>
            </div>
        </div>





        <jsp:include page="../components/footer.jsp"></jsp:include>

    </body>
</html>



