package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Post;
import models.User;
import services.PostService;
import services.UserService;

import java.io.IOException;

@WebServlet("/posts/add")
public class AddPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/add_post.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        PostService service = new PostService();


        if (UserService.getAuthUser().isPresent()) {
            User authUser = UserService.getAuthUser().get();

            Post post = Post.builder()
                    .title(title)
                    .text(text)
                    .userId(authUser.getId())
                    .build();

            service.savePost(post);
        }

        resp.sendRedirect("/posts");

    }
}
