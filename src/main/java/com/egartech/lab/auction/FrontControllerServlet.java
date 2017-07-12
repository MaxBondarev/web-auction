package com.egartech.lab.auction;

import com.egartech.lab.auction.commands.FrontCommand;
import com.egartech.lab.auction.commands.UnknownCommand;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


public class FrontControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("login", req.getRequestURI().toString().replace("/",""));
        //req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);

        FrontCommand command = getCommand(req);
        command.init(getServletContext(), req, resp);
        command.process();
    }

    private FrontCommand getCommand(HttpServletRequest req) {
        try {
            if (req.getRequestURI().equals("/")){
                Class type = Class.forName(
                                "com.egartech.lab.auction.commands.IndexCommand");
                return (FrontCommand) type
                        .asSubclass(FrontCommand.class)
                        .newInstance();
            } else {
                Class type = Class.forName(
                        String.format(
                                "com.egartech.lab.auction.commands.%sCommand",
                                req.getRequestURI().toString().replace("/","")
                        )
                );
                return (FrontCommand) type
                        .asSubclass(FrontCommand.class)
                        .newInstance();

            }

        } catch (Exception e) {
            return new UnknownCommand();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        FrontCommand command = getCommand(req);
        command.init(getServletContext(), req, resp);
        command.process();
    }

    /*
      generate the page showing all the request parameters
    */
    /*
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(200);

        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        // Get the values of all request parameters
        Enumeration en = request.getParameterNames();
        while (en.hasMoreElements()) {
            // Get the name of the request parameter
            String name = (String) en.nextElement();
            out.println(name);

            // Get the value of the request parameter
            String value = request.getParameter(name);

            // If the request parameter can appear more than once in the query string, get all values
            String[] values = request.getParameterValues(name);

            for (int i = 0; i < values.length; i++) {
                out.println(" " + values[i]);
            }
        }
        out.close();
        }


        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username= req.getParameter("login");
        String password= req.getParameter("password");

        req.setAttribute("log", username);
        req.setAttribute("pas", password);
        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
        //super.doPost(req, resp);
        //this.process(req, resp);
    }
        */
    }