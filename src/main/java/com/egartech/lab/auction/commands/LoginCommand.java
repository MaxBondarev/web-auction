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
import java.util.List;


public class LoginCommand extends FrontCommand  {

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

        //testUser = new User("admin", "admin");
    }

    public void process() throws ServletException, IOException {

        String login= request.getParameter("login");
        String password= request.getParameter("password");

        UserService userService = new UserService();
        User user = userService.findByLogin(login);
        if (user != null && user.getLogin().equals(login) && user.getPassword().equals(password)){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            forward("list");
        } else {
            request.setAttribute("error", "Error! Login or password incorrect!");
            forward("index");
        }

        //serviceCall();

        /*
        System.out.println(testUser.getLogin());
        if (testUser.getLogin().equals(username) & testUser.getPassword().equals(password) ){
            request.setAttribute("log", username);
            request.setAttribute("pas", password);
            forward("list");
        } else {
            request.setAttribute("error", "Error! Login or password is incorrect!");
            forward("index");
        }
        */
        forward("list");
    }

}