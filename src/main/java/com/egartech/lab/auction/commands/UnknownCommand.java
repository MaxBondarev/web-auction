package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.Strategy.StrategyInterface;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnknownCommand extends FrontCommand {

    final String ADRESS = "unknown";
    private StrategyInterface privateStrategy;

    public UnknownCommand(){
        if(privateStrategy != null){
            setStrategy(privateStrategy);
        }
    }

    @Override
    public void process() throws ServletException, IOException {
        doStrategy();
        forward(ADRESS);
    }
}
