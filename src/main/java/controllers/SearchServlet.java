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
import java.util.stream.Collectors;

@WebServlet("/posts/find")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        PostService service = new PostService();
        List<Post> posts =  service.getAllPosts();

        req.setAttribute("posts", posts.stream().filter(post -> post.getTitle().contains(query)).collect(Collectors.toList()));
        req.getRequestDispatcher("/WEB-INF/jsp/posts.jsp").forward(req, resp);
    }
}
