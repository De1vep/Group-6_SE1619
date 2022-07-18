<%-- 
    Document   : CourseContentDetail
    Created on : Jun 22, 2022, 9:41:37 PM
    Author     : Minh-PC
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <c:if test="${sessionScope.account.getRoleId()==5}">
            <title>Admin</title>

        </c:if>
        <c:if test="${sessionScope.account.getRoleId()==4}">
            <title> QPS - Quiz Practice System</title>
        </c:if>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="../css/AdminStyle/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/courseContentDetail.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </head>
    <body>
        <c:if test="${sessionScope.account.getRoleId()==5}">
            <%@include file="../components/navbarAdmin.jsp" %>
            <div id="layoutSidenav">
                <%@include file="../components/leftAdmin.jsp" %>
                <div id="layoutSidenav_content">
                    <div class="row">
                        <div class="col-md-2"></div>
                        <c:if test="${empty displayTab}"><c:set var="displayTab" value="overview"/></c:if>
                        <c:if test="${page > 1}"><c:set var="displayTab" value="dimension"/></c:if>
                        <c:if test="${page1 > 1}"><c:set var="displayTab" value="pricePackage"/></c:if>

                        <%-- Center form --%>
                        <div class="col-md-8">
                            <%-- Header nav tab --%>
                            </br>
                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="tab col-md-8">
                                    <button class="tablinks" onclick="openTab(event, 'tab1')" id="overview">Overview</button>
                                    <button class="tablinks" onclick="openTab(event, 'tab2')" id="dimension">Dimension</button>
                                    <button class="tablinks" onclick="openTab(event, 'tab3')" id="pricePackage">Price Package</button>                                   
                                </div>
                                <div class="col-md-2"></div>
                            </div>
                            <%-- Main tab details --%>
                            </br>
                            <div class="details">
                                <div id="tab1" class="tabcontent">
                                    <h4 style="color: #565e64">Subject Overview/ SubId <c:out value="${subject.getSubjectId()}"/></h4>
                                    <%-- Form details: The whole tab is a form with the subject's details as set values --%>
                                    <form style="padding: 5px;" action="CourseContentDetail">
                                        <%-- First bootstrap form row: subject name, category, featured subject, status and thumbnail image --%>
                                        <div class="form-row">
                                            <div class="form-group col-md-7">
                                                <%-- Subject name --%>
                                                <br>
                                                <label for="subjectName">Subject Name</label>
                                                <input type="text" name="subjectName" class="form-control" value="${subject.getSubjectName()}" style="margin-bottom: 5px;" >
                                                <%-- Category list --%>
                                                <label for="subjectCate">Category</label>
                                                <div class="dropdown">
                                                    <button class="btn btn-default dropdown-toggle" type="button" 
                                                            id="dropdownMenu1" data-toggle="dropdown" 
                                                            aria-haspopup="true" aria-expanded="true"
                                                            style="width: 100%; border: 1px solid #ced4da">
                                                        <i class="fas fa-bars"></i>
                                                        <span class="caret"></span>
                                                    </button>
                                                    <%-- Dropdown for category --%>
                                                    <ul class="dropdown-menu checkbox-menu allow-focus" aria-labelledby="dropdownMenu1">
                                                        <c:if test="${!empty categoryList}">
                                                            <c:forEach items = "${categoryList}" var="category">
                                                                <li>
                                                                    <label>
                                                                        <input type="checkbox" checked name="subjectCategory" value="${category.getSubjectCateId()}"> <c:out value="${category.getSubjectCateName()}"/>
                                                                    </label>
                                                                </li>
                                                            </c:forEach>
                                                        </c:if>
                                                        <c:if test="${!empty categoryRemainList}">
                                                            <c:forEach items = "${categoryRemainList}" var="category">
                                                                <li>
                                                                    <label>
                                                                        <input type="checkbox"  name="subjectCategory" value="${category.getSubjectCateId()}"> <c:out value="${category.getSubjectCateName()}"/>
                                                                    </label>
                                                                </li>
                                                            </c:forEach>
                                                        </c:if>
                                                    </ul>
                                                </div>

                                                <br>
                                                <div class="form-row">
                                                    <div class="form-group col-md-5">
                                                        <%-- Featured subject --%>
                                                        <div class="form-check">
                                                            <c:choose>
                                                                <c:when test="${subject.isFeaturedSubject()}">
                                                                    <input class="form-check-input" type="checkbox" checked id="gridCheck" name="isFeaturedSubject">
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input class="form-check-input" type="checkbox" id="gridCheck" name="isFeaturedSubject">
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <label class="form-check-label" for="gridCheck">
                                                                Featured Subject
                                                            </label>
                                                        </div>
                                                    </div>
                                                    <%-- Status --%>
                                                    <div class="form-group col-md-2">
                                                        <label for="status">Status</label>
                                                    </div>
                                                    <div class="form-group col-md-5" >
                                                        <select id="inputState" class="form-control" style="border: 1px solid #ced4da" name="subjectStatus">
                                                            <c:choose>
                                                                <c:when test="${subject.isStatus()}">
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
                                                </div>

                                            </div>
                                            <div class="form-group col-md-1"></div>
                                            <%-- Thumbnail image --%>
                                            <div class="form-group col-md-4">
                                                <img src="${contextPath}/assets/subject/${subject.getThumbnail()}" style="height: 100%; width: 100%;">
                                                <input type="hidden" name="subjectThumbnail" value="${subject.getThumbnail()}">
                                            </div>
                                        </div>
                                        <%-- Description --%>
                                        <div class="form-group">
                                            <label for="subjectDescription">Description</label>
                                            <textarea class="form-control" style="min-height: 4em; overflow: scroll;" name="subjectDescription" ><c:out value="${subject.getDescription()}"/></textarea>
                                        </div>
                                        <%-- Submit --%>
                                        <div class="form-row">
                                            <div class="form-group">
                                                <a href="CourseContentList?service=CourseContentList" class="btn btn-danger">Back</a>
                                            </div>
                                            <div class="form-group" style="margin-right: 1em; margin-left: 1em;">
                                                <input type="hidden" name="service" value="updateSubject">
                                                <input type="hidden" name="subjectId" value="${subject.getSubjectId()}">
                                                <input type="hidden" name="sujectDimension" value="${subject.getDimensions()}">
                                                <button type="submit" id="submit" class="btn btn-success">Submit</button>
                                            </div>

                                        </div>
                                    </form>
                                    <%-- Display message if available --%>
                                    <c:if test="${!empty detailMessage}">
                                        <h6 style="color: ${detailColor}"><c:out value="${detailMessage}"/></h6>
                                    </c:if>

                                </div>

                                <%-- Subject dimension --%>
                                <div id="tab2" class="tabcontent">

                                    <h4 style="color: #565e64">Subject Dimension/ Id <c:out value="${subject.getSubjectId()}"/></h4>
                                    <%-- Display message if available --%>
                                    <c:if test="${!empty dimensionMessage}">
                                        <h6><c:out value="${dimensionMessage}"/></h6>
                                    </c:if>
                                    <%-- Dimension table: each row is a form that allows admin/expert to edit/delete the dimension --%>
                                    <table class="table table-striped table-bordered table-hover" >
                                        <%-- Table head --%>
                                        <thead class="thead-light" style="background-color: #F0D8D5;">
                                            <tr style="background-color: #F0D8D5;">
                                                <th scope="col" style="background-color: #F0D8D5;">#</th>
                                                <th scope="col" style="background-color: #F0D8D5;">Type</th>
                                                <th scope="col" style="background-color: #F0D8D5;">Dimension</th>
                                                <th scope="col" style="background-color: #F0D8D5;">Description</th>
                                                <th scope="col" style="background-color: #F0D8D5;">Action</th>
                                            </tr>
                                        </thead>
                                        <%-- Table body --%>
                                        <tbody>
                                            <%-- For each dimension, print a row --%>
                                            <c:forEach items = "${ListDimension}" var="dimension" >
                                                <tr>
                                            <form action="CourseContentDetail">
                                                <input type="hidden" name="subjectId" value="${subject.getSubjectId()}">
                                                <input type="hidden" name="dimensionId" value="${dimension.getDimensionId()}">
                                                <input type="hidden" name="service" value="updateDimension">
                                                <th scope="row"><c:out value="${dimension.getDimensionId()}"/></th>
                                                <td>
                                                    <%-- Print the dimension type select box with the selected value of dimension on top --%>
                                                    <select id="inputState" class="inputBorderless" name="dimensionType">
                                                        <option selected value="${dimension.getDimensionTypeId()}"><c:out value="${dimension.getDimensionTypeName()}"/></option>
                                                        <c:if test = "${!empty dimensionTypes}">
                                                            <c:forEach items = "${dimensionTypes}" var="dimensionTypes">
                                                                <c:if test="${dimensionTypes.getDimensionTypeId() != dimension.getDimensionId()}">
                                                                    <option value="${dimensionTypes.getDimensionTypeId()}"><c:out value="${dimensionTypes.getDimensionTypeName()}"/></option>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </td>
                                                <td><input class="inputBorderless" type="text" name="dimensionName" value="${dimension.getDimensionName()}" required></td>
                                                <td><input class="inputBorderless" type="text" name="description" value="${dimension.getDescription()}" placeholder="Description"></td>
                                                <td><input type="submit" class="btn btn-success" name="subService" value="Update" />
                                                    <input type="submit" class="btn btn-danger" name="subService" value="Delete" />
                                                </td>
                                            </form>
                                            </tr>
                                        </c:forEach>
                                        <%-- Form to create a new dimension --%>
                                        <tr>
                                        <form action="CourseContentDetail">
                                            <input type="hidden" name="service" value="addDimension">
                                            <input type="hidden" name="subjectId" value="${subject.getSubjectId()}">
                                            <th scope="row">New</th>
                                            <td>
                                                <select id="inputState" class="inputBorderless" name="dimensionType">
                                                    <c:forEach items = "${dimensionTypes}" var="dimensionTypes">
                                                        <option value="${dimensionTypes.getDimensionTypeId()}"><c:out value="${dimensionTypes.getDimensionTypeName()}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td><input  type="text" name="dimensionName" placeholder="Dimension Name" required></td>
                                            <td><input  type="text" name="description" placeholder="Description"></td>
                                            <td><button type="submit" id="submit" class="btn btn-success">Submit</button>
                                            </td>
                                        </form>
                                        </tr>

                                        </tbody>
                                    </table>

                                    </br>
                                    <c:if test="${totalPage == 2}">
                                        <div class="d-grid gap-2 col-md-12 mx-auto">
                                            <nav aria-label="Page navigation example">
                                                <ul class="pagination justify-content-center">
                                                    <c:if test="${page == 1}">
                                                        <li class="page-item disabled">
                                                            <a class="page-link"  href="#" aria-label="Previous">
                                                                <span aria-hidden="true">Previous</span>
                                                            </a>
                                                        </li>
                                                        <li class="page-item active"><a class="page-link">1</a></li>
                                                        <li class="page-item"><a class="page-link"  href="${previousLink}${page+1}">2</a></li>

                                                        <li class="page-item">
                                                            <a class="page-link"  href="${previousLink}${page+1}" aria-label="Next">
                                                                <span aria-hidden="true">Next</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:if test="${page == totalPage}">
                                                        <li class="page-item">
                                                            <a class="page-link"  href="${previousLink}${page-1}" aria-label="Previous">
                                                                <span aria-hidden="true">Previous</span>
                                                            </a>
                                                        </li>
                                                        <li class="page-item"><a class="page-link" href="${previousLink}${page-1}">1</a></li>
                                                        <li class="page-item active"><a class="page-link">2</a></li>

                                                        <li class="page-item disabled">
                                                            <a class="page-link"  href="${previousLink}${page+1}" aria-label="Next">
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
                                                        <li class="page-item"><a class="page-link"  href="${previousLink}${page+1}">${page+1}</a></li>
                                                        <li class="page-item"><a class="page-link"  href="${previousLink}${page+2}">${page+2}</a></li>

                                                        <li class="page-item">
                                                            <a class="page-link"  href="${previousLink}${page+1}" aria-label="Next">
                                                                <span aria-hidden="true">Next</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:if test="${page >=2  && page < totalPage}">
                                                        <li class="page-item">
                                                            <a class="page-link"  href="${previousLink}${page-1}" aria-label="Previous">
                                                                <span aria-hidden="true">Previous</span>
                                                            </a>
                                                        </li>

                                                        <li class="page-item"><a class="page-link"  href="${previousLink}${page-1}">${page-1}</a></li>
                                                        <li class="page-item active"><a class="page-link">${page}</a></li>
                                                        <li class="page-item"><a class="page-link" href="${previousLink}${page+1}">${page+1}</a></li>

                                                        <li class="page-item">
                                                            <a class="page-link"  href="${previousLink}${page+1}" aria-label="Next">
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
                                                        <li class="page-item"><a class="page-link"  href="${previousLink}${page-2}">${page-2}</a></li>
                                                        <li class="page-item"><a class="page-link"  href="${previousLink}${page-1}">${page-1}</a></li>
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
                                <%-- Subject Price Package --%>
                                <div id="tab3" class="tabcontent">
                                    <h4 style="color: #565e64">Price Package/ SubId <c:out value="${subject.getSubjectId()}"/></h4>
                                    <%-- Display message if available --%>
                                    <c:if test="${!empty pricePackageMessage}">
                                        <h6><c:out value="${pricePackageMessage}"/></h6>
                                    </c:if>
                                    <%-- Dimension table: each row is a form that allows admin/expert to edit/delete the dimension --%>
                                    <table class=" table table-striped table-bordered table-hover" style=" width: 100%;" >
                                        <%-- Table head --%>
                                        <thead  >
                                            <tr >
                                                <td style="background-color: #F0D8D5;" style="width: 10%;" >#</td>
                                                <td style="background-color: #F0D8D5;" style="width: 30%;">Pack Name</td>
                                                <td  style="background-color: #F0D8D5;" style="width: 10%;">Duration</td>
                                                <td  style="background-color: #F0D8D5;" style="width: 10%;">Price($)</td>
                                                <td  style="background-color: #F0D8D5;" style="width: 10%;">Sale($)</td>
                                                <td  style="background-color: #F0D8D5;"  style="width: 30%;">Action</td>

                                            </tr>
                                        </thead>
                                        <%-- Table body --%>
                                        <tbody>
                                            <%-- For each dimension, print a row --%>
                                            <c:forEach items = "${ListPricePackage}" var="package" >
                                                <tr>
                                            <form action="CourseContentDetail">
                                                <input type="hidden" name="subjectId" value="${subject.getSubjectId()}">
                                                <input type="hidden" name="packId" value="${package.getPackId()}">
                                                <input type="hidden" name="service" value="updatePricePackage">
                                                <%--------------------------------------------------------------%>
                                                <td style="width: 10%;"><c:out value="${package.getPackId()}"/></td>
                                                <td style="width: 30%;">
                                                    <select class="inputBorderless" id="inputState" class="form-control"  name="packName">
                                                        <option selected>${package.getPackName()}</option>
                                                        <option value="3 months package">3 months package</option>
                                                        <option value="6 months package">6 months package</option>
                                                        <option value="12 months package">12 months package</option>
                                                    </select>
                                                <td style="width: 10%;">
                                                    <input class="inputBorderless" type="text" name="duration" value="${package.getDuration()}" maxlength="5" size="5" required></td>
                                                <td style="width: 10%;">
                                                    <input class="inputBorderless" type="text" name="listprice" value="${package.getListPrice()} " maxlength="5" size="5" required></td>
                                                <td style="width: 10%;">
                                                    <input class="inputBorderless" type="text" name="saleprice" value="${package.getSalePrice()} " maxlength="5" size="5" required></td>
                                                <td style="width: 30%;">
                                                    <input type="submit" class="btn btn-success" name="subService" value="Update" />
                                                    <input type="submit" class="btn btn-danger" name="subService" value="Delete" />
                                                </td>
                                            </form>
                                            </tr>
                                        </c:forEach>
                                        <%-- Form to create a new price package --%>
                                        <tr>
                                        <form action="CourseContentDetail">
                                            <input type="hidden" name="service" value="addPricePackage">
                                            <input type="hidden" name="subjectId" value="${subject.getSubjectId()}">
                                            <th >New</th>
                                            <td>
                                                <select class="inputBorderless" id="inputState" class="form-control"  name="packName" required>
                                                    <option selected>Choose..</option>
                                                    <option value="3 months package">3 months package</option>
                                                    <option value="6 months package">6 months package</option>
                                                    <option value="12 months package">12 months package</option>
                                                </select>
                                            <td>
                                                <input  type="text" name="duration" placeholder="Enter" maxlength="12" size="5" required></td>
                                            <td>
                                                <input type="text" name="listprice" placeholder="Enter"  maxlength="12" size="5" required></td>
                                            <td>
                                                <input  type="text" name="saleprice" placeholder="Enter"  maxlength="12" size="5" required></td>
                                            <td>
                                                <button type="submit" id="submit" class="btn btn-success">Submit</button>
                                            </td>
                                        </form>
                                        </tr>
                                        </tbody>
                                    </table>
                                    </br>
                                    <c:if test="${totalPage1 == 2}">
                                        <div class="d-grid gap-2 col-md-12 mx-auto">
                                            <nav aria-label="Page navigation example">
                                                <ul class="pagination justify-content-center">
                                                    <c:if test="${page1 == 1}">
                                                        <li class="page-item disabled">
                                                            <a class="page-link" onclick="openTab(event, 'tab3')" href="#" aria-label="Previous">
                                                                <span aria-hidden="true">Previous</span>
                                                            </a>
                                                        </li>
                                                        <li class="page-item active"><a class="page-link">1</a></li>
                                                        <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+1}">2</a></li>

                                                        <li class="page-item">
                                                            <a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+1}" aria-label="Next">
                                                                <span aria-hidden="true">Next</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:if test="${page1 == totalPage1}">
                                                        <li class="page-item">
                                                            <a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1-1}" aria-label="Previous">
                                                                <span aria-hidden="true">Previous</span>
                                                            </a>
                                                        </li>
                                                        <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1-1}">1</a></li>
                                                        <li class="page-item active"><a class="page-link">2</a></li>

                                                        <li class="page-item disabled">
                                                            <a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+1}" aria-label="Next">
                                                                <span aria-hidden="true">Next</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </nav>
                                        </div>
                                    </c:if>
                                    <c:if test="${totalPage1 > 2}">
                                        <div class="d-grid gap-2 col-md-12 mx-auto">
                                            <nav aria-label="Page navigation example">
                                                <ul class="pagination justify-content-center">
                                                    <c:if test="${page1 == 1}">
                                                        <li class="page-item disabled">
                                                            <a class="page-link" href="#" aria-label="Previous">
                                                                <span aria-hidden="true">Previous</span>
                                                            </a>
                                                        </li>

                                                        <li class="page-item active"><a class="page-link">${page1}</a></li>
                                                        <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+1}">${page1+1}</a></li>
                                                        <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+2}">${page1+2}</a></li>

                                                        <li class="page-item">
                                                            <a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+1}" aria-label="Next">
                                                                <span aria-hidden="true">Next</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:if test="${page1 >=2  && page1 < totalPage1}">
                                                        <li class="page-item">
                                                            <a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1-1}" aria-label="Previous">
                                                                <span aria-hidden="true">Previous</span>
                                                            </a>
                                                        </li>

                                                        <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1-1}">${page1-1}</a></li>
                                                        <li class="page-item active"><a class="page-link">${page1}</a></li>
                                                        <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+1}">${page1+1}</a></li>

                                                        <li class="page-item">
                                                            <a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+1}" aria-label="Next">
                                                                <span aria-hidden="true">Next</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:if test="${page1 == totalPage1}">
                                                        <li class="page-item">
                                                            <a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1-1}" aria-label="Previous">
                                                                <span aria-hidden="true">Previous</span>
                                                            </a>
                                                        </li>
                                                        <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1-2}">${page1-2}</a></li>
                                                        <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1-1}">${page1-1}</a></li>
                                                        <li class="page-item active"><a class="page-link">${page1}</a></li>

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
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                    <%@include file="../components/footerAdmin.jsp" %>
                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
            <script src="../js/scripts.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
            <script src="../assets/demo/chart-area-demo.js"></script>
            <script src="../assets/demo/chart-bar-demo.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
            <script src="../js/datatables-simple-demo.js"></script>
        </c:if>
        <c:if test="${sessionScope.account.getRoleId()==4}">
            </br>
            </br>
            <%@include file="../components/navbar.jsp" %>
            </br>
            </br>
            <div class="row">
                <div class="col-md-2"></div>
                <c:if test="${empty displayTab}"><c:set var="displayTab" value="overview"/></c:if>
                <c:if test="${page > 0}"><c:set var="displayTab" value="dimension"/></c:if>
                <c:if test="${page1 > 0}"><c:set var="displayTab" value="pricePackage"/></c:if>
                <%-- Center form --%>
                <div class="col-md-8">
                    <%-- Header nav tab --%>
                    </br>
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="tab col-md-8">
                            <button class="tablinks" onclick="openTab(event, 'tab1')" id="overview">Overview</button>
                            <button class="tablinks" onclick="openTab(event, 'tab2')" id="dimension">Dimension</button>
                            <button class="tablinks" onclick="openTab(event, 'tab3')" id="pricePackage">Price Package</button>                                   
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                    <%-- Main tab details --%>
                    </br>
                    <div class="details">
                        <div id="tab1" class="tabcontent">
                            <h4 style="color: #565e64">Subject Overview/ SubId <c:out value="${subject.getSubjectId()}"/></h4>
                            <%-- Form details: The whole tab is a form with the subject's details as set values --%>
                            <form style="padding: 5px;" action="CourseContentDetail">
                                <%-- First bootstrap form row: subject name, category, featured subject, status and thumbnail image --%>
                                <div class="form-row">
                                    <div class="form-group col-md-7">
                                        <%-- Subject name --%>
                                        <br>
                                        <label for="subjectName">Subject Name</label>
                                        <input type="text" name="subjectName" class="form-control" value="${subject.getSubjectName()}" style="margin-bottom: 5px;" >
                                        <%-- Category list --%>
                                        <label for="subjectCate">Category</label>
                                        <div class="dropdown">
                                            <button class="btn btn-default dropdown-toggle" type="button" 
                                                    id="dropdownMenu1" data-toggle="dropdown" 
                                                    aria-haspopup="true" aria-expanded="true"
                                                    style="width: 100%; border: 1px solid #ced4da">
                                                <i class="fas fa-bars"></i>
                                                <span class="caret"></span>
                                            </button>
                                            <%-- Dropdown for category --%>
                                            <ul class="dropdown-menu checkbox-menu allow-focus" aria-labelledby="dropdownMenu1">
                                                <c:if test="${!empty categoryList}">
                                                    <c:forEach items = "${categoryList}" var="category">
                                                        <li>
                                                            <label>
                                                                <input type="checkbox" checked name="subjectCategory" value="${category.getSubjectCateId()}"> <c:out value="${category.getSubjectCateName()}"/>
                                                            </label>
                                                        </li>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${!empty categoryRemainList}">
                                                    <c:forEach items = "${categoryRemainList}" var="category">
                                                        <li>
                                                            <label>
                                                                <input type="checkbox"  name="subjectCategory" value="${category.getSubjectCateId()}"> <c:out value="${category.getSubjectCateName()}"/>
                                                            </label>
                                                        </li>
                                                    </c:forEach>
                                                </c:if>
                                            </ul>
                                        </div>

                                        <br>
                                        <div class="form-row">
                                            <div class="form-group col-md-5">
                                                <%-- Featured subject --%>
                                                <div class="form-check">
                                                    <c:choose>
                                                        <c:when test="${subject.isFeaturedSubject()}">
                                                            <input class="form-check-input" type="checkbox" checked id="gridCheck" name="isFeaturedSubject">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input class="form-check-input" type="checkbox" id="gridCheck" name="isFeaturedSubject">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <label class="form-check-label" for="gridCheck">
                                                        Featured Subject
                                                    </label>
                                                </div>
                                            </div>
                                            <%-- Status --%>
                                            <div hidden class="form-group col-md-2">
                                                <label for="status">Status</label>
                                            </div>
                                            <div hidden class="form-group col-md-5" >
                                                <select id="inputState" class="form-control" style="border: 1px solid #ced4da" name="subjectStatus">
                                                    <c:choose>
                                                        <c:when test="${subject.isStatus()}">
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
                                        </div>

                                    </div>
                                    <div class="form-group col-md-1"></div>
                                    <%-- Thumbnail image --%>
                                    <div class="form-group col-md-4">
                                        <img src="${contextPath}/assets/subject/${subject.getThumbnail()}" style="height: 100%; width: 100%;">
                                        <input type="hidden" name="subjectThumbnail" value="${subject.getThumbnail()}">
                                    </div>
                                </div>
                                <%-- Description --%>
                                <div class="form-group">
                                    <label for="subjectDescription">Description</label>
                                    <textare a class="form-control" style="min-height: 4em; overflow: scroll;" name="subjectDescription" ><c:out value="${subject.getDescription()}"/></textarea>
                                </div>
                                <%-- Submit --%>
                                <div class="form-row">
                                    <div class="form-group" style="margin-right: 1em; margin-left: 1em;">
                                        <input type="hidden" name="service" value="updateSubject">
                                        <input type="hidden" name="subjectId" value="${subject.getSubjectId()}">
                                        <input type="hidden" name="sujectDimension" value="${subject.getDimensions()}">
                                        <button type="submit" id="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                    <div class="form-group">
                                        <a href="CourseContentList?service=CourseContentList" class="btn btn-primary">Back</a>
                                    </div>
                                </div>
                            </form>
                            <%-- Display message if available --%>
                            <c:if test="${!empty detailMessage}">
                                <h6 style="color: ${detailColor}"><c:out value="${detailMessage}"/></h6>
                            </c:if>

                        </div>

                        <%-- Subject dimension --%>
                        <div id="tab2" class="tabcontent">
                            <h4 style="color: #565e64">Subject Dimension/ Id <c:out value="${subject.getSubjectId()}"/></h4>
                            <%-- Display message if available --%>
                            <c:if test="${!empty dimensionMessage}">
                                <h6 style="color: ${dimensionColor}"><c:out value="${dimensionMessage}"/></h6>
                            </c:if>
                            <%-- Dimension table: each row is a form that allows admin/expert to edit/delete the dimension --%>
                            <table class="table table-striped table-bordered table-hover" >
                                <%-- Table head --%>
                                <thead class="thead-light" style="background-color: #F0D8D5;">
                                    <tr style="background-color: #F0D8D5;">
                                        <th scope="col" style="background-color: #F0D8D5;">#</th>
                                        <th scope="col" style="background-color: #F0D8D5;">Type</th>
                                        <th scope="col" style="background-color: #F0D8D5;">Dimension</th>
                                        <th scope="col" style="background-color: #F0D8D5;">Description</th>
                                        <th scope="col" style="background-color: #F0D8D5;">Action</th>
                                    </tr>
                                </thead>
                                <%-- Table body --%>
                                <tbody>
                                    <%-- For each dimension, print a row --%>
                                    <c:forEach items = "${ListDimension}" var="dimension" >
                                        <tr>
                                    <form action="CourseContentDetail">
                                        <input type="hidden" name="subjectId" value="${subject.getSubjectId()}">
                                        <input type="hidden" name="dimensionId" value="${dimension.getDimensionId()}">
                                        <input type="hidden" name="service" value="updateDimension">
                                        <th scope="row"><c:out value="${dimension.getDimensionId()}"/></th>
                                        <td>
                                            <%-- Print the dimension type select box with the selected value of dimension on top --%>
                                            <select id="inputState" class="inputBorderless" name="dimensionType">
                                                <option selected value="${dimension.getDimensionTypeId()}"><c:out value="${dimension.getDimensionTypeName()}"/></option>
                                                <c:if test = "${!empty dimensionTypes}">
                                                    <c:forEach items = "${dimensionTypes}" var="dimensionTypes">
                                                        <c:if test="${dimensionTypes.getDimensionTypeId() != dimension.getDimensionId()}">
                                                            <option value="${dimensionTypes.getDimensionTypeId()}"><c:out value="${dimensionTypes.getDimensionTypeName()}"/></option>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </td>
                                        <td><input class="inputBorderless" type="text" name="dimensionName" value="${dimension.getDimensionName()}" required></td>
                                        <td><input class="inputBorderless" type="text" name="description" value="${dimension.getDescription()}" placeholder="Description"></td>
                                        <td><input type="submit" class="btn btn-success" name="subService" value="Update" />
                                            <input type="submit" class="btn btn-danger" name="subService" value="Delete" />
                                        </td>
                                    </form>
                                    </tr>
                                </c:forEach>
                                <%-- Form to create a new dimension --%>
                                <tr>
                                <form action="CourseContentDetail">
                                    <input type="hidden" name="service" value="addDimension">
                                    <input type="hidden" name="subjectId" value="${subject.getSubjectId()}">
                                    <th scope="row">New</th>
                                    <td>
                                        <select id="inputState" class="inputBorderless" name="dimensionType">
                                            <c:forEach items = "${dimensionTypes}" var="dimensionTypes">
                                                <option value="${dimensionTypes.getDimensionTypeId()}"><c:out value="${dimensionTypes.getDimensionTypeName()}"/></option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><input  type="text" name="dimensionName" placeholder="Dimension Name" required></td>
                                    <td><input  type="text" name="description" placeholder="Description"></td>
                                    <td><button type="submit" id="submit" class="btn btn-success">Submit</button>
                                    </td>
                                </form>
                                </tr>
                                </tbody>
                            </table>
                            </br>
                            <c:if test="${totalPage == 2}" >
                                <div id="tab2" class="d-grid gap-2 col-md-12 mx-auto">
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination justify-content-center">
                                            <c:if test="${page == 1}">
                                                <li class="page-item disabled">
                                                    <a class="page-link" onclick="openTab(event, 'tab2')" href="#" aria-label="Previous">
                                                        <span aria-hidden="true">Previous</span>
                                                    </a>
                                                </li>
                                                <li class="page-item active"><a class="page-link">1</a></li>
                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab2')" href="${previousLink}${page+1}">2</a></li>

                                                <li class="page-item">
                                                    <a class="page-link" onclick="openTab(event, 'tab2')" href="${previousLink}${page+1}" aria-label="Next">
                                                        <span aria-hidden="true">Next</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:if test="${page == totalPage}">
                                                <li class="page-item">
                                                    <a class="page-link" onclick="openTab(event, 'tab2')" href="${previousLink}${page-1}" aria-label="Previous">
                                                        <span aria-hidden="true">Previous</span>
                                                    </a>
                                                </li>
                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab2')" href="${previousLink}${page-1}">1</a></li>
                                                <li class="page-item active"><a class="page-link">2</a></li>

                                                <li class="page-item disabled">
                                                    <a class="page-link" onclick="openTab(event, 'tab2')" href="${previousLink}${page+1}" aria-label="Next">
                                                        <span aria-hidden="true">Next</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </nav>
                                </div>
                            </c:if>
                            <c:if test="${totalPage > 2}">
                                <div id="tab2" class="d-grid gap-2 col-md-12 mx-auto">
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination justify-content-center">
                                            <c:if test="${page == 1}">
                                                <li class="page-item disabled">
                                                    <a class="page-link" href="#" aria-label="Previous">
                                                        <span aria-hidden="true">Previous</span>
                                                    </a>
                                                </li>

                                                <li class="page-item active"><a class="page-link">${page}</a></li>
                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab2')" href="${previousLink}${page+1}">${page+1}</a></li>
                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab2')" href="${previousLink}${page+2}">${page+2}</a></li>

                                                <li class="page-item">
                                                    <a class="page-link" onclick="openTab(event, 'tab2')" href="${previousLink}${page+1}" aria-label="Next">
                                                        <span aria-hidden="true">Next</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:if test="${page >=2  && page < totalPage}">
                                                <li class="page-item">
                                                    <a class="page-link" onclick="openTab(event, 'tab2')" href="${previousLink}${page-1}" aria-label="Previous">
                                                        <span aria-hidden="true">Previous</span>
                                                    </a>
                                                </li>

                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab2')" href="${previousLink}${page-1}">${page-1}</a></li>
                                                <li class="page-item active"><a class="page-link">${page}</a></li>
                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab2')" href="${previousLink}${page+1}">${page+1}</a></li>

                                                <li class="page-item">
                                                    <a class="page-link" onclick="openTab(event, 'tab2')" href="${previousLink}${page+1}" aria-label="Next">
                                                        <span aria-hidden="true">Next</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:if test="${page == totalPage}">
                                                <li class="page-item">
                                                    <a class="page-link" onclick="openTab(event, 'tab2')" href="${previousLink}${page-1}" aria-label="Previous">
                                                        <span aria-hidden="true">Previous</span>
                                                    </a>
                                                </li>
                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab2')" href="${previousLink}${page-2}">${page-2}</a></li>
                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab2')" href="${previousLink}${page-1}">${page-1}</a></li>
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
                        <%-- Subject Price Package --%>
                        <div id="tab3" class="tabcontent">
                            <h4 style="color: #565e64">Price Package/ SubId <c:out value="${subject.getSubjectId()}"/></h4>
                            <%-- Display message if available --%>
                            <c:if test="${!empty pricePackageMessage}">
                                <h6 style="color: ${dimensionColor}"><c:out value="${pricePackageMessage}"/></h6>
                            </c:if>
                            <%-- Dimension table: each row is a form that allows admin/expert to edit/delete the dimension --%>
                            <table class=" table table-striped table-bordered table-hover" style=" width: 100%;" >
                                <%-- Table head --%>
                                <thead  >
                                    <tr >
                                        <td style="background-color: #F0D8D5;" style="width: 10%;" >#</td>
                                        <td style="background-color: #F0D8D5;" style="width: 30%;">Pack Name</td>
                                        <td  style="background-color: #F0D8D5;" style="width: 10%;">Duration</td>
                                        <td  style="background-color: #F0D8D5;" style="width: 10%;">Price($)</td>
                                        <td  style="background-color: #F0D8D5;" style="width: 10%;">Sale($)</td>
                                        <td hidden style="background-color: #F0D8D5;"  style="width: 30%;">Action</td>

                                    </tr>
                                </thead>
                                <%-- Table body --%>
                                <tbody>
                                    <%-- For each dimension, print a row --%>
                                    <c:forEach items = "${ListPricePackage}" var="package" >
                                        <tr>
                                    <form action="CourseContentDetail">
                                        <input type="hidden" name="subjectId" value="${subject.getSubjectId()}">
                                        <input type="hidden" name="packId" value="${package.getPackId()}">
                                        <input type="hidden" name="service" value="updatePricePackage">
                                        <%--------------------------------------------------------------%>
                                        <td style="width: 10%;"><c:out value="${package.getPackId()}"/></td>
                                        <td style="width: 30%;">
                                            <input class="inputBorderless" id="inputState" type="text" name="packName" value="${package.getPackName()}" readonly></td>
                                        <td style="width: 10%;">
                                            <input class="inputBorderless" type="text" name="duration" value="${package.getDuration()}" maxlength="5" size="5" readonly></td>
                                        <td style="width: 10%;">
                                            <input class="inputBorderless" type="text" name="listprice" value="${package.getListPrice()}" maxlength="5" size="5" readonly></td>
                                        <td style="width: 10%;">
                                            <input class="inputBorderless" type="text" name="saleprice" value="${package.getSalePrice()} " maxlength="5" size="5" readonly></td>
                                        <td hidden style="width: 30%;">
                                            <input type="submit" class="btn btn-success" name="subService" value="Update" />
                                            <input type="submit" class="btn btn-danger" name="subService" value="Delete" />
                                        </td>
                                    </form>
                                    </tr>
                                </c:forEach>
                                <%-- Form to create a new price package --%>
                                <tr hidden>
                                <form action="CourseContentDetail">
                                    <input type="hidden" name="service" value="addPricePackage">
                                    <input type="hidden" name="subjectId" value="${subject.getSubjectId()}">
                                    <th >New</th>
                                    <td>
                                        <input  type="text" name="packName" placeholder="Pack Name" required></td>
                                    <td>
                                        <input  type="text" name="duration" placeholder="Enter" maxlength="12" size="5" required></td>
                                    <td>
                                        <input type="text" name="listprice" placeholder="Enter"  maxlength="12" size="5" required></td>
                                    <td>
                                        <input  type="text" name="saleprice" placeholder="Enter"  maxlength="12" size="5" required></td>
                                    <td>
                                        <button type="submit" id="submit" class="btn btn-success">Submit</button>
                                    </td>
                                </form>
                                </tr>
                                </tbody>
                            </table>
                            </br>
                            <c:if test="${totalPage1 == 2}">
                                <div class="d-grid gap-2 col-md-12 mx-auto">
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination justify-content-center">
                                            <c:if test="${page1 == 1}">
                                                <li class="page-item disabled">
                                                    <a class="page-link" onclick="openTab(event, 'tab3')" href="#" aria-label="Previous">
                                                        <span aria-hidden="true">Previous</span>
                                                    </a>
                                                </li>
                                                <li class="page-item active"><a class="page-link">1</a></li>
                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+1}">2</a></li>

                                                <li class="page-item">
                                                    <a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+1}" aria-label="Next">
                                                        <span aria-hidden="true">Next</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:if test="${page1 == totalPage1}">
                                                <li class="page-item">
                                                    <a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1-1}" aria-label="Previous">
                                                        <span aria-hidden="true">Previous</span>
                                                    </a>
                                                </li>
                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1-1}">1</a></li>
                                                <li class="page-item active"><a class="page-link">2</a></li>

                                                <li class="page-item disabled">
                                                    <a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+1}" aria-label="Next">
                                                        <span aria-hidden="true">Next</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </nav>
                                </div>
                            </c:if>
                            <c:if test="${totalPage1 > 2}">
                                <div class="d-grid gap-2 col-md-12 mx-auto">
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination justify-content-center">
                                            <c:if test="${page1 == 1}">
                                                <li class="page-item disabled">
                                                    <a class="page-link" href="#" aria-label="Previous">
                                                        <span aria-hidden="true">Previous</span>
                                                    </a>
                                                </li>

                                                <li class="page-item active"><a class="page-link">${page1}</a></li>
                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+1}">${page1+1}</a></li>
                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+2}">${page1+2}</a></li>

                                                <li class="page-item">
                                                    <a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+1}" aria-label="Next">
                                                        <span aria-hidden="true">Next</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:if test="${page1 >=2  && page1 < totalPage1}">
                                                <li class="page-item">
                                                    <a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1-1}" aria-label="Previous">
                                                        <span aria-hidden="true">Previous</span>
                                                    </a>
                                                </li>

                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1-1}">${page1-1}</a></li>
                                                <li class="page-item active"><a class="page-link">${page}</a></li>
                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+1}">${page1+1}</a></li>

                                                <li class="page-item">
                                                    <a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1+1}" aria-label="Next">
                                                        <span aria-hidden="true">Next</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:if test="${page1 == totalPage1}">
                                                <li class="page-item">
                                                    <a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1-1}" aria-label="Previous">
                                                        <span aria-hidden="true">Previous</span>
                                                    </a>
                                                </li>
                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1-2}">${page1-2}</a></li>
                                                <li class="page-item"><a class="page-link" onclick="openTab(event, 'tab3')" href="${previousLink1}${page1-1}">${page1-1}</a></li>
                                                <li class="page-item active"><a class="page-link">${page1}</a></li>

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
                </div>

                <div class="col-md-2"></div>
            </div>
            </br>
            </br>
            <%@include file="../components/footer.jsp" %>
        </c:if>
    </body>
    <script>
        <%-- Javascript for tabs opening --%>
        function openTab(evt, tabName) {
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(tabName).style.display = "block";
            evt.currentTarget.className += " active";
        }
        <%-- JS to activate sellect element --%>
        $(".checkbox-menu").on("change", "input[type='checkbox']", function () {
            $(this).closest("li").toggleClass("active", this.checked);
        });

        $(document).on('click', '.allow-focus', function (e) {
            e.stopPropagation();
        });

        <%-- JS to click on (display) proper tab --%>
        window.onload = function () {
            document.getElementById("${displayTab}").click();

        };
        function myFunction() {
            document.getElementById("overview").values(overview);
            document.getElementById("dimension").values(dimension);
            document.getElementById("pricePackage").values(pricePackage);
        }
    </script>

</html>
