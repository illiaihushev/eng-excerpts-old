package app.model;

import app.model.base.CoreEntity;
import app.model.base.TrackableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "members")
public class Member extends CoreEntity implements TrackableEntity {

    @OneToMany(mappedBy = "addedBy")
    private List<Book> books;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;

    public Member() {
    }

    public Member(String login, String password, String email) {

    }

    @NotNull
    private Timestamp created;

    @NotNull
    @Column(name = "last_modified")
    private Timestamp lastModified;

    @PrePersist
    private void onCreate() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        this.created = currentTime;
        this.lastModified = currentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;

        Member member = (Member) o;

        return login.equals(member.login);
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public Member getAddedBy() {
        throw new UnsupportedOperationException("Member has no owner");
    }
}
