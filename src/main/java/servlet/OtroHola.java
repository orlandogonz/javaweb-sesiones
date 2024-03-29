
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "OtroHola", urlPatterns = {"/procesar", "/login","/desloguin"})
public class OtroHola extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException{
        if(request.getParameter("accion") !=null && request.getParameter("accion").equals("desloguin")){
            HttpSession sesion = request.getSession();
            sesion.invalidate();
        }
        
        resp.sendRedirect("login.jsp");
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      PrintWriter p = response.getWriter();
      String user = request.getParameter("user");
      String pass = request.getParameter("pass");
      
      if (user.equalsIgnoreCase("admin") && pass.equals("admin")){
          
          Cookie c = new Cookie("leng", "es"); //Cookie me pide que le pase dos parametros, un nombre y un valor
          c.setMaxAge(30 * 60);
          response.addCookie(c);//Se lo asigno a la respuesta    
          
          HttpSession sesion = request.getSession();
          sesion.setAttribute("usuario", pass);
         //request.getRequestDispatcher("bienvenido.jsp").forward(request, response);
          response.sendRedirect("bienvenido.jsp");
         
      }else{
          response.sendRedirect("login.jsp");
      }
    }
    
    
    
}
