package app.repository;

import app.model.BookExcerpt;
import app.model.base.CompleteTrackableEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookExcerptRepository extends CrudRepository<BookExcerpt, Long> {
    @Override
    List<BookExcerpt> findAll();

    Optional<BookExcerpt> findById(Long id);

    @Query(value = "SELECT bexc as item, fit.followedSince as relatedTimestamp, fit.id.follower as relatedMember, 'book-excerpts', 'follow' as actionName " +
            "FROM BookExcerpt bexc " +
            "INNER JOIN FollowedItem fit on fit.id.refId = bexc.id " +
            "WHERE bexc.addedBy.id = ?1 AND fit.id.type.id = ?2 "+
            "ORDER BY relatedTimestamp DESC")
    @Transactional(readOnly = true)
    List<CompleteTrackableEntity> findLastFollowed(Long memberId, Long typeId, Pageable pageable);


    @Query(value = "SELECT bexc as item, bexc.created as relatedTimestamp, bexc.addedBy as relatedMember, 'book-excerpts', 'create' as actionName " +
            "FROM BookExcerpt bexc " +
            "WHERE bexc.addedBy.id = ?1 "+
            "ORDER BY relatedTimestamp DESC")
    @Transactional(readOnly = true)
    List<CompleteTrackableEntity> findLastCreated(Long memberId, Pageable pageable);

//    @Query(value = "SELECT NEW app.model.TestConstructor(bexc as item, fit.followedSince, fit.id.follower, fit.id.type.name, 'follow') " +
//            "FROM BookExcerpt bexc " +
//            "INNER JOIN FollowedItem fit on fit.id.refId = bexc.id " +
//            "WHERE bexc.addedBy.id = ?1 AND fit.id.type.id = 8 "+
//            "ORDER BY bexc.created DESC"/*, nativeQuery = true*/)
//    @Transactional(readOnly = true/*, propagation= Propagation.REQUIRES_NEW*/)
//    List<TestConstructor> test1(Long memberId, int limit);



//    List<BookExcerpt> findFirst3ByAddedByOrderByCreatedDesc(Member member);
}
