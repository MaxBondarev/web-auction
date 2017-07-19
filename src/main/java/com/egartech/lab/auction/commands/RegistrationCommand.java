package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.data.Lot;
import com.egartech.lab.auction.data.User;
import com.egartech.lab.auction.service.LotService;
import com.egartech.lab.auction.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    }

    @Override
    public void process() throws ServletException, IOException {
        
        try {
            //Validation
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            //Login validation
            login = login.replaceAll("[/\\s/]+", "");
            login = login.trim();

            if  (login.matches("") || login.matches(" ")) {
                request.setAttribute("loginError", "Login must contain letters!");
                forward("checkin");
            //If user with such login exist
            } else if (new UserService().findByLogin(login) == null) {
                request.setAttribute("loginError", "Error! Login " + login + " already exist!");
                forward("checkin");
            }
            //Password validation
            password = password.replaceAll("[/\\s/]+", " ");
            password = password.trim();
            if  (password == null || password.matches("") || password.matches(" ")) {
                request.setAttribute("pasError", "Password should not contain spaces and must contain letters!");
                forward("checkin");
            }

            //Create new user
            User newUser = new User();
            newUser.setLogin(login);
            newUser.setPassword(password);

            //Save to DB
            UserService userService = new UserService();
            userService.persist(newUser);

            //Save to session
            HttpSession session = request.getSession();
            session.setAttribute("user", newUser);

            //Redirect
            ListCommand lc = new ListCommand();
            lc.init(context, request, response);
            lc.process();

        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
}

