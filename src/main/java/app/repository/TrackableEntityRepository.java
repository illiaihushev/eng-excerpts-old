package app.repository;

import app.model.base.CompleteTrackableEntity;
import app.model.util.EntitiesNames;
import app.model.util.ItemTypeUtil;
import app.service.BookExcerptService;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackableEntityRepository {
    private static String findLastCreatedStatement =
            "SELECT new app.model.base.CompleteTrackableEntity(ent, ent.addedBy, ent.created as relatedTimestamp, '%s', 'create') " +
                    "FROM %s ent " +
                    "WHERE ent.addedBy.id = %d " +
                    "ORDER BY relatedTimestamp DESC ";


    private static String findLastFollowedStatement =
            "SELECT new app.model.base.CompleteTrackableEntity(ent as item, fit.id.follower, fit.followedSince as relatedTimestamp, '%s', 'follow') " +
                    "FROM %s ent " +
                    "INNER JOIN FollowedItem fit on fit.id.refId = item.id " +
                    "WHERE item.addedBy.id = %d AND fit.id.type.id = %d "+
                    "ORDER BY relatedTimestamp DESC ";

    @Autowired
    BookExcerptService bookExcerptService;

    private List<CompleteTrackableEntity> findLastCreated(Long memberId, String entityName, int size) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String str = String.format(findLastCreatedStatement, entityName, entityName, memberId, size);

        Query<CompleteTrackableEntity> query  =  session.createQuery(str, CompleteTrackableEntity.class);
        query.setMaxResults(size);
        return query.list();
    }

    private List<CompleteTrackableEntity> findLastFollowed(Long memberId, String entityName, Long typeId, int size) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String str = String.format(findLastFollowedStatement, entityName, entityName, memberId, typeId, size);

        Query<CompleteTrackableEntity> query  =  session.createQuery(str, CompleteTrackableEntity.class);
        return query.list();
    }

    ///////////////////////  findLastFollowed... functions  ///////////////////////

    public List<CompleteTrackableEntity> findLastFollowedArtists(Long memberId, int size) {
        return findLastFollowed(memberId, EntitiesNames.ARTIST_ENTITY_NAME, ItemTypeUtil.ARTIST_TYPE_ID, size);
    }

    public List<CompleteTrackableEntity> findLastFollowedAuthors(Long memberId, int size) {
        return findLastFollowed(memberId, EntitiesNames.AUTHOR_ENTITY_NAME, ItemTypeUtil.AUTHOR_TYPE_ID, size);
    }

    public List<CompleteTrackableEntity> findLastFollowedBooks(Long memberId, int size) {
        return findLastFollowed(memberId, EntitiesNames.BOOK_ENTITY_NAME, ItemTypeUtil.BOOK_TYPE_ID, size);
    }

    public List<CompleteTrackableEntity> findLastFollowedBookExcerpts(Long memberId, int size) {
        return findLastFollowed(memberId, EntitiesNames.BOOK_EXCERPT_ENTITY_NAME, ItemTypeUtil.BOOK_EXCERPT_TYPE_ID, size);
    }

    public List<CompleteTrackableEntity> findLastFollowedMembers(Long memberId, int size) {
        return findLastFollowed(memberId, EntitiesNames.MEMBER_ENTITY_NAME, ItemTypeUtil.MEMBER_TYPE_ID, size);
    }

    public List<CompleteTrackableEntity> findLastFollowedReleases(Long memberId, int size) {
        return findLastFollowed(memberId, EntitiesNames.RELEASE_ENTITY_NAME, ItemTypeUtil.RELEASE_TYPE_ID, size);
    }

    public List<CompleteTrackableEntity> findLastFollowedSongs(Long memberId, int size) {
        return findLastFollowed(memberId, EntitiesNames.SONG_ENTITY_NAME, ItemTypeUtil.SONG_TYPE_ID, size);
    }

    public List<CompleteTrackableEntity> findLastFollowedSongExcerpts(Long memberId, int size) {
        return findLastFollowed(memberId, EntitiesNames.SONG_EXCERPT_ENTITY_NAME, ItemTypeUtil.SONG_EXCERPT_TYPE_ID, size);
    }


///////////////////////  findLastCreated... functions  ///////////////////////

    public List<CompleteTrackableEntity> findLastCreatedArtists(Long memberId, int size) {
        return findLastCreated(memberId, EntitiesNames.ARTIST_ENTITY_NAME, size);
    }

    public List<CompleteTrackableEntity> findLastCreatedAuthors(Long memberId, int size) {
        return findLastCreated(memberId, EntitiesNames.AUTHOR_ENTITY_NAME, size);
    }

    public List<CompleteTrackableEntity> findLastCreatedBooks(Long memberId, int size) {
        return findLastCreated(memberId, EntitiesNames.BOOK_ENTITY_NAME, size);
    }

    public List<CompleteTrackableEntity> findLastCreatedBookExcerpts(Long memberId, int size) {
        return findLastCreated(memberId, EntitiesNames.BOOK_EXCERPT_ENTITY_NAME, size);
    }

    public List<CompleteTrackableEntity> findLastCreatedReleases(Long memberId, int size) {
        return findLastCreated(memberId, EntitiesNames.RELEASE_ENTITY_NAME, size);
    }

    public List<CompleteTrackableEntity> findLastCreatedSongs(Long memberId, int size) {
        return findLastCreated(memberId, EntitiesNames.SONG_ENTITY_NAME, size);
    }

    public List<CompleteTrackableEntity> findLastCreatedSongExcerpts(Long memberId, int size) {
        return findLastCreated(memberId, EntitiesNames.SONG_EXCERPT_ENTITY_NAME, size);
    }
//    public List<CompleteTrackableEntity> findLastRegisteredMembers(Long memberId) {
//

//    }

}
