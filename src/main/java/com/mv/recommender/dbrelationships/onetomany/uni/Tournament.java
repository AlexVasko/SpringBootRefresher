package com.mv.recommender.dbrelationships.onetomany.uni;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;


    /**
     * The Tournament class has a one-to-many relationship with the Registration class as one tournament
     * can have multiple registrations. This can be modelled by the @OneToMany annotation.
     * In a one-to-many relationship, the primary key of the one side is placed as a foreign key in the many side.
     * The @JoinColumn annotation shows that this is the owning side of the relationship.
     * tournament_id will be added as a foreign key column in the registration table.
     *
     * Note: In the absence of the @JoinColumn annotation,
     * Hibernate creates a join table for the one-to-many relationship containing the primary keys of both the tables.
     *
     * The @OneToMany annotation has an orphanRemoval attribute, which can be used to delete records that have been orphaned.
     */
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="tournament_id")
    private List<Registration> registrations = new ArrayList<>();

    public Tournament(int id, String name, String location, List<Registration> registrations) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.registrations = registrations;
    }

    public Tournament(){}

    public void addRegistration(Registration reg) {
        registrations.add(reg);
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public void removeRegistration(Registration reg) {
        if (registrations != null)
            registrations.remove(reg);
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", registrations=" + registrations +
                '}';
    }
}
