package sda.controller;

import sda.model.User;
import sda.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "AccountActivationServlet", urlPatterns = "/activateUser")
public class AccountActivationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String token = request.getParameter("token");

        Optional<User> optionalUser = UserRepository.findUserBySecurityToken(token);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActivated(true);
            user.setActivationToken(null);
            UserRepository.saveOrUpdateUser(user);
            response.sendRedirect(request.getContextPath() + "/");
        }else{
            response.sendError(404);
        }
    }
}
