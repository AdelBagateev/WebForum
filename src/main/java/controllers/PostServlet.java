package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Comment;
import models.Post;
import models.User;
import services.CommentService;
import services.PostService;
import services.UserService;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/posts/*")
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long postId = Long.parseLong(req.getPathInfo().substring(1));
            PostService service = new PostService();
            CommentService commentService = new CommentService();
            Post post = service.getPost(postId);

            List<Comment> comments = commentService.getAllComments().stream()
                    .filter(comment -> comment.getPostID().equals(postId))
                    .collect(Collectors.toList());

            req.setAttribute("post", post);
            req.setAttribute("comments", comments);
            req.getRequestDispatcher("/WEB-INF/jsp/post.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");
        Long postID = Long.parseLong(req.getPathInfo().substring(1));
        Optional<User> user = UserService.getAuthUser();
        CommentService service = new CommentService();

        if (user.isPresent()) {
            Comment comment = Comment.builder()
                    .text(text)
                    .userID(user.get().getId())
                    .postID(postID)
                    .build();

            service.saveComment(comment);
        }

        resp.sendRedirect("/posts/" + postID);
    }
}
