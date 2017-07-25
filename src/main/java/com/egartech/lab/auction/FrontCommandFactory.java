package com.egartech.lab.auction;

import com.egartech.lab.auction.commands.*;
import com.egartech.lab.auction.commands.FrontCommand;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontCommandFactory {
    public static FrontCommand getFrontCommand(HttpServletRequest req) {
        try {
            switch (req.getRequestURI()) {
                case "/":
                    return new IndexCommand();
                case "/LogOut":
                    return new IndexCommand();
                case "/NewLot":
                    return new NewLotCommand();
                case "/NewBet":
                    return new NewBetCommand();
                case "/Checkin":
                    return new CheckinCommand();
                case "/CreateLot":
                    return new CreateLotCommand();
                case "/List":
                    return new ListCommand();
                case "/Login":
                    return new LoginCommand();
                case "/Registration":
                    return new RegistrationCommand();
                case "/Index":
                    return new IndexCommand();
                default:
                    return new IndexCommand();
            }
        }catch (Exception e) {
            return new IndexCommand();
        }
    }
}
