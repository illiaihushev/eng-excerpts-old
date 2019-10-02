package app.service;

import app.model.Book;
import app.model.BookExcerpt;
import app.model.base.CompleteTrackableEntity;
import app.model.Member;
import app.repository.BookExcerptRepository;
import app.service.util.CompleteTrackableEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:update_depth.properties")
public class BookExcerptServiceImpl extends GenericTrackableEntityServiceAbstr<BookExcerpt, Book> implements BookExcerptService {
    @Value("${update_depth.on_create.book_excerpt}")
    public int UPDATE_DEPTH_ON_CREATE;

    @Value("${update_depth.on_update.book_excerpt}")
    public int UPDATE_DEPTH_ON_UPDATE;

    private final BookService bookService;
    private final BookExcerptRepository bookExcerptRepository;

    @Autowired
    public BookExcerptServiceImpl(BookExcerptRepository bookExcerptRepository,
                                  BookService bookService,
                                  MemberService memberService) {
        this.bookExcerptRepository = bookExcerptRepository;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @Override
    public int getOnUpdateDepth() {
        return UPDATE_DEPTH_ON_UPDATE;
    }

//    @Override
//    public List<CompleteTrackableEntity> findLastFollowedBookExcerpts(Member member, int size) {
////        Long bookTypeId = itemTypeRepository.findIdByName(bookExcerptTableName);
//        TrackableEntityRepository trackableEntityRepository = new TrackableEntityRepository();
//        return trackableEntityRepository.findLastFollowedBookExcerpts(member.getId(), bookTypeId, PageRequest.of(0, size));
//    }

    @Override
    public int getOnCreateDepth() {
        return UPDATE_DEPTH_ON_CREATE;
    }
    //TODO:
    public List<CompleteTrackableEntity> getRecentChanges(Member member, int size) {
        List<List<CompleteTrackableEntity>> changes = new ArrayList<>();
//        changes.add(findLastCreatedBookExcerpts(member, size));
//        changes.add(findLastFollowedBookExcerpts(member, size));
        return CompleteTrackableEntityUtil.getMostRecentChangesList(changes, size);
    }


    public boolean isChangesValuable(BookExcerpt oldExcerpt, BookExcerpt newExcerpt) {
        return (!newExcerpt.getOriginal().equals(oldExcerpt.getOriginal()))
                || (!newExcerpt.getTranslation().equals(oldExcerpt.getTranslation()));
    }


    @Override
    public CrudRepository<BookExcerpt, Long> getRepository() {
        return bookExcerptRepository;
    }

    @Override
    public GenericTrackableEntityService getParentService() {
        return bookService;
    }

    @Override
    public Book getParentEntity(BookExcerpt child) {
        return child.getBook();
    }


    //TODO: combine two methods, call in controller
}
