package services;

import models.Comment;
import repositories.CommentsRepository;

import java.util.List;

public class CommentService {
    private static final CommentsRepository repository = new CommentsRepository();

    public void saveComment(Comment comment) {
        repository.save(comment);
    }

    public List<Comment> getAllComments() {
        return repository.findAll();
    }

    public void deleteComment(Long id) {
        repository.delete(id);
    }

}
