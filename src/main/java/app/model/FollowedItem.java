package app.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Table(name = "followed_items")
public class FollowedItem {
    @EmbeddedId
    private FollowedItemId id;

    @Column(name = "followed_since")
    private Timestamp followedSince;

    public Timestamp getFollowedSince() {
        return followedSince;
    }

    public void setFollowedSince(Timestamp followedSince) {
        this.followedSince = followedSince;
    }

    public FollowedItemId getId() {
        return id;
    }

    public void setId(FollowedItemId id) {
        this.id = id;
    }
}
