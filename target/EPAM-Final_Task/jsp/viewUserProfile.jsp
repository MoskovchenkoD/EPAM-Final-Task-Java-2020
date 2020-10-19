<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="loc" uri="/WEB-INF/taglib/i18n.tld"%>
<%@ page import="ua.nure.moskovchenko.db.Role" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Cabinet</title>

    <link rel="stylesheet" href="../../css/bootstrap.min.css"> <%-- http://java.sun.com/jsp/jstl/functions equals main/webapp/css/bootstrap.min.css --%>
    <link rel="stylesheet" href="../../css/style-courseDetail.css">
    <link rel="stylesheet" href="../../css/style-courses.css" >
    <link rel="stylesheet" href="../../css/simple-sidebar.css">
</head>


<body>

<div id="page-content-wrapper">

    <c:choose>
        <c:when test="${sessionScope.user.role == Role.ADMIN}">
            <jsp:include page="navbarAdmin.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="navbar.jsp"/>
        </c:otherwise>
    </c:choose>

    <div class="container">

        <div class="card">
            <div class="row">
                <aside class="col-sm-7">
                    <article class="card-body p-5">
                        <h3 class="title mb-3 text-info">User profile</h3>
                        <p class="price-detail-wrap">
                            <span class="price h5 text-primary">
                                <span>User ID: </span>
                            </span>
                            <span class="num">
                                <c:out value="${user.id}"/>
                            </span>
                        </p>

                        <dl class="param param-feature">
                            <dt><loc:i18n value="user.firstName"/></dt>
                            <dd><c:out value="${user.firstName}"/></dd>
                        </dl>
                        <dl class="param param-feature">
                            <dt><loc:i18n value="user.lastName"/></dt>
                            <dd><c:out value="${user.lastName}"/></dd>
                        </dl>

                        <c:if test="${not empty user.patronymic}">
                            <dl class="param param-feature">
                                <dt><loc:i18n value="user.patronymic"/></dt>
                                <dd><c:out value="${user.patronymic}"/></dd>
                            </dl>
                        </c:if>

                        <dl class="param param-feature">
                            <dt><loc:i18n value="user.login"/></dt>
                            <dd><c:out value="${user.login}"/></dd>
                        </dl>
                        <dl class="param param-feature">
                            <dt><loc:i18n value="user.email"/></dt>
                            <dd><c:out value="${user.email}"/></dd>
                        </dl>
                        <dl class="param param-feature">
                            <dt><loc:i18n value="user.role"/></dt>
                            <dd><c:out value="${user.role}"/></dd>
                        </dl>

                    </article> <!-- card-body.// -->
                </aside> <!-- col.// -->
            </div> <!-- row.// -->
        </div> <!-- card.// -->

    </div> <!--container.//-->


<%--    <div class="container-fluid">--%>

<%--        <c:if test="${user.role == Role.STUDENT}">--%>
<%--            <c:choose>--%>
<%--                <c:when test="${not empty studCourses}">--%>
<%--                    <br><h3 class="title mb-3 text-success"><loc:i18n value="cabinet.yourCourses"/></h3><br>--%>
<%--                    <jsp:include page="cabinetStudCourses.jsp"/>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <br><h3 class="title mb-3"><loc:i18n value="cabinet.noStudCourses"/></h3><br>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--        </c:if>--%>
<%--        <c:if test="${user.role == Role.LECTURER}">--%>
<%--            <c:choose>--%>
<%--                <c:when test="${not empty lecturerCourses}">--%>
<%--                    <br><h3 class="title mb-3 text-success"><loc:i18n value="cabinet.yourCourses"/></h3><br>--%>
<%--                    <jsp:include page="cabinetLecturerCourses.jsp"/> <--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <br><h3 class="title mb-3"><loc:i18n value="cabinet.noStudCourses"/></h3><br>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--        </c:if>--%>
<%--        <br><br>--%>
<%--    </div>--%>

<%--</div>--%>

<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/popper.min.js"></script>
<script type="text/javascript" src="../../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../js/datatables.min.js"></script>

<!-- This script assigns the current servlet URL for language-related inputs, so the LanguageServlet
    will be able to redirect to the page from where the command to change the language was sent -->
<script type="text/javascript" src="../../js/language-help.js"></script>
<%--<script type="text/javascript" src="../../js/navbar.js"></script>--%>

<!-- This script enables the local table search, so the user could find specific rows that match the entered text -->
<script>
    $(document).ready(function () {
        $('#dtBasicExample2').DataTable();
        $('.dataTables_length').addClass('bs-select');
    });
</script>

</body>
</html>
