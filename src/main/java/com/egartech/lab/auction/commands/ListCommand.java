package com.egartech.lab.auction.commands;


import com.egartech.lab.auction.Strategy.ListStrategy;
import com.egartech.lab.auction.Strategy.StrategyInterface;
import com.egartech.lab.auction.data.Lot;
import com.egartech.lab.auction.service.LotService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

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