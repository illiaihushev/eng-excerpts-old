package app.service;

import app.model.base.TrackableEntity;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
/**
 *
 * Root of the all services, which manages trackable entity
 *
 * @param <T> type of managed entity
 * @param <P> parent type of managed entity
 *
 */
public abstract class GenericTrackableEntityServiceAbstr<T extends TrackableEntity, P extends TrackableEntity> implements GenericTrackableEntityService<T, P> {
    protected MemberService memberService;

    @Transactional
    public T save(T ent) {
        if (ent.getId() == null) {
            cascadeUpdateLastModifiedProp(ent, getOnCreateDepth(),true);
        } else {
            if (isChangesValuable(getRepository().findById(ent.getId()).get(), ent)) {
                ent.setLastModified(new Timestamp(System.currentTimeMillis()));
                cascadeUpdateLastModifiedProp(ent, getOnUpdateDepth(), true);
            }
        }
        return getRepository().save(ent);
    }

    @Override
    public void cascadeUpdateLastModifiedProp(T child, int depth, boolean isActualLastModified) {
        if (!isActualLastModified) {
            child.setLastModified(new Timestamp(System.currentTimeMillis()));
            getRepository().save(child);
        }
        if (depth > 0){
            getParentService().cascadeUpdateLastModifiedProp(getParentEntity(child), --depth, false);
        } else {
            if (depth == 0) {
                memberService.cascadeUpdateLastModifiedProp(child.getAddedBy(), --depth, false);
            }
        }
    }

    public MemberService getMemberService() {
        return memberService;
    }
}
