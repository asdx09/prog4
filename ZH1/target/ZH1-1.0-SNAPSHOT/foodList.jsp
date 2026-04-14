<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib uri="/WEB-INF/tlds/favorite.tld" prefix="h"%>
<t:page title="list">
    <table>
        <thead>
        <tr>
            <td>ID</td>
            <td>Restaurant</td>
            <td>Name</td>
            <td>Price</td>
            <td>Báttönsz</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${foods}" var="food">
            <tr>
                <td>
                    <c:out value="${food.id}"/>
                </td>
                <td>
                    <c:out value="${food.restaurant}"/>
                </td>
                <td>
                    <c:out value="${food.name}"/>
                </td>
                <td>
                    <c:out value="${food.price}"/>
                </td>
                <td>
                    <form action="delete" method="post">
                        <input type="hidden" name="id" value="${food.id}">
                        <input type="submit" value="delete">
                    </form>
                    <form action="favorite" method="post">
                        <input type="hidden" name="id" value="${food.id}">
                        <input type="submit" value="favorite">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h:favorite></h:favorite>
</t:page>
