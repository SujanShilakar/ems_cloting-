<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/includes/header.jspf" %>
<% String placed = request.getParameter("placed"); if ("1".equals(placed)) { %>
  <div class="alert alert-success">Thank you. Your order has been placed.</div>
<% } %>

<%
  var o = (com.mycompany.emsclothing.model.Order) request.getAttribute("o");
%>
<h3 class="mb-3">Order #<%= o.getId() %></h3>
<p class="text-muted mb-2">Placed: <%= o.getCreatedAt() %></p>

<div class="table-responsive">
  <table class="table align-middle">
    <thead class="table-light">
      <tr><th>Product</th><th>Price</th><th>Qty</th><th>Subtotal</th></tr>
    </thead>
    <tbody>
    <% for (var it : o.getItems()) { %>
      <tr>
        <td><%= it.getProduct().getName() %></td>
        <td>$<%= String.format("%.2f", it.getPrice()) %></td>
        <td><%= it.getQty() %></td>
        <td>$<%= String.format("%.2f", it.getPrice()*it.getQty()) %></td>
      </tr>
    <% } %>
    </tbody>
    <tfoot>
      <tr>
        <th colspan="3" class="text-end">Total</th>
        <th>$<%= String.format("%.2f", o.getTotal()) %></th>
      </tr>
    </tfoot>
  </table>
</div>

<div class="mt-3">
  <a class="btn btn-primary" href="${pageContext.request.contextPath}/products">Continue shopping</a>
  <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/orders">My orders</a>
</div>
<%@ include file="/WEB-INF/includes/footer.jspf" %>
