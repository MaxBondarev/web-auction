package com.egartech.lab.auction;

import com.egartech.lab.auction.commands.FrontCommand;
import com.egartech.lab.auction.commands.ListCommand;
import com.egartech.lab.auction.commands.UnknownCommand;
import com.egartech.lab.auction.dao.LotDao;
import com.egartech.lab.auction.data.Bet;
import com.egartech.lab.auction.data.Lot;
import com.egartech.lab.auction.data.User;
import com.egartech.lab.auction.service.BetService;
import com.egartech.lab.auction.service.LotService;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.hibernate.Hibernate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.*;


public class FrontControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("login", req.getRequestURI().toString().replace("/",""));
        //req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);

        FrontCommand command = getCommand(req);
        command.init(getServletContext(), req, resp);
        command.process();
    }

    private FrontCommand getCommand(HttpServletRequest req) {
        try {
            if (req.getRequestURI().equals("/")){
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

            }else if(req.getRequestURI().equals("/CreateLot")) {
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
                    System.out.println(betPrice);
                    if (betPrice == null || betPrice <= 0) {
                        req.setAttribute("error", "Error! The bet must be greater than zero!");
                        ListCommand lc = new ListCommand();
                        lc.init(getServletContext(), req, resp);
                        lc.process();
                        //req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("error", "");
                        System.out.println("else");
                        Bet bet = new Bet();
                        bet.setPrice(betPrice);
                        HttpSession session = req.getSession();
                        User user = (User) session.getAttribute("user");
                        bet.setUser(user);

                        LotService lotService = new LotService();
                        Lot lot = lotService.findById(req.getParameter("lot_id"));
                        bet.setLot(lot);
                        BetService betService = new BetService();
                        betService.persist(bet);
                        ListCommand lc = new ListCommand();
                        lc.init(getServletContext(), req, resp);
                        lc.process();
                        //req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);
                    }
                } catch (Exception e){
                    req.setAttribute("error", "Error! The bet must be greater than zero!");
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

    /*
      generate the page showing all the request parameters
    */
    /*
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(200);

        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        // Get the values of all request parameters
        Enumeration en = request.getParameterNames();
        while (en.hasMoreElements()) {
            // Get the name of the request parameter
            String name = (String) en.nextElement();
            out.println(name);

            // Get the value of the request parameter
            String value = request.getParameter(name);

            // If the request parameter can appear more than once in the query string, get all values
            String[] values = request.getParameterValues(name);

            for (int i = 0; i < values.length; i++) {
                out.println(" " + values[i]);
            }
        }
        out.close();
        }


        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username= req.getParameter("login");
        String password= req.getParameter("password");

        req.setAttribute("log", username);
        req.setAttribute("pas", password);
        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
        //super.doPost(req, resp);
        //this.process(req, resp);
    }
        */
    }