package com.egartech.lab.auction.Strategy;

import com.egartech.lab.auction.commands.ListCommand;
import com.egartech.lab.auction.data.Bet;
import com.egartech.lab.auction.data.Lot;
import com.egartech.lab.auction.data.User;
import com.egartech.lab.auction.service.BetService;
import com.egartech.lab.auction.service.LotService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class NewBetStrategy implements StrategyInterface {

    @Override
    public void doLogic(
            ServletContext context,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            Double betPrice = Double.parseDouble(
                    request.getParameter("bet_price")
            );
            if ((betPrice == null) || (betPrice <= 0)) {
                request.setAttribute("error",
                        "Error! The bet must be greater than zero!");
                ListCommand lc = new ListCommand();
                lc.init(context, request, response);
                lc.process();
            } else {
                request.setAttribute("error", "");
                Bet bet = new Bet();
                bet.setPrice(betPrice);
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                bet.setUser(user);
                LotService lotService = new LotService();
                Lot lot = lotService.findById(
                        request.getParameter("lot_id"));
                bet.setLot(lot);
                BetService betService = new BetService();
                betService.save(bet, lot);
                ListCommand lc = new ListCommand();
                lc.init(context, request, response);
                lc.process();
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error! Some error!");
            System.out.println(e.toString());
            ListCommand lc = new ListCommand();
            lc.init(context, request, response);
            lc.process();
        }
    }
}
