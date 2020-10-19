<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="loc" uri="/WEB-INF/taglib/i18n.tld"%>

<table id="dtBasicExample2" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th class="th-sm">ID course
        </th>
        <th class="th-sm">ID student
        </th>
        <th class="th-sm">Last Name
        </th>
        <th class="th-sm">First Name
        </th>
        <th class="th-sm">Login
        </th>
        <th class="th-sm">Email
        </th>
        <th class="th-sm">Mark
        </th>
        <th class="th-sm">State
        </th>
        <th> <!-- button -->
        </th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="student" items="${students}">

    <tr>
<%--        this.courseId = courseId;--%>
<%--        this.userScore = userScore;--%>
<%--        this.id = id;--%>
<%--        this.firstName = firstName;--%>
<%--        this.lastName = lastName;--%>
<%--        this.login = login;--%>
<%--        this.email = email;--%>
<%--        this.state = State.getById(state);--%>

        <td>${student.courseId}</td>
        <td>${student.id}</td>
        <td>${student.lastName}</td>
        <td>${student.firstName}</td>
        <td>${student.login}</td>
        <td>${student.email}</td>
        <td>
            <c:choose>
                <c:when test="${student.userScore == 0}">
                    none
                </c:when>
                <c:otherwise>
                    ${student.userScore}
                </c:otherwise>
            </c:choose>
        </td>
        <td>${student.state}</td>
        <td>
            <form method="get" action="watchStudentProfile" id="editForm[${student.id}]"> <!-- TODO: replace button -->
                <input type="hidden" name="userID" value="${student.id}">
                <button type="submit">View profile</button>
            </form>
        </td>
    </tr>

    </c:forEach>


    <tfoot>
    <tr>
        <th class="th-sm">ID course
        </th>
        <th class="th-sm">ID student
        </th>
        <th class="th-sm">Last Name
        </th>
        <th class="th-sm">First Name
        </th>
        <th class="th-sm">Login
        </th>
        <th class="th-sm">Email
        </th>
        <th class="th-sm">Mark
        </th>
        <th class="th-sm">State
        </th>
        <th> <!-- button -->
        </th>
    </tr>
    </tfoot>
</table>