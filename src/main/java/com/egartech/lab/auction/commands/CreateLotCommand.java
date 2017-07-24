package com.egartech.lab.auction.commands;

import com.egartech.lab.auction.data.Lot;
import com.egartech.lab.auction.service.LotService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CreateLotCommand extends FrontCommand  {
    
    @Override
    public void init(
            ServletContext servletContext,
            HttpServletRequest req,
            HttpServletResponse resp
    ) {
        this.context = servletContext;
        this.request = req;
        this.response = resp;
    }

    public void process() throws ServletException, IOException {
        try {
    if (request.getRequestURI().equals("/CreateLot")) {

        //Validation
        String lotname = request.getParameter("name");
        lotname = lotname.replaceAll("[/\\s/]+", " ");
        lotname = lotname.trim();
        if ((lotname.matches("")) || (lotname.matches(" "))) {
            request.setAttribute("error", "The name must contain letters!");
            request.getRequestDispatcher("/WEB-INF/jsp/newlot.jsp")
                    .forward(request, response);
        } else if (!new LotService().isNameUnique(lotname)) {
            request.setAttribute("error",
                    "Error! Lot name " + lotname + " already exist!");
            request.getRequestDispatcher("/WEB-INF/jsp/newlot.jsp")
                    .forward(request, response);

            //Creation
        } else {
            try {
                Lot lot = new Lot();
                lot.setName(lotname);
                LotService lotService = new LotService();
                lotService.save(lot);
                ListCommand lc = new ListCommand();
                lc.init(context, request, response);
                lc.process();
            } catch (Exception e) {
                System.out.println(e.toString());
                request.setAttribute("error", "Some error!" + e);
                request.getRequestDispatcher("/WEB-INF/jsp/newlot.jsp")
                        .forward(request, response);
            }
        }}}catch (Exception e) {
            System.out.println(e.toString());
            request.setAttribute("error", "Some error!" + e);
            request.getRequestDispatcher("/WEB-INF/jsp/newlot.jsp")
                    .forward(request, response);
        }
    
    
}
}