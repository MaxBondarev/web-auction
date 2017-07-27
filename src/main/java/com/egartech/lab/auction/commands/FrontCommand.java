package com.egartech.lab.auction.commands;


import com.egartech.lab.auction.strategy.StrategyInterface;
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

    public void setStrategy(StrategyInterface strategy){
        this.strategy = strategy;
    }

    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse
    ) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
    }

    public abstract void process() throws ServletException, IOException;

    public void doStrategy() throws ServletException, IOException {
        if (strategy != null) {
            strategy.doLogic(context, request, response);
        }
    }

    protected void forward(String target) throws ServletException, IOException {
        target = String.format(LINK, target);
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }
}
