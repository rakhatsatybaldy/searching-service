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

@WebServlet(value = "/edithotel")
public class EditHotelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        if (currentUser!=null){
            Hotel hotel = null;
            try {
                Long id = Long.valueOf(request.getParameter("id"));
                hotel = DBManager.getHotel(id);
                if (hotel.getAuthor().getId() == currentUser.getId()) {
                    if (hotel != null) {
                        request.setAttribute("hotel", hotel);
                        request.getRequestDispatcher("/edithotel.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
                    }
                }else {
                    request.getRequestDispatcher("/errorPage.jsp").forward(request , response);
                }
            }catch(Exception e){
                    e.printStackTrace();
                }


        }else {
            response.sendRedirect("/login?mustlog");
        }
    }
}
