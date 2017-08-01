package com.egartech.lab.auction.strategy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class LogOutStrategy is a strategy for the end of user session from any page.
 * Implements {@link StrategyInterface}.
 *
 * @author Max Bondarev.
 */
public class LogOutStrategy implements StrategyInterface  {

    final String LINK_INDEX = "/WEB-INF/jsp/index.jsp";

    /**
     * Method of performing logic. Delete User data from session and redirect
     * on index.jsp.
     * @param context
     * @param request
     * @param response
     */
    public void doLogic(
            ServletContext context,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        request.getRequestDispatcher(LINK_INDEX)
                .forward(request, response);
    }
}
