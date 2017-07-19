package com.egartech.lab.auction.commands;


import com.egartech.lab.auction.data.Lot;
import com.egartech.lab.auction.service.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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
                System.out.println("-" + b.toString());
            }
            request.setAttribute("lots", lots);
            forward("list");
        } else {
            forward("index");
        }
    }
}