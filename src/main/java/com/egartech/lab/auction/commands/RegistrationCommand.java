package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.data.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand extends FrontCommand {
    User testUser;

    @Override
    public void init(
            ServletContext servletContext,
            HttpServletRequest req,
            HttpServletResponse resp
    ) {
        this.context = servletContext;
        this.request = req;
        this.response = resp;

        testUser = new User("admin", "admin");
    }

    @Override
    public void process() throws ServletException, IOException {

    String username= request.getParameter("login");
    String password= request.getParameter("password");

        System.out.println(username);
        System.out.println(testUser.getLogin());
        if (testUser.getLogin().equals(username)){
            request.setAttribute("error", "Error! User " + username + " already exist!");
            forward("checkin");
        } else {
            request.setAttribute("log", username);
            request.setAttribute("pas", password);
            forward("registration");
        }
    }
}
