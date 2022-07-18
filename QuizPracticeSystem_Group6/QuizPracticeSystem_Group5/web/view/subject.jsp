<%-- 
    Document   : subject
    Created on : May 29, 2022, 2:01:02 AM
    Author     : DPV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/x-icon" href="../assets/q-icon.png" />
        <link href="../css/styles.css" rel="stylesheet" />
        <title>Subject List | QPS</title>
        <script>
            function doDetail(id) {
                window.location.href = "subject?service=subjectDetails&subjectId=" + id;
            }
        </script>
    </head>
    <body style="background-color: #F6F7FB">
        <%@include file="../components/navbar.jsp" %>
        <main class="container-fluid">
            <div class="row g-5 mt-5 px-5">
                <div class="col-md-3">
                    <div class="position-sticky" style="top: 5rem;">
                        <form action="subject" method="get" class="mb-4">
                            <input name="service" value="search" hidden>
                            <div class="row">
                                <div class="col-md-12 mb-3">
                                    <input class="form-control" placeholder="Search" name="keyword" value="${keyword}">
                                </div>

                                <div class="accordion mb-3" id="accordionExample">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header" id="headingOne">
                                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                                Category
                                            </button>
                                        </h2>
                                        <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                            <div class="accordion-body">
                                                <c:forEach items="${listSubjectCategory}" var="lsc">
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="checkbox" name="cateId" value="${lsc.getSubjectCateId()}"
                                                               <c:forEach items="${cateId}" var="cate">
                                                                   <c:if test="${lsc.getSubjectCateId()==cate}">
                                                                       checked
                                                                   </c:if>
                                                               </c:forEach>
                                                               >
                                                        <label class="form-check-label">${lsc.getSubjectCateName()}</label>
                                                    </div>
                                                </c:forEach>
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
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <button class="btn btn-warning mt-2 mb-2 w-100" type="submit">Search</button>
                            </div>

                        </form>

                        <h3 class="mb-3">Featured Subjects</h3>

                        <c:forEach items="${listFeatureSubject}" var="featureSubject">
                            <div class="card mb-3" style="max-width: 540px;">
                                <div class="row g-0">
                                    <div class="col-md-4">
                                        <img src="../assets/subject/${featureSubject.getThumbnail()}" class="img-fluid rounded-start h-100" alt="...">
                                    </div>
                                    <div class="col-md-8">
                                        <div class="card-body">
                                            <h5 class="card-title">${featureSubject.getSubjectName()}</h5>
                                            <a href="subject?service=subjectDetails&subjectId=${featureSubject.getSubjectId()}" class="stretched-link"></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
                <style>
                    .card:hover{
                        transform: scale(1.1);
                        box-shadow: 0 0 8px;
                    }
                    .card{
                        transition: transform .5s;
                    }
                </style>
                <div class="col-md-9">
                    <div class="container">
                        <c:if test="${not empty listSubject}">
                            <div class="dropdown mb-3">
                                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                    Price
                                    <c:if test="${sortBy.equals('price-asc')}">
                                        : Low to High
                                    </c:if>
                                    <c:if test="${sortBy.equals('price-desc')}">
                                        : High to Low
                                    </c:if>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <li><a class="dropdown-item ${sortBy.equals('price-asc') ? "active":""}" href="${previousLink}${page}&sortBy=price-asc">Price: Low to High</a></li>
                                    <li><a class="dropdown-item ${sortBy.equals('price-desc') ? "active":""}" href="${previousLink}${page}&sortBy=price-desc">Price: High to Low</a></li>
                                </ul>
                            </div>
                        </c:if>


                        <div class="row gx-4  row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                            <c:forEach items="${listSubject}" var="ls">
                                <div class="col mb-5">
                                    <div class="card h-100">
                                        <!-- Featured badge-->
                                        <div class="badge bg-transparent text-white position-absolute" style="top: 0.5rem; left: 0.5rem"><img src="../assets/stickergiant-sale.gif" alt="" height="25"/></div>
                                        <!-- Subject image-->
                                        <!--<img src="../assets/subject/java.jpg" style="width: 100%; height: 80%;" class="card-img-top" alt="">-->
                                        <img class="card-img-top" src="../assets/subject/${ls.getThumbnail()}" alt="..." onclick="doDetail(${ls.getSubjectId()})" />

                                        <!-- Subject details-->
                                        <div class="card-body p-4">


                                            <!-- Subject Name-->
                                            <a href="subject?service=subjectDetails&subjectId=${ls.getSubjectId()}" class="text-decoration-none"><h5 class="fw-bolder" style="color: black">${ls.getSubjectName()}</h5></a>
                                            <!-- Subject Tag line-->
                                            <p style="color: #6A6F8A">${ls.getDescription()}</p>
                                            <p>Categories</p>
                                            <c:forEach items="${ls.getCategories()}" var="categories">

                                                <p class="badge bg-secondary text-decoration-none link-light">${categories.getSubjectCateName()}</p>
                                            </c:forEach>
                                            <!-- Product reviews-->
                                            <div class="d-flex small mb-2" style="color: #E59819">
                                                <div class="text-center">
                                                    <span class="fw-bold fs-6 me-1" style="color: #B4690E">4.5</span>
                                                    <i class="bi-star-fill"></i>
                                                    <i class="bi-star-fill"></i>
                                                    <i class="bi-star-fill"></i>
                                                    <i class="bi-star-fill"></i>
                                                    <i class="bi-star-fill"></i>
                                                    <span class="fs-6 ms-1" style="color: #6A6F8A">(20)</span>
                                                </div>
                                            </div>

                                            <!-- Subject price-->

                                            <c:if test="${not empty ls.getPricePackage()}">
                                                <span class="fw-bold fs-5 me-2">$${ls.getPricePackage().get(0).getSalePrice()}</span>
                                                <span class="text-muted text-decoration-line-through fs-6">$${ls.getPricePackage().get(0).getListPrice()}</span>
                                            </c:if>

                                        </div>
                                        <!-- Subject actions-->
                                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                            <div class="d-grid gap-2">
                                                <a class="btn btn-warning mt-auto fw-bold" data-bs-toggle="modal" data-bs-target="#exampleModal" >Register</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
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
            </div>

        </main>
        <%@include file="../components/footer.jsp" %>
    </body>
</html>
