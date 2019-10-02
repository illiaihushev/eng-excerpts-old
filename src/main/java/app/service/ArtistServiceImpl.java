package app.service;

import app.model.Artist;
import app.model.Member;
import app.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@PropertySource("classpath:update_depth.properties")
public class ArtistServiceImpl extends GenericTrackableEntityServiceAbstr<Artist, Member> implements ArtistService {
    @Value("${update_depth.on_create.artist}")
    public int UPDATE_DEPTH_ON_CREATE;

    @Value("${update_depth.on_update.artist}")
    public int UPDATE_DEPTH_ON_UPDATE;

    private ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository, MemberService memberService) {
        this.artistRepository = artistRepository;
        this.memberService = memberService;
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
    public boolean isChangesValuable(Artist oldEnt, Artist newEnt) {
        return !oldEnt.getName().equals(newEnt.getName())
                || !oldEnt.getGenres().equals(newEnt.getGenres())
                || !oldEnt.getCountry().equals(newEnt.getCountry());
    }

    @Override
    public CrudRepository<Artist, Long> getRepository() {
        return artistRepository;
    }

    @Override
    public GenericTrackableEntityService getParentService() {
        return memberService;
    }

    @Override
    public Member getParentEntity(Artist child) {
        return child.getAddedBy();
    }
}

