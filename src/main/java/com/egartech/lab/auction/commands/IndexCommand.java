package com.egartech.lab.auction.commands;


import com.egartech.lab.auction.strategy.StrategyInterface;

import javax.servlet.ServletException;
import java.io.IOException;

public class IndexCommand extends FrontCommand {

    final String ADRESS = "index";
    private StrategyInterface privateStrategy;

    public IndexCommand(){
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

