<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/includes/header.jspf" %>
<%
  // expects: request attribute "orders" = List<Order>
  var orders = (java.util.List<com.mycompany.emsclothing.model.Order>) request.getAttribute("orders");
%>

<div class="d-flex align-items-center justify-content-between mb-3">
  <h3 class="mb-0">My Orders</h3>
  <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/products">Continue shopping</a>
</div>

<% if (orders == null || orders.isEmpty()) { %>
  <div class="text-center p-5 bg-white border rounded">
    <h5 class="mb-2">No orders yet</h5>
    <p class="text-muted mb-3">Browse products and place your first order.</p>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/products">Shop now</a>
  </div>
<% } else { %>

<div class="card">
  <div class="card-body p-0">
    <div class="table-responsive">
      <table class="table align-middle mb-0">
        <thead class="table-light">
          <tr>
            <th style="width:120px;">Order #</th>
            <th style="width:220px;">Placed</th>
            <th>Status</th>
            <th class="text-end" style="width:140px;">Total</th>
            <th style="width:140px;"></th>
          </tr>
        </thead>
        <tbody>
        <% for (var o : orders) { %>
          <tr>
            <td class="fw-semibold">#<%= o.getId() %></td>
            <td><%= o.getCreatedAt() %></td>
            <td>
              <span class="badge 
                <%= "DELIVERED".equals(o.getStatus()) ? "bg-success" :
                    "SHIPPED".equals(o.getStatus())   ? "bg-info" :
                    "PAID".equals(o.getStatus())      ? "bg-primary" :
                    "CANCELLED".equals(o.getStatus())  ? "bg-secondary" : "bg-warning" %>">
                <%= o.getStatus()==null ? "PENDING" : o.getStatus() %>
              </span>
            </td>
            <td class="text-end">$<%= String.format("%.2f", o.getTotal()) %></td>
            <td class="text-end">
              <a class="btn btn-sm btn-outline-secondary"
                 href="${pageContext.request.contextPath}/orders/<%= o.getId() %>">View</a>
            </td>
          </tr>
        <% } %>
        </tbody>
      </table>
    </div>
  </div>
</div>

<% } %>

<%@ include file="/WEB-INF/includes/footer.jspf" %>
