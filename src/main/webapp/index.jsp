<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head><title>EMS Clothing</title></head>
<body>
  <h1>EMS Clothing</h1>
  <p><a href="products">View Products</a></p>
  <%@ page session="true" %>
<%
  Object user = session.getAttribute("user");
  if (user == null) {
%>
  <a href="${pageContext.request.contextPath}/login">Login</a> |
  <a href="${pageContext.request.contextPath}/register">Register</a>
<%
  } else {
%>
  <form method="post" action="${pageContext.request.contextPath}/logout" style="display:inline;">
      <button type="submit">Logout</button>
  </form>
<%
  }
%>
<a href="${pageContext.request.contextPath}/orders">My Orders</a>

</body>
</html>
