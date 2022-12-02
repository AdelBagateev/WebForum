package services;

import models.Post;
import models.User;
import repositories.PostRepository;

import java.util.List;
import java.util.Optional;

public class PostService {
    private static final PostRepository repository = new PostRepository();

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public void savePost(Post post) {
        repository.save(post);
    }

    public Post getPost(Long id) {
        Optional<Post> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new IllegalArgumentException("No such post exception");
        }
    }

    public void updatePost(Post post) {
        repository.update(post);
    }

    public void deletePost(Long id) {
        repository.delete(id);
    }
}
