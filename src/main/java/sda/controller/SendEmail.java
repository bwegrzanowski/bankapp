package sda.controller;

import sda.model.MailAndPassword;
import sda.model.SendMail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet(name = "SendEmail")
public class SendEmail extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = (String) request.getAttribute("token");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String to = request.getParameter("email");

        String subject = "Account activation";

//        test mail account and password (created on exercise purposes) hidden in another class MailAndPassword
        MailAndPassword mailAndPassword = new MailAndPassword();
        String user = mailAndPassword.getMailAccount();
        String pass = mailAndPassword.getMailPassword();
        SendMail.send(to,subject, user, pass, token);
        out.println("Mail send successfully");

        response.sendRedirect("activationMailSent.jsp");
    }
}

