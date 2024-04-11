package com.mv.recommender.dbrelationships.manytomany.bi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    /**
     * we do not want the same category name to appear more than once,
     * we will impose the unique key constraint using the unique attribute of the @Column annotation.
     */
    @Column(unique=true)
    private String name;

    /**
     * On the tournaments field created above, use the @ManyToMany annotation with the mappedBy property.
     * This shows the value that is used to map the relationship in the Tournament class.
     * playingCategories in mapped by matches the same field name in tournament table.
     */
    @ManyToMany(mappedBy= "playingCategories",
            cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnoreProperties("playingCategories")
    private List<Tournament> tournaments = new ArrayList<>();

    public Category(){}
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
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

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tournaments=" + tournaments +
                '}';
    }
}
