package com.egartech.lab.auction.commands;


import com.egartech.lab.auction.data.User;
import com.egartech.lab.auction.service.UserService;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginCommand extends FrontCommand  {

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

    public void process() throws ServletException, IOException {

        try {
            //Validation
            String login= request.getParameter("login");
            String password= request.getParameter("password");

            //Login validation
            login = login.replaceAll("[/\\s/]+", "");
            login = login.trim();

            if  (login == null || login.matches("") || login.matches(" ")) {
                request.setAttribute("loginError", "Login must contain letters!");
                forward("index");
                //If user with such login exist
            } else if (new UserService().findByLogin(login) == null) {
                request.setAttribute("loginError", "Error! Login " + login + " does not exist!");
                forward("index");
            }

            User user = new UserService().findByLogin(login);

            //Password validation
            password = password.replaceAll("[/\\s/]+", " ");
            password = password.trim();
            if  (password == null || password.matches("") || password.matches(" ")) {
                request.setAttribute("pasError", "Password should not contain spaces and must contain letters!");
                forward("index");
            } else if(!user.getPassword().equals(password)){
                request.setAttribute("pasError", "Login or password incorrect!");
                forward("index");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                ListCommand lc = new ListCommand();
                lc.init(context, request, response);
                lc.process();
            }
        } catch (Exception e){
            System.out.println(e.toString());
            request.setAttribute("pasError", "Some error!");
            forward("index");
        }
    }

}