<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Programming school home page</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<div class="content">
    <h1>Programming school home page</h1>
    <br>
    <h3>Most recent solutions</h3>
    <table class="myTable">
        <tr>
            <th>Exercise title</th>
            <th>Author</th>
            <th>Last modified</th>
            <th>Details</th>
        </tr>
        <c:forEach items="${requestScope.lastsolutions}" var="lastSolution">
            <tr>
                <td>${lastSolution.title}</td>
                <td>${lastSolution.name}</td>
                <td>${lastSolution.modified}</td>
                <td><a href="${pageContext.request.contextPath}/solutiondetails?id=${lastSolution.id}">
                        Details</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
<jsp:include page="/jsp/footer.jsp"/>
</body>
</html>