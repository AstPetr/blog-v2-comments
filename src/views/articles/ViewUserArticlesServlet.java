package views.articles;

import controllers.ArticlesController;
import controllers.UsersController;
import models.Article;
import models.User;
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
 * Created by Asta on 2016-10-21.
 */
@WebServlet("/ViewUserArticles")
public class ViewUserArticlesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Layout la = new Layout();
        la.header(out);
        HttpSession session = request.getSession(false);
        la.loggedIn(out, session);

        String sid = request.getParameter("userId");
        int userId = Integer.parseInt(sid);
        UsersController usersController = new UsersController();
        User u = usersController.getUserById(userId);

        out.println("<h1>" + u.getName() + " straipsniai</h1>");
        ArticlesController ArticlesController = new ArticlesController();
        List<Article> list = ArticlesController.getAllArticlesIdByUserId(userId);

        out.print("<ul class=\"articles\">");
        for (Article a : list) {
            out.print("<li class= 'article'> <a href= 'ViewArticle?id=" + a.getId() + "'> " + a.getTitle() + "</a> </li>");
        }
        out.print("</ul>");
        la.footer(out);
        out.close();

    }
}
