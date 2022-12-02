package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Post;
import services.PostService;

import java.io.IOException;
import java.util.List;

@WebServlet("/posts")
public class PostsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostService service = new PostService();
        List<Post> posts = service.getAllPosts();

        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/WEB-INF/jsp/posts.jsp").forward(req, resp);
    }
}
