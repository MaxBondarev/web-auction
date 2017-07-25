package com.egartech.lab.auction;

import com.egartech.lab.auction.commands.CreateLotCommand;
import com.egartech.lab.auction.commands.FrontCommand;
import com.egartech.lab.auction.commands.RegistrationCommand;
import com.egartech.lab.auction.commands.UnknownCommand;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FrontControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        startFrontController(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        startFrontController(req, resp);
    }

    private void startFrontController(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontCommand command = FrontCommandFactory.getFrontCommand(req);
            command.init(getServletContext(), req, resp);
            command.doStrategy();
            command.process();
    }
}