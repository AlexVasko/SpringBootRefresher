package com.mv.recommender.dbrelationships.manytomany.bi;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    /**
     * The Player class has a one-to-many relationship with the Registration class as one player can register for many tournaments.
     * This can be modelled by the @OneToMany annotation.
     * Since the many side (Registration) is the owning side of a bidirectional relationship,
     * we will use the mappedBy attribute here (in the Player class) to specify that this is the inverse side of the relationship.
     * We are using cascade type ALL here because we want a player’s registrations to be deleted when the player record is deleted.
     * The player in the mappedBy attribute references the player field in the Registration class.
     * Hibernate looks at the @JoinColumn annotation on the player field to find the foreign key column.
     */
    @OneToMany(mappedBy="player", cascade= CascadeType.ALL)
    private List<Registration> registrations = new ArrayList<>();

    @OneToOne(cascade=CascadeType.ALL)//, optional = false)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private PlayerProfile playerProfile;

    public Player( ) {

    }

    public Player(String name) {
        super();
        this.name = name;
    }

    public Player(String name, PlayerProfile profile) {
        super();
        this.name = name;
        this.playerProfile = profile;
    }

    public void registerPlayer(Registration reg) {
        //add registration to the list
        registrations.add(reg);
        //set the player field in the registration
        reg.setPlayer(this);
    }

    public void removeRegistration(Registration reg) {
        if (registrations != null)
            registrations.remove(reg);
        //set the player field in the registration
        reg.setPlayer(null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerProfile getPlayerProfile() {
        return playerProfile;
    }

    public void setPlayerProfile(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", name=" + name + ", playerProfile=" + playerProfile + "]";
    }
}
