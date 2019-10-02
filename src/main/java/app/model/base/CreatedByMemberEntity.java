package app.model.base;

//import org.springframework.data.rest.core.config.Projection;

import app.model.Member;
import app.model.base.CoreEntity;
import app.model.base.TrackableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

//@Projection(types = BookExcerpt.class)
@MappedSuperclass
public class CreatedByMemberEntity extends CoreEntity implements TrackableEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Member addedBy;

    @NotNull
    private Timestamp created;

    @NotNull
    @Column(name = "last_modified")
    private Timestamp lastModified;

    @PrePersist
    private void onCreate() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        this.created = currentTime;
        this.lastModified = currentTime;
    }


    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Member getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Member addedBy) {
        this.addedBy = addedBy;
    }
}
