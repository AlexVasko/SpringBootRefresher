package com.mv.recommender.dbrelationships.manytomany.bi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
     *  In databases, this relationship is modelled using a join table which has the primary
     *  keys of both tables in the relationship.
     *  Here, the foreign keys are stored in a separate table called a join table instead of being placed inside
     *  the parent table or the child table. The join table connects two tables and contains the foreign keys of both tables.
     *  The tournament and category tables do not contain the keys of each other; rather the primary keys of both these
     *  tables go in the join table.
     *
     *  joinColumns attribute specifies the column(s) in the owner table that becomes a foreign key in the join table.
     *  inverseJoinColumns attribute specifies the foreign key column(s) from the inverse side
     *
     *  We will not use cascade type REMOVE as we do not want to delete tournaments when we delete a category.
     *  We will also not use cascade type PERSIST, because that will cause an error if we try to add a tournament with
     *  nested category values.
     *
     *  JSON gets into infinite recursion when trying to de-serialize bidirectional relationships.
     *  We can use the property that we want to ignore with the @JsonIgnoreProperties.
     *  This annotation can be used at field level in both the Tournament and Category class.
     */
    @ManyToMany(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "tournament_categories",
            joinColumns= @JoinColumn(name ="tournament_id"),  //FK of the owning side
            inverseJoinColumns=@JoinColumn(name="category_id")  //FK of inverse side
    )
    @JsonIgnoreProperties("tournaments")
    private List<Category> playingCategories = new ArrayList<>();


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

    public Tournament() {
    }

    public Tournament(String name, String location) {
        super();
        this.name = name;
        this.location = location;
    }

    public Tournament(String name, String location, List<Registration> registrations) {
        super();
        this.name = name;
        this.location = location;
        this.registrations = registrations;
    }


    public void addCategory(Category category) {
        playingCategories.add(category);
        //set up bidirectional relationship
        category.getTournaments().add(this);
    }

    public void removeCategory(Category category) {
        if (playingCategories != null)
            playingCategories.remove(category);
        //update bidirectional relationship
        category.getTournaments().remove(this);
    }
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

    public List<Category> getPlayingCategories() {
        return playingCategories;
    }

    public void setPlayingCategories(List<Category> playingCategories) {
        this.playingCategories = playingCategories;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", playingCategories=" + playingCategories +
                ", registrations=" + registrations +
                '}';
    }
}
