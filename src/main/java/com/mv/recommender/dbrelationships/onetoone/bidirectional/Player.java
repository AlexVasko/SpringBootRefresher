package com.mv.recommender.dbrelationships.onetoone.bidirectional;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    /**
     * The cascade property ensures that changes made to a Player entity are reflected
     * in the PlayerProfile entity. The PlayerProfile entity does not have a
     * meaning of its own, rather, it defines the Player entity.
     * If we delete a Player entity, the associated details should also be deleted.
     * Cascading allows an operation on the Player entity to be
     * propagated to the PlayerProfile entity.
     *
     * In relationships, one side is the owning side. We use the @JoinColumn annotation on the owning side.
     * Here, the Player class is the owning side of the relationship.
     * The @JoinColumn annotation specifies the name of the foreign key column in the player table.
     * We will call the column profile_id. If the name is not specified, then JPA names the column based on some rules.
     * In the player_profile table, the column that is being referenced is id.
     * The name of the corresponding field in the PlayerProfile class is Id, which we specify as referencedColumnName.
     *
     * The @JsonManagedReference annotation is used on the playerProfile field in the owning side (Player class).
     * On the inverse side (PlayerProfile class), the @JsonBackReference annotation is used to the player field.
     * These annotations solve the infinite recursion problem.
     *
     * Another solution is to use the @JsonIdentityInfo annotation at class level. Both Player and PlayerProfile classes
     * are annotated with @JsonIdentityInfo to avoid infinite recursion while converting POJOs to String.
     * The property attribute specifies the property name of the target reference.
     * Here, id field is used to break out of the recursion. The first time id is encountered, it is replaced
     * with the object and for subsequent occurrences of id, the numerical value is used instead of replacing it with
     * the object.
     *
     * The @OneToOne annotation has an optional attribute. By default the value is true meaning that the
     * association can be null. We can explicitly set it to false for the playerProfile attribute in the Player class.
     * If the value of the optional attribute is set to false,
     * then we will get an error when a Player object is added without an associated PlayerProfile object.
     *
     *
     *
     * Note: When we add the PlayerProfile object first and then add the Player object by using the reference
     * of the PlayerProfile object, JPA throws the detached entity passed to persist error.
     * This error can be removed by changing the cascade type in the Player class to CascadeType.Merge.
     * REASON: save() method calls persist() for new entities and merge() for existing entities.
     * Player is a new entity so persist() is called and the operation is cascaded to PlayerProfile.
     * However, since the PlayerProfile already exists, it needs to be merged, not persisted.
     * When we change the cascade type to MERGE, persist() is not cascaded to PlayerProfile and the exception is avoided.
     * However, if we add a Player object with a nested PlayerProfile object now, we will get
     * the Not-null property references a transient value error.
     */
    @OneToOne(cascade=CascadeType.ALL, optional = true)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
//    @JsonManagedReference
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
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
