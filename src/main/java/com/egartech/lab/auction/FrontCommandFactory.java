package com.egartech.lab.auction;

import com.egartech.lab.auction.commands.*;
import com.egartech.lab.auction.commands.FrontCommand;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class FrontCommandFactory returns the servlet corresponding to the query.
 *
 * @author Max Bondarev.
 */
public class FrontCommandFactory {

    /**
     * Returns the servlet corresponding to the query.
     * @param con
     * @param req
     * @param resp
     * @return instance of executing servlet
     */
    public static FrontCommand getFrontCommand(ServletContext con,
                                               HttpServletRequest req,
                                               HttpServletResponse resp
    ) throws ServletException, IOException {
            switch (req.getRequestURI()) {
                case "/":
                    return new IndexCommand(con, req, resp);
                case "/Index":
                    return new IndexCommand(con, req, resp);
                case "/LogOut":
                    return new LogOutCommand(con, req, resp);
                case "/NewLot":
                    return new NewLotCommand(con, req, resp);
                case "/Checkin":
                    return new CheckinCommand(con, req, resp);
                case "/NewBet":
                    return new NewBetCommand(con, req, resp);
                case "/CreateLot":
                    return new CreateLotCommand(con, req, resp);
                case "/List":
                    return new ListCommand(con, req, resp);
                case "/Login":
                    return new LoginCommand(con, req, resp);
                case "/Registration":
                    return new RegistrationCommand(con, req, resp);
                default:
                    return new IndexCommand(con, req, resp);
            }
    }
}
