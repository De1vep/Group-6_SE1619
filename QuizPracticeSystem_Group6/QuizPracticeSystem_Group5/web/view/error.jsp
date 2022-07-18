<%-- 
    Document   : Error
    Created on : May 30, 2022, 2:03:09 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../css/styles.css"/>
    </head>
    <body class="bg-light">
        <div class="container">
            <div class="row py-5">
                <div class="col-md-12 justify-content-center d-flex">
                    <img src="https://media.istockphoto.com/vectors/comic-speech-bubble-with-text-oops-message-in-pop-art-style-vector-id1290154699?k=20&m=1290154699&s=612x612&w=0&h=suUB7nSYg7yKBELyk_XI_gUXFb1DBTDFGm0XJq-TOyc=" alt="" class="img-fluid rounded-circle "/>
                </div>
                <div class="col-md-12 text-center">
                    <h1 class="py-3">${mess}</h1>
                    <p>This URL is not valid anymore.</p>
                    <c:if test="${account.roleId == 5 ||account.roleId == 4}">
                        <a href="home.jsp">Go to Home</a>
                    </c:if>
                    <c:if test="${account.roleId != 5 ||account.roleId != 4}">
                        <a href="HomeAdmin.jsp">Go to HomeAdmin</a>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
