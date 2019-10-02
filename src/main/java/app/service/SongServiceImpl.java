package app.service;

import app.model.Release;
import app.model.Song;
import app.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@PropertySource("classpath:update_depth.properties")
public class SongServiceImpl extends GenericTrackableEntityServiceAbstr<Song, Release> implements SongService {
    @Value("${update_depth.on_create.song_excerpt}")
    private int UPDATE_DEPTH_ON_CREATE;

    @Value("${update_depth.on_update.song_excerpt}")
    private int UPDATE_DEPTH_ON_UPDATE;

    private final SongRepository songRepository;
    private final ReleaseService releaseService;

    @Autowired
    public SongServiceImpl(SongRepository songRepository, ReleaseService releaseService) {
        this.songRepository = songRepository;
        this.releaseService = releaseService;
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
    public boolean isChangesValuable(Song oldEnt, Song newEnt) {
        return !oldEnt.getName().equals(newEnt.getName())
                || newEnt.getRelease().equals(oldEnt.getRelease());
    }

    @Override
    public CrudRepository<Song, Long> getRepository() {
        return songRepository;
    }

    @Override
    public GenericTrackableEntityService getParentService() {
        return releaseService;
    }

    @Override
    public Release getParentEntity(Song child) {
        return child.getRelease();
    }
}
