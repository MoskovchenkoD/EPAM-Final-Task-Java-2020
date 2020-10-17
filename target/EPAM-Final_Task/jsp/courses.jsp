<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="loc" uri="/WEB-INF/taglib/i18n.tld"%>
<%@ page import="ua.nure.moskovchenko.db.Role" %>

<!doctype html>
<html>
<head>
    <title>Courses</title> <!-- TODO: i18n -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/bootstrap.min.css"> <%-- main/webapp/css/bootstrap.min.css --%>
    <link rel="stylesheet" href="../../css/datatables.min.css" >
    <link rel="stylesheet" href="../../css/style-courses.css" >
    <link rel="stylesheet" href="../../css/simple-sidebar.css">

</head>
<body>

    <!-- Page Content -->
    <div id="page-content-wrapper">

        <c:choose>
            <c:when test="${sessionScope.user != null && sessionScope.user.role == Role.ADMIN}">
                <jsp:include page="navbarAdmin.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="navbar.jsp"/>
            </c:otherwise>
        </c:choose>

<%--        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom"> <!--   -->--%>

<%--            <div class="collapse navbar-collapse" id="navbarSupportedContent">--%>
<%--                <ul class="navbar-nav ml-auto mt-2 mt-lg-0"> <!--   -->--%>
<%--                    <li class="nav-item dropdown active">--%>
<%--                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
<%--                            <loc:i18n value="navbar.switchLanguage"/>--%>
<%--                        </a>--%>
<%--                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">--%>
<%--                            <form action="/language" method="post" id="en">--%>
<%--                                <input hidden name="lang" value="en">--%>
<%--                                <input hidden id="loc" name="location"> &lt;%&ndash; for language-help.js &ndash;%&gt;--%>
<%--                            </form>--%>
<%--                            <form action="/language" method="post" id="ru">--%>
<%--                                <input hidden name="lang" value="ru">--%>
<%--                                <input hidden id="loc2" name="location"> &lt;%&ndash; for language-help.js &ndash;%&gt;--%>
<%--                            </form>--%>

<%--                            <button class="dropdown-item" form="en" type="submit">--%>
<%--                                <loc:i18n value="navbar.language.en"/>--%>
<%--                            </button>--%>

<%--                            <button class="dropdown-item" form="ru" type="submit">--%>
<%--                                <loc:i18n value="navbar.language.ru"/>--%>
<%--                            </button>--%>

<%--                        </div>--%>
<%--                    </li>--%>

<%--                    <li class="nav-item active">--%>
<%--                        <c:url var="viewCourse" value="courses"/>--%>
<%--                        <a class="nav-link" href="${viewCourse}"><loc:i18n value="navbar.viewCourses"/></a>--%>
<%--                    </li>--%>

<%--                    <li class="nav-item dropdown active">--%>
<%--                        <a class="nav-link dropdown-toggle btn" href="" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
<%--                            <loc:i18n value="navbar.welcome"/>--%>
<%--                            <c:out value="${user.login}"/>--%>
<%--                        </a>--%>
<%--                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown2">--%>
<%--                            <c:url var="viewCabinet" value="cabinet"/>--%>
<%--                            <a class="dropdown-item" href="${viewCabinet}"><loc:i18n value="navbar.goToCabinet"/></a>--%>
<%--                                <div class="dropdown-divider"></div>--%>
<%--                            <c:url var="logout" value="logout"/>--%>
<%--                            <a class="dropdown-item" href="${logout}"><loc:i18n value="navbar.logout"/></a>--%>
<%--                        </div>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </div>--%>
<%--        </nav>--%>

        <div class="container-fluid"> <%-- container --%>

            <h4><loc:i18n value="course.availableCourses"/>: </h4>
            <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                <thead> <!-- table-sm  -->
                <tr>
                    <th class="th-sm">ID
                    </th>
                    <th class="th-sm"><loc:i18n value="course.headline"/>
                    </th>
                    <th class="th-sm"><loc:i18n value="course.topic"/>
                    </th>
                    <th class="th-sm"><loc:i18n value="course.lecturerName"/>
                    </th>
                    <th class="th-sm"><loc:i18n value="course.length"/>
                    </th>
                    <th class="th-sm"><loc:i18n value="course.studentCount"/>
                    </th>
                    <th class="th-sm"><loc:i18n value="course.status"/>
                    </th>
                    <th class="th-sm"></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="course" items="${coursesForUser}">

                <c:url var="loadDetailsLink" value="courseDetails">
                    <c:param name="id" value="${course.id}"/>
                </c:url>

                <tr>
                    <td>${course.id}</td>
                    <td>${course.headline}</td>
                    <td>${course.topicName}</td>
                    <td>${course.lastName}</td>
                    <td>${course.length}</td>
                    <td>${course.studentCount}</td>
                    <td>${course.statusName}</td>
                    <td>
                        <a href="${loadDetailsLink}"><loc:i18n value="course.seeDetails"/></a>
                    </td>
                </tr>
                </c:forEach>

                <tfoot>
                <tr>
                    <th>ID
                    </th>
                    <th><loc:i18n value="course.headline"/>
                    </th>
                    <th><loc:i18n value="course.topic"/>
                    </th>
                    <th><loc:i18n value="course.lecturerName"/>
                    </th>
                    <th><loc:i18n value="course.length"/>
                    </th>
                    <th><loc:i18n value="course.studentCount"/>
                    </th>
                    <th><loc:i18n value="course.status"/>
                    </th>
                    <th></th>
                </tr>
                </tfoot>
            </table>


        </div>
    </div>

<%--    <script type="text/javascript">--%>
<%--        document.getElementById("loc").setAttribute('value',window.location.href);--%>
<%--        document.getElementById("loc2").setAttribute('value',window.location.href);--%>
<%--    </script>--%>

    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/popper.min.js"></script>
    <script type="text/javascript" src="../../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../js/datatables.min.js"></script>

    <!-- This script assigns the current servlet URL for language-related inputs, so the LanguageServlet
    will be able to redirect to the page from where the command to change the language was sent -->
    <script type="text/javascript" src="../../js/language-help.js"></script>

    <!-- This script enables the local table search, so the user could find specific rows that match the entered text -->
    <script>
        $(document).ready(function () {
            $('#dtBasicExample').DataTable();
            $('.dataTables_length').addClass('bs-select');
        });
    </script>
</body>
</html>
