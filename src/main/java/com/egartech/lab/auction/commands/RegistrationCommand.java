package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.strategy.RegistrationStrategy;
import com.egartech.lab.auction.strategy.StrategyInterface;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class RegistrationCommand contains strategy for processing request, info
 * about access checking and redirect request to target jsp page.
 *
 * @author Max Bondarev.
 */
public class RegistrationCommand extends FrontCommand {

    public StrategyInterface strategy = new RegistrationStrategy();
    public boolean protectedLink = false;

    /**
     * Public constructor.
     * @param context
     * @param request
     * @param response
     */
    public RegistrationCommand(ServletContext context,
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
    @Override
    public void process() throws ServletException, IOException {
        doStrategy();
    }
}

