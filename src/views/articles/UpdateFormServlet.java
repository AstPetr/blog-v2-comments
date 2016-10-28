package views.articles;

import controllers.ArticlesController;
import models.Article;
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
 * Created by Asta on 2016-10-18.
 */
@WebServlet("/UpdateForm")
public class UpdateFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Layout la = new Layout();
        la.header(out);
        HttpSession session = request.getSession(false);
        la.loggedIn(out, session);

        out.println("<h1>Redaguok Straipsnį</h1>");
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        ArticlesController articlesController = new ArticlesController();
        Article a = articlesController.getArticleById(id);

        out.print(
                "    <form action=\"Update\" accept-charset=\"UTF-8\" method=\"post\">\n" +
                        "\n" +
                        "      <p>\n" +
                        "        <input type='hidden' name='id' value='" + a.getId() + "'/>" +
                        "        <input type='hidden' name='created_at' value='" + a.getCreated_at() + "'/>" +
                        "        <label for=\"article_title\">Pavadinimas</label><br />\n" +
                        "        <input type=\"text\" name=\"title\" id=\"article_title\" value='" + a.getTitle() + "'/>\n" +
                        "      </p>\n" +
                        "      <p>\n" +
                        "        <label for=\"article_body\">Tekstas</label><br />\n" +
                        "        <textarea name=\"body\" id=\"article_body\">" + a.getBody() + "</textarea>\n" +
                        "      </p>\n" +
                        "\n" +
                        "\n" +
                        "      <p>\n" +
                        "        <input type=\"submit\" name=\"commit\" value=\"Atnaujinti\" class=\"btn\"/>\n" +
                        "      </p>\n" +
                        "\n" +
                        "\n" +
                        "    </form>\n" +
                        "\n" +
                        "    <br/>\n" +
                        "    <a href=\"View\">Visi Straipsniai</a>"
        );

        la.footer(out);
        out.close();

    }
}
