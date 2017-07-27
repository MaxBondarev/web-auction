package com.egartech.lab.auction.commands;


import com.egartech.lab.auction.strategy.StrategyInterface;
import com.egartech.lab.auction.validation.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrontCommand {

    public StrategyInterface strategy;
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    final String LINK = "/WEB-INF/jsp/%s.jsp";
    final String LINK_INDEX = "/WEB-INF/jsp/index.jsp";
    public boolean protectedLink = false;

    public void setStrategy(StrategyInterface strategy){
        this.strategy = strategy;
    }

    public void setProtectedLink(boolean protectedLink){
        this.protectedLink = protectedLink;
    }

    public FrontCommand(ServletContext context,
                        HttpServletRequest request,
                        HttpServletResponse response
    ) throws ServletException, IOException {
        init (context, request, response);
    }

    private void init(ServletContext context, HttpServletRequest request,
            HttpServletResponse response) {
        this.context = context;
        this.request = request;
        this.response = response;
    }

    private void isUserAuthorized() throws ServletException, IOException {
        if (!Validator.isUserInSession(request)){
            RequestDispatcher dispatcher = context
                    .getRequestDispatcher(LINK_INDEX);
            dispatcher.forward(request, response);
        }
    }

    public void doStrategy() throws ServletException, IOException {
        if(protectedLink){
            isUserAuthorized();
        }
        if (strategy != null) {
            strategy.doLogic(context, request, response);
        }
    }
    public abstract void process() throws ServletException, IOException;


    protected void forward(String target) throws ServletException, IOException {
        target = String.format(LINK, target);
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }
}
