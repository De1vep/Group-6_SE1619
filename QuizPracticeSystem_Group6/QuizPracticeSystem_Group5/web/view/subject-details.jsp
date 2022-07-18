<%-- 
    Document   : subject-details
    Created on : Jun 14, 2022, 10:13:27 AM
    Author     : DPV
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/x-icon" href="../assets/q-icon.png" />
        <link href="../css/styles.css" rel="stylesheet" />
        <title>${subject.getSubjectName()} | QPS</title>
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="../css/styles.css" rel="stylesheet" />
    </head>
    <body class="bg-light">
        <!-- Responsive navbar-->
        <%@include file="../components/navbar.jsp" %>
        <!-- Header-->
        <header class="bg-dark py-5 mt-5">
            <div class="container px-5">
                <div class="row gx-5 justify-content-center">
                    <div class="col-lg-6">
                        <div class=" my-5">
                            <h1 class="display-5 fw-bolder text-white mb-2">${subject.getSubjectName()}</h1>
                            <p class="lead text-white-50 mb-4">${subject.getDescription()}</p>
                            <p class="text-white me-3">Categories:</p>
                            <div class="d-flex">
                                
                                <c:forEach items="${subject.getCategories()}" var="categories">
                                    <p class="badge bg-secondary text-decoration-none link-light me-2">${categories.getSubjectCateName()}</p>
                                </c:forEach>
                            </div>

                            <div class="d-flex small mb-2" style="color: #E59819">
                                <div class="text-center">
                                    <span class="fw-bold fs-6 me-1" style="color: #B4690E">4.5</span>
                                    <i class="bi-star-fill"></i>
                                    <i class="bi-star-fill"></i>
                                    <i class="bi-star-fill"></i>
                                    <i class="bi-star-fill"></i>
                                    <i class="bi-star-fill"></i>
                                    <span class="fs-6 ms-1" style="color: white">(20)</span>
                                </div>
                            </div>
                            <c:if test="${not empty subjectExpert}">
                                <p class="text-white">Created by ${subjectExpert.getUserName()}</p>
                            </c:if>
                            <c:if test="${not empty subject}">
                                <p class="text-white">Last updated: <fmt:formatDate type = "date" value = "${subject.getCreated()}" /></p>
                            </c:if>
                            <a class="" href="#details">View Details</a>

                            <c:if test="${not empty subject.getPricePackage()}">
                                <div class="d-flex justify-content-start mt-3 mb-3">
                                    <span class="fw-bold fs-2 me-2" style="color: #E59819">$${subject.getPricePackage().get(0).getSalePrice()}</span>
                                    <span class="fw-bold fs-5 me-2 text-decoration-line-through" style="color: #E59819">$${subject.getPricePackage().get(0).getListPrice()}</span>
                                    <p class="fw-bold fs-3 me-2 text-white">(${subject.getPricePackage().get(0).getPackName()})</p>
                                </div>
                            </c:if>

                            <a class="btn btn-warning btn-lg px-5 me-sm-2 text-white fw-bold mt-3" data-bs-toggle="modal" data-bs-target="#exampleModal">Enroll</a>

                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="text-center my-5">
                            <img src="../assets/subject/${subject.getThumbnail()}" alt=""/>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- Features section-->
        <section class="py-4 bg-white border-bottom" id="details">
            <div class="container px-5">
                <div class="row gx-5">
                </div>
            </div>
        </section>
        <!-- Pricing section-->
        <div class="bg-light">
            <div class="container px-5 my-5">
                <div class="row">
                    <div class="col-md-8 bg-white rounded">
                        <h4 class="fw-bolder mb-3 mt-3">Subject Content</h4>
                        <c:if test="${not empty countQuestionInSubject && not empty listLessonById}">
                            <p class="text-muted">${countQuestionInSubject} question <i class="bi bi-dot"></i> ${listLessonById.size()} lessons</p>
                            <div class="accordion mb-3" id="accordionExample">
                                <c:forEach items="${listLessonById}" var="lesson">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#lesson${lesson.getLessonId()}" aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">
                                                <strong>
                                                    <c:forEach items="${listLessonType}" var="lessonType">
                                                        <c:if test="${lessonType.getLessonTypeId() == lesson.getLessonTypeId()}">
                                                            ${lessonType.getLessonTypeName()}
                                                        </c:if>
                                                    </c:forEach>
                                                    : ${lesson.getLessonName()}
                                                </strong>
                                            </button>
                                        </h2>
                                        <div id="lesson${lesson.getLessonId()}" class="accordion-collapse collapse border-bottom" aria-labelledby="panelsStayOpen-headingTwo">
                                            <div class="accordion-body">
                                                <p><i class="bi bi-dot"></i> ${lesson.getContent()}</p>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:if>

                        <h4 class="fw-bolder mb-5 mt-5">Description</h4>
                        <c:if test="${not empty subject}">
                            <p><i class="bi bi-dot"></i> ${subject.getDescription()}</p>
                        </c:if>

                        <h4 class="fw-bolder mb-5 mt-5">Expert</h4>
                        <c:if test="${not empty subjectExpert}">
                            <div class="row">
                                <div class="col-md-2">
                                    <img src="../assets/profile/${subjectExpert.getImage()}" class="rounded-3 ms-2" style="width: 100px;" alt="Avatar" />
                                </div>
                                <div class="col-md-10">
                                    <p><i class="bi bi-dot"></i><strong>Author:</strong> ${subjectExpert.getUserName()}</p>
                                    <p><i class="bi bi-dot"></i><strong>Email:</strong> ${subjectExpert.getEmail()}</p>
                                    <p><i class="bi bi-dot"></i><strong>Phone:</strong> ${subjectExpert.getPhone()}</p>
                                </div>
                            </div> 
                        </c:if>


                    </div>
                    <div class="col-md-4">
                        <div class="position-sticky" style="top: 5rem;">
                            <form action="subject" method="get" class="mb-4">
                                <input name="service" value="search" hidden>
                                <div class="row">
                                    <div class="col-md-12 mb-3">
                                        <input class="form-control" placeholder="Search" name="keyword" value="">
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
                                                            <input class="form-check-input" type="checkbox" name="cateId" value="${lsc.getSubjectCateId()}">
                                                            <label class="form-check-label">${lsc.getSubjectCateName()}</label>
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
                </div>
            </div>
        </div>
        <!-- Footer-->
        <%@include file="../components/footer.jsp" %>
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>




