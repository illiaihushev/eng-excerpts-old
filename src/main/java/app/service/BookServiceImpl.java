package app.service;

import app.model.*;
import app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;

@Service
@PropertySource("classpath:update_depth.properties")
public class BookServiceImpl extends GenericTrackableEntityServiceAbstr<Book, Member> implements BookService {
    private BookRepository bookRepository;
    private MemberService memberService;
    private AuthorService authorService;

    @Value("${update_depth.on_create.author}")
    public int UPDATE_DEPTH_ON_CREATE;

    @Value("${update_depth.on_update.author}")
    public int UPDATE_DEPTH_ON_UPDATE;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository/*, AuthorService authorService*/, MemberService memberService) {
        this.bookRepository = bookRepository;
//        this.authorService = authorService;
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
    public boolean isChangesValuable(Book oldEnt, Book newEnt) {
        return (!oldEnt.getAuthors().equals(newEnt.getAuthors())
                || oldEnt.getTitle().equals(newEnt.getTitle()));
    }

    @Override
    public CrudRepository<Book, Long> getRepository() {
        return bookRepository;
    }

    @Override
    public GenericTrackableEntityService getParentService() {
        return memberService;
    }

    @Override
    public Member getParentEntity(Book child) {
        return child.getAddedBy();
    }

    @Override
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

//    public List<CompleteTrackableEntity> getMostRelevantEvents(List<CompleteTrackableEntity> books, List<CompleteTrackableEntity> bookExcerpts) {
//        int bookIndex = 0;
//        int excerptIndex = 0;
//        List<CompleteTrackableEntity> result = new ArrayList<>();
//        while ((bookIndex + excerptIndex < /*|||*/3)) {
//            CompleteTrackableEntity currentBook = books.get(bookIndex);
//            CompleteTrackableEntity currentExcerpt = bookExcerpts.get(excerptIndex);
//            if (currentExcerpt.getRelatedTimestamp().compareTo(currentBook.getRelatedTimestamp()) <= 0) {
//                result.add(currentExcerpt);
//                excerptIndex++;
//            } else {
//                result.add(currentBook);
//                bookIndex++;
//            }
//        }
//        return result;
//    }


    @Override
    public void cascadeUpdateLastModifiedProp(Book child, int depth, boolean isActualLastModified) {
        if (!isActualLastModified) {
            child.setLastModified(new Timestamp(System.currentTimeMillis()));
            bookRepository.save(child);
        }
        if (depth > 0) {
            Set<Author> authors = child.getAuthors();
            for (Author auhtor : authors) {
                authorService.cascadeUpdateLastModifiedProp(auhtor, -1, false);
            }
            memberService.cascadeUpdateLastModifiedProp(child.getAddedBy(), --depth, false);
        } else {
            if (depth == 0) {
                memberService.cascadeUpdateLastModifiedProp(child.getAddedBy(), --depth, false);
            }
        }
    }
}
