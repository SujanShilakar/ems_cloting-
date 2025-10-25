<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/includes/header.jspf" %>
<%
  com.mycompany.emsclothing.model.Product p =
      (com.mycompany.emsclothing.model.Product) request.getAttribute("p");
  boolean editing = Boolean.TRUE.equals(request.getAttribute("editing"));
  java.util.List<com.mycompany.emsclothing.model.Category> cats =
      (java.util.List<com.mycompany.emsclothing.model.Category>) request.getAttribute("categories");
  if (cats == null) cats = java.util.Collections.emptyList();
%>

<h3 class="mb-3"><%= editing ? "Edit Product" : "Add Product" %></h3>

<form method="post"
      enctype="multipart/form-data"
      action="${pageContext.request.contextPath}<%= editing ? "/admin/products/edit" : "/admin/products/new" %>"
      class="card card-body">

  <% if (editing) { %>
    <input type="hidden" name="id" value="<%= p.getId() %>">
  <% } %>

  <div class="mb-3">
    <label class="form-label">Name</label>
    <input class="form-control" name="name" value="<%= editing? p.getName() : "" %>" required>
  </div>

  <div class="mb-3">
    <label class="form-label">Price</label>
    <input class="form-control" type="number" step="0.01" name="price"
           value="<%= editing? p.getPrice() : 0 %>" required>
  </div>

  <div class="mb-3">
    <label class="form-label">Stock</label>
    <input class="form-control" type="number" min="0" name="stock"
           value="<%= editing? p.getStock() : 0 %>" required>
  </div>

  <div class="mb-3">
    <label class="form-label">Category</label>
    <select class="form-select" name="categoryId">
      <option value="">-- none --</option>
      <% for (var c : cats) { %>
        <option value="<%= c.getId() %>"
          <%= (editing && p.getCategory()!=null && p.getCategory().getId().equals(c.getId())) ? "selected" : "" %>>
          <%= c.getName() %>
        </option>
      <% } %>
    </select>
  </div>

  <div class="mb-3">
    <label class="form-label">Image file</label>
    <input class="form-control" type="file" name="imageFile" accept="image/*">
    <% if (editing && p.getImageUrl()!=null && !p.getImageUrl().isBlank()) { %>
      <div class="form-text">Current:</div>
      <img src="<%= p.getImageUrl() %>" style="max-height:120px" class="mt-1 border rounded">
    <% } %>
  </div>

  <div class="d-flex gap-2">
    <button class="btn btn-primary"><%= editing ? "Save changes" : "Create" %></button>
    <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/products">Cancel</a>
  </div>
</form>

<%@ include file="/WEB-INF/includes/footer.jspf" %>
