package com.egartech.lab.auction;

import com.egartech.lab.auction.commands.FrontCommand;
import com.egartech.lab.auction.commands.NewBetCommand;
import com.egartech.lab.auction.commands.NewLotCommand;
import com.egartech.lab.auction.commands.ListCommand;
import com.egartech.lab.auction.commands.UnknownCommand;
import com.egartech.lab.auction.data.Bet;
import com.egartech.lab.auction.data.Lot;
import com.egartech.lab.auction.data.User;
import com.egartech.lab.auction.service.BetService;
import com.egartech.lab.auction.service.LotService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FrontControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("login",
                req.getRequestURI().toString().replace("/",""));
        FrontCommand command = getCommand(req);
        command.init(getServletContext(), req, resp);
        command.process();
    }

    private FrontCommand getCommand(HttpServletRequest req) {
        try {
            if (req.getRequestURI().equals("/")) {
                Class type = Class.forName(
                        "com.egartech.lab.auction.commands.IndexCommand");
                return (FrontCommand) type
                        .asSubclass(FrontCommand.class)
                        .newInstance();
            } else if (req.getRequestURI().equals("/LogOut")) {
                HttpSession session = req.getSession();
                session.setAttribute("user", null);
                Class type = Class.forName(
                        "com.egartech.lab.auction.commands.IndexCommand");
                return (FrontCommand) type
                        .asSubclass(FrontCommand.class)
                        .newInstance();
            } else {
                Class type = Class.forName(
                        String.format(
                                "com.egartech.lab.auction.commands.%sCommand",
                                req.getRequestURI().toString().replace("/","")
                        )
                );
                return (FrontCommand) type
                        .asSubclass(FrontCommand.class)
                        .newInstance();
            }
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {

            //New Lot
            if (req.getRequestURI().equals("/NewLot")) {
                NewLotCommand command = new NewLotCommand();
                command.init(getServletContext(), req, resp);
                command.process();
            }

            //New Bet
             else if (req.getRequestURI().equals("/NewBet")) {
                NewBetCommand command = new NewBetCommand();
                command.init(getServletContext(), req, resp);
                command.process();

            //Other commands
            } else {
                FrontCommand command = getCommand(req);
                command.init(getServletContext(), req, resp);
                command.process();
            }
        } catch (Exception e) {
            new UnknownCommand();
        }
    }
}