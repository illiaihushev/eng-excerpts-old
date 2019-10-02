package app.service;

import app.model.base.TrackableEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface GenericTrackableEntityService<T extends TrackableEntity, P extends TrackableEntity> {
    @Transactional
    void cascadeUpdateLastModifiedProp(T child, int depth, boolean isActualLastModified);

    int getOnUpdateDepth();

    int getOnCreateDepth();

    boolean isChangesValuable(T oldEnt, T newEnt);

    CrudRepository<T, Long> getRepository();

    GenericTrackableEntityService<P, ?> getParentService();

    P getParentEntity(T child);

}
