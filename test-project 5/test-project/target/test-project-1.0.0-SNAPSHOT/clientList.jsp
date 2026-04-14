<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Client List</title>
    <style>
        td {
            border: 1px solid black;
        }

        thead > tr > td {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>List of the clients</h1>
    <table>
        <thead>
            <tr>
                <td>ID</td>
                <td>Type</td>
                <td>Name</td>
                <td>Address</td>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${clients}" var="client">
            <tr>
                <td>
                    <c:out value="${client.id}"/>
                </td>
                <td>
                    <c:out value="${client.getClass().getSimpleName()}"/>
                </td>
                <td>
                    <c:out value="${client.name}"/>
                </td>
                <td>
                    <c:out value="${client.address}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/personPayment">Person</a>
    <a href="${pageContext.request.contextPath}/companyPayment">Company</a>
</body>
</html>
