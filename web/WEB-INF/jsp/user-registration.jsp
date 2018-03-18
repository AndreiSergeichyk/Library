<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registrationServlet" method="post">
    логин: <input type="text" name="name" value="${requestScope.description}"><br>
    пароль: <input type="text" name="password"  value="${requestScope.date}"><br>
    почта: <input type="text" name="addresMailBox" value="${requestScope.date}"><br>
    <input type="submit" value="Зарегестрироваться">
</form>
<c:if test="${not empty requestScope.errors}">
    <div>
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error}</span><br>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
