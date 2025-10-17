<%@ page contentType="text/html;charset=UTF-8" %>
<h2>Your Orders</h2>
<ul>
<%
java.util.List<com.mycompany.emsclothing.model.Order> orders =
 (java.util.List<com.mycompany.emsclothing.model.Order>) request.getAttribute("orders");
if (orders == null || orders.isEmpty()) { %>
  <li>No orders yet.</li>
<% } else { for (var o : orders) { %>
  <li>
    Order #<%= o.getId() %> — $<%= o.getTotal() %> — <%= o.getCreatedAt() %>
    [<a href="${pageContext.request.contextPath}/orders/<%= o.getId() %>">details</a>]
  </li>
<% } } %>
</ul>
