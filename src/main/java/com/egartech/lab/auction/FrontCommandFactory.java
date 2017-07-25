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
                    NewBetCommand newBetCommand = new NewBetCommand();
                    newBetCommand.setStrategy(new NewBetStrategy());
                    return newBetCommand;
                case "/CreateLot":
                    CreateLotCommand createLotCommand = new CreateLotCommand();
                    createLotCommand.setStrategy(new CreateLotStrategy());
                    return createLotCommand;
                case "/List":
                    ListCommand listCommand = new ListCommand();
                    listCommand.setStrategy(new ListStrategy());
                    return listCommand;
                case "/Login":
                    LoginCommand loginCommand = new LoginCommand();
                    loginCommand.setStrategy(new LoginStrategy());
                    return loginCommand;
                case "/Registration":
                    RegistrationCommand regCommand = new RegistrationCommand();
                    regCommand.setStrategy(new RegistrationStrategy());
                    return regCommand;
                default:
                    return new IndexCommand();
            }
    }
}
