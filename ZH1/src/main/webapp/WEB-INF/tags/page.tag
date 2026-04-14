<%@ tag dynamic-attributes="params" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${params.get("title")}</title>
    </head>
    <body>
        <h1>WELCOME!</h1>
        <div>
            <a href="${pageContext.request.contextPath}/new">New</a><br>
            <a href="${pageContext.request.contextPath}/list">List</a>
        </div>
        <div>
            <jsp:doBody/>
        </div>
    </body>
</html>