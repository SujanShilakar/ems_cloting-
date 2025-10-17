<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>

<%
java.util.List<com.mycompany.emsclothing.model.CartItem> cart =
 (java.util.List<com.mycompany.emsclothing.model.CartItem>) session.getAttribute("cart");
double total = 0;
if (cart != null) for (var ci : cart) total += ci.getLineTotal();
%>

<h2>Your Shopping Cart</h2>

<% if (cart == null || cart.isEmpty()) { %>
    <p>Your cart is empty.</p>
    <p><a href="${pageContext.request.contextPath}/products">Continue shopping</a></p>
<% } else { %>
<table border="1" cellpadding="6" cellspacing="0">
  <tr>
    <th>Product</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Subtotal</th>
    <th>Action</th>
  </tr>

  <% for (var ci : cart) { %>
  <tr>
    <td><%= ci.getName() %></td>
    <td>$<%= ci.getUnitPrice() %></td>
    <td>
      <form method="post" action="${pageContext.request.contextPath}/cart/update" style="display:inline;">
        <input type="hidden" name="productId" value="<%= ci.getProductId() %>"/>
        <input type="number" name="qty" value="<%= ci.getQty() %>" min="1" style="width:50px"/>
        <button type="submit">Update</button>
      </form>
    </td>
    <td>$<%= ci.getLineTotal() %></td>
    <td>
      <form method="post" action="${pageContext.request.contextPath}/cart/remove" style="display:inline;">
        <input type="hidden" name="productId" value="<%= ci.getProductId() %>"/>
        <button type="submit">Remove</button>
      </form>
    </td>
  </tr>
  <% } %>
</table>

<h3>Total: $<%= total %></h3>

<form method="post" action="${pageContext.request.contextPath}/cart/clear">
  <button type="submit">Clear Cart</button>
</form>

<p><a href="${pageContext.request.contextPath}/products">Continue Shopping</a></p>
<a href="${pageContext.request.contextPath}/checkout">Proceed to Checkout</a>


<% } %>
