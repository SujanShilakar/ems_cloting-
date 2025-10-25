<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/includes/header.jspf" %>
<%
  java.util.List<com.mycompany.emsclothing.model.CartItem> cart =
    (java.util.List<com.mycompany.emsclothing.model.CartItem>) session.getAttribute("cart");
  double total = 0;
  if (cart != null) for (var ci : cart) total += ci.getLineTotal();
%>

<h3 class="mb-3">Your Cart</h3>

<% if (cart == null || cart.isEmpty()) { %>
  <div class="alert alert-info">Your cart is empty.</div>
  <a class="btn btn-primary" href="${pageContext.request.contextPath}/products">Browse products</a>
<% } else { %>
  <div class="table-responsive">
    <table class="table align-middle">
      <thead class="table-light">
        <tr><th>Product</th><th style="width:140px;">Price</th><th style="width:160px;">Qty</th><th style="width:140px;">Subtotal</th><th style="width:120px;"></th></tr>
      </thead>
      <tbody>
      <% for (var ci : cart) { %>
        <tr>
          <td><strong><%= ci.getName() %></strong></td>
          <td>$<%= ci.getUnitPrice() %></td>
          <td>
            <form class="d-flex" method="post" action="${pageContext.request.contextPath}/cart/update">
              <input type="hidden" name="productId" value="<%= ci.getProductId() %>"/>
              <input class="form-control me-2" type="number" name="qty" value="<%= ci.getQty() %>" min="1" style="max-width:80px">
              <button class="btn btn-outline-primary">Update</button>
            </form>
          </td>
          <td>$<%= ci.getLineTotal() %></td>
          <td>
            <form method="post" action="${pageContext.request.contextPath}/cart/remove">
              <input type="hidden" name="productId" value="<%= ci.getProductId() %>"/>
              <button class="btn btn-outline-danger">Remove</button>
            </form>
          </td>
        </tr>
      <% } %>
      </tbody>
    </table>
  </div>

  <div class="d-flex justify-content-between align-items-center mt-3">
    <form method="post" action="${pageContext.request.contextPath}/cart/clear">
      <button class="btn btn-outline-secondary">Clear Cart</button>
    </form>
    <div class="fs-5">Total: <strong>$<%= total %></strong></div>
  </div>

  <div class="d-flex justify-content-end mt-3">
    <a class="btn btn-light me-2" href="${pageContext.request.contextPath}/products">Continue shopping</a>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/checkout">Proceed to Checkout</a>
  </div>
<% } %>

<%@ include file="/WEB-INF/includes/footer.jspf" %>
