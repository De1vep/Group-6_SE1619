<%-- 
    Document   : blog-details
    Created on : May 30, 2022, 9:40:39 AM
    Author     : DPV
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/x-icon" href="../assets/q-icon.png" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet" />
        <link href="../css/styles.css" rel="stylesheet" />
        <title>${blog.getBlogTitle()} | QPS</title>
    </head>
    <body class="bg-light">
        <%@include file="../components/navbar.jsp" %>

        <div class="container mt-5">
            <div class="row g-5 mt-1">
                <!-- Posts Details -->
                <div class="col-md-8 rounded" style="background-color: white">

                    <!-- Button Return blog list page -->
                    <div class="pb-4 mb-4 mt-4 border-bottom d-flex">
                        <a class="btn btn-close me-2" onclick="history.back()"></a>
                        <b>Return</b>
                    </div>

                    <article class="blog-post">
                        <!--Categories and Updated date-->
                        <div class="d-flex justify-content-between mb-3">
                            <blockquote class="blockquote">
                                <p class="badge bg-secondary text-decoration-none link-light">${blog.getPostCategory().getPostCateName()}</p>
                            </blockquote>
                            <p class="blog-post-meta text-end text-muted fst-italic">Last Edited: <fmt:formatDate type = "date" value = "${blog.getLastEdited()}" /></p>
                        </div>
                        <!-- Blog title -->
                        <h2 class="blog-post-title">${blog.getBlogTitle()}</h2>
                        <!-- Blog author -->
                        <p class="blog-post-meta fst-italic">Author: <strong>${blog.getAuthor().getUserName()}</strong></p>
                        <hr>
                        <!-- Blog detail -->
                        <p class="mb-5">${blog.getDetail()}</p>
                    </article>

                </div>

                <!-- Sider -->
                <div class="col-md-4">
                    <div class="position-sticky" style="top: 5rem;">

                        <form action="blog" method="get">
                            <input name="service" value="search" hidden="">
                            <input class="form-control" placeholder="Search" name="keyword" value="">
                            <h3 class="mt-2">Categories</h3>
                            <c:forEach items="${listPostCategory}" var="postCategory">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="cateId" value="${postCategory.getPostCateId()}"
                                           <c:forEach items="${cateId}" var="cate">
                                               <c:if test="${postCategory.getPostCateId()==cate}">
                                                   checked
                                               </c:if>
                                           </c:forEach>
                                           >
                                    <label class="form-check-label">${postCategory.getPostCateName()}</label>
                                </div>
                            </c:forEach>
                            <button class="btn btn-outline-primary mt-2 mb-2" type="submit">Search</button>
                        </form>

                        <h3 class="mb-3">Latest Posts</h3>

                        <c:forEach items="${listLatestPost}" var="latestPost">
                            <div class="card mb-3" style="max-width: 540px;">
                                <div class="row g-0">
                                    <div class="col-md-4">
                                        <img src="../assets/blog/${latestPost.getThumbnail()}" class="img-fluid rounded-start h-100" alt="...">
                                    </div>
                                    <div class="col-md-8">
                                        <div class="card-body">
                                            <h5 class="card-title">${latestPost.getBlogTitle().substring(0,19)}...</h5>
                                            <i>${latestPost.getPostCategory().getPostCateName()}</i>
                                            <a href="../view/blog?service=blogDetails&blogId=${latestPost.getBlogId()}" class="stretched-link"></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>

            </div>

        </div>
        <%@include file="../components/footer.jsp" %>
    </body>

</html>
