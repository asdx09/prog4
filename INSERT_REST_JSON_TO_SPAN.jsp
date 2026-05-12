<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<t:page>
  <style type="text/css">
    td {
      border: 1px solid black;
    }
  </style>
  <div>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Film cím</th>
          <th>Rendező</th>
          <th>Megjelenés</th>
          <th>Értékelések</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${movies}" var="movie">
            <tr>
              <td><c:out value="${movie.id}"/></td>
              <td><c:out value="${movie.title}"/></td>
              <td><c:out value="${movie.directorName}"/></td>
              <td><c:out value="${movie.releaseYear}"/></td>
              <td><c:out value="${movie.genre}"/></td>
              <td>
                <span class="rating" data-id="${movie.id}">
                  ...
                </span>
              </td>
            </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
  <script>
    document.querySelectorAll(".rating").forEach(async el => {
      let id = el.dataset.id;

      let res = await fetch("${pageContext.request.contextPath}/api/movie/rating/" + id);

      if (res.ok) {
        let obj = await res.json();
        el.textContent = obj;
      } else {
        el.textContent = "Error!";
      }
    });
  </script>
</t:page>