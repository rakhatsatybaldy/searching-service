package kz.bitlab.course.servlets;

import kz.bitlab.course.db.DBManager;
import kz.bitlab.course.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/updatepassword")
public class UpdatePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/login";
        User user = (User) request.getSession().getAttribute("CURRENT_USER");
        if (user!=null){
            redirect = "/profile?oldpassworderror";
            String password = request.getParameter("old_password");
            String newPassword = request.getParameter("new_password");
            String confirmNewPassword = request.getParameter("confirm_new_password");
            if (user.getPassword().equals(password)){
                redirect = "/profile?newpasswordserror";
                if (newPassword.equals(confirmNewPassword)) {
                    redirect = "/profile?oldpassequalnewpass";
                    if (!password.equals(newPassword)) {
                        user.setPassword(newPassword);
                        if (DBManager.updatePassword(user)) {
                            request.getSession().setAttribute("CURRENT_USER", user);
                            redirect = "/profile?success";
                        }
                    }
                }
            }

        }
        response.sendRedirect(redirect);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
