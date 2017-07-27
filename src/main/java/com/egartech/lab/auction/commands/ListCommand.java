package com.egartech.lab.auction.commands;


import com.egartech.lab.auction.strategy.ListStrategy;
import com.egartech.lab.auction.strategy.StrategyInterface;

import javax.servlet.ServletException;
import java.io.IOException;

public class ListCommand extends FrontCommand {

    private StrategyInterface privateStrategy = new ListStrategy();

    public ListCommand(){
        if(privateStrategy != null){
            setStrategy(privateStrategy);
        }
    }

    @Override
    public void process() throws ServletException, IOException {
        doStrategy();
    }
}