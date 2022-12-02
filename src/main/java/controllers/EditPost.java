package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Post;
import services.PostService;

import java.io.IOException;

@WebServlet("/posts/edit")
public class EditPost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PostService service = new PostService();
            Long postID = Long.parseLong(req.getParameter("postId"));
            Post post =  service.getPost(postID);

            req.setAttribute("post", post);
            req.getRequestDispatcher("/WEB-INF/jsp/edit_post.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        Long postID = Long.parseLong(req.getParameter("postId"));
        PostService service = new PostService();

        Post post = service.getPost(postID);
        post.setTitle(title);
        post.setText(text);

        service.updatePost(post);
        resp.sendRedirect("/posts");
    }
}
