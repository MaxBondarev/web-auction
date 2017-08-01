package com.egartech.lab.auction.strategy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * StrategyInterface an interface for executing servlet strategies
 * that handle requests.
 *
 * @author Max Bondarev.
 */
public interface StrategyInterface {

   public void doLogic(
           ServletContext context,
           HttpServletRequest request,
           HttpServletResponse response
   )throws ServletException, IOException;
}
