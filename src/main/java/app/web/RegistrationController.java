package app.web;

import app.model.Artist;
import app.model.Book;
import app.model.Member;
import app.repository.ArtistRepository;
import app.repository.BookExcerptRepository;
import app.repository.BookRepository;
import app.service.*;
import app.web.model.MemberRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@SessionAttributes("member")
@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookExcerptRepository test;

    @Autowired
    private MemberService memberService;

    @Autowired
    private BookExcerptService bookExcerptService;

    @Autowired
    private SongExcerptService songExcerptService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;


    @ModelAttribute("member")
    public Member getMember(){
        return new Member();
    }

    @PostMapping
    public ResponseEntity<?> addMember(HttpSession session, @RequestBody MemberRegistrationForm form) {
        Member member = memberService.findByLogin("randyblythe");
//        bookService.findLastAddedBooks(member);
//        bookService.findLastCreatedBooks(member);

//        List<CompleteTrackableEntity> listCopy =  test.test1(1L, 1);
//        List<CompleteTrackableEntity> listCopy =  test.test1(1L, 1, PageRequest.of(0,1));
//        List<CompleteTrackableEntity> list = bookExcerptService.getRecentChanges(member, 3);
//        List<CompleteTrackableEntity>list = bookExcerptService.findLastCreatedBookExcerpts(member);
//        list.addAll(bookExcerptService.findLastCreatedBookExcerpts (member));
//        CompleteTrackableEntityUtil.sortByMostRecentWithIndexes(list, new int[0]);
//        list.sort(new CompleteTrackableEntityByDateComparator());
        artistRepository.save(new Artist("Colour Haze", "psych, stoner", "Germany"));
        bookRepository.save(new Book("IPhuck 10"));
        artistRepository.save(new Artist("Crowbar", "sludge", "USA"));
        bookRepository.save(new Book("Generation P"));
//        artistRepository.save(new Artist("Colour Haze", "psych, stoner", "Germany"));



//        listCopy.get(0).getRelatedTimestamp();
//        listCopy.get(0).getActionName();
//        listCopy.get(0).getItem();


//        personCars.get(0).getItem();
        //...........

//        if (memberService.findByLogin(form.getLogin()) == null) {
//            Member registeredMember = memberService.createMember(form.getLogin(), form.getPassword(), form.getEmail());
//            memberService.save(registeredMember);
//            session.setAttribute("member", registeredMember);
//            return ResponseEntity.ok(registeredMember);
//        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Login is already in use");
//        }
    }
}
