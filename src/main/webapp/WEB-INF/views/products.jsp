<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/includes/header.jspf" %>
<%
  var cats = (java.util.List<com.mycompany.emsclothing.model.Category>) request.getAttribute("categories");
  Integer sel = (Integer) request.getAttribute("categoryId");
  String q = (String) request.getAttribute("q");
  String sort = (String) request.getAttribute("sort");
  // DO NOT redeclare 'isAdmin' or 'u' here. They come from header.jspf.
%>


<div class="d-flex align-items-center justify-content-between mb-3">
  <h3 class="mb-0">Products</h3>
  <% if (isAdmin) { %>
    <a class="btn btn-success" href="${pageContext.request.contextPath}/admin/products/new">+ Add Product</a>
  <% } %>
</div>

<form class="row g-2 mb-3" method="get" action="${pageContext.request.contextPath}/products">
  <div class="col-sm-4">
    <input class="form-control" type="text" name="q" value="<%= q==null? "" : q %>" placeholder="Search products">
  </div>
  <div class="col-sm-3">
    <select class="form-select" name="categoryId">
      <option value="">All categories</option>
      <% if (cats != null) for (var c : cats) { %>
        <option value="<%= c.getId() %>" <%= (sel!=null && sel.equals(c.getId())) ? "selected":"" %>><%= c.getName() %></option>
      <% } %>
    </select>
  </div>
  <div class="col-sm-3">
    <select class="form-select" name="sort">
      <option value="">Newest</option>
      <option value="price_asc"  <%= "price_asc".equals(sort)?"selected":"" %>>Price ↑</option>
      <option value="price_desc" <%= "price_desc".equals(sort)?"selected":"" %>>Price ↓</option>
    </select>
  </div>
  <div class="col-sm-2 d-grid">
    <button class="btn btn-primary" type="submit">Apply</button>
  </div>
</form>

<%
  var ps = (java.util.List<com.mycompany.emsclothing.model.Product>) request.getAttribute("products");
  if (ps == null || ps.isEmpty()) {
%>
  <div class="alert alert-info">No products found.</div>
<% } else { %>
  <div class="row g-3">
  <% for (var p : ps) { String img = p.getImageUrl(); %>
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
      <div class="card h-100">
        <% if (img != null && !img.isBlank()) { %>
          <img src="<%= img %>" class="card-img-top" alt="<%= p.getName() %>">
        <% } %>
        <div class="card-body d-flex flex-column">
          <h6 class="card-title mb-1">
            <a class="text-decoration-none" href="${pageContext.request.contextPath}/product?id=<%= p.getId() %>">
              <%= p.getName() %>
            </a>
          </h6>
          <div class="text-muted mb-2">$<%= p.getPrice() %></div>
          <div class="mb-3">
            <span class="badge bg-<%= p.getStock()>0 ? "success" : "secondary" %>">
              Stock: <%= p.getStock() %>
            </span>
          </div>
          <form action="${pageContext.request.contextPath}/cart/add" method="post" class="mt-auto">
            <input type="hidden" name="productId" value="<%= p.getId() %>"/>
            <div class="input-group">
              <input class="form-control" type="number" name="qty" value="1" min="1">
              <button class="btn btn-outline-primary" <%= p.getStock()<=0 ? "disabled":"" %>>Add</button>
            </div>
          </form>
          <% if (isAdmin) { %>
            <div class="mt-2">
              <a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/admin/products/edit?id=<%= p.getId() %>">Edit</a>
              <form class="d-inline" action="${pageContext.request.contextPath}/admin/products/delete" method="post">
                <input type="hidden" name="id" value="<%= p.getId() %>">
                <button class="btn btn-sm btn-outline-danger">Delete</button>
              </form>
            </div>
          <% } %>
        </div>
      </div>
    </div>
  <% } %>
  </div>
<% } %>

<%@ include file="/WEB-INF/includes/footer.jspf" %>
