<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Grades</title>
</head>
<body>
<h1>Student Grades</h1>
<table border="1">
    <thead>
    <tr>
        <th>Subject</th>
        <th>Grade</th>
    </tr>
    </thead>
    <tbody>
    <%-- Loop through the grades and display them --%>
    <c:forEach items="${grades}" var="grade">
        <tr>
            <td>${grade.subject}</td>
            <td>${grade.grade}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

