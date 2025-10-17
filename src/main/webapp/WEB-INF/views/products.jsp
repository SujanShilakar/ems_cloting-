<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head><title>Products</title></head>
<body>
<h2>Products</h2>
<ul>
<%
java.util.List<com.mycompany.emsclothing.model.Product> ps =
(java.util.List<com.mycompany.emsclothing.model.Product>) request.getAttribute("products");
for (com.mycompany.emsclothing.model.Product p : ps) {
%>
  <li><%= p.getName() %> — $<%= p.getPrice() %></li>
<% } %>
</ul>
<p><a href="<%= request.getContextPath() %>/">Back</a></p>
</body>
</html>
