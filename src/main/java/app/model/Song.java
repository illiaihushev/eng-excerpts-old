package app.model;

import app.model.base.CreatedByMemberEntity;

import javax.persistence.*;

@Entity
@Table(name = "songs")
public class Song extends CreatedByMemberEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "songs_releases_fk"))
    private Release release;

    private Byte number;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;

        Song song = (Song) o;

        if (!number.equals(song.number)) return false;
        return release.equals(song.release);
    }

    @Override
    public int hashCode() {
        int result = number.hashCode();
        result = 31 * result + release.hashCode();
        return result;
    }


    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public Byte getNumber() {
        return number;
    }

    public void setNumber(Byte number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
