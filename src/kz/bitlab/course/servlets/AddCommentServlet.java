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

@WebServlet(value = "/addcomment")
public class AddCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        if (currentUser!=null){
            request.setCharacterEncoding("utf8");

            Long hotel_id = Long.parseLong(request.getParameter("hotel_id"));

            Hotel hotel = DBManager.getHotel(hotel_id);
            if (hotel!=null){
                String commentText = request.getParameter("comment");
                Comment comment = new Comment();
                comment.setComment(commentText);
                comment.setUser(currentUser);
                comment.setHotel(hotel);
                DBManager.addComent(comment);
                        response.sendRedirect("/details?id="+hotel.getId()+"#addCommentDiv");

            }else {
                response.sendRedirect("/home");
            }
        }else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
