package app.model.base;

import app.model.Member;

import java.sql.Timestamp;

public class CompleteTrackableEntity {
    private TrackableEntity item;
    private Member relatedMember;
    private Timestamp relatedTimestamp;
    private String typeName;
    private String actionName;

    public CompleteTrackableEntity(TrackableEntity item, Member relatedMember, Object relatedTimestamp, String typeName, String actionName) {
        this.item = item;
        this.relatedMember = relatedMember;
        this.relatedTimestamp = (Timestamp) relatedTimestamp;
        this.typeName = typeName;
        this.actionName = actionName;
    }

    public CompleteTrackableEntity(TrackableEntity item, Member relatedMember, Object relatedTimestamp) {
        this.item = item;
        this.relatedMember = relatedMember;
        this.relatedTimestamp = (Timestamp) relatedTimestamp;
    }

    public void setItem(TrackableEntity item) {
        this.item = item;
    }

    public void setRelatedMember(Member relatedMember) {
        this.relatedMember = relatedMember;
    }

    public Timestamp getRelatedTimestamp() {
        return relatedTimestamp;
    }

    public void setRelatedTimestamp(Timestamp relatedTimestamp) {
        this.relatedTimestamp = relatedTimestamp;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

}
