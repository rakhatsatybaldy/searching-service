package kz.bitlab.course.servlets;

import kz.bitlab.course.db.DBManager;
import kz.bitlab.course.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String redirect = "/login?usererror&email="+email;
        User user = DBManager.getUser(email);

        if (user!=null){
            redirect = "/login?passworderror&email="+email;
            if (user.getPassword().equals(password)){
                request.getSession().setAttribute("CURRENT_USER" , user);
                redirect = "/home";
            }
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request , response);
    }
}
