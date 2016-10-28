package views.users;

import controllers.UsersController;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Asta on 2016-10-20.
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password_confirmation"); // reiks panaudoti

        if (password.equals(passwordConfirmation)) {
            User u = new User();
            u.setName(name);
            u.setEmail(email);
            u.setPassword(password);

            UsersController usersController = new UsersController();
            int status = usersController.save(u);
            if (status > 0) {
                out.print("<p class='flash'>Registracija baigta!</p>");
                request.getRequestDispatcher("index.html").include(request, response);
            } else {
                out.print("<p class='flash'>Atsiprasome! Įvyko klaida</p>");
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
        } else {
            out.print("<p class='flash'>Slptažodis nesutampa</p>");
            request.getRequestDispatcher("index.html").include(request, response);
        }

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
