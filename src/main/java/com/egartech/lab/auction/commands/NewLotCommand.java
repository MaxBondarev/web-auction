package com.egartech.lab.auction.commands;


import com.egartech.lab.auction.strategy.StrategyInterface;

import javax.servlet.ServletException;
import java.io.IOException;

public class NewLotCommand extends FrontCommand {

    final String ADRESS = "newlot";
    private StrategyInterface privateStrategy;

    public NewLotCommand(){
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
