<%-- 
    Document   : FalseUser
    Created on : May 29, 2022, 1:59:46 PM
    Author     : Minh-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test = "${requestScope.falseOldPassword != null }">
    <script>
        alert("Wrong password!");
    </script>
</c:if>
<c:if test = "${requestScope.falseNewPassword != null }">
    <script>
        alert("Password mismatch!");
    </script>
</c:if>
<c:if test = "${requestScope.ChangeSuccessfull != null }">
    <script>
        alert("Change Password Successfull!");
    </script>
</c:if>
<c:if test = "${requestScope.falseEmail != null }">
    <script>
        alert("Email does not exist!");
    </script>
</c:if>
<c:if test = "${requestScope.loginSuccess != null }">
    <script>
        alert("Login Successfull!");
    </script>
</c:if>
<c:if test = "${requestScope.Eemail != null }">
    <script>
        alert("Email exist!");
    </script>
</c:if>
<c:if test = "${requestScope.confirmPassNotMatch != null }">
    <script>
        alert("Confirm password not match !");
    </script>
</c:if>
<c:if test = "${requestScope.falseActivated != null }">
    <script>
        alert("User Non-Activated!");
    </script>
</c:if>
<c:if test = "${requestScope.mess != null }">
    <script>
        alert("Check Your Email !");
    </script>
</c:if>

<c:if test = "${requestScope.error != null }">
    <script>
        window.alert(${requestScope.error});
    </script>
</c:if>

<c:if test = "${requestScope.success != null }">
    <script>
        alert(${requestScope.success});
    </script>
</c:if>