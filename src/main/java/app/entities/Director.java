package app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Director {

    @Id
    private int id;

    private int gender;
    private String name;

    @OneToMany(mappedBy = "director")
    private List<Movie> movies = new ArrayList<>();

    public Director() {
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
