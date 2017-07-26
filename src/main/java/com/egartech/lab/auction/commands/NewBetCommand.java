package com.egartech.lab.auction.commands;


import com.egartech.lab.auction.Strategy.NewBetStrategy;
import com.egartech.lab.auction.Strategy.StrategyInterface;
import com.egartech.lab.auction.data.Bet;
import com.egartech.lab.auction.data.Lot;
import com.egartech.lab.auction.data.User;
import com.egartech.lab.auction.service.BetService;
import com.egartech.lab.auction.service.LotService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
