<%--
  Created by IntelliJ IDEA.
  User: kelle
  Date: 2025. 04. 02.
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="h" uri="/WEB-INF/tlds/favourite.tld" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/AddFood" >Étel hozzáadása</a>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Étterem</th>
                <th>Étel</th>
                <th>Ár (Ft)</th>
                <th>Funkciók</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="food" items="${model}">
            <tr>
                <td><c:out value="${food.id}"/></td>
                <td><c:out value="${food.restaurantName}"/></td>
                <td><c:out value="${food.foodName}"/></td>
                <td><c:out value="${food.price}"/></td>
                <td>
                    <form method="post">
                        <input name="deleteid" id="deleteid" type="hidden" value="${food.id}"/>
                        <input type="submit" value="Törlés" />
                    </form>
                    <form method="get">
                        <input name="cookieid" id="cookieid" type="hidden" value="${food.id}"/>
                        <input type="submit" value="Kedvenc" />
                    </form>
                    <form method="get">
                        <input name="XMLid" id="XMLid" type="hidden" value="${food.id}"/>
                        <input type="submit" value="XML log" />
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h:favourite />
</body>
</html>
