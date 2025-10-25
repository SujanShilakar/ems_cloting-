<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/includes/header.jspf" %>
<%
  var p = (com.mycompany.emsclothing.model.Product) request.getAttribute("p");
%>
<h3><%= p.getName() %></h3>
<% if (p.getImageUrl()!=null && !p.getImageUrl().isBlank()) { %>
  <img class="img-fluid" src="<%= p.getImageUrl() %>" alt="<%= p.getName() %>">
<% } %>
<p class="fs-5">$<%= p.getPrice() %></p>
<p>Stock: <%= p.getStock() %></p>
<form action="${pageContext.request.contextPath}/cart/add" method="post">
  <input type="hidden" name="productId" value="<%= p.getId() %>"/>
  <input class="form-control" style="max-width:120px" type="number" name="qty" value="1" min="1" max="<%= p.getStock() %>">
  <button class="btn btn-primary mt-2" <%= p.getStock()<=0?"disabled":"" %>>Add to Cart</button>
</form>
<%@ include file="/WEB-INF/includes/footer.jspf" %>
