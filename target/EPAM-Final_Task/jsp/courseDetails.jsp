<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="loc" uri="/WEB-INF/taglib/i18n.tld"%>
<%@ page import="ua.nure.moskovchenko.db.Role" %>

<!DOCTYPE html>
<%--<html lang="en">--%>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>Детали курса</title>
  <!-- 
  <link rel="icon" href="img/mdb-favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="css/mdb.min.css">
  
  https://bootsnipp.com/snippets/orOGB
  -->
  <link rel="stylesheet" href="../../css/bootstrap.min.css"> <%-- main/webapp/css/bootstrap.min.css --%>
  <link rel="stylesheet" href="../../css/style-courseDetail.css"> <%-- main/webapp/css/style-courseDetail.css --%>
  
</head>
<body>

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
			<aside class="col-sm-5 border-right">
				<article class="gallery-wrap">
					<div class="img-big-wrap">
						<div><a href="#"><img src=""></a></div>
					</div> <!-- slider-product.// -->
					<div class="img-small-wrap">
						<div class="item-gallery"><img src=""></div>
						<div class="item-gallery"><img src=""></div>
					</div> <!-- slider-nav.// -->

				</article> <!-- gallery-wrap .end// -->
			</aside>
			<aside class="col-sm-7">
				<article class="card-body p-5">
					<h3 class="title mb-3"><c:out value="${course.headline}"/></h3>

					<dl class="item-property">
						<dt><loc:i18n value="course.description"/></dt> <!-- Description -->
						<dd><p><c:out value="${course.description}"/> </p></dd>
					</dl>
					<dl class="param param-feature">
						<dt><loc:i18n value="course.topic"/></dt>
						<dd><c:out value="${course.topicName}"/></dd>
					</dl>  <!-- item-property-hor .// -->
					<dl class="param param-feature">
						<dt><loc:i18n value="course.lecturerName"/></dt>
						<dd>
							<c:out value="${course.lecturerLastName}"/>
							<c:out value="${course.lecturerFirstName}"/>
							<c:out value="${course.lecturerPatronymic}"/>
						</dd>
					</dl>  <!-- item-property-hor .// -->
					<dl class="param param-feature">
						<dt><loc:i18n value="course.length"/></dt>
						<dd><c:out value="${course.length}"/></dd>
					</dl>  <!-- item-property-hor .// -->

					<p class="price-detail-wrap">
						<c:choose>
							<c:when test="${course.studentCount == 0}">
								<span class="price h5 text-warning">
									<loc:i18n value="course.beTheFirst"/>
								</span>
							</c:when>
							<c:otherwise>
								<span class="price h5 text-success">
									<loc:i18n value="course.studentCount"/>
								</span>
								<span class="num"> <c:out value="${course.studentCount}"/></span>
							</c:otherwise>
						</c:choose>
					</p>


					<hr>
					<c:choose>
						<c:when test="${not empty isUserApplied}">
							<dl class="param param-feature">
								<dt><loc:i18n value="user.alreadyJoined"/></dt>
							</dl>
						</c:when>
						<c:when test="${sessionScope.user == null}">
							<dl class="param param-feature">
								<dt><loc:i18n value="user.notLoggedIn"/></dt>
							</dl>
						</c:when>
						<c:when test="${sessionScope.user.role != Role.STUDENT}">
							<dl class="param param-feature">
								<dt><loc:i18n value="user.notStudent"/></dt>
							</dl>
						</c:when>
						<c:otherwise>
							<form method="post" action="courseDetails">
								<input hidden name="courseIdToJoin" value="${course.id}">
								<button class="btn btn-lg btn-primary text-uppercase"> <loc:i18n value="button.joinCourse"/> </button>
							</form>
						</c:otherwise>
					</c:choose>

	
				</article> <!-- card-body.// -->
			</aside> <!-- col.// -->
		</div> <!-- row.// -->
	</div> <!-- card.// -->

</div>
<!--container.//-->

  <!-- jQuery -->
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <!-- Bootstrap tooltips -->
  <script type="text/javascript" src="js/popper.min.js"></script>
  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
  
</body>
</html>
