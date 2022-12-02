package filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.UserService;

import java.io.IOException;

@WebFilter(urlPatterns = "/posts/add")
public class AddPostFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        if (UserService.getAuthUser().isEmpty()) {
            res.sendRedirect("/home");
            return;
        }

        chain.doFilter(req, res);
    }
}
