package views.articles;

import controllers.ArticlesController;
import models.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 * Created by Asta on 2016-10-17.
 */
@WebServlet("/Save")
public class SaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String title = request.getParameter("title");
        String body = request.getParameter("body");
        String uid = request.getParameter("userId");
        int userId = Integer.parseInt(uid);

        Article a = new Article();

        a.setTitle(title);
        a.setBody(body);
        a.setCreated_at(LocalDate.now().toString());
        a.setUpdated_at(LocalDate.now().toString());
        a.setUserId(userId);

        ArticlesController articlesController = new ArticlesController();

        int status = articlesController.save(a);
        if (status > 0) {
            out.print("<p class='flash'>Straipsnis išsaugotas!</p>");
            request.getRequestDispatcher("index.html").include(request, response);
        } else {
            out.print("<p class='flash'>Atsiprašome! Nepavyko išsaugoti</p>");
            request.getRequestDispatcher("index.html").include(request, response);
        }

        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
