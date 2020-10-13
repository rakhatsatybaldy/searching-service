package kz.bitlab.course.servlets;

import kz.bitlab.course.db.Comment;
import kz.bitlab.course.db.DBManager;
import kz.bitlab.course.db.Hotel;
import kz.bitlab.course.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deletecomment")
public class DeleteCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("CURRENT_USER");
        if (user!=null){
            try {
                Long id = Long.parseLong(request.getParameter("comment_id"));
                Comment comment = DBManager.getComment(id);
                if (comment.getUser().getId()==user.getId()){
                    DBManager.deleteComment(id);
                    response.sendRedirect("/home");
                }else {
                    response.sendRedirect("/login?forthis");
                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }else {
            response.sendRedirect("/errorPage.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
