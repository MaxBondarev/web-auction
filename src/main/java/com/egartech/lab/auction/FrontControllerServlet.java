package com.egartech.lab.auction;

import com.egartech.lab.auction.commands.FrontCommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class FrontControllerServlet distributes requests to executing servlets
 * {@link FrontCommand} using {@link FrontCommandFactory}.
 *
 * @author Max Bondarev.
 */
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

    /**
     * Redirect requests to {@link FrontCommand} servlets using
     * {@link FrontCommandFactory}.
     */
    private void startFrontController(HttpServletRequest req,
                                      HttpServletResponse resp
    ) throws ServletException, IOException {
        FrontCommand command = FrontCommandFactory
                .getFrontCommand(getServletContext(), req, resp);
        command.process();
    }
}