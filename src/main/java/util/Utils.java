package util;

import services.UserService;

public class Utils {
    public static String getUsernameById(Long postId) {
        UserService service = new UserService();
        return service.getUser(postId).getName();
    }
}
