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

    <link rel="stylesheet" href="../../css/bootstrap.min.css"> <%-- equals main/webapp/css/bootstrap.min.css --%>
    <link rel="stylesheet" href="../../css/style-courseDetail.css">
    <link rel="stylesheet" href="../../css/style-courses.css" >
    <link rel="stylesheet" href="../../css/simple-sidebar.css">
</head>


<body>

<div id="page-content-wrapper">

    <jsp:include page="navbarAdmin.jsp"/>

    <div class="container-fluid">

        <c:choose>
            <c:when test="${not empty students}">
                <br><h3 class="title mb-3 text-success"><loc:i18n value="admin.studentAccess"/></h3><br>
                <jsp:include page="studentOpsTable.jsp"/>
            </c:when>
            <c:otherwise>
                <br><h3 class="title mb-3"><loc:i18n value="admin.noStudents"/></h3><br>
            </c:otherwise>
        </c:choose>


    </div>

</div>

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
