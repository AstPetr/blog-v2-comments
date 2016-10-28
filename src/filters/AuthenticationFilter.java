package filters;

import views.articles.SaveServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Asta on 2016-10-21.
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        if (       (session == null && uri.endsWith("UpdateForm"))
                || (session == null && uri.endsWith("Update"))
                || (session == null && uri.endsWith("Delete"))
                || (session == null && uri.endsWith("New"))
                || (session == null && uri.endsWith("Save"))) {
            res.sendRedirect("LoginForm");
        } else if ((session != null && uri.endsWith("LoginForm"))
                || (session != null && uri.endsWith("RegisterForm"))) {
            res.sendRedirect("View");
        } else {
            chain.doFilter(request, response);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
