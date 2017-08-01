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

/**
 * Class ListStrategy is a strategy for show all lots and redirect to the
 * appropriate page. Implements {@link StrategyInterface}.
 *
 * @author Max Bondarev.
 */
public class LoginStrategy implements StrategyInterface {

    final String LINK_INDEX = "/WEB-INF/jsp/index.jsp";

    /**
     * Method of performing logic. Validation of login and password,
     * create {@link User}, add User in session, redirect to
     * {@link ListCommand}. If error, redirect back on index.jsp.
     * @param context
     * @param request
     * @param response
     */
    @Override
    public void doLogic(
            ServletContext context,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

            //Validation
            String login= request.getParameter("login");
            String password= request.getParameter("password");

            if (Validator.checkAuth(login, password, request)) {
                User user = new UserService().findByLogin(login);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                //Forward List
                ListCommand lc = new ListCommand(context, request, response);
                lc.process();
            } else {
                request.getRequestDispatcher(LINK_INDEX)
                        .forward(request, response);
            }
    }
}
