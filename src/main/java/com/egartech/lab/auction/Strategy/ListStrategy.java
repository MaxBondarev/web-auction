package com.egartech.lab.auction.Strategy;


import com.egartech.lab.auction.commands.FrontCommand;
import com.egartech.lab.auction.data.Lot;
import com.egartech.lab.auction.service.LotService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListStrategy implements StrategyInterface {

    @Override
    public void doLogic(
            ServletContext context,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession hSession = request.getSession();
        if (hSession.getAttribute("user") != null) {
            LotService lotService = new LotService();
            List<Lot> lots = lotService.findAll();
            request.setAttribute("lots", lots);
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp")
                    .forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp")
                    .forward(request, response);
        }
    }


}
