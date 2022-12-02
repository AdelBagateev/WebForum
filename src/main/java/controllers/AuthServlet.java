package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import services.UserService;
import utils.MD5HashFunction;

import java.io.IOException;
import java.util.List;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = MD5HashFunction.hashPassword(req.getParameter("password"));

        UserService service = new UserService();
        List<User> users = service.getAllUsers();

        for (User user : users) {
            if (user.getName().equals(name)) {
                if (user.getPassword().equals(password)) {
                    req.getSession().setAttribute("authUser", user);
                    resp.sendRedirect("/home");
                    return;
                }
            }
        }

        resp.sendRedirect("/auth");
    }
}
