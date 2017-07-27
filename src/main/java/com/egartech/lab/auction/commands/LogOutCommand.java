package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.strategy.LogOutStrategy;
import com.egartech.lab.auction.strategy.LoginStrategy;
import com.egartech.lab.auction.strategy.StrategyInterface;
import javax.servlet.ServletException;
import java.io.IOException;


public class LogOutCommand extends FrontCommand {

    private StrategyInterface privateStrategy = new LogOutStrategy();

    public LogOutCommand(){
        if(privateStrategy != null){
            setStrategy(privateStrategy);
        }
    }

    public void process() throws ServletException, IOException {
        doStrategy();
    }

}
