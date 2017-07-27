package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.strategy.CreateLotStrategy;
import com.egartech.lab.auction.strategy.StrategyInterface;

import javax.servlet.ServletException;
import java.io.IOException;


public class CreateLotCommand extends FrontCommand {
    private StrategyInterface privateStrategy = new CreateLotStrategy();

    public CreateLotCommand(){
        if(privateStrategy != null){
            setStrategy(privateStrategy);
        }
    }
    public void process() throws ServletException, IOException {
        doStrategy();
    }
}