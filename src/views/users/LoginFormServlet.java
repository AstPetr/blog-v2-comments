package views.users;

import views.templates.Layout;

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
@WebServlet("/LoginForm")
public class LoginFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Layout la = new Layout();
        la.header(out);

        out.println("<h1>Prisijungimas</h1>");
        out.print("<form action=\"Login\" method=\"post\">\n" +
                "<p>\n" +
                "    <label for=\"email\">Elektroninis Paštas</label><br>\n" +
                "    <input type=\"text\" name=\"email\" id=\"email\">\n" +
                "  </p>\n" +
                "<p>\n" +
                "    <label for=\"password\">Slaptažodis</label><br>\n" +
                "    <input type=\"password\" name=\"password\" id=\"password\">\n" +
                "  </p>\n" +
                "<p>\n" +
                "    <input type=\"submit\" value=\"Prisijunk\" class=\"btn\">\n" +
                "  </p>\n" +
                "</form>");
        out.print("<a href='/RegisterForm'>Registruokis</a>");

        la.footer(out);
        out.close();

    }
}
