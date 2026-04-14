<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/hello.tld" prefix="h"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<t:page title="Company">
    <c:if test="${name != null}">
        <h1>
<%--            <c:out value="Hello ${name}"/>--%>
            <h:hello name="${name}"/>
        </h1>
    </c:if>

    <form method="post">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><input type="text" name="address"/></td>
            </tr>
            <tr>
                <td>Tax number:</td>
                <td><input type="text" name="taxNumber"/></td>
            </tr>
            <tr>
                <td><input type="submit"/></td>
            </tr>
        </table>
    </form>
</t:page>