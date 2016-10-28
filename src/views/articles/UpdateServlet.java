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
 * Created by Asta on 2016-10-19.
 */
@WebServlet("/Update")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        String created_at = request.getParameter("created_at");
        String updated_at = LocalDate.now().toString();

        Article a = new Article();

        a.setId(id);
        a.setTitle(title);
        a.setBody(body);
        a.setCreated_at(created_at);
        a.setUpdated_at(updated_at);

        ArticlesController articlesController = new ArticlesController();

        int status= articlesController.update(a);
        if(status>0){
            //out.print("<p class='flash'>Straipsnis atnaujintas!</p>");
            response.sendRedirect("ViewArticle?id="+a.getId());
        }else{
            out.print("<p class='flash'>Atsiprašome! Nepavyko atnaujinti</p>");
            request.getRequestDispatcher("index.html").include(request, response);
        }

        out.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
