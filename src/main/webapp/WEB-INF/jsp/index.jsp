<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Welcome</title>
</head>
<body>
<h1>Директории и файлы</h1>
<form:form action="path" modelAttribute="path_name">
    Название директории: <form:input path="name"/>
    <input type="submit" value="Добавить файл"/>
    <form:errors path="name"/>
</form:form>
<h2>Список директорий и файлов </h2>
<%--<input type="button" value="Add" onclick="window.location.href = 'fileList'"/>--%>
<div>
    <table border="1">
        <tr>
            <th>Дата</th>
            <th>Базовая директория</th>
            <th>Директорий</th>
            <th>Файлов</th>
            <th>Суммарный размер файлов</th>
            <th></th>
        </tr>
        <c:forEach items="${paths}" var="path">
            <tr>
                <td>${path.date_created}</td>
                <td>${path.path}</td>
                <td>${path.numOfDirs}</td>
                <td>${path.numOfFiles}</td>
                <td>${path.totalFileSize}</td>
                <td>
                    <input type="button" value="Файлы" onclick="window.location.href = 'fileList?path=${path.path}'"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>

</html>