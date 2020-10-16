<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="loc" uri="/WEB-INF/taglib/i18n.tld"%>
<%@ page import="ua.nure.moskovchenko.db.Role" %>

<!DOCTYPE html>
<html>
<head>

  <title>Registration Page</title>
  <link rel="stylesheet" href="../../css/bootstrap.min.css">
  
</head>
<body>

<c:choose>
  <c:when test="${sessionScope.user != null && sessionScope.user.role == Role.ADMIN}">
    <jsp:include page="navbarAdmin.jsp"/>
  </c:when>
  <c:otherwise>
    <jsp:include page="navbar.jsp"/>
  </c:otherwise>
</c:choose>

<div class="container mt-2 mb-4">
  <div class="row justify-content-md-center">
    <div class="col-sm-4 border border-primary shadow rounded pt-2">
      <div class="text-center"><img src="https://placehold.it/80x80" class="rounded-circle border p-1"></div>
      <div class="col-sm-12">
        <form action="register" method="post" id="singnupFrom" onSubmit="return validation();">
          <div class="form-group">
            <label class="font-weight-bold"><loc:i18n value="user.email"/></label>
            <div class="input-group">
              <input type="email" name="email" id="email" class="form-control" placeholder="Enter valid email">
              <div class="input-group-append"><button type="button" class="btn btn-primary" onClick="return emailCheck();"><i class="fa fa-envelope"></i></button></div>
            </div>
          </div>
          <div id="next-form" class="collapse">

            <c:if test="${sessionScope.user != null && user.role == Role.ADMIN}">
              <input type="hidden" name="role" value="lecturer">
            </c:if>
		  
		    <div class="form-group">
              <label class="font-weight-bold"><loc:i18n value="user.firstName"/></label>
              <input type="text" name="firstName" id="firstName" class="form-control">
			  <em id="cfirstName"></em>
            </div>
		    <div class="form-group">
              <label class="font-weight-bold"><loc:i18n value="user.lastName"/></label>
              <input type="text" name="lastName" id="lastName" class="form-control">
			  <em id="clastName"></em>
            </div>
			<div class="form-group">
              <label class="font-weight-bold"><loc:i18n value="user.patronymic"/>
			  <small class="text-warning"><em><loc:i18n value="register.patronymicWarn"/></em></small></label> <!-- TODO: i18n -->
              <input type="text" name="patronymic" id="patronymic" class="form-control">
            </div>
		  
		  
            <div class="form-group">
              <label class="font-weight-bold"><loc:i18n value="user.login"/></label>
              <input type="text" name="login" id="username" class="form-control">
			  <em id="cusername"></em>
            </div>
            
            <div class="form-group">
              <label class="font-weight-bold"><loc:i18n value="user.password"/></label>
              <input type="password" name="password" id="password" class="form-control" placeholder="***********">
			  <em id="cp"></em>
            </div>
            
            <div class="form-group">
              <input type="submit" name="submit" value="GO!" class="btn btn-block btn-danger">
            </div>
          </div>
          <!--/.next-form-->
        </form>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/popper.min.js"></script>
<script type="text/javascript" src="../../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../js/language-help.js"></script>

<script>

function emailCheck() {
  if ($("#email").val() == "") {
    $("#email").addClass("is-invalid");
    return false;
  } else {
    var regMail = /^([_a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\.)+([a-zA-Z]{2,3})$/;
    if (regMail.test($("#email").val()) == false) {
      $("#email").addClass("is-invalid");
      return false;
    } else {
      $("#email").removeClass("is-invalid");
      $("#next-form").collapse("show");
    }
  }
}
function validation() {
/*
  if ($("#firstName, #lastName, #username, #password").val() == "") {
    $("#firstName, #lastName, #username, #password").addClass("is-invalid");
    return false;
  } else {
    $("#firstName, #lastName, #username, #password").removeClass("is-invalid");
  }
  */
  
  if ($("#email").val() == "") {
    $("#email").addClass("is-invalid");
    return false;
  } else {
    var regMail = /^([_a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\.)+([a-zA-Z]{2,3})$/;
    if (regMail.test($("#email").val()) == false) {
      $("#email").addClass("is-invalid");
      return false;
    } 
	/*
	else {
	$("#email").removeClass("is-invalid");
	*/
  }
  
  
  if ($("#firstName").val() == "") {
    $("#firstName").addClass("is-invalid");
    $("#cfirstName").html(
      '<span class="text-danger"> must not be empty!</span>' // TODO: i18n
    );
    return false;
  }
  
  
  if ($("#lastName").val() == "") {
    $("#lastName").addClass("is-invalid");
    $("#clastName").html(
      '<span class="text-danger"> must not be empty!</span>' // TODO: i18n
    );
    return false;
  }
  
  if ($("#username").val() == "") {
    $("#username").addClass("is-invalid");
    $("#cusername").html(
      '<span class="text-danger"> must not be empty!</span>' // TODO: i18n
    );
    return false;
  }

  if ($("#password").val() == "") {
    $("#password").addClass("is-invalid");
    $("#cp").html(
      '<span class="text-danger">Password must not be empty!</span>' // TODO: i18n
    );
    return false;
  }
}
$(document).ready(function(e) {
  $("#firstName").on("keyup", function() {
    if ($("#firstName").val() == "") {
      $("#firstName").addClass("is-invalid");
      return false;
    } else {
      $("#firstName").removeClass("is-invalid");
    }
  });
  $("#lastName").on("keyup", function() {
    if ($("#lastName").val() == "") {
      $("#lastName").addClass("is-invalid");
      return false;
    } else {
      $("#lastName").removeClass("is-invalid");
    }
  });
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
