package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.strategy.LogOutStrategy;
import com.egartech.lab.auction.strategy.LoginStrategy;
import com.egartech.lab.auction.strategy.StrategyInterface;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LogOutCommand extends FrontCommand {

    private StrategyInterface strategy = new LogOutStrategy();
    private boolean protectedLink = true;

    public LogOutCommand(ServletContext context,
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
    public void process() throws ServletException, IOException {
        doStrategy();
    }

}
