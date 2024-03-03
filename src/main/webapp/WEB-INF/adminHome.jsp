
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Home</title>
</head>
<body>
<h1>Welcome, Admin!</h1>
<form action="editGrade" method="post">
    <label for="studentId">Student ID:</label>
    <input type="text" id="studentId" name="studentId"><br>
    <label for="subject">Subject:</label>
    <input type="text" id="subject" name="subject"><br>
    <label for="newGrade">New Grade:</label>
    <input type="text" id="newGrade" name="newGrade"><br>
    <input type="submit" value="Update Grade">
</form>

</body>
</html>