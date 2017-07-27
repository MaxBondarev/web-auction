package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.strategy.RegistrationStrategy;
import com.egartech.lab.auction.strategy.StrategyInterface;

import javax.servlet.ServletException;
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

