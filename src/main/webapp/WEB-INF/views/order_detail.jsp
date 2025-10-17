<%@ page contentType="text/html;charset=UTF-8" %>
<%
com.mycompany.emsclothing.model.Order o =
 (com.mycompany.emsclothing.model.Order) request.getAttribute("order");
%>
<h2>Order #<%= o.getId() %></h2>
<p>Total: $<%= o.getTotal() %> | Date: <%= o.getCreatedAt() %></p>

<table border="1" cellpadding="6">
<tr><th>Product</th><th>Price</th><th>Qty</th><th>Line</th></tr>
<% for (var it : o.getItems()) { %>
<tr>
  <td><%= it.getProduct().getName() %></td>
  <td>$<%= it.getPrice() %></td>
  <td><%= it.getQty() %></td>
  <td>$<%= it.getPrice() * it.getQty() %></td>
</tr>
<% } %>
</table>

<p><a href="${pageContext.request.contextPath}/orders">Back to Orders</a></p>
