<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>

    <%-- if DBExpection then code = 500 --%>
    <%-- TODO: i18n --%>

    <c:out value="${errorMessage}"/>

</body>
</html>
