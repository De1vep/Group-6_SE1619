<%-- 
    Document   : PracticeDetail
    Created on : Jun 14, 2022, 12:26:45 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="icon" type="image/x-icon" href="../assets/q-icon.png" />
        <link href="../css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <%-- Check If user is logged in or not, if not redirect to index --%>
        

        <c:if test="${sessionScope.doingQuiz != null}">
            <c:redirect url="PracticeList?service=getPracticeListInformation"/>
        </c:if>
        <%-- Check If user registedSubject is avaiable not, if not redirect to load information --%>
        <c:if test="${registedSubject == null}">
            <c:redirect url="PracticeList?service=getPracticeDetail"/>
        </c:if>        
        <%-- Include header page --%>
         <%@include file="../components/FalseUser.jsp" %>
        <!-- Navbar -->
        <%@include file="../components/navbar.jsp" %>
        <div class="main">

            <%-- Login form --%>
            <div class="container mt-5 pt-5" style="align-self: center; min-height: 50vh" >
                <%-- Start form --%>
                <form action="PracticeList" method="POST">
                    <div class="row">
                        <%-- Bootstrap to center form --%>
                        <div class="col-md-3"></div>
                        <div class="col-md-6">
                            <h1>Practice Detail</h1>
                            <label class="label control-label">Subject</label>
                            <div class="form-group">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-user"></span>
                                </span>
                                <select class="form-control" name="subject">
                                    <c:forEach items="${registedSubject}" var="subject">
                                        <option value="${ subject.getSubjectId()}" onclick=""><c:out value="${subject.getSubjectName()}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <label class="label control-label">Number Of Question</label>
                            <div class="form-group">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-user"></span>
                                </span>
                                <input class="form-control" type="number" name="numberOfQuestion" min="1" max="30" required="">
                            </div>
                            <label class="label control-label"> Dimension </label>
                            <div class="form-group">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-user"></span>
                                </span>
                                <select class="form-control" name="dimension">
                                    <c:forEach items="${dimensionTypes}" var="dimensionType">
                                        <option value="${ dimensionType.getDimensionTypeId()}"><c:out value="${dimensionType.getDimensionTypeName()}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <label class="label control-label">Duration (in minute)</label>
                            <div class="form-group">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-user"></span>
                                </span>
                                <input class="form-control" type="number" name="duration" min="1" max="60" required="">
                            </div>
                            <%-- Display messages, if any --%>
                            <div>
                               
                                <c:if test="${message != null}" >
                                    <h4 style="color:red"> <c:out value="${message}"/> </h4>
                                </c:if>
                            </div>
                            <br>
                            <%-- Submit form --%>
                            <div class="input-group" style="justify-content: center" >
                                <button onclick ="resetTime()" type="submit" id="submit" class="btn btn-success">Practice</button>
                                <input type="hidden" name="service" value="createPractice">
                            </div>
                        </div>
                    </div>
                </form> 
            </div>

        </div>
        <%-- Include footer page --%>
        <jsp:include page="../components/footer.jsp"/>
    </body>
    <script>
        function resetTime() {
            localStorage.clear();
        }
    </script>
</html>