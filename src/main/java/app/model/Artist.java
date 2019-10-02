package app.model;

import app.model.base.CreatedByMemberEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "artists")
public class Artist extends CreatedByMemberEntity {
    @NotEmpty
    private String name;

    @NotEmpty
    private String genres;

    @NotEmpty
    private String country;

    public Artist(@NotEmpty String name, @NotEmpty String genres, @NotEmpty String country) {
        this.name = name;
        this.genres = genres;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;

        Artist artist = (Artist) o;

        if (!name.equals(artist.name)) return false;
        return country.equals(artist.country);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
