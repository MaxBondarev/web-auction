package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.strategy.StrategyInterface;

import javax.servlet.ServletException;
import java.io.IOException;

public class CheckinCommand extends FrontCommand {

    final String ADRESS = "checkin";
    private StrategyInterface privateStrategy;

    public CheckinCommand(){
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