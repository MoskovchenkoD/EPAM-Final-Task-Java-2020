<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="loc" uri="/WEB-INF/taglib/i18n.tld"%>
<%@ page import="ua.nure.moskovchenko.db.Status" %>

<table id="dtBasicExample2" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th class="th-sm">ID
        </th>
        <th class="th-sm"><loc:i18n value="course.headline"/>
        </th>
        <th class="th-sm"><loc:i18n value="course.topic"/>
        </th>
        <th class="th-sm"><loc:i18n value="course.length"/>
        </th>
        <th class="th-sm"><loc:i18n value="course.studentCount"/>
        </th>
        <th class="th-sm"><loc:i18n value="course.status"/>
        </th>
        <th>
        </th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="course" items="${lecturerCourses}">

    <tr>

        <td>${course.id}</td>
        <td>${course.headline}</td>
        <td>${course.topicName}</td>
        <td>${course.length}</td>
        <td>${course.studentCount}</td>
        <td>${course.statusName}</td>
        <td>
            <form method="post" action="courseStatus" id="courseForm[${course.id}]">
                <input type="hidden" name="id" value="${course.id}">
                <input type="hidden" name="status" value="${course.statusName}">
                <c:choose>
                    <c:when test="${course.statusName == Status.NEW}">
                        <button type="submit"><loc:i18n value="lecturer.startCourse"/></button> <%--  --%>
                    </c:when>
                    <c:when test="${course.statusName == Status.STARTED}">
                        <button type="submit"><loc:i18n value="lecturer.finishCourse"/></button>
                    </c:when>
                    <c:otherwise>
                        <c:url var="viewJournal" value="journal">
                            <c:param name="id" value="${course.id}"/>
                        </c:url>
                        <a href="${viewJournal}"><loc:i18n value="lecturer.viewGradeBook"/></a>
                    </c:otherwise>
                </c:choose>
            </form>
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
        <th><loc:i18n value="course.length"/>
        </th>
        <th><loc:i18n value="course.studentCount"/>
        </th>
        <th><loc:i18n value="course.status"/>
        </th>
        <th>
        </th>
    </tr>
    </tfoot>
</table>