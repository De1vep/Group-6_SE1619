<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/styles.css" rel="stylesheet" />
        <script>
            validateEditProfile();
            function getImg(event) {
                var image = URL.createObjectURL(event.target.files[0]);
                var imagediv = document.getElementById("nimage");
                imagediv.setAttribute('src', image);
            }
        </script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top shadow-sm">
            <div class="container-fluid">
                <a class="navbar-brand ms-lg-5" href="../view/home">
                    <img src="../assets/logo.png" alt="" height="40"/>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-2">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="../view/home">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="../view/subject?service=subjectList">Subject</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="../view/blog?service=blogList">Blog</a>
                        </li>
                        <c:if test="${account.getRoleId() ==4}">
                            <li class="nav-item">
                            <a class="nav-link" href="../view/CourseContentList?service=CourseContentList">CourseContentList</a>
                        </li>
                        </c:if>
                    </ul>
                    <div class="d-flex">
                        <c:choose>
                            <c:when test="${account != null}">
                                <div class="dropdown me-5">
                                    <a class="btn btn-secondary dropdown-toggle me-2" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                                        <i class="bi bi-person-circle me-2 ms-2"></i>Profile
                                    </a>

                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                        <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#modalUserProfile"><i class="bi bi-person-fill"></i> Your info</a></li>
                                        <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#changPassword"><i class="bi bi-shield-lock-fill"></i> Change Password</a></li>
                                            <c:if test="${account.getRoleId()==5}">
                                            <li><a class="dropdown-item" href="Dashboard"><i class="bi bi-speedometer"></i> Dashboard</a></li>
                                            </c:if>
                                        <li><a class="dropdown-item" href="MyRegistration?service=listRegistration"><i class="bi bi-bag-check-fill"></i> My Registration</a></li>
                                        <li><a class="dropdown-item" href="PracticeList?service=getPracticeListInformation"><i class="bi bi-bag-check-fill"></i> My Taken Quiz</a></li>
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item" href="../view/login?service=logout">Log out <i class="ms-5 bi bi-box-arrow-right"></i></a></li>
                                    </ul>
                                </div> 
                            </c:when>
                            <c:otherwise>
                                <!-- Button trigger modal -->
                                <button type="button" class="btn btn-outline-dark me-2" data-bs-toggle="modal" data-bs-target="#modalLoginForm">
                                    Log in
                                </button>
                                <button type="button" class="btn btn-dark me-lg-5" data-bs-toggle="modal" data-bs-target="#modalRegistetForm">
                                    Sign up
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </nav>


        <!-- Modal Login -->
        <div class="modal fade" id="modalLoginForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title text-center" id="exampleModalLabel">Login</h5>
                    </div>
                    <div class="modal-body">
                        <form action="login" method="POST">
                            <div class="mb-3">
                                <label class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Email" required=/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required/>
                            </div>
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" id="rememberMe" />
                                <label class="form-check-label" for="rememberMe">Remember me</label>
                                <a href="../login/forgot-password?service=submitEmail" class="float-end">Forgot password?</a>
                            </div>
                            </br>
                            <div class="d-block mx-4 mb-3 mb-lg-4 text-center">
                                <button type="submit" class="btn btn-warning">Login</button>
                            </div>

                        </form>
                    </div>

                </div>
            </div>
        </div>

        <!--Model Register-->
        <div class="modal fade" id="modalRegistetForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title text-center" id="exampleModalLabel">SignUp</h5>
                    </div>

                    <div class="modal-body">
                        <form action="register?service=register" source="custom" method="POST">
                            <div class="mb-3">
                                <label class="form-label">Email Address</label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${param.email}" required/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Your Name</label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="Your Name"  required/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" >Gender</label>
                                <select class="form-select" aria-label="Default select example" name="gender">                                             
                                    <option value="true">Male</option>
                                    <option value="false">Female</option>                                                   
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Phone</label>
                                <input type="text" class="form-control" id="phone" name="phone" placeholder="Phone"required/>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Re-Password</label>
                                <input type="password" class="form-control" id="re_password" name="repassword" placeholder="Password" required/>
                            </div>

                            </br>
                            <div class="d-block mx-4 mb-3 mb-lg-4 text-center">
                                <button type="submit" class="btn btn-warning" >Sign Up</button>
                            </div>

                        </form>
                    </div>

                </div>
            </div>
        </div>

        <!--modalUserProfile-->
        <div class="modal fade" id="modalUserProfile" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">

                        <h5 class="modal-title text-center" id="exampleModalLabel">User Profile</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>

                    </div>
                    <div class="row modal-body">

                        <form action="<%=request.getContextPath()%>/view/userProfile" method="POST" enctype="multipart/form-data">

                            <div>
                                <div class=" text-center">
                                    <c:choose>
                                        <c:when test="${empty sessionScope.account.image}">
                                            <img  style="width: 200px; height: 230px; object-fit:cover; border-radius:50%;" src="../assets/userConst.png" alt="...">
                                        </c:when>
                                        <c:otherwise>
                                            <img  style="width: 200px; height: 230px; object-fit:cover; border-radius:50%;" src="../assets/${sessionScope.account.image}" alt="...">   
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Change  new Avater</label>
                                    <input value="${sessionScope.account.image}" type="text" hidden="hidden" name = "oldimage"/>
                                    <input type="file" onchange="getImg(event)" class="form-control" name="nimage" id="nimage">
                                </div>
                                <input type="hidden" class="form-control" name="id" value="${sessionScope.account.userId}"/>
                                <div class="mb-3">
                                    <label class="form-label">Email Address</label>
                                    <input type="text" class="form-control" name="email" value="${sessionScope.account.email}" readonly="readonly"/>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">User Name</label>
                                    <input type="text" class="form-control" id="nusername" name="nusername" value="${sessionScope.account.userName}" required/>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Phone</label>
                                    <input type="text" class="form-control" id="nphone" name="nphone" value="${sessionScope.account.phone}" required/>
                                </div>
                                <div class="mb-3" >
                                    <label class="form-label">Gender</label>
                                    <select class="form-select" aria-label="Default select example" name="ngender">   
                                        <option selected value="${sessionScope.account.gender}">
                                            <c:if test="${sessionScope.account.gender==true}">
                                            <p>Male</p>
                                        </c:if>
                                        <c:if test="${sessionScope.account.gender==false}">
                                            <p>Female</p>
                                        </c:if>
                                        </option >
                                        <option value="true">Male</option>
                                        <option value=false>Female</option>                                                   
                                    </select>
                                </div>
                                </br>
                                <div class="d-block mx-4 mb-3 mb-lg-4 text-center">
                                    <button type="submit" class="btn btn-warning">Save profile</button>
                                </div>
                            </div>
                        </form>
                    </div>                   
                </div>
            </div>
        </div>

        <!--changPassword-->
        <div class="modal fade" id="changPassword" tabindex="-2" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title text-center" id="exampleModalLabel">Change Password</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="<%=request.getContextPath()%>/view/changepassword" method="POST">
                            <div class="mb-3">
                                <label class="form-label">Email Address</label>
                                <input type="hidden" class="form-control" /></br>${sessionScope.acc.email}
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Password</label>
                                <input type="password" class="form-control" id="oldpassword" name="oldpassword" placeholder="Password" required/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">newPassword</label>
                                <input type="password" class="form-control" id="newpassword" name="newpassword" placeholder="Password" required/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Re-newPassword</label>
                                <input type="password" class="form-control" id="re_newpassword" name="re_newpassword" placeholder="Password" required/>
                            </div>
                            </br>
                            </br>
                            <div class="d-block mx-4 mb-3 mb-lg-4 text-center">
                                <button type="submit" class="btn btn-warning">Change Password</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>                                    

        

        <!-- Enroll subject  -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        ...
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</body>
</html>

