<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/includes/header.jspf" %>
<h3>Top Sold Products</h3>
<table class="table table-striped">
  <thead><tr><th>Product</th><th>Units</th><th>Revenue</th></tr></thead>
  <tbody>
  <%
    var rows = (java.util.List<com.mycompany.emsclothing.dao.ReportDAO.Row>) request.getAttribute("rows");
    for (var r : rows) {
  %>
    <tr>
      <td><%= r.name %></td>
      <td><%= r.units %></td>
      <td>$<%= String.format("%.2f", r.revenue) %></td>
    </tr>
  <% } %>
  </tbody>
</table>
<%@ include file="/WEB-INF/includes/footer.jspf" %>
