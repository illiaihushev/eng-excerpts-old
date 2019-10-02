package app.service;

import app.model.Artist;
import app.model.Member;
import app.model.Release;
import app.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@PropertySource("classpath:update_depth.properties")
public class ReleaseServiceImpl extends GenericTrackableEntityServiceAbstr<Release, Artist> implements ReleaseService {
    @Value("${update_depth.on_create.release}")
    private int UPDATE_DEPTH_ON_CREATE;

    @Value("${update_depth.on_update.release}")
    private int UPDATE_DEPTH_ON_UPDATE;

    private ReleaseRepository releaseRepository;
    private ArtistService artistService;

    @Autowired
    public ReleaseServiceImpl(ArtistService artistService, ReleaseRepository releaseRepository) {
        this.artistService = artistService;
        this.releaseRepository = releaseRepository;
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
    public boolean isChangesValuable(Release oldEnt, Release newEnt) {
        return (!newEnt.getArtist().equals(oldEnt.getArtist())
                || !newEnt.getTitle().equals(oldEnt.getTitle()));
    }

    @Override
    public CrudRepository<Release, Long> getRepository() {
        return releaseRepository;
    }

    @Override
    public GenericTrackableEntityService getParentService() {
        return artistService;
    }

    @Override
    public Artist getParentEntity(Release child) {
        return child.getArtist();
    }
}
