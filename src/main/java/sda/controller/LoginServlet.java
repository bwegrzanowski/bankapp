package sda.controller;

import sda.model.User;
import sda.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");


        session.setMaxInactiveInterval(600);
        if (request.getParameter("rememberMe") != null) {
            session.setMaxInactiveInterval(3600);
        }
        Optional<User> userByEmail = UserRepository.findUserByEmail(email);

        if (password.equals(userByEmail.get().getPassword())) {
            User user = User.builder()
                    .firstName(userByEmail.get().getFirstName())
                    .lastName(userByEmail.get().getLastName())
                    .account(userByEmail.get().getAccount())
                    .build();
            session.setAttribute("user", user);
//            request.getRequestDispatcher("/account.jsp").forward(request, response);
            response.sendRedirect("/account.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
