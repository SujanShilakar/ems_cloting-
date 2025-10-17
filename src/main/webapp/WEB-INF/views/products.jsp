<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>

<%
    // Get user session
    Object user = session.getAttribute("user");
%>

<h2>Products</h2>

<!-- Top navigation -->
<div style="margin-bottom: 10px;">
  <% if (user == null) { %>
      <a href="${pageContext.request.contextPath}/login">Login</a> |
      <a href="${pageContext.request.contextPath}/register">Register</a>
  <% } else { %>
      <form method="post" action="${pageContext.request.contextPath}/logout" style="display:inline;">
          <button type="submit">Logout</button>
      </form> |
      <a href="${pageContext.request.contextPath}/cart">Cart</a>
  <% } %>
</div>

<!-- Admin link -->
<p>
  <a href="${pageContext.request.contextPath}/admin/products/new">+ Add Product</a>
</p>

<!-- Product listing -->
<ul>
<%
    java.util.List<com.mycompany.emsclothing.model.Product> ps =
        (java.util.List<com.mycompany.emsclothing.model.Product>) request.getAttribute("products");

    if (ps == null || ps.isEmpty()) {
%>
    <li>No products found.</li>
<%
    } else {
        for (com.mycompany.emsclothing.model.Product p : ps) {
%>
    <li>
        <strong><%= p.getName() %></strong> — $<%= p.getPrice() %>

        <!-- Add to cart form -->
        <form action="${pageContext.request.contextPath}/cart/add" method="post" style="display:inline;">
            <input type="hidden" name="productId" value="<%= p.getId() %>"/>
            <input type="number" name="qty" value="1" min="1" style="width:60px"/>
            <button type="submit">Add to Cart</button>
        </form>

        <!-- Admin options -->
        [<a href="${pageContext.request.contextPath}/admin/products/edit?id=<%=p.getId()%>">edit</a>]
        <form action="${pageContext.request.contextPath}/admin/products/delete" method="post" style="display:inline;">
            <input type="hidden" name="id" value="<%= p.getId() %>"/>
            <button type="submit">delete</button>
        </form>
    </li>
<%
        }
    }
%>
</ul>
