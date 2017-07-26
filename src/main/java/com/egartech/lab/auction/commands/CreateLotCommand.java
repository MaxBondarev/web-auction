package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.Strategy.CreateLotStrategy;
import com.egartech.lab.auction.Strategy.StrategyInterface;
import com.egartech.lab.auction.data.Lot;
import com.egartech.lab.auction.service.LotService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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