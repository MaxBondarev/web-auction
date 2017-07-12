package com.egartech.lab.auction.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckinCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward("checkin");
    }

}