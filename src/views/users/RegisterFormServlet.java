package views.users;

import views.templates.Layout;

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
@WebServlet("/RegisterForm")
public class RegisterFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Layout la = new Layout();
        la.header(out);

        HttpSession session = request.getSession(false);
        la.loggedIn(out, session);
        out.println("<h1>Registracija</h1>");
        out.print("<form action=\"Register\" method=\"post\">\n" +
                "\t<p>\n" +
                "    <label for=\"author_username\">Vardas</label><br>\n" +
                "    <input type=\"text\" name=\"username\" id=\"author_username\">\n" +
                "  </p>\n" +
                "      <p>\n" +
                "    <label for=\"author_email\">Elektroninis Paštas</label><br>\n" +
                "    <input type=\"text\" name=\"email\" id=\"author_email\">\n" +
                "  </p>\n" +
                "      <p>\n" +
                "    <label for=\"author_password\">Slaptažodis</label><br>\n" +
                "    <input type=\"password\" name=\"password\" id=\"author_password\">\n" +
                "  </p>\n" +
                "  <p>\n" +
                "    <label for=\"author_password_confirmation\">Slaptažodžio Patvirtinimas</label><br>\n" +
                "    <input type=\"password\" name=\"password_confirmation\" id=\"author_password_confirmation\">\n" +
                "  </p>\n" +
                "<input type=\"submit\" value=\"Siųsti\" class='btn'>\n" +
                "</form>");

        la.footer(out);
        out.close();

    }
}
