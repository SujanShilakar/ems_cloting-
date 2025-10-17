package com.mycompany.emsclothing.web;
import com.mycompany.emsclothing.model.User;
import jakarta.servlet.*; import jakarta.servlet.annotation.WebFilter; import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter("/admin/*")
public class AuthFilter implements Filter {
  @Override public void doFilter(ServletRequest r, ServletResponse s, FilterChain c) throws IOException, ServletException{
    HttpServletRequest req=(HttpServletRequest) r; HttpServletResponse resp=(HttpServletResponse) s;
    User u=(User) req.getSession().getAttribute("user");
    if(u==null || !"ADMIN".equals(u.getRole())) { resp.sendRedirect(req.getContextPath()+"/login"); return; }
    c.doFilter(r,s);
  }
}
