<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="loc" uri="/WEB-INF/taglib/i18n.tld"%>

<html>
<head>
    <meta charset="UTF-8">

    <title>Login Page</title>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/styles-login.css">

</head>

    <body>

    <div class="sidenav">
        <div class="login-main-text">
            <h2>Facultativ<br> Login Page</h2> <!-- TODO: i18n -->
            <p>Login or register from here to access.</p> <!-- TODO: i18n -->
            <br><br>
            <p>October 2020, EPAM Java Final Task</p>
            <p>Student: Moskovchenko Dmytro</p>
        </div>
    </div>
    <div class="main">
        <div class="col-md-6 col-sm-12">
            <div class="login-form">
                <form action="/login" method="post" id="singnupFrom" onSubmit="return validation();">
                    <div class="form-group">
                        <label><loc:i18n value="login.username"/></label>
                        <input type="text" class="form-control" name="login" id="username" value="${login}">
                    </div>
                    <div class="form-group">
                        <label><loc:i18n value="login.password"/></label>
                        <input type="password" class="form-control" name="password" id="password">
                        <em id="cp"></em>
                    </div>
                    <button type="submit" class="btn btn-black"><loc:i18n value="login.login"/></button>
                </form>
                <form action="/register" method="get">
                    <button type="submit" class="btn btn-secondary"><loc:i18n value="register.register"/></button>
                </form>
            </div>
            <c:choose>
                <c:when test="${not empty errorMessage}">
                    <i><loc:i18n value="login.fail"/></i>
                </c:when>
            </c:choose>
        </div>
    </div>


    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/popper.min.js"></script>
    <script type="text/javascript" src="../../js/bootstrap.min.js"></script>

    <script>
        function validation() {
            if ($("#username, #password").val() == "") {
                $("#username, #password").addClass("is-invalid");
                return false;
            } else {
                $("#username, #password").removeClass("is-invalid");
            }

            if ($("#password").val() == "") {
                $("#password").addClass("is-invalid");
                $("#cp").html(
                    '<span class="text-danger">Password must not be empty!</span>'
                );
                return false;
            }
        }
        $(document).ready(function(e) {
            $("#username").on("keyup", function() {
                if ($("#username").val() == "") {
                    $("#username").addClass("is-invalid");
                    return false;
                } else {
                    $("#username").removeClass("is-invalid");
                }
            });
            $("#password").on("keyup", function() {
                if ($("#password").val() == "") {
                    $("#password").addClass("is-invalid");
                    return false;
                } else {
                    $("#password").removeClass("is-invalid");
                }
            });

        });

    </script>


    </body>
</html>
