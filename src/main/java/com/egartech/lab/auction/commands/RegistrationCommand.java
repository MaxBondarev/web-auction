package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.strategy.LoginStrategy;
import com.egartech.lab.auction.strategy.RegistrationStrategy;
import com.egartech.lab.auction.strategy.StrategyInterface;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand extends FrontCommand {

    public StrategyInterface strategy = new RegistrationStrategy();
    public boolean protectedLink = false;

    public RegistrationCommand(ServletContext context,
                        HttpServletRequest request,
                        HttpServletResponse response
    ) throws ServletException, IOException {
        super(context, request, response);

        if(strategy != null){
            setStrategy(strategy);
        }
        if(protectedLink != false){
            setProtectedLink(protectedLink);
        }
    }

    @Override
    public void process() throws ServletException, IOException {
        doStrategy();
    }
}

