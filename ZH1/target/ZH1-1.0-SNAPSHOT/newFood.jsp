<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<t:page title="new">
  <form method="post">
    <table>
      <tr>
        <td>Restaurant:</td>
        <td><input type="text" name="restaurant"></td>
      </tr>
      <tr>
        <td>Name:</td>
        <td><input type="text" name="name"></td>
      </tr>
      <tr>
        <td>Price:</td>
        <td><input type="number" name="price"></td>
      </tr>
      <tr>
        <td>Báttön:</td>
        <td><input type="submit"></td>
      </tr>
    </table>
  </form>
</t:page>