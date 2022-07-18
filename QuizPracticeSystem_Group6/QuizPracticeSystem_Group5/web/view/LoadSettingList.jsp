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
        <title>Question List</title>
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
    <script>
        function doBackUserList() {
            window.location.href = "userList?service=LoadUserList";
        }
    </script>
    <body class="sb-nav-fixed">
        <%@include file="../components/navbarAdmin.jsp" %>
        <div id="layoutSidenav">
            <%@include file="../components/leftAdmin.jsp" %>
            <div id="layoutSidenav_content">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">

                            <h5 class="modal-title text-center" id="exampleModalLabel">Setting Detail</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>

                        </div>
                        <div class="row modal-body">

                            <form action="settingList?service=editSetting" method="POST" >

                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="mb-3">
                                            <label class="form-label">Setting Id</label>
                                            <input type="text" class="form-control" id="id" name="id" value="${setting.getId()}" readonly="readonly" required/>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Setting Type</label>
                                            <input type="text" class="form-control" id="settingtype" name="settingtype" value="${setting.getSettingType()}" readonly="readonly" required/>
                                        </div>
                                        
                                        <div class="mb-3">
                                            <label class="form-label">Setting Name</label>
                                            <input type="text" class="form-control" name="settingName" value="${setting.getName()}" />
                                        </div>

                                        
                                        <div class="mb-3">
                                            <label class="form-label">Setting Status</label>
                                            <select class="form-select" aria-label="Default select example" name="status" >
                                                <option ${(setting.isStatus())?"selected = \"selected\"":""}
                                                    value="1" >Active</option>
                                                <option ${(setting.isStatus() == false)?"selected = \"selected\"":""}
                                                    value="0" >InActive</option>
                                            </select>
                                        </div>
                                        </br>
                                        <div class="d-block mx-4 mb-3 mb-lg-4 text-center">
                                            <button type="submit" class="btn btn-warning">Save Setting</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>                   
                    </div>
                </div>

                <%@include file="../components/footerAdmin.jsp" %>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="../js/datatables-simple-demo.js" type="text/javascript"></script>
    </body>
</html>


