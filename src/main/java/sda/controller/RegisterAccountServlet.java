package sda.controller;

import org.apache.commons.lang3.RandomStringUtils;
import sda.model.BankAccount;
import sda.model.User;
import sda.repository.BankAccountRepository;
import sda.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.SecureRandom;

@WebServlet(name = "RegisterAccountServlet", urlPatterns = "/registerAccount")
public class RegisterAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String randomAccountNumber = RandomStringUtils.randomNumeric(26);
        while (!BankAccountRepository.isAccountNumberIsUnique(randomAccountNumber)) {
            randomAccountNumber = RandomStringUtils.randomNumeric(26);
        }
        BankAccount account = BankAccount.builder()
                .accountNumber(randomAccountNumber)
                .availableMoney(BigDecimal.ZERO)
                .balance(BigDecimal.ZERO)
                .currency("PLN")
                .build();
        BankAccountRepository.saveAccount(account);
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .account(account)
                .activationToken(RandomStringUtils.randomAlphanumeric(20))
                .build();

        UserRepository.saveUser(user);
        String token = user.getActivationToken();
        request.setAttribute("token", token);

        request.getRequestDispatcher("/SendEmail").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
