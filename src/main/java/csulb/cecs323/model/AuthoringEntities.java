package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@NamedNativeQuery(
        name="ReturnAuthoringEntities",
        query = "SELECT * " +
                "FROM   AUTHORINGENTITIES " +
                "WHERE  email = ? ",
        resultClass = Publishers.class
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AuthoringEntities {
    @Id
    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 80, nullable = false)
    private String name;

    @OneToMany (mappedBy = "entities",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private ArrayList<Books> books = new ArrayList<>();

    public AuthoringEntities() { }

    public AuthoringEntities(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addBooks(Books book) {
        books.add(book);
        book.setAuthoringEntities(this);
    }

    public void removeBooks(Books book) {
        if (this.books != null) {
            books.remove(book);
            book.setAuthoringEntities(null);
        }
    }

    public ArrayList<Books> getBooks() { return books; }

    public void setBooks(ArrayList<Books> books) { this.books = books; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
