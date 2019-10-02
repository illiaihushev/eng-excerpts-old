package app.model.base;

import app.model.Member;

import java.sql.Timestamp;

public interface TrackableEntity {
    void setLastModified(Timestamp currentTime);

    Timestamp getLastModified();

    Timestamp getCreated();

    Long getId();

    Member getAddedBy();
}