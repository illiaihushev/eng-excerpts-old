package app.repository;

        import app.model.SongExcerpt;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Repository;

        import java.util.List;
        import java.util.Optional;

@Repository
public interface SongExcerptRepository extends CrudRepository<SongExcerpt, Long> {
    @Override
    List<SongExcerpt> findAll();

    Optional<SongExcerpt> findById(Long id);
}
