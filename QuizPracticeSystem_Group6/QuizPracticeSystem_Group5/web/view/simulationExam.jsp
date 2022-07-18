<%-- 
    Document   : practiceList
    Created on : Jun 9, 2022, 11:20:20 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/x-icon" href="../assets/q-icon.png" />
        <link href="../css/styles.css" rel="stylesheet" />
        <link href="../css/AdminStyle/styles.css" rel="stylesheet" />
        <title>JSP Page</title>


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
                        <div class="row">
                            <a href="simulationExam" class="text-decoration-none"><h2 class="text-black">Simulation Exam</h2></a>
                        </div>
                        <div class="row bg-gradient" >

                            <div class="col-sm-12">
                                <div class="row functions" style="margin-top: 30px;">
                                    <div class="col-1">
                                        <h5 style="float:right; margin-top: 6px;">Subject:</h5>
                                    </div>
                                    <div class="col-8">

                                        <form action="simulationExam" method="get">

                                            <button class="btn btn-outline-success text-black" type="submit" >Search</button>
                                            <select name = "subjectSearchId" class="btn btn-outline-success text-black">
                                                <option value="">Choose Subject</option>
                                                <c:forEach items="${subjectList}" var="r">
                                                    <option ${(subjectSearchId == r.getSubjectId())?"selected = \"selected\"":""}
                                                        value="${r.getSubjectId()}">${r.getSubjectName()}</option>    
                                                </c:forEach>
                                            </select>     
                                        </form>


                                    </div>

                                </div>
                            </div>


                        </div>
                    </div>

                    <div class="card-body">
                        <table id="datatablesSimple">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Subject</th>
                                    <th>Simulation Exam</th>
                                    <th>Level</th>
                                    <th>#Question</th>
                                    <th>Duration</th>
                                    <th>Pass Rate</th>   
                                    <th>Action</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:choose>
                                    <c:when test="${!empty simulationList}">
                                        <c:forEach items="${simulationList}" var="quiz">
                                            <tr>
                                                <td>${quiz.getQuizId()}</td>
                                                <td>${quiz.getSubject().getSubjectName()}</td>
                                                <td>${quiz.getQuizName()}</td>
                                                <td>${quiz.getQuizLevelName()}</td>
                                                <td>${quiz.getNumberQuestion()}</td>
                                                <td>${quiz.getDurationStringg()}</td>
                                                <td>${quiz.getPassRate()}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${doingQuiz!=null&&currUser.getUserId()==doingQuiz.getUser().getUserId()}">
                                                            <button style="" data-toggle="modal" data-target="#ModalCenter${quiz.getQuizId()}" ${doingQuiz.getQuiz().getQuizId()==quiz.getQuizId()?"":"disabled"}>${doingQuiz.getQuiz().getQuizId()==quiz.getQuizId()?"Continue":"Currently Taking Another Exam"}</button>   
                                                        </c:when>
                                                        <c:otherwise>
                                                            <button style="" data-toggle="modal" data-target="#ModalCenter${quiz.getQuizId()}">Take Exam</button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>

                                        </c:forEach>
                                    <style>
                                        button{
                                            background-color: #4472c4;
                                            border: 1px white solid;
                                            color:white;
                                        }
                                        button:disabled,
                                        button[disabled]{
                                            opacity: 0.5;
                                        }
                                    </style>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                    <text style="color:red;">There is no simulation exam</text>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>
                    </div>






                </div>
                <a href="#">
                    <button type="button" class="btn btn-primary" onclick="history.back()">Back </button>
            </div>
        </div>


        <%@include file="../components/footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="../assets/demo/chart-area-demo.js"></script>
        <script src="../assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="../js/datatables-simple-demo.js"></script>
    </body>
</html>
