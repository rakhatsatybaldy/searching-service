package kz.bitlab.course.servlets;

import kz.bitlab.course.db.Cities;
import kz.bitlab.course.db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(value = "/ajax")
public class AjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("country_id"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ArrayList<Cities> cities = DBManager.getCityByCountryId(id);
        if (cities!=null){
            for (Cities c: cities){
                out.print("<option value = '"+c.getId()+"'>"+c.getName()+"</option>");
            }
        }
    }

}
