package views.users;

import controllers.UsersController;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Asta on 2016-10-20.
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UsersController usersController = new UsersController();

        if (usersController.userExists(email, password)) {
            User u = usersController.getUserByEmailAndPassword(email, password);
            HttpSession session = request.getSession();
            session.setAttribute("name", u.getName());
            session.setAttribute("id", u.getId());
            // out.print("<p class='flash'>Labas, " + session.getAttribute("name") + "</p>");
            response.sendRedirect("/View");
        } else {
            out.print("<p class='flash'>Tokio vartotojo nėra</p>");
            request.getRequestDispatcher("index.html").include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
