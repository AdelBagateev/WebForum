package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.User;
import services.UserService;
import utils.MD5HashFunction;

import java.io.IOException;

@WebServlet(name = "RegServlet", value = "/reg")
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/reg.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = MD5HashFunction.hashPassword(request.getParameter("password"));

        UserService service = new UserService();

        boolean alreadyExists = service.getAllUsers().stream()
                .anyMatch(user -> user.getName().equals(name));

        if (alreadyExists) {
            response.sendRedirect("/reg");
            return;
        }

        User user = User.builder()
                .name(name)
                .password(password)
                .build();

        service.saveUser(user);
        response.sendRedirect("/auth");
    }
}
