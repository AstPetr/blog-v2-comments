package views.articles;

import controllers.ArticlesController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Asta on 2016-10-19.
 */
@WebServlet("/Delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        ArticlesController articlesController = new ArticlesController();
        int status = articlesController.delete(id);
        if (status > 0) {
            out.print("<p class='flash'>Straipsnis ištrintas!</p>");
            request.getRequestDispatcher("View").include(request, response);
        } else {
            out.print("<p class='flash'>Atsiprašome! Nepavyko ištrinti</p>");
            request.getRequestDispatcher("View").include(request, response);
        }

        out.close();


    }
}
