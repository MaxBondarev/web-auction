package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.strategy.CreateLotStrategy;
import com.egartech.lab.auction.strategy.LoginStrategy;
import com.egartech.lab.auction.strategy.StrategyInterface;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class CreateLotCommand contains strategy for processing request, info about
 * access checking and redirect request to target jsp page.
 *
 * @author Max Bondarev.
 */
public class CreateLotCommand extends FrontCommand {

    private StrategyInterface strategy = new CreateLotStrategy();
    private boolean protectedLink = true;

    public CreateLotCommand(ServletContext context,
                        HttpServletRequest request,
                        HttpServletResponse response
    ) throws ServletException, IOException {
        super(context, request, response);

        if(strategy != null){
            setStrategy(strategy);
        }
        if(protectedLink != false){
            setProtectedLink(protectedLink);
        }
    }

    public void process() throws ServletException, IOException {
        doStrategy();
    }
}