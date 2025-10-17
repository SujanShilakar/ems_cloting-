<%@ page contentType="text/html;charset=UTF-8" %>
<%
com.mycompany.emsclothing.model.Product p =
 (com.mycompany.emsclothing.model.Product) request.getAttribute("product");
boolean editing = p != null;
%>
<h2><%= editing ? "Edit" : "New" %> Product</h2>
<% String err = (String) request.getAttribute("error"); if (err != null) { %>
  <div style="color:red"><%= err %></div>
<% } %>
<form method="post" action="<%= editing ? (request.getContextPath()+"/admin/products/edit") : (request.getContextPath()+"/admin/products/create") %>">
  <% if (editing) { %><input type="hidden" name="id" value="<%= p.getId() %>"/><% } %>
  <label>Name: <input name="name" value="<%= editing ? p.getName() : "" %>" required></label><br>
  <label>Price: <input name="price" value="<%= editing ? p.getPrice() : "" %>" required></label><br>
  <button type="submit"><%= editing ? "Update" : "Create" %></button>
</form>
<p><a href="${pageContext.request.contextPath}/products">Back</a></p>
