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

    /**
     * id - Lot id from database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * name - Lot name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * bet - the highest {@link Bet} of this Lot.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bet_id", nullable = true)
    public Bet getBet() {
        return bet;
    }

    /**
     * bet - the highest {@link Bet} of this Lot.
     */
    public void setBet(Bet bet) {
        this.bet = bet;
    }

    /**
     * bets - Set of all {@link Bet} of this Lot.
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "lot_id", nullable = true)
    public Set<Bet> getBets() {
        return bets;
    }

    /**
     * bets - Set of all {@link Bet} of this Lot.
     */
    public void setBets(Set<Bet> bets) {
        this.bets = bets;
    }
}
