<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="loc" uri="/WEB-INF/taglib/i18n.tld"%>

<table id="dtBasicExample2" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
    <thead>
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
        <th class="th-sm"><loc:i18n value="journal.userScore"/>
        </th>
        <th>
        </th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="course" items="${studCourses}">

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
            <c:choose>
                <c:when test="${course.userScore == 0}">
                    ?
                </c:when>
                <c:otherwise>
                    ${course.userScore}
                </c:otherwise>
            </c:choose>
        </td>
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
        <th><loc:i18n value="journal.userScore"/>
        </th>
        <th>
        </th>
    </tr>
    </tfoot>
</table>