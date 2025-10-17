package com.mycompany.emsclothing.web;
import com.mycompany.emsclothing.dao.UserDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*; import jakarta.servlet.http.*; import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
  private final UserDAO dao=new UserDAO();
  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
  }
  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    var email=req.getParameter("email"); var pass=req.getParameter("password");
    if(email==null||pass==null||email.isBlank()||pass.isBlank()){
      req.setAttribute("error","Email and password required"); doGet(req,resp); return;
    }
    if(dao.findByEmail(email)!=null){ req.setAttribute("error","Email already used"); doGet(req,resp); return; }
    dao.create(email, BCrypt.hashpw(pass, BCrypt.gensalt()), "CUSTOMER");
    resp.sendRedirect(req.getContextPath()+"/login");
  }
}
