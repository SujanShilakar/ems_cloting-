<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/includes/header.jspf" %>
<%
  var cart = (java.util.List<com.mycompany.emsclothing.model.CartItem>) session.getAttribute("cart");
  double total = 0; if (cart != null) for (var ci : cart) total += ci.getLineTotal();
%>

<h3 class="mb-3">Checkout</h3>

<% if (cart == null || cart.isEmpty()) { %>
  <div class="alert alert-info">Your cart is empty.</div>
  <a class="btn btn-primary" href="${pageContext.request.contextPath}/products">Browse products</a>
<% } else { %>

<div class="row g-3">
  <div class="col-md-7">
    <div class="card card-body">
      <h5 class="mb-3">Shipping details</h5>
      <!-- minimal form for assignment; extend with address fields later -->
      <div class="row g-2">
        <div class="col-md-6">
          <label class="form-label">Full name</label>
          <input class="form-control" name="fullName" form="placeOrder" required>
        </div>
        <div class="col-md-6">
          <label class="form-label">Phone</label>
          <input class="form-control" name="phone" form="placeOrder" required>
        </div>
        <div class="col-12">
          <label class="form-label">Address</label>
          <input class="form-control" name="address" form="placeOrder" required>
        </div>
        <div class="col-md-6">
          <label class="form-label">City</label>
          <input class="form-control" name="city" form="placeOrder" required>
        </div>
        <div class="col-md-3">
          <label class="form-label">Postcode</label>
          <input class="form-control" name="postcode" form="placeOrder" required>
        </div>
        <div class="col-md-3">
          <label class="form-label">Country</label>
          <input class="form-control" name="country" form="placeOrder" required>
        </div>
      </div>
    </div>
  </div>

  <div class="col-md-5">
    <div class="card card-body">
      <h5 class="mb-3">Order summary</h5>
      <ul class="list-group list-group-flush">
        <% for (var ci : cart) { %>
          <li class="list-group-item d-flex justify-content-between">
            <span><%= ci.getName() %> × <%= ci.getQty() %></span>
            <span>$<%= String.format("%.2f", ci.getLineTotal()) %></span>
          </li>
        <% } %>
        <li class="list-group-item d-flex justify-content-between fw-bold">
          <span>Total</span>
          <span>$<%= String.format("%.2f", total) %></span>
        </li>
      </ul>

      <form id="placeOrder" method="post" action="${pageContext.request.contextPath}/checkout" class="mt-3">
        <button class="btn btn-primary w-100">Place order</button>
      </form>
    </div>
  </div>
</div>
<% } %>

<%@ include file="/WEB-INF/includes/footer.jspf" %>
