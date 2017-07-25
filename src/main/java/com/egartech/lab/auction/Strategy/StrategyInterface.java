package com.egartech.lab.auction.Strategy;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface StrategyInterface {

   public void doLogic(
           ServletContext context,
           HttpServletRequest request,
           HttpServletResponse response
   )throws ServletException, IOException;
}
