package app.service;

import app.model.Author;
import app.model.Book;
import app.model.Member;
import app.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;

@Service
@PropertySource("classpath:update_depth.properties")
public class AuthorServiceImpl extends GenericTrackableEntityServiceAbstr<Author, Member> implements AuthorService {
    @Value("${update_depth.on_create.author}")
    public int UPDATE_DEPTH_ON_CREATE;

    @Value("${update_depth.on_update.author}")
    public int UPDATE_DEPTH_ON_UPDATE;

    private final AuthorRepository authorRepository;
    private final BookService bookService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookService bookService, MemberService memberService) {
        this.authorRepository = authorRepository;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @PostConstruct
    private void init() {
        bookService.setAuthorService(this);
    }

    @Override
    public void cascadeUpdateLastModifiedProp(Author child, int depth, boolean isActualLastModified) {
        if (!isActualLastModified) {
            child.setLastModified(new Timestamp(System.currentTimeMillis()));
            authorRepository.save(child);
        }
        if (depth > 0) {
            Set<Book> books = child.getBooks();
            for (Book book : books) {
                bookService.cascadeUpdateLastModifiedProp(book, -1, false);
            }
            memberService.cascadeUpdateLastModifiedProp(child.getAddedBy(), -1, false);
        }
        if (depth == 0) {
            memberService.cascadeUpdateLastModifiedProp(child.getAddedBy(), -1, false);
        }
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
    public boolean isChangesValuable(Author oldEnt, Author newEnt) {
        return (!oldEnt.getFirstName().equals(newEnt.getFirstName())
                || oldEnt.getLastName().equals(newEnt.getLastName()));
    }

    @Override
    public CrudRepository<Author, Long> getRepository() {
        return authorRepository;
    }

    @Override
    public GenericTrackableEntityService getParentService() {
        return memberService;
    }

    @Override
    public Member getParentEntity(Author child) {
        return child.getAddedBy();
    }
}
