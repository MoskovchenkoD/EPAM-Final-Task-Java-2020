<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="loc" uri="/WEB-INF/taglib/i18n.tld"%>

<html>
    <head>
        <title>Edit the course</title>
    </head>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <link rel="stylesheet" href="../../css/bootstrap.min.css"> <%-- equals main/webapp/css/bootstrap.min.css --%>
    <link rel="stylesheet" href="../../css/style-courses.css" >
    <link rel="stylesheet" href="../../css/simple-sidebar.css">
<body>

    <jsp:include page="navbarAdmin.jsp"/>
    <br>

    <h4>Edit course</h4><br>

    <c:choose>
        <c:when test="${course != null}">
            <c:set var="action" value="editCourse"/>
        </c:when>
        <c:otherwise>
            <c:set var="action" value="addCourse"/>
        </c:otherwise>
    </c:choose>

    <form action="${action}" method="post">
        <input type="hidden" name="courseId" value="${course.id}">
        <label> <loc:i18n value="course.headline"/>: </label>
        <input type="text" class="form-control" name="headline" value="${course.headline}"><br>
        <label> <loc:i18n value="course.description"/>: </label>
        <input type="text" class="form-control" name="description" value="${course.description}"><br>
        <label> <loc:i18n value="course.length"/>: </label>
        <input type="number" min="1" class="form-control" name="length" value="${course.length}"><br>
        <label> <loc:i18n value="course.topic"/>: </label>
        <select name="topic" id="topic">
            <c:forEach items="${topics}" var="value">
                <c:choose>
                    <c:when test="${value == course.topic}">
                        <option selected>${value}</option>
                    </c:when>
                    <c:otherwise>
                        <option>${value}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <br>
        <label> <loc:i18n value="lecturer.id"/>: </label>
        <input type="number" min="1" class="form-control" name="userId" value="${course.userId}"><br>

        <label> <loc:i18n value="course.status"/>: </label>
        <select name="status" id="status">
            <c:forEach items="${statuses}" var="value">
                <c:choose>
                    <c:when test="${value == course.statusName}">
                        <option selected>${value}</option>
                    </c:when>
                    <c:otherwise>
                        <option>${value}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <br>
        <button type="submit"><loc:i18n value="button.submit"/></button>
    </form>

    <div class="container-fluid"> <%-- container --%>
        <br>
        <h4><loc:i18n value="lecturer.availableLecturers"/>:</h4>
        <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
            <thead> <!-- table-sm  -->
            <tr>
                <th class="th-sm">ID
                </th>
                <th class="th-sm"><loc:i18n value="user.lastName"/>
                </th>
                <th class="th-sm"><loc:i18n value="user.firstName"/>
                </th>
                <th class="th-sm"><loc:i18n value="user.patronymic"/>
                </th>
                <th class="th-sm"><loc:i18n value="user.login"/>
                </th>
                <th class="th-sm"><loc:i18n value="user.email"/>
                </th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="lecturer" items="${lecturers}">
            <tr>
                <td>${lecturer.id}</td>
                <td>${lecturer.lastName}</td>
                <td>${lecturer.firstName}</td>
                <td>${lecturer.patronymic}</td>
                <td>${lecturer.login}</td>
                <td>${lecturer.email}</td>
            </tr>
            </c:forEach>

            <tfoot>
            <tr>
                <th class="th-sm">ID
                </th>
                <th class="th-sm"><loc:i18n value="user.lastName"/>
                </th>
                <th class="th-sm"><loc:i18n value="user.firstName"/>
                </th>
                <th class="th-sm"><loc:i18n value="user.patronymic"/>
                </th>
                <th class="th-sm"><loc:i18n value="user.login"/>
                </th>
                <th class="th-sm"><loc:i18n value="user.email"/>
                </th>
            </tr>
            </tfoot>
        </table>


    </div>
    </div>


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
