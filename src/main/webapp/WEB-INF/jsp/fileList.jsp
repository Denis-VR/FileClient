<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Список файлов</title>
</head>
<body>
<div>
    <table border="1">
        <tr>
            <th>Файл</th>
            <th>Размер</th>
        </tr>
        <c:forEach items="${paths}" var="path">
            <tr>
                <td>${path.name}</td>
                <td>${path.size}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>

</html>