package kz.bitlab.course.servlets;

import kz.bitlab.course.db.DBManager;
import kz.bitlab.course.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/updateavatar")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("CURRENT_USER");
        if (user!=null){
            String url = request.getParameter("url");
            user.setAvatar(url);
            if (DBManager.updateAvatar(user)){
                request.getSession().setAttribute("CURRENT_USER" , user);
            }
            response.sendRedirect("/profile");
        }else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
