<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="loc" uri="/WEB-INF/taglib/i18n.tld"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">

	<title>Error 404</title>

</head>

<body>

	<div id="notfound">
		<div class="notfound">
			<div class="notfound-404">
				<h1>404</h1>
			</div>
			<h2>Oops! This Page Could Not Be Found</h2>
			<p>Sorry but the page you are looking for does not exist, have been removed. name changed or is temporarily unavailable</p>
			<c:url value="courses" var="redirect"/>
			<a href="${redirect}">Go To Homepage</a>

			<br>

		</div>
	</div>

</body>

</html>
