<%-- 
    Document   : forgotPassword
    Created on : May 27, 2022, 12:14:48 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <!--Author: DPV-->
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>QPS</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/q-icon.png" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="../css/styles.css" rel="stylesheet" />
    </head>

    <body>


        <section class="vh-100" style="background-color: #eee;">
            <div class="container h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-lg-12 col-xl-11">
                        <div class="card text-black" style="border-radius: 5px;">
                            <div class="card-body p-md-5">
                                <div class="row justify-content-center">
                                    <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                                        <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Forget Password?</p>
                                        

                                        <div class="d-flex flex-row align-items-center mb-2">
                                            <form class="input-group mb-3" action="forgot-password?service=submitEmail" method="Post">
                                                <input type="text" class="form-control" placeholder="Enter your email" aria-describedby="button-addon2" required="" name="email" value="${emailAddress}">
                                                <button class="btn btn-outline-secondary" type="submit" id="button-addon2">SUBMIT</button>
                                            </form>
                                        </div>
                                        <div class="d-flex flex-row align-items-center mb-2  text-danger"><b>${mess}</b></div>
                                        <div class="text-center pt-2">
                                            <a href="../view/home" class="text-decoration-none">Back to Home</a>
                                        </div>


                                    </div>
                                    <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
                                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                                             class="img-fluid" alt="Sample image">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>

