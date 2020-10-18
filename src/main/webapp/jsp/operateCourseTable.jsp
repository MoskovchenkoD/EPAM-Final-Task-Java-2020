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
        <th class="th-sm"><loc:i18n value="user.lastName"/>
        </th>
        <th class="th-sm"><loc:i18n value="course.length"/>
        </th>
        <th class="th-sm"><loc:i18n value="course.studentCount"/>
        </th>
        <th class="th-sm"><loc:i18n value="course.status"/>
        </th>
        <th>
        </th>
        <th>
        </th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="course" items="${courses}">

    <tr>
        <td>${course.id}</td>
        <td>${course.headline}</td>
        <td>${course.topicName}</td>
        <td>${course.lecturerLastName}</td>
        <td>${course.length}</td>
        <td>${course.studentCount}</td>
        <td>${course.statusName}</td>
        <td>
            <form method="get" action="editCourse" id="editForm[${course.id}]">
                <input type="hidden" name="courseId" value="${course.id}">
                <button type="submit"><loc:i18n value="admin.editCourse"/></button>
            </form>
        </td>
        <td>
            <form method="post" action="deleteCourse" id="deleteForm[${course.id}]">
                <input type="hidden" name="courseId" value="${course.id}">
                <button type="submit"><loc:i18n value="admin.deleteCourse"/></button>
            </form>
        </td>

    </tr>

    </c:forEach>


    <tfoot>
    <tr>
        <th class="th-sm">ID
        </th>
        <th class="th-sm"><loc:i18n value="course.headline"/>
        </th>
        <th class="th-sm"><loc:i18n value="course.topic"/>
        </th>
        <th class="th-sm"><loc:i18n value="user.lastName"/>
        </th>
        <th class="th-sm"><loc:i18n value="course.length"/>
        </th>
        <th class="th-sm"><loc:i18n value="course.studentCount"/>
        </th>
        <th class="th-sm"><loc:i18n value="course.status"/>
        </th>
        <th>
        </th>
        <th>
        </th>
    </tr>
    </tfoot>
</table>