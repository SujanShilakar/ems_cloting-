<%@ page contentType="text/html;charset=UTF-8" %>
<h2>Register</h2>
<% if(request.getAttribute("error")!=null){ %><div style="color:red"><%=request.getAttribute("error")%></div><% } %>
<form method="post">
  <label>Email: <input name="email" type="email" required></label><br>
  <label>Password: <input name="password" type="password" required></label><br>
  <button type="submit">Create Account</button>
</form>
