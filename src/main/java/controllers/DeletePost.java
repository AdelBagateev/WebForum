package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CommentService;
import services.PostService;

import java.io.IOException;

@WebServlet("/posts/delete")
public class DeletePost extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long postID = Long.parseLong(req.getParameter("postId"));
        PostService postService = new PostService();
        CommentService commentService = new CommentService();

        commentService.getAllComments().forEach(comment -> {
            if (comment.getPostID().equals(postID)) {
                commentService.deleteComment(comment.getId());
            }
        });

        postService.deletePost(postID);
        resp.sendRedirect("/posts");
    }
}
