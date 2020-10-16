<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="loc" uri="/WEB-INF/taglib/i18n.tld"%>
<%@ page import="ua.nure.moskovchenko.db.State" %>

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
        <th class="th-sm"><loc:i18n value="user.email"/>
        </th>
        <th>
        </th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="student" items="${students}">

    <tr>

        <td>${student.id}</td>
        <td>${student.lastName}</td>
        <td>${student.firstName}</td>
        <td>${student.patronymic}</td>
        <td>${student.login}</td>
        <td>${student.email}</td>
        <td>
            <form method="post" action="student" id="courseForm[${student.id}]">
                <input type="hidden" name="id" value="${student.id}">
                <input type="hidden" name="status" value="${student.state}">
                <c:choose>
                    <c:when test="${student.state == State.FREE}">
                        <button type="submit"><loc:i18n value="admin.blockStudent"/></button> <%--  --%>
                    </c:when>
                    <c:otherwise>
                        <button type="submit"><loc:i18n value="admin.unblockStudent"/></button> <%--  --%>
                    </c:otherwise>
                </c:choose>
            </form>
        </td>

    </tr>

    </c:forEach>

    <tfoot>
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
        <th class="th-sm"><loc:i18n value="user.email"/>
        </th>
        <th>
        </th>
    </tr>
    </tfoot>
</table>