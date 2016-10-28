package views.templates;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Created by Asta on 2016-10-17.
 */
public class Layout {

    public void loggedIn(PrintWriter out, HttpSession session) {
        out.print("<h6>");
        if (session != null) {
            String name = (String) session.getAttribute("name");
            int id = (Integer) session.getAttribute("id");
            out.print("<a href='ViewUserArticles?userId=" + id + "'>" + name + " </a>");
            out.print("<a href=\"/Logout\">| Atsijunk</a>");
            // out.print("<p><a href='ViewUserArticles?userId=" + id + "'>Mano Straipsniai</a></p>");           /// paimti id
        } else {
            out.print(" <a href=\"/LoginForm\">Prisijunk</a>");
        }
        out.print("</h6><hr>");
    }

    public void header(PrintWriter out) {
        out.print("<html>\n" +
                "<head>\n" +
                "  <title>Java blogas</title>\n" +
                "  <link rel=\"stylesheet\" media=\"all\" href=\"assets/stylesheets/screen.css\" data-turbolinks-track=\"reload\" />\n" +
                "  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans|Oswald:300,400|Roboto:100,300,400&subset=latin-ext\" rel=\"stylesheet\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "  <div class=\"header\">\n" +
                "    <h1 class=\"header-heading\">JAVA</h1>\n" +
                "  </div>\n" +
                "\n" +
                "  <div class=\"nav-bar\">\n" +
                "    <ul class=\"nav\">\n" +
                "      <li><a href=\"/View\">Straipsniai</a></li>\n");


        out.print("   <li><a href=\"/New\">Naujas</a></li>\n");

        out.print("      <li><a href=\"/index.html\">Indeksas</a></li>\n" +
                "    </ul>\n" +
                "  </div>\n" +
                "\n" +
                "  <div class=\"content\">");


    }

    public void footer(PrintWriter out) {
        // out.print(" <br/>\n" +
        //"    <a href=\"View\">Visi Straipsniai</a>");
        out.print("  </div>\n" +
                "\n" +
                "    <div class=\"footer\">\n" +
                "      &copy; Copyright 2016\n" +
                "    </div></div>");
    }
}
