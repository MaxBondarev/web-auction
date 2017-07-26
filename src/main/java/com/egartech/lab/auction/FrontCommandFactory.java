package com.egartech.lab.auction;

import com.egartech.lab.auction.Strategy.*;
import com.egartech.lab.auction.commands.*;
import com.egartech.lab.auction.commands.FrontCommand;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontCommandFactory {
    public static FrontCommand getFrontCommand(HttpServletRequest req) {
            switch (req.getRequestURI()) {
                case "/":
                    return new IndexCommand();
                case "/Index":
                    return new IndexCommand();
                case "/LogOut":
                    return new IndexCommand();
                case "/NewLot":
                    return new NewLotCommand();
                case "/Checkin":
                    return new CheckinCommand();
                case "/NewBet":
                    return new NewBetCommand();
                case "/CreateLot":
                    return new CreateLotCommand();
                case "/List":
                    return new ListCommand();
                case "/Login":
                    return new LoginCommand();
                case "/Registration":
                    return new RegistrationCommand();
                default:
                    return new IndexCommand();
            }
    }
}
