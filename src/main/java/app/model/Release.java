package app.model;

import app.model.base.CreatedByMemberEntity;

import javax.persistence.*;

@Entity
@Table(name = "releases")
public class Release extends CreatedByMemberEntity {
    private String title;

    private Integer year;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "albums_artists_fk"))
    private Artist artist;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Release)) return false;

        Release release = (Release) o;

        if (!title.equals(release.title)) return false;
        return artist.equals(release.artist);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + artist.hashCode();
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
