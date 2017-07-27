package com.egartech.lab.auction;

import com.egartech.lab.auction.commands.*;
import com.egartech.lab.auction.commands.FrontCommand;

import javax.servlet.http.HttpServletRequest;

public class FrontCommandFactory {
    public static FrontCommand getFrontCommand(HttpServletRequest req) {
            switch (req.getRequestURI()) {
                case "/":
                    return new IndexCommand();
                case "/Index":
                    return new IndexCommand();
                case "/LogOut":
                    return new LogOutCommand();
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
