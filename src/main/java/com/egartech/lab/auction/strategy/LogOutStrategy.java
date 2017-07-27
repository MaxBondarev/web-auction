package com.egartech.lab.auction.strategy;


import com.egartech.lab.auction.data.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutStrategy implements StrategyInterface  {

    final String LINK_INDEX = "/WEB-INF/jsp/index.jsp";

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
