<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/includes/header.jspf" %>

<%
  // Remove duplicate declaration of 'u'
  String err = (String) request.getAttribute("error");
  boolean saved = "1".equals(request.getParameter("ok"));
%>
<h3 class="mb-3">Profile settings</h3>

<% if (saved) { %>
  <div class="alert alert-success">Profile updated.</div>
<% } %>
<% if (err != null) { %>
  <div class="alert alert-danger"><%= err %></div>
<% } %>

<form method="post" action="${pageContext.request.contextPath}/profile" class="card card-body">
  <div class="mb-3">
    <label class="form-label">Full name</label>
    <input class="form-control" name="fullName" required
           value="<%= u.getFullName()==null? "" : u.getFullName() %>">
  </div>
  <div class="mb-3">
    <label class="form-label">Phone</label>
    <input class="form-control" name="phone" required
           value="<%= u.getPhone()==null? "" : u.getPhone() %>">
  </div>
  <div class="mb-3">
    <label class="form-label">Address</label>
    <textarea class="form-control" name="address" rows="3" required><%= u.getAddress()==null? "" : u.getAddress() %></textarea>
  </div>
  <div class="d-flex gap-2">
    <button class="btn btn-primary">Save changes</button>
    <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/">Cancel</a>
  </div>
</form>

<%@ include file="/WEB-INF/includes/footer.jspf" %>
