<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/includes/header.jspf" %>
<h3>Admin Dashboard</h3>
<div class="list-group">
  <a class="list-group-item list-group-item-action" href="${pageContext.request.contextPath}/admin/products/new">Add Product</a>
  <a class="list-group-item list-group-item-action" href="${pageContext.request.contextPath}/products">Manage Products</a>
  <a class="list-group-item list-group-item-action" href="${pageContext.request.contextPath}/orders">View Orders</a>
  <a class="list-group-item list-group-item-action" href="${pageContext.request.contextPath}/admin/sales">Sales report</a>

</div>
<%@ include file="/WEB-INF/includes/footer.jspf" %>
