package views.articles;

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
 * Created by Asta on 2016-10-21.
 */
@WebServlet("/New")
public class NewFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Layout la = new Layout();
        la.header(out);


        //Session tikrinimas
        HttpSession session = request.getSession(false);
        la.loggedIn(out, session);


        out.println(" <h1>Sukurk Naują Straipsnį</h1>");
        out.print("<form action=\"Save\" accept-charset=\"UTF-8\" method=\"post\">\n" +
                "\n" +
                "      <p>\n" +
                "        <input type='hidden' name='userId' value='" + session.getAttribute("id") + "'/>" +
                "        <label for=\"article_title\">Pavadinimas</label><br />\n" +
                "        <input type=\"text\" name=\"title\" id=\"article_title\" />\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        <label for=\"article_body\">Tekstas</label><br />\n" +
                "        <textarea name=\"body\" id=\"article_body\"></textarea>\n" +
                "      </p>\n" +
                "\n" +
                "\n" +
                "      <p>\n" +
                "        <input type=\"submit\" name=\"commit\" value=\"Atnaujinti\" class=\"btn\"/>\n" +
                "      </p>\n" +
                "\n" +
                "\n" +
                "    </form>");

        la.footer(out);
        out.close();
    }
}
