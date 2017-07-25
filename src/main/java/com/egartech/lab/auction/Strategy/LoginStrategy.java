package com.egartech.lab.auction.Strategy;

import com.egartech.lab.auction.commands.ListCommand;
import com.egartech.lab.auction.data.User;
import com.egartech.lab.auction.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginStrategy implements StrategyInterface {

    @Override
    public void doLogic(
            ServletContext context,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            //Validation
            String login= request.getParameter("login");
            String password= request.getParameter("password");

            //Login validation
            login = login.replaceAll("[/\\s/]+", "");
            login = login.trim();

            if (login == null || login.matches("") || login.matches(" ")) {
                request.setAttribute("loginError",
                        "Login must contain letters!");
                request.getRequestDispatcher("/WEB-INF/jsp/index.jsp")
                        .forward(request, response);

                //If user with such login exist
            } else if (new UserService().findByLogin(login) == null) {
                request.setAttribute("loginError",
                        "Error! Login " + login + " does not exist!");
                request.getRequestDispatcher("/WEB-INF/jsp/index.jsp")
                        .forward(request, response);
            }
            User user = new UserService().findByLogin(login);

            //Password validation
            password = password.replaceAll("[/\\s/]+", " ");
            password = password.trim();
            if (password == null || password.matches("")
                    || password.matches(" ")) {
                request.setAttribute("pasError", "Password should not " +
                        "contain spaces and must contain letters!");
                request.getRequestDispatcher("/WEB-INF/jsp/index.jsp")
                        .forward(request, response);
            } else if (!user.getPassword().equals(password)) {
                request.setAttribute("pasError",
                        "Login or password incorrect!");
                request.getRequestDispatcher("/WEB-INF/jsp/index.jsp")
                        .forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                //Forward List
                ListCommand lc = new ListCommand();
                lc.init(context, request, response);
                lc.process();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            request.setAttribute("pasError", "Some error!");
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp")
                    .forward(request, response);
        }
    }
}
