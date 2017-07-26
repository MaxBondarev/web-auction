package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.Strategy.StrategyInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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