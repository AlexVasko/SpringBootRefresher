package com.mv.recommender.dbrelationships.onetomany.bidirectional;

import jakarta.persistence.*;

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    /**
     * There is a many-to-one relationship between the Registration and Player classes where many registrations
     * can map to one player. The many side of a many-to-one bidirectional relationship is always the owning side of the relationship.
     * To model this relationship, we will use the @ManyToOne annotation with @JoinColumn
     * specifying the column that corresponds to the foreign key column in the registration table.
     * The player table has a column id which will become the foreign key column player_id in the registration table.
     * This is how the Registration knows how to find its Player.
     *
     * If a Registration object is deleted, the associated Player should not be deleted. This means that the delete
     * operation should not be cascaded. Since we have fine grain control over the cascade types,
     * we will list all of them except for REMOVE.
     */
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,  CascadeType.REFRESH})
    @JoinColumn(name="player_id", referencedColumnName = "id")
    private Player player;
    public Registration(int id) {
        this.id = id;
    }

    public Registration(int id, Player player) {
        this.id = id;
        this.player = player;
    }

    public Registration(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", player=" + player +
                '}';
    }
}
