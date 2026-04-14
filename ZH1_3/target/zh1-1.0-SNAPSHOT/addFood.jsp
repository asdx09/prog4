<%--
  Created by IntelliJ IDEA.
  User: kelle
  Date: 2025. 04. 02.
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Étel hozzáadása</title>
</head>
<body>
<form method="post">
<table>
  <thead>
  <tr>
    <th>Étterem</th>
    <th>Étel</th>
    <th>Ár (Ft)</th>
    <th></th>
  </tr>
  </thead>
  <tbody>
    <tr>
      <td><input name="restaurant" id="restaurant" type="text" /> </td>
      <td><input name="name" id="name" type="text" /> </td>
      <td><input name="price" id="price" type="number" /> </td>
      <td>
        <input type="submit" value="felvitel" />
      </td>
    </tr>
  </tbody>
</table>
</form>
</body>
</html>
