<%-- 
    Document   : QuestionList
    Created on : Jun 26, 2022, 7:39:19 PM
    Author     : DPV
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Question Details</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="../css/AdminStyle/styles.css" rel="stylesheet" type="text/css"/>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <link rel="icon" type="image/x-icon" href="../assets/q-icon.png" />
    </head>
    <style>
        .borderQuestion{
            background: whitesmoke;
            border-radius: 10px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            border: solid #0075ff 1px;
        }
    </style>
    <body class="sb-nav-fixed">
        <%@include file="../components/navbarAdmin.jsp" %>
        <div id="layoutSidenav">
            <%@include file="../components/leftAdmin.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Question Details</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="Dashboard" class="text-decoration-none">Dashboard</a></li>
                            <li class="breadcrumb-item"><a href="question?service=questionList" class="text-decoration-none">Question List</a></li>
                            <li class="breadcrumb-item active">Question Details</li>
                        </ol>

                        <%-- Display messages, if any --%>
                        <div>
                            <c:if test="${!empty notification}">
                                <h6 class="text-danger"><c:out value="${notification}"/></h6>
                            </c:if>
                        </div>

                        <form action="question?service=questionUpdate" method="POST">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="container-fluid border border-3 border-info" style="height: 600px; border-radius: 5px">
                                        <h2 style="text-align: center;">Question</h2>
                                        <input type="text" name="questionId" value="${question.getQuestionId()}" hidden="">
                                        <label class="label control-label fw-bold">Subject</label>
                                        <div class="form-group">
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-user"></span>
                                            </span>
                                            <select class="form-control" name="subject">
                                                <option value="0">Choose</option>
                                                <c:forEach items="${listSubject}" var="subject">
                                                    <option value="${subject.getSubjectId()}" ${question.getSubjectId() == subject.getSubjectId() ? "selected":""}><c:out value="${subject.getSubjectName()}" /></option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div>
                                            <label class="label control-label fw-bold">Dimension</label>
                                            <div class="form-group">
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-user"></span>
                                                </span>
                                                <select class="form-control" name="dimension">
                                                    <option value="0">Choose</option>
                                                    <c:forEach items="${listDimension}" var="dimension">
                                                        <option value="${dimension.getDimensionId()}" ${question.getDimensionId() == dimension.getDimensionId() ? "selected":""}><c:out value="${dimension.getDimensionName()}" /></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div >
                                            <label class="label control-label fw-bold">Lesson</label>
                                            <div class="form-group">
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-user"></span>
                                                </span>
                                                <select class="form-control" name="lesson">
                                                    <option value="0">Choose</option>
                                                    <c:forEach items="${listLesson}" var="lesson">
                                                        <option value="${lesson.getLessonId()}" ${question.getLessonId() == lesson.getLessonId() ? "selected":""}><c:out value="${lesson.getLessonName()}" /></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div>
                                            <label class="label control-label fw-bold">Status</label>
                                            <select id="inputState" class="form-control" style="border: 1px solid #ced4da" name="questionStatus">
                                                <c:choose>
                                                    <c:when test="${question.isStatus()}">
                                                        <option selected value="1">Available</option>
                                                        <option value="0">Disabled</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option  value="1">Available</option>
                                                        <option selected value="0">Disabled</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                        </div>

                                        <div>
                                            <label class="label control-label fw-bold">Question Content</label>
                                            <div class="form-group">
                                                <textarea class="form-control" type="textarea"  name="content" required>${question.getContent()}</textarea>
                                            </div>
                                        </div>

                                        <div>
                                            <label class="label control-label fw-bold">Media</label>
                                            <div class="form-group d-flex justify-content-center">
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-user"></span>
                                                </span>
                                                <input class="form-control" type="textarea" value="${question.getMedia()}" name="media" >

                                                <!-- Button trigger modal -->
                                                <button type="button" class="btn btn-outline-primary ms-2" data-bs-toggle="modal" data-bs-target="#previewMedia">
                                                    Preview
                                                </button>

                                                <!-- Modal -->
                                                <div class="modal fade" id="previewMedia" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog modal-xl">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLabel">Preview Media</h5>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <c:if test="${question.getMedia()!=null}">
                                                                    <c:if test="${mediaType==1}">
                                                                        <iframe width="420" height="315" style="width:100%; height:600px;"
                                                                                src=${question.getMedia()}>
                                                                        </iframe>
                                                                    </c:if>
                                                                    <c:if test="${mediaType==2}">
                                                                        <img src="${question.getMedia()}" style="width:100%; height:auto;">
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${question.getMedia()==null}">
                                                                    <b class="text-danger">No media available!</b>
                                                                </c:if>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>

                                        </div>

                                        <div>
                                            <label class="label control-label fw-bold">Explanation</label>
                                            <div class="form-group">
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-user"></span>
                                                </span>
                                                <textarea class="form-control" type="textarea" name="explanation" required>${question.getExplanation()}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="container-fluid border border-3 border-info" style="height: 600px; border-radius: 5px">
                                        <h2 style="text-align: center">Answer</h2>
                                        <div>
                                            <label class="label control-label fw-bold">True Answer</label><small style="color:red">(*)</small>
                                            <div class="form-group">
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-user"></span>
                                                </span>
                                                <input type="hidden" name="trueAnswerId" value="${trueAnswer.getAnswerId()}">
                                                <input class="form-control" type="textarea" name="trueAnswer" value="${trueAnswer.getAnswerContent()}" required="">
                                            </div>
                                        </div>
                                        <div>
                                            <label class="label control-label fw-bold">Wrong Answer</label><small style="color:red">(*)</small>
                                            <div class="form-group">
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-user"></span>
                                                </span>
                                                <input type="hidden" name="wrongAnswer1Id" value="${falseAnswer.get(0).getAnswerId()}">
                                                <input class="form-control" type="textarea" name="wrongAnswer1" value="${falseAnswer.get(0).getAnswerContent()}" required="">
                                            </div>
                                        </div>
                                        <div>
                                            <label class="label control-label fw-bold">Wrong Answer</label>
                                            <div class="form-group">
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-user"></span>
                                                </span>
                                                <input type="hidden" name="wrongAnswer2Id" value="${falseAnswer.get(1).getAnswerId()}">
                                                <input class="form-control" type="textarea" name="wrongAnswer2" <c:if test="${falseAnswer.size() >= 2}"> value="${falseAnswer.get(1).getAnswerContent()}"</c:if>>
                                                </div>
                                            </div>
                                            <div>
                                                <label class="label control-label fw-bold">Wrong Answer</label>
                                                <div class="form-group">
                                                    <span class="input-group-addon">
                                                        <span class="glyphicon glyphicon-user"></span>
                                                    </span>
                                                    <input type="hidden" name="wrongAnswer3Id" value="${falseAnswer.get(2).getAnswerId()}">
                                                <input class="form-control" type="textarea" name="wrongAnswer3" <c:if test="${falseAnswer.size() == 3}"> value="${falseAnswer.get(2).getAnswerContent()}"</c:if>>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <br>
                                <%-- Submit form --%>
                                <div class="d-flex justify-content-center" >
                                    <button style="background: #7066E0; color: white" type="button" class="btn btn-lg mt-3" data-bs-toggle="modal" data-bs-target="#submitEdit">
                                        Save
                                    </button>
                                </div>

                                <!-- Modal Submit-->
                                <div class="modal fade" id="submitEdit" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <div class="text-center mt-2">
                                                <i class="bi bi-exclamation-circle text-warning fa-6x"></i>
                                                <h3 class="" id="exampleModalLabel">Are you sure?</h3>
                                            </div>
                                            <div class="modal-body text-center">
                                                <h5 class="text-secondary">You won't be able to revert this!<h5>
                                                        </div>
                                                        <div class="modal-footer border-0 d-flex justify-content-center">
                                                            <button type="submit" class="btn btn-primary w-25">Yes</button>
                                                            <button type="button" class="btn btn-danger w-25" data-bs-dismiss="modal">Cancel</button>
                                                        </div>
                                                        </div>
                                                        </div>
                                                        </div>

                                                        </div>
                                                        </form>

                                                        </div>
                                                        </main>
                                                        <%@include file="../components/footerAdmin.jsp" %>
                                                        </div>
                                                        </div>

                                                        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
                                                        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
                                                        <script src="../js/datatables-simple-demo.js" type="text/javascript"></script>
                                                        </body>
                                                        </html>

