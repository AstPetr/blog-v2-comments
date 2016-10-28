package views.comments;

import controllers.CommentsController;
import models.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Asta on 2016-10-21.
 */
@WebServlet("/Comment")
public class CommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String body = request.getParameter("body");
        String spid = request.getParameter("parentId");

        int parentId = Integer.parseInt(spid);

        Comment a = new Comment();

        a.setName(name);
        a.setBody(body);
        a.setParentId(parentId);


        CommentsController commentsController = new CommentsController();

        int status = commentsController.save(a);
        if (status > 0) {
            response.sendRedirect("ViewArticle?id=" + parentId);
        } else {
            out.print("<p class='flash'>Atsiprašome! Nepavyko išsaugoti</p>");
            request.getRequestDispatcher("index.html").include(request, response);
        }

        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
