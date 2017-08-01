package com.egartech.lab.auction;

import com.egartech.lab.auction.commands.FrontCommand;
import java.io.IOException;
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

    private void startFrontController(HttpServletRequest req,
                                      HttpServletResponse resp
    ) throws ServletException, IOException {
        FrontCommand command = FrontCommandFactory
                .getFrontCommand(getServletContext(), req, resp);
        command.process();
    }
}