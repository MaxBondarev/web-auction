package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.strategy.StrategyInterface;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class IndexCommand contains info about access checking and redirect
 * request to target jsp page.
 *
 * @author Max Bondarev.
 */
public class IndexCommand extends FrontCommand {

    final String ADRESS = "index";
    public StrategyInterface strategy;
    public boolean protectedLink = false;

    public IndexCommand(ServletContext context,
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

    @Override
    public void process() throws ServletException, IOException {
        doStrategy();
        forward(ADRESS);
    }
}

