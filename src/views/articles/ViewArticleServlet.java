package views.articles;

import controllers.ArticlesController;
import controllers.CommentsController;
import controllers.UsersController;
import models.Article;
import models.Comment;
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
 * Created by Asta on 2016-10-18.
 */
@WebServlet("/ViewArticle")
public class ViewArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Layout la = new Layout();
        la.header(out);
        HttpSession session = request.getSession(false);
        la.loggedIn(out, session);

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        ArticlesController articlesController = new ArticlesController();
        UsersController usersController = new UsersController();
        Article a = articlesController.getArticleById(id);
        User u = usersController.getUserById(a.getUserId());

        out.println("<h1>" + a.getTitle() + "</h1>");
        out.print("<p>Straipsnį sukūrė <b><a href='ViewUserArticles?userId=" + u.getId() + "'>" + u.getName() + "</a></b></p>");
        out.print("<p>" + a.getBody() + "</p>");


        // comments
        out.println("<hr>");
        out.print("<h3>Komentarai</h3>");

        CommentsController commentsController = new CommentsController();
        List<Comment> list = commentsController.getAllCommentsByParentId(a.getId());
        out.print("<div>");
        for (Comment c : list) {
            out.print("<h4>" + c.getName() + " komentaras</h4>");
            // out.print("<p>"+ c.get);
            out.print("<p class= 'comment' >" + c.getBody() + "</p>");
        }
        out.print("</div>");

        out.println("<hr>");
        out.print("<h3>Rašyk Komentarą</h3>\n" +
                "<form class=\"new_comment\" id=\"new_comment\" action=\"/Comment\" accept-charset=\"UTF-8\" method=\"post\">\n" +
                "  <p>\n" +
                "        <input type='hidden' name='parentId' value='" + a.getId() + "'/>" +
                "    <label for=\"comment_author_name\">Tavo Vardas</label><br>\n" +
                "    <input type=\"text\" name=\"name\" id=\"name\">\n" +
                "  </p>\n" +
                "  <p>\n" +
                "    <label for=\"comment_body\">Tavo Komentaras</label><br>\n" +
                "    <textarea name=\"body\" id=\"body\"></textarea>\n" +
                "  </p>\n" +
                "  <p>\n" +
                "    <input type=\"submit\" name=\"commit\" value=\"Komentuoti\" class=\"btn\">\n" +
                "  </p>\n" +
                "</form>");

        // comments end
        out.println("<hr>");

        if (session != null) {
            String name = (String) session.getAttribute("name");
            if (u.getName().equals(name)) {
                out.print("<a href= 'UpdateForm?id=" + a.getId() + "'> Redaguoti </a>");
                out.print(" | ");
                out.print("<a href= 'Delete?id=" + a.getId() + "'> Ištrinti </a>");
            }
        }
        la.footer(out);
        out.close();
    }
}
