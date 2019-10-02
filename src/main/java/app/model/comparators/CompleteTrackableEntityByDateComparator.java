package app.model.comparators;

import app.model.base.CompleteTrackableEntity;

import java.util.Comparator;

public class CompleteTrackableEntityByDateComparator implements Comparator<CompleteTrackableEntity> {
    @Override
    public int compare(CompleteTrackableEntity o1, CompleteTrackableEntity o2) {
        return -o1.getRelatedTimestamp().compareTo(o2.getRelatedTimestamp());
    }

}
