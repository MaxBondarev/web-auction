package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.data.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand extends FrontCommand {

    @Override
    public void init(
            ServletContext servletContext,
            HttpServletRequest req,
            HttpServletResponse resp
    ) {
        this.context = servletContext;
        this.request = req;
        this.response = resp;

        User testUser = new User();
    }

    @Override
    public void process() throws ServletException, IOException {
        forward("registration");


    String username= request.getParameter("login");
    String password= request.getParameter("password");

        request.setAttribute("log", username);
        request.setAttribute("pas", password);
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    //super.doPost(req, resp);
    //this.process(req, resp);
    }
}
