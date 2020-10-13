package kz.bitlab.course.servlets;

import kz.bitlab.course.db.DBManager;
import kz.bitlab.course.db.Hotel;
import kz.bitlab.course.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/savehotel")
public class SaveHotelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        if (currentUser!=null){
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int stars = Integer.parseInt(request.getParameter("stars"));
            int price = Integer.parseInt(request.getParameter("price"));
            Hotel hotel = DBManager.getHotel(id);
            if (hotel!=null){
                hotel.setName(name);
                hotel.setDescription(description);
                hotel.setStars(stars);
                hotel.setPrice(price);
                DBManager.updateHotel(hotel);
                response.sendRedirect("/home?success");
            }
        }else {
            response.sendRedirect("/login?mustlog");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
