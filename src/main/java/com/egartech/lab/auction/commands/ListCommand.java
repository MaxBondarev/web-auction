package com.egartech.lab.auction.commands;


import com.egartech.lab.auction.data.Lot;
import com.egartech.lab.auction.service.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Timer;

public class ListCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        HttpSession hSession = request.getSession();
        if(hSession.getAttribute("user") != null) {
            System.out.println("List start");
            LotService lotService = new LotService();
            System.out.println("new LotService");
            List<Lot> lots = lotService.findAll();
            System.out.println("Find All");
            System.out.println("Lots Persisted are :");
            for (Lot b : lots) {
                System.out.println("-" + b.getName().toString());
                if (b.getBet() != null) {
                    System.out.println("-" + b.getBet().getPrice());


                }
                System.out.println("-" );
            }
            request.setAttribute("lots", lots);
            forward("list");
        } else {
            forward("index");
        }
    }
}