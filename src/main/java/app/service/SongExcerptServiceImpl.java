package app.service;

import app.model.Song;
import app.model.SongExcerpt;
import app.repository.SongExcerptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@PropertySource("classpath:update_depth.properties")
public class SongExcerptServiceImpl extends GenericTrackableEntityServiceAbstr<SongExcerpt, Song> implements SongExcerptService {
    @Value("${update_depth.on_create.song_excerpt}")
    private int UPDATE_DEPTH_ON_CREATE;

    @Value("${update_depth.on_update.song_excerpt}")
    private int UPDATE_DEPTH_ON_UPDATE;

    private SongService songService;
    private SongExcerptRepository songExcerptRepository;

    @Autowired
    public SongExcerptServiceImpl(SongExcerptRepository songExcerptRepository, SongService songService) {
        this.songExcerptRepository = songExcerptRepository;
        this.songService = songService;
    }

    @Override
    public int getOnUpdateDepth() {
        return UPDATE_DEPTH_ON_UPDATE;
    }

    @Override
    public int getOnCreateDepth() {
        return UPDATE_DEPTH_ON_CREATE;
    }

    @Override
    public boolean isChangesValuable(SongExcerpt oldEnt, SongExcerpt newEnt) {
        return (!oldEnt.getOriginal().equals(newEnt.getOriginal())
                || oldEnt.getTranslation().equals(newEnt.getTranslation()));
    }

    @Override
    public CrudRepository<SongExcerpt, Long> getRepository() {
        return songExcerptRepository;
    }

    @Override
    public GenericTrackableEntityService getParentService() {
        return songService;
    }

    @Override
    public Song getParentEntity(SongExcerpt child) {
        return null;
    }
}

