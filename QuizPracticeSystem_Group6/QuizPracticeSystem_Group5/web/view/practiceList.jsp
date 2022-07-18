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
                            <a href="PracticeList?service=getPracticeListInformation" class="text-decoration-none"><h2 class="text-black">Practice List</h2></a>
                        </div>
                        <div class="row bg-gradient" >

                            <div class="col-sm-9">
                                <form action="PracticeList" method="get">
                                    <input name="service" value="filterPracticeListInformation" hidden> 
                                    <button class="btn btn-outline-success text-black" type="submit" >Search</button>
                                    <select name = "subjectId" class="btn btn-outline-success text-black">
                                        <option value="0">Choose Subject</option>
                                        <c:forEach items="${registedSubject}" var="r">
                                            <option ${(subjectId == r.getSubjectId())?"selected = \"selected\"":""}
                                                value="${r.getSubjectId()}">${r.getSubjectName()}</option>    
                                        </c:forEach>
                                    </select>     
                                </form>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-outline-success"><a href="PracticeList?service=getPracticeDetail" class="text-decoration-none text-black">New Practice</a></button>
                                <button type="button" class="btn btn-outline-success" style="margin-left:  20px"><a href="simulationExam" class="text-decoration-none text-black">Simulation Exam</a></button>
                                
                            </div>
                            
                        </div>
                    </div>

                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Brief Information</th>
                                <th>Test type</th>
                                <th>Date taken</th>
                                <th>Duration</th>
                                <th>Score</th>
                                <th>Detail</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${doingQuiz!=null}">
                                <tr>
                                    <td>
                                        Subject name: <c:out value="${doingQuiz.getSubject().getSubjectName()}"/>
                                        <br>
                                        Exam name: 
                                        <c:if test="${doingQuiz.getQuizName() == null}">
                                            None
                                        </c:if>
                                        <c:if test="${doingQuiz.getQuizName() != null}">
                                            <c:out value="${doingQuiz.getQuizName()}"/>
                                        </c:if>
                                    </td>
                                    <td>${doingQuiz.getTestTypeName()}</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><a href="${contextPath}/quizHandleController?service=quizHandle"> Continue </a></td>
                                </tr>
                            </c:if>
                            <c:if test="${empty customerQuizs}">
                                <tr style="color: red"><td colspan="6">No Taken Test Found</td></tr>
                            </c:if>
                            <c:if test="${!empty customerQuizs}">
                                <c:forEach items="${customerQuizs}" var="quiz">
                                    <tr>
                                        <td>
                                            Subject name: <c:out value="${quiz.getSubjectName()}"/>
                                            <br>
                                            Exam name: 
                                            <c:if test="${quiz.getQuizName() == null}">
                                                None
                                            </c:if>
                                            <c:if test="${quiz.getQuizName() != null}">
                                                <c:out value="${quiz.getQuizName()}"/>
                                            </c:if>
                                        </td>
                                        <td><c:out value="${quiz.getTestTypeName()}"/></td>
                                        <td><c:out value="${quiz.getDateTaken()}"/></td>
                                        <td><c:out value="${quiz.getDurationString()}"/></td>
                                        <td><c:out value="${quiz.getScore()}"/></td>
                                        <td><a href="${contextPath}/quizHandleController?service=quizReview&quizTakeId=${quiz.getQuizTakeId()}&questionNumber=1"> Detail </a></td>
                                    </tr>
                                </c:forEach>
                            </c:if>

                        </tbody>
                    </table>

                    <c:choose>
                        <c:when test="${customerQuizs==null || customerQuizs.size()==0}">
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


                </div>
                <a href="#">
                    <button type="button" class="btn btn-primary" onclick="history.back()">Back</button>
            </div>
        </div>


        <%@include file="../components/footer.jsp" %>
    </body>
</html>
