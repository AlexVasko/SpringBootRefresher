package com.mv.recommender.dbrelationships.onetoone.bidirectional;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
public class PlayerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String twitter;

    /**
     * mappedBy is an optional attribute of the @oneToOne annotation which specifies the name
     * of the field which owns the relationship.
     * In our case, it is the playerProfile field in the Player class.
     * The mappedBy attribute is placed on the inverse side on the relationship only.
     * The owning side cannot have this attribute.
     *
     * The @JsonManagedReference annotation is used on the playerProfile field in the owning side (Player class).
     * On the inverse side (PlayerProfile class), the @JsonBackReference annotation is used to the player field.
     * These annotations solve the infinite recursion problem.
     *
     * Another solution is to use the @JsonIdentityInfo annotation at class level. Both Player and PlayerProfile classes
     * are annotated with @JsonIdentityInfo to avoid infinite recursion while converting POJOs to String.
     *
     * The property attribute specifies the property name of the target reference. Here, id field is used to break out
     * of the recursion. The first time id is encountered, it is replaced with the object and for
     * subsequent occurrences of id, the numerical value is used instead of replacing it with the object.
     */
    @OneToOne(mappedBy= "playerProfile", cascade= CascadeType.ALL)
//    @JsonBackReference
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    private Player player;

    public PlayerProfile() {
    }

    public PlayerProfile(String twitter) {
        super();
        this.twitter = twitter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "PlayerProfile{" +
                "id=" + id +
                ", twitter='" + twitter + '\'' +
                ", player=" + player +
                '}';
    }
}