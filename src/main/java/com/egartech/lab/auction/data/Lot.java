package com.egartech.lab.auction.data;

import javax.persistence.*;
import java.util.Set;

/**
 * Class Lot keeps the highest {@link Bet} of its kind.
 *
 * @author Max Bondarev.
 */
@Entity
@Table(name = "lots", schema = "new_schema")
public class Lot {

    private int id;
    private String name;
    private Bet bet;
    private Set<Bet> bets;

    public Lot(){}

    public Lot(String name){
        setName(name);
    }

    /**
     * id - Lot id from database.
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    /**
     * id - Lot id from database.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * name - Lot name.
     * @return name
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * name - Lot name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * bet - the highest {@link Bet} of this Lot.
     * @return bet
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bet_id", nullable = true)
    public Bet getBet() {
        return bet;
    }

    /**
     * bet - the highest {@link Bet} of this Lot.
     * @param bet
     */
    public void setBet(Bet bet) {
        this.bet = bet;
    }

    /**
     * bets - Set of all {@link Bet} of this Lot.
     * @return bets
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id", nullable = true)
    public Set<Bet> getBets() {
        return bets;
    }

    /**
     * bets - Set of all {@link Bet} of this Lot.
     * @param bets
     */
    public void setBets(Set<Bet> bets) {
        this.bets = bets;
    }
}
