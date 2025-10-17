<%@ page contentType="text/html;charset=UTF-8" %>
<h2>Checkout</h2>
<p>Click to place your order.</p>
<form method="post" action="${pageContext.request.contextPath}/checkout">
  <button type="submit">Place Order</button>
</form>
<p><a href="${pageContext.request.contextPath}/cart">Back to cart</a></p>
