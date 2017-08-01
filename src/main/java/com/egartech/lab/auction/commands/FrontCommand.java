package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.strategy.StrategyInterface;
import com.egartech.lab.auction.validation.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Abstract class FrontCommand contains general methods for servlets
 * which will processing user queries.
 *
 * @author Max Bondarev.
 */
public abstract class FrontCommand {

    public StrategyInterface strategy;
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    final String LINK = "/WEB-INF/jsp/%s.jsp";
    final String LINK_INDEX = "/WEB-INF/jsp/index.jsp";
    public boolean protectedLink = false;

    /**
     * Public constructor.
     * @param context
     * @param request
     * @param response
     */
    public FrontCommand(ServletContext context,
                        HttpServletRequest request,
                        HttpServletResponse response
    ) throws ServletException, IOException {
        init (context, request, response);
    }

    /**
     * Initialisation of parameters.
     * @param context
     * @param request
     * @param response
     */
    private void init(ServletContext context, HttpServletRequest request,
                      HttpServletResponse response) {
        this.context = context;
        this.request = request;
        this.response = response;
    }

    /**
     * Set strategy.
     * @param strategy
     */
    public void setStrategy(StrategyInterface strategy){
        this.strategy = strategy;
    }

    /**
     * Set boolean value to know need check user existence in session or not.
     * @param protectedLink
     */
    public void setProtectedLink(boolean protectedLink){
        this.protectedLink = protectedLink;
    }

    /**
     * Checks is User authorized
     */
    private void isUserAuthorized() throws ServletException, IOException {
        if (!Validator.isUserInSession(request)){
            RequestDispatcher dispatcher = context
                    .getRequestDispatcher(LINK_INDEX);
            dispatcher.forward(request, response);
        }
    }

    /**
     * Starts using the strategy if necessary
     */
    public void doStrategy() throws ServletException, IOException {
        if(protectedLink){
            isUserAuthorized();
        }
        if (strategy != null) {
            strategy.doLogic(context, request, response);
        }
    }

    /**
     * Process method
     */
    public abstract void process() throws ServletException, IOException;

    /**
     * Redirect to target jsp page.
     * @param target
     */
    protected void forward(String target) throws ServletException, IOException {
        target = String.format(LINK, target);
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }
}
