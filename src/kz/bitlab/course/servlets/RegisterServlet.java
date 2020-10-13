package kz.bitlab.course.servlets;

import kz.bitlab.course.db.DBManager;
import kz.bitlab.course.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confPassword = request.getParameter("confirm_password");
        String fullName = request.getParameter("full_name");

        String redirect = "/register?passworderror&email="+(email!=null?email:"") + "&full_name="+(fullName!=null?fullName:"");


        if (password.equals(confPassword)){
            redirect = "/register?usererror&email="+(email!=null?email:"") + "&full_name="+(fullName!=null?fullName:"");
            User user = DBManager.getUser(email);
            if (user==null){
                user = new User(null , email , password , fullName , "/images/default_user.png");
                if (DBManager.addUser(user)) {
                    redirect = "register?success";
                }
            }
        }

        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/registration.jsp").forward(request , response);
    }
}
