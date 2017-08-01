package com.egartech.lab.auction.strategy;

import com.egartech.lab.auction.commands.ListCommand;
import com.egartech.lab.auction.data.Bet;
import com.egartech.lab.auction.data.Lot;
import com.egartech.lab.auction.data.User;
import com.egartech.lab.auction.service.BetService;
import com.egartech.lab.auction.service.LotService;
import com.egartech.lab.auction.validation.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class NewBetStrategy is a strategy for create a bet and redirect to the
 * appropriate page. Implements {@link StrategyInterface}.
 *
 * @author Max Bondarev.
 */
public class NewBetStrategy implements StrategyInterface {

    @Override
    public void doLogic(
            ServletContext context,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        if (Validator.checkBet(request)) {
            Double betPrice = Double.parseDouble(
                    request.getParameter("bet_price"));
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            LotService lotService = new LotService();
            Lot lot = lotService.findById(
                    request.getParameter("lot_id"));
            Bet bet = new Bet(betPrice, user, lot);
            BetService betService = new BetService();
            betService.save(bet, lot);
        }

        ListCommand lc = new ListCommand(context, request, response);
        lc.process();
    }
}
