package com.egartech.lab.auction.strategy;

import com.egartech.lab.auction.commands.ListCommand;
import com.egartech.lab.auction.data.User;
import com.egartech.lab.auction.service.UserService;
import com.egartech.lab.auction.validation.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class RegistrationStrategy implements StrategyInterface {

    final String CHECKIN = "/WEB-INF/jsp/checkin.jsp";

    @Override
    public void doLogic(
            ServletContext context,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

            //Validation
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            if (Validator.checkReg(login, password, request)) {
                User newUser = new User(login, password);
                UserService userService = new UserService();
                userService.save(newUser);

                HttpSession session = request.getSession();
                session.setAttribute("user", newUser);

                //Forward List
                ListCommand lc = new ListCommand(context, request, response);
                lc.process();
            } else {
                request.getRequestDispatcher(CHECKIN)
                        .forward(request, response);
            }
    }
}
