package com.egartech.lab.auction;

import com.egartech.lab.auction.commands.FrontCommand;
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
        req.setAttribute("login", req.getRequestURI().toString().replace("/",""));

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
            }else if(req.getRequestURI().equals("/LogOut")){
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
                    req.getRequestDispatcher("/WEB-INF/jsp/newlot.jsp").forward(req, resp);

            } else if(req.getRequestURI().equals("/CreateLot")) {
                //Validation
                String lotname = req.getParameter("name");
                lotname = lotname.replaceAll("[/\\s/]+", " ");
                lotname = lotname.trim();
                if (lotname.matches("") || lotname.matches(" ")) {
                    req.setAttribute("error", "The name must contain letters!");
                    req.getRequestDispatcher("/WEB-INF/jsp/newlot.jsp").forward(req, resp);
                } else if (!new LotService().isNameUnique(lotname)) {
                    req.setAttribute("error", "Error! Lot name " + lotname + " already exist!");
                    req.getRequestDispatcher("/WEB-INF/jsp/newlot.jsp").forward(req, resp);
                    //Creation
                } else {
                    try {
                        Lot lot = new Lot();
                        lot.setName(lotname);
                        LotService lotService = new LotService();
                        lotService.persist(lot);
                        ListCommand lc = new ListCommand();
                        lc.init(getServletContext(), req, resp);
                        lc.process();

                    } catch (Exception e) {
                        System.out.println(e.toString());
                        req.setAttribute("error", "Some error!" + e);
                        req.getRequestDispatcher("/WEB-INF/jsp/newlot.jsp").forward(req, resp);
                    }
                }
            //New Bet
            }else if (req.getRequestURI().equals("/NewBet")) {

                try {
                    Double betPrice = Double.parseDouble(req.getParameter("bet_price"));
                    if (betPrice == null || betPrice <= 0) {
                        req.setAttribute("error", "Error! The bet must be greater than zero!");
                        ListCommand lc = new ListCommand();
                        lc.init(getServletContext(), req, resp);
                        lc.process();
                    } else {
                        req.setAttribute("error", "");

                        Bet bet = new Bet();
                        bet.setPrice(betPrice);
                        HttpSession session = req.getSession();
                        User user = (User) session.getAttribute("user");
                        bet.setUser(user);

                        LotService lotService = new LotService();
                        Lot lot = lotService.findById(req.getParameter("lot_id"));
                        bet.setLot(lot);
                        BetService betService = new BetService();
                        betService.persist(bet, lot);
                        ListCommand lc = new ListCommand();
                        lc.init(getServletContext(), req, resp);
                        lc.process();
                    }
                } catch (Exception e){
                    req.setAttribute("error", "Error! Some error!");
                    System.out.println(e.toString());
                    ListCommand lc = new ListCommand();
                    lc.init(getServletContext(), req, resp);
                    lc.process();
                }
            //Other commands
            }else{
        FrontCommand command = getCommand(req);
        command.init(getServletContext(), req, resp);
        command.process();
            }

        } catch (Exception e) {
            new UnknownCommand();
        }
    }
}