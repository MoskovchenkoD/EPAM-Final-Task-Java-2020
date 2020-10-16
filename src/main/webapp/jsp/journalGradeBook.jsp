<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="loc" uri="/WEB-INF/taglib/i18n.tld"%>
<%@ page import="ua.nure.moskovchenko.db.Status" %>

<table id="dtBasicExample2" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
    <thead>
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
        <th class="th-sm"><loc:i18n value="journal.dateJoin"/>
        </th>
        <th class="th-sm"><loc:i18n value="journal.userScore"/>
        </th>
        <th>
        </th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="student" items="${students}">

    <tr>
        <td>${student.userId}</td>
        <td>${student.lastName}</td>
        <td>${student.firstName}</td>
        <td>${student.patronymic}</td>
        <td>${student.login}</td>
        <td>${student.dateJoin}</td>
        <td>
            <input form="journalForm[${student.userId}]" type="number" name="score" min="1" max="10" value="${student.userScore}">
        </td>
        <td>
            <form method="post" action="journal" id="journalForm[${student.userId}]">
                <input type="hidden" name="userId" value="${student.userId}">
                <input type="hidden" name="courseId" value="${student.courseId}">
                <button type="submit"><loc:i18n value="journal.grade"/></button>
            </form>
        </td>

    </tr>

    </c:forEach>


    <tfoot>
    <tr>
        <th>ID
        </th>
        <th class="th-sm"><loc:i18n value="user.lastName"/>
        </th>
        <th class="th-sm"><loc:i18n value="user.firstName"/>
        </th>
        <th class="th-sm"><loc:i18n value="user.patronymic"/>
        </th>
        <th class="th-sm"><loc:i18n value="user.login"/>
        </th>
        <th class="th-sm"><loc:i18n value="journal.dateJoin"/>
        </th>
        <th class="th-sm"><loc:i18n value="journal.userScore"/>
        </th>
        <th>
        </th>
    </tr>
    </tfoot>
</table>