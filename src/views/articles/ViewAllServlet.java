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
import java.util.List;

/**
 * Created by Asta on 2016-10-17.
 */
@WebServlet("/View")
public class ViewAllServlet extends HttpServlet {
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


        out.println("<h1>Visi Straipsniai</h1>");
        ArticlesController ArticlesController = new ArticlesController();
        List<Article> list = ArticlesController.getAllArticles();


        out.print("<ul class=\"articles\">");
        for (Article e : list) {
            out.print("<li class= 'article'> <a href= 'ViewArticle?id=" + e.getId() + "'> " + e.getTitle() + "</a> </li>");
        }
        out.print("</ul>");
        la.footer(out);
        out.close();

    }
}
