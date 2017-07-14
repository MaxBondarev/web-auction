package com.egartech.lab.auction.commands;


import com.egartech.lab.auction.data.Lot;
import com.egartech.lab.auction.service.LotService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class ListCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
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
        //serviceCall();
        forward("list");
    }
    /*
    public void serviceCall() {
        LotService lotService = new LotService();
        Lot book1 = new Lot("1", "The Brothers Karamazov", "Fyodor Dostoevsky");
        Lot book2 = new Lot("2", "War and Peace", "Leo Tolstoy");
        Lot book3 = new Lot("3", "Pride and Prejudice", "Jane Austen");
        System.out.println("*** Persist - start ***");
        lotService.persist(book1);
        lotService.persist(book2);
        lotService.persist(book3);
        List<Lot> lots = lotService.findAll();
        System.out.println("Lots Persisted are :");
        for (Lot b : lots) {
            System.out.println("-" + b.toString());
        }
        System.out.println("*** Persist - end ***");
        System.out.println("*** Update - start ***");
        book1.setTitle("The Idiot");
        lotService.update(book1);
        System.out.println("Lot Updated is =>" + lotService.findById(book1.getId()).toString());
        System.out.println("*** Update - end ***");
        System.out.println("*** Find - start ***");
        String id1 = book1.getId();
        Lot another = lotService.findById(id1);
        System.out.println("Lot found with id " + id1 + " is =>" + another.toString());
        System.out.println("*** Find - end ***");
        System.out.println("*** Delete - start ***");
        String id3 = book3.getId();
        lotService.delete(id3);
        System.out.println("Deleted book with id " + id3 + ".");
        System.out.println("Now all books are " + lotService.findAll().size() + ".");
        System.out.println("*** Delete - end ***");
        System.out.println("*** FindAll - start ***");
        List<Lot> books2 = lotService.findAll();
        System.out.println("Lots found are :");
        for (Lot b : books2) {
            System.out.println("-" + b.toString());
        }
        System.out.println("*** FindAll - end ***");
        System.out.println("*** DeleteAll - start ***");
        lotService.deleteAll();
        System.out.println("Lots found are now " + lotService.findAll().size());
        System.out.println("*** DeleteAll - end ***");
        System.exit(0);
    }
    */
}