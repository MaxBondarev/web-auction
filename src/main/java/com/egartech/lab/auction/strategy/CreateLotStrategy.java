package com.egartech.lab.auction.strategy;


import com.egartech.lab.auction.commands.ListCommand;
import com.egartech.lab.auction.data.Lot;
import com.egartech.lab.auction.service.LotService;
import com.egartech.lab.auction.validation.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateLotStrategy implements StrategyInterface {

    final String LINK_NEW_LOT = "/WEB-INF/jsp/newlot.jsp";

    @Override
    public void doLogic(
            ServletContext context,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        //Validation
        String lotname = request.getParameter("name");

        if(Validator.checkNewLot(lotname, request)){
            Lot lot = new Lot(lotname);
            LotService lotService = new LotService();
            lotService.save(lot);
            ListCommand lc = new ListCommand(context, request, response);
            lc.process();
        } else {
            request.getRequestDispatcher(LINK_NEW_LOT)
                    .forward(request, response);
        }
    }

}
