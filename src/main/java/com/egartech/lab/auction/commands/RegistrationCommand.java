package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.Strategy.RegistrationStrategy;
import com.egartech.lab.auction.Strategy.StrategyInterface;
import com.egartech.lab.auction.data.User;
import com.egartech.lab.auction.service.UserService;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommand extends FrontCommand {

    private StrategyInterface privateStrategy = new RegistrationStrategy();

    public RegistrationCommand(){
        if(privateStrategy != null){
            setStrategy(privateStrategy);
        }
    }

    @Override
    public void process() throws ServletException, IOException {
        doStrategy();
    }
}

