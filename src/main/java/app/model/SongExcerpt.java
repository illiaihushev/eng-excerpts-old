package app.model;

import app.model.base.CreatedByMemberEntity;

import javax.persistence.*;

@Entity
@Table(name = "song_excerpts")
public class SongExcerpt extends CreatedByMemberEntity {
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "songs_song_excerpts_fk"))
    private Song song;

    @Column(length = 600)
    private String original;

    @Column(length = 600)
    private String translation;

    @Column(name = "is_chorus")
    private boolean isChorus;

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public boolean isChorus() {
        return isChorus;
    }

    public void setChorus(boolean chorus) {
        isChorus = chorus;
    }
}
