package app.service;

import app.model.Book;
import app.model.Member;

import java.util.List;
import java.util.Optional;

public interface BookService extends GenericTrackableEntityService<Book, Member>{
    void setAuthorService(AuthorService authorService);
}
