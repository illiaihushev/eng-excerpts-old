package app.model;

import app.model.base.CreatedByMemberEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class Book extends CreatedByMemberEntity {
    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();

    @NotEmpty
    private String title;

    public Book(@NotEmpty String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (!title.equals(book.title)) return false;

        return (authors.stream()
                .filter(book.authors::contains)
                .collect(Collectors.toSet()).size() > 0);
    }

    @Override
    public int hashCode() {
        int result = authors.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
