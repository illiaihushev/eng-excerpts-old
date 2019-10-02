package app.repository;

import app.model.Book;
import app.model.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();

    @Override
    Optional<Book> findById(Long id);

    @Query(value = "SELECT book.id, book.added_by, book.title, book.created, book.last_modified, fit.followed_since " +
            "FROM books book " +
            "INNER JOIN followed_items fit on fit.ref_id = book.id " +
            "WHERE follower_id = ?1 AND type_id = ?2 " +
            "ORDER BY followed_since DESC LIMIT 3", nativeQuery = true)
    @Transactional(readOnly = true)
    List<Book> findLastAddedBooks(Long memberId, Long bookTypeId);

//    List<Book> findFirst3ByAddedByOrderByCreatedDesc(Member member);

    @Query(value = "SELECT book.id, book.added_by, book.title, book.created, book.last_modified, " +
            "book.created relatedEventDate " +
            "FROM books book " +
            "WHERE book.added_by = ?1 " +
            "ORDER BY created DESC LIMIT ?2", nativeQuery = true)
    @Transactional(readOnly = true)
    List<Book> findFirstByAddedByOrderByCreatedDesc(long memberId, int limit);




}
