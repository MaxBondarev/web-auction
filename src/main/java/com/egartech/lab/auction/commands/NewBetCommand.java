package com.egartech.lab.auction.commands;


import com.egartech.lab.auction.strategy.NewBetStrategy;
import com.egartech.lab.auction.strategy.StrategyInterface;

import javax.servlet.ServletException;
import java.io.IOException;

public class NewBetCommand extends FrontCommand  {

    private StrategyInterface privateStrategy = new NewBetStrategy();

    public NewBetCommand(){
        if(privateStrategy != null){
            setStrategy(privateStrategy);
        }
    }

    public void process() throws ServletException, IOException {
        doStrategy();
    }
}
