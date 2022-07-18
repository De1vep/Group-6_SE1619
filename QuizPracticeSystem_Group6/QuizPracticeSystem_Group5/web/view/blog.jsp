<%-- 
    Document   : blog
    Created on : May 30, 2022, 9:18:00 AM
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet" />
        <link href="../css/styles.css" rel="stylesheet" />
        <title>Blog List | QPS</title>
    </head>
    <body class="bg-light">

        <%@include file="../components/navbar.jsp" %>

        <div class="container mt-5">
            <div class="row g-5 mt-1">
                <div class="col-md-8">

                    <!-- Blog List -->
                    <c:choose>
                        <c:when test="${empty listPost}">
                            <div class="card mb-3" >
                                <div class="row">
                                    <h5 class="text-center">No Blog available!</h5>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${listPost}" var="lp">
                                <div class="card mb-4 border">
                                    <div class="card-header">
                                        <a href="../view/blog?service=blogDetails&&blogId=${lp.getBlogId()}" class="text-decoration-none" style="color: #4257B2">
                                            <h2>${lp.getBlogTitle()}</h2>
                                        </a>
                                    </div>
                                    <div class="card-body">
                                        <a href="../view/blog?service=blogDetails&&blogId=${lp.getBlogId()}"><img src="../assets/blog/${lp.getThumbnail()}" class="card-img-top" alt="..."></a>
                                    </div>
                                    <div class="card-footer">
                                        <p class="card-text fs-6"><c:out value="${lp.getDetail().substring(0, 320)}"/> . . .</p>
                                        <p><small class="text-muted fs-6">
                                                <b>${lp.getPostCategory().getPostCateName()}</b><br>
                                                Posted by ${lp.getAuthor().getUserName()} 
                                                on <fmt:formatDate type = "date" value = "${lp.getLastEdited()}" /> 
                                                · <a class="text-decoration-none" style="color: #4257B2" href="../view/blog?service=blogDetails&&blogId=${lp.getBlogId()}">View post</a>
                                            </small></p>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>


                    <!-- Pagination -->
                    <c:if test="${totalPage == 2}">
                        <div class="d-grid gap-2 col-md-12 mx-auto">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <c:if test="${page == 1}">
                                        <li class="page-item disabled">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">Previous</span>
                                            </a>
                                        </li>
                                        <li class="page-item active"><a class="page-link">1</a></li>
                                        <li class="page-item"><a class="page-link" href="${previousLink}${page+1}">2</a></li>

                                        <li class="page-item">
                                            <a class="page-link" href="${previousLink}${page+1}" aria-label="Next">
                                                <span aria-hidden="true">Next</span>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:if test="${page == totalPage}">
                                        <li class="page-item">
                                            <a class="page-link" href="${previousLink}${page-1}" aria-label="Previous">
                                                <span aria-hidden="true">Previous</span>
                                            </a>
                                        </li>
                                        <li class="page-item"><a class="page-link" href="${previousLink}${page-1}">1</a></li>
                                        <li class="page-item active"><a class="page-link">2</a></li>

                                        <li class="page-item disabled">
                                            <a class="page-link" href="${previousLink}${page+2}" aria-label="Next">
                                                <span aria-hidden="true">Next</span>
                                            </a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                        </div>
                    </c:if>
                    <c:if test="${totalPage > 2}">
                        <div class="d-grid gap-2 col-md-12 mx-auto">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <c:if test="${page == 1}">
                                        <li class="page-item disabled">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">Previous</span>
                                            </a>
                                        </li>

                                        <li class="page-item active"><a class="page-link">${page}</a></li>
                                        <li class="page-item"><a class="page-link" href="${previousLink}${page+1}">${page+1}</a></li>
                                        <li class="page-item"><a class="page-link" href="${previousLink}${page+2}">${page+2}</a></li>

                                        <li class="page-item">
                                            <a class="page-link" href="${previousLink}${page+1}" aria-label="Next">
                                                <span aria-hidden="true">Next</span>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:if test="${page > 1 && page < totalPage}">
                                        <li class="page-item">
                                            <a class="page-link" href="${previousLink}${page-1}" aria-label="Previous">
                                                <span aria-hidden="true">Previous</span>
                                            </a>
                                        </li>

                                        <li class="page-item"><a class="page-link" href="${previousLink}${page-1}">${page-1}</a></li>
                                        <li class="page-item active"><a class="page-link">${page}</a></li>
                                        <li class="page-item"><a class="page-link" href="${previousLink}${page+1}">${page+1}</a></li>

                                        <li class="page-item">
                                            <a class="page-link" href="${previousLink}${page+1}" aria-label="Next">
                                                <span aria-hidden="true">Next</span>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:if test="${page == totalPage}">
                                        <li class="page-item">
                                            <a class="page-link" href="${previousLink}${page-1}" aria-label="Previous">
                                                <span aria-hidden="true">Previous</span>
                                            </a>
                                        </li>
                                        <li class="page-item"><a class="page-link" href="${previousLink}${page-2}">${page-2}</a></li>
                                        <li class="page-item"><a class="page-link" href="${previousLink}${page-1}">${page-1}</a></li>
                                        <li class="page-item active"><a class="page-link">${page}</a></li>

                                        <li class="page-item disabled">
                                            <a class="page-link" aria-label="Next">
                                                <span aria-hidden="true">Next</span>
                                            </a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                        </div>
                    </c:if>

                </div>

                <div class="col-md-4">
                    <div class="position-sticky" style="top: 5rem;">

                        <form action="blog" method="get">
                            <input name="service" value="search" hidden="">
                            <input class="form-control" placeholder="Search" name="keyword" value="${keyword}">
                            <h3 class="mt-2">Categories</h3>

                            <c:choose>
                                <c:when test="${empty listPostCategory}">
                                    <div class="card mb-3" >
                                        <div class="row">
                                            <h5 class="text-center">No Categories available!</h5>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
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
                                </c:otherwise>
                            </c:choose>

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
