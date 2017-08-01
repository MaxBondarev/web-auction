package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.strategy.NewBetStrategy;
import com.egartech.lab.auction.strategy.StrategyInterface;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class NewBetCommand contains strategy for processing request, info about
 * access checking and redirect request to target jsp page.
 *
 * @author Max Bondarev.
 */
public class NewBetCommand extends FrontCommand  {

    private StrategyInterface strategy = new NewBetStrategy();
    private boolean protectedLink = true;

    /**
     * Public constructor.
     * @param context
     * @param request
     * @param response
     */
    public NewBetCommand(ServletContext context,
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

    /**
     * @see FrontCommand
     */
    public void process() throws ServletException, IOException {
        doStrategy();
    }
}
