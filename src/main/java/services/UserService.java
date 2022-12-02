package services;

import jakarta.servlet.http.HttpSession;
import listeners.InitListener;
import models.User;
import repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static final UserRepository repository = new UserRepository();

    public static boolean isPostBelongsToUser(Long userID) {
        return (getAuthUser().isPresent() && getAuthUser().get().getId().equals(userID))
                || (getAuthUser().isPresent() && getAuthUser().get().getRole().equals("admin"));
    }

    public static Optional<User> getAuthUser() {
        HttpSession session = InitListener.getSession();

        if (session.getAttribute("authUser") != null) {
            User user = (User) session.getAttribute("authUser");
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    public User getUser(Long id) {
        Optional<User> user = repository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new IllegalArgumentException("No such user");
        }
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
