<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/includes/header.jspf" %>

<div class="row justify-content-center">
  <div class="col-md-5">
    <h3 class="mb-3">Create account</h3>

    <% String err = (String) request.getAttribute("error"); if (err != null) { %>
      <div class="alert alert-danger"><%= err %></div>
    <% } %>

    <form method="post" action="${pageContext.request.contextPath}/register" class="card card-body">
      <div class="mb-3">
        <label class="form-label">Email</label>
        <input class="form-control" type="email" name="email" required>
      </div>
      <div class="mb-3">
        <label class="form-label">Password</label>
        <input class="form-control" type="password" name="password" minlength="6" required>
      </div>
      <button class="btn btn-primary w-100">Create Account</button>
    </form>

    <p class="mt-3">Already have an account? <a href="${pageContext.request.contextPath}/login">Login</a></p>
  </div>
</div>

<%@ include file="/WEB-INF/includes/footer.jspf" %>
