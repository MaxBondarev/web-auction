package com.egartech.lab.auction.data;

import javax.persistence.*;

/**
 * Class Bet contains information about the {@link Lot}, {@link User} and
 * Bet price.
 *
 * @author Max Bondarev.
 */
@Entity
@Table(name = "bets", schema = "new_schema")
public class Bet {
    private int id;
    private double price;
    private User user;
    private Lot lot;

    public Bet(){}

    public Bet(double price,User user,Lot lot){
        setPrice(price);
        setUser(user);
        setLot(lot);
    }

    /**
     * id - Bet id from database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    /**
     * id - Bet id from database.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * price - price of the Bet.
     */
    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    /**
     * price - price of the Bet.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * lot - The {@link Lot} to which the Bet belongs.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id", nullable = false)
    public Lot getLot() {
        return lot;
    }

    /**
     * lot - The {@link Lot} to which the Bet belongs.
     */
    public void setLot(Lot lot) {
        this.lot = lot;
    }

    /**
     * user - The {@link User} to which the Bet belongs.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    /**
     * user - The {@link User} to which the Bet belongs.
     */
    public void setUser(User user) {
        this.user = user;
    }
}


