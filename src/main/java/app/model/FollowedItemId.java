package app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FollowedItemId implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "followed_items_members_fk"), name = "follower_id")
    private Member follower;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "followed_items_item_types_fk"), name = "type_id")
    private ItemType type;

    @Column(name = "ref_id")
    private Long refId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowedItemId)) return false;
        FollowedItemId id = (FollowedItemId) o;
        return Objects.equals(refId, id.refId) &&
                Objects.equals(type, id.type) &&
                Objects.equals(follower, id.follower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(follower, type, refId);
    }

    public Member getFollower() {
        return follower;
    }

    public void setFollower(Member follower) {
        this.follower = follower;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
