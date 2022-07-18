<%-- 
    Document   : index
    Created on : May 26, 2022, 10:04:11 PM
    Author     : DPV
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/x-icon" href="../assets/q-icon.png" />
        <link href="../css/styles.css" rel="stylesheet" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>

        <title>QPS - Quiz Practice System</title>
    </head>

    <body>
        <!--Notification-->
        <%@include file="../components/FalseUser.jsp" %>
        <!-- Navbar -->
        <%@include file="../components/navbar.jsp" %>
        <!-- Slider -->



        <header class="py-5 mt-5">
            <div class="container">
                <c:choose>
                    <c:when test="${empty listSlider}">
                        <div class="row">
                            <h5 class="text-center">No Slide available!</h5>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div id="carouselExample" class="carousel slide" data-bs-ride="carousel">
                            <div class="carousel-inner">
                                <c:forEach items="${listSlider}" var="slider" begin = "0" end = "0">
                                    <div class="carousel-item active">
                                        <img src="../assets/${slider.getImage()}" class="d-block w-100" alt="...">
                                        <div class="carousel-caption d-md-block ">
                                            <h1 class="fs-1">${slider.getSliderTitle()}</h1>
                                        </div>
                                        <a href="subject?service=subjectDetails&subjectId=${slider.getLink()}" class="stretched-link"></a>
                                    </div>
                                </c:forEach>

                                <c:forEach items = "${listSlider}" var="slider" begin = "1" end = "${listSlider.size()-1}">
                                    <div class="carousel-item">
                                        <img src="../assets/${slider.getImage()}" class="d-block w-100" alt="...">
                                        <div class="carousel-caption d-md-block">
                                            <h1 class="fs-1">${slider.getSliderTitle()}</h1>
                                        </div>
                                        <a href="subject?service=subjectDetails&subjectId=${slider.getLink()}" class="stretched-link"></a>
                                    </div>
                                </c:forEach>
                            </div>
                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </header>
        <!-- Latest Post -->
        <div class="container">
            <h2 class="text-center">Latest Post</h2><hr>
            <c:choose>
                <c:when test="${empty listLatestPost}">
                    <div class="card mb-3" >
                        <div class="row">
                            <h5 class="text-center">No Blog available!</h5>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${listLatestPost}" var="latestPost">
                        <div class="card mb-3" >
                            <div class="row g-0">
                                <div class="col-md-4">
                                    <a href="../view/blog?service=blogDetails&&blogId=${latestPost.getBlogId()}"><img src="../assets/blog//${latestPost.getThumbnail()}" class="img-fluid rounded-start" alt="..."></a>
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <a class="text-decoration-none text-black" href="../view/blog?service=blogDetails&&blogId=${latestPost.getBlogId()}"><h5 class="card-title">${latestPost.getBlogTitle()}</h5></a>
                                        <p class="card-text"><small class="text-muted">Published by ${latestPost.getAuthor().getUserName()} on <fmt:formatDate type = "date" value = "${latestPost.getCreated()}" /> </small></p>
                                        <p class="card-text"><c:out value="${latestPost.getDetail().substring(0, 320)}"/> . . .</p>
                                        <a href="../view/blog?service=blogDetails&&blogId=${latestPost.getBlogId()}" class="btn btn-primary">Read More</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

            <div class="d-grid col-2 mx-auto">
                <a href="../view/blog?service=blogList" class="btn btn-outline-primary text-center rounded-pill">Show More</a>
            </div>

        </div>

        <!-- Popular Course -->
        <div class="container">
            <h2 class="text-center mt-5">Featured Subjects</h2><hr>

            <div class="row">
                <c:choose>
                    <c:when test="${empty listFeatureSubject}">
                        <div class="card mb-3" >
                            <div class="row">
                                <h5 class="text-center">No Subject available!</h5>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${listFeatureSubject}" var="featureSubject">
                            <div class="col-md-3 mb-2">
                                <div class="card h-100 mb-2">
                                    <img src="../assets/subject/${featureSubject.getThumbnail()}" class="card-img-top" alt="">
                                    <div class="card-body">
                                        <h5 class="card-title">
                                            ${featureSubject.getSubjectName()}
                                        </h5>
                                        <p class="card-text" style="overflow: hidden">
                                            ${featureSubject.getDescription()}
                                        </p>
                                        <a href="subject?service=subjectDetails&subjectId=${featureSubject.getSubjectId()}" class="stretched-link"></a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </c:otherwise>
                </c:choose>

            </div>
            <div class="d-grid col-2 mx-auto">
                <a href="subject?service=subjectList" class="btn btn-outline-primary text-center mt-2 rounded-pill">Show More</a>
            </div>
        </div>


        <%@include file="../components/footer.jsp" %>
    </body>
</html>
