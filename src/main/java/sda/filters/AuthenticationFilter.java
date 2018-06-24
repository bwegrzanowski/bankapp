package sda.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//
@WebFilter(urlPatterns = "/account.jsp")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            chain.doFilter(httpRequest, response);

        } else {
            PrintWriter writer = response.getWriter();
            writer.write("User do not have an active session");
        }


    }

    @Override
    public void destroy() {

    }
}
