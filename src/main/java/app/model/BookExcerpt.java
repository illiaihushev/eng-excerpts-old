package app.model;

import app.model.base.CreatedByMemberEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "book_excerpts")
public class BookExcerpt extends CreatedByMemberEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "books_book_excerpts_fk"))
    private Book book;

    @NotEmpty
    @Column(length = 600)
    private String original;

    @NotEmpty
    @Column(length = 600)
    private String translation;

    private String chapter;

    @NotEmpty
    private Integer page;

    public BookExcerpt(Book book, @NotEmpty String original, @NotEmpty String translation, String chapter, @NotEmpty Integer page) {
        this.book = book;
        this.original = original;
        this.translation = translation;
        this.chapter = chapter;
        this.page = page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookExcerpt)) return false;

        BookExcerpt bookExcerpt = (BookExcerpt) o;

        if (!original.equals(bookExcerpt.original)) return false;
        if (!book.equals(bookExcerpt.book)) return false;
        return getAddedBy().equals(bookExcerpt.getAddedBy());
    }

    @Override
    public int hashCode() {
        int result = original.hashCode();
        result = 31 * result + book.hashCode();
        result = 31 * result + getAddedBy().hashCode();
        return result;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
