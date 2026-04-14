<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ tag dynamic-attributes="params" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${params.get("title")}</title>
</head>
<body>
    <h3>Welcome here</h3>
    <div>
        <div>
            <a href="${pageContext.request.contextPath}/personPayment">Person</a>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/companyPayment">Company</a>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/clientList">Client list</a>
        </div>
    </div>
    <div>
        <jsp:doBody/>
    </div>
</body>
</html>
