package com.egartech.lab.auction.strategy;


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

    final String LINK_LIST = "/WEB-INF/jsp/list.jsp";
    final String LINK_INDEX = "/WEB-INF/jsp/index.jsp";

    @Override
    public void doLogic(
            ServletContext context,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
            LotService lotService = new LotService();
            List<Lot> lots = lotService.findAll();
            request.setAttribute("lots", lots);
            request.getRequestDispatcher(LINK_LIST)
                    .forward(request, response);
    }


}
