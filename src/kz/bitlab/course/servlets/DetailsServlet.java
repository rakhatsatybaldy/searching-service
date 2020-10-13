package kz.bitlab.course.servlets;

import kz.bitlab.course.db.Comment;
import kz.bitlab.course.db.DBManager;
import kz.bitlab.course.db.Hotel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hotel hotel = null;
        try {
            Long id = Long.valueOf(request.getParameter("id"));
            hotel = DBManager.getHotel(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (hotel!=null){
            request.setAttribute("hotel" , hotel);

            ArrayList<Comment> comments = DBManager.getAllComments(hotel.getId());
            request.setAttribute("comments" , comments);
            request.getRequestDispatcher("/details.jsp").forward(request , response);
        }else {
            request.getRequestDispatcher("/errorPage.jsp").forward(request , response);
        }
    }
}
