<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="loc" uri="/WEB-INF/taglib/i18n.tld"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom"> <!--   -->

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto mt-2 mt-lg-0"> <!--   -->
            <li class="nav-item dropdown active">
                <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <loc:i18n value="navbar.switchLanguage"/>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                    <form action="/language" method="post" id="en">
                        <input hidden name="lang" value="en">
                        <input hidden id="loc" name="location"> <%-- for JS --%>
                    </form>
                    <form action="/language" method="post" id="ru">
                        <input hidden name="lang" value="ru">
                        <input hidden id="loc2" name="location"> <%-- for JS --%>
                    </form>

                    <button class="dropdown-item" form="en" type="submit">
                        <loc:i18n value="navbar.language.en"/>
                    </button>

                    <button class="dropdown-item" form="ru" type="submit">
                        <loc:i18n value="navbar.language.ru"/>
                    </button>

                </div>
            </li>

            <li class="nav-item active">
                <c:url var="viewCourse" value="courses"/>
                <a class="nav-link" href="${viewCourse}"><loc:i18n value="navbar.viewCourses"/></a>
            </li>

            <li class="nav-item dropdown active">
                <c:choose>
                    <c:when test="${sessionScope.user == null}">
                        <li class="nav-item active">
                            <c:url var="logIn" value="login"/>
                            <a class="nav-link" href="${logIn}"><loc:i18n value="login.login"/></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link dropdown-toggle btn" href="" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <loc:i18n value="navbar.welcome"/>
                            <c:out value="${user.login}"/>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown2">
                            <c:url var="viewCabinet" value="cabinet"/>
                            <a class="dropdown-item" href="${viewCabinet}"><loc:i18n value="navbar.goToCabinet"/></a>
                            <div class="dropdown-divider"></div>
                            <c:url var="logout" value="logout"/>
                            <a class="dropdown-item" href="${logout}"><loc:i18n value="navbar.logout"/></a>
                        </div>
                    </c:otherwise>
                </c:choose>

            </li>
        </ul>
    </div>
</nav>
