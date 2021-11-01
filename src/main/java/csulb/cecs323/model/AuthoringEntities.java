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

    @Column(length = 31, nullable = false)
    private String authoringEntityType;

    @OneToMany (mappedBy = "entities",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private ArrayList<Books> books = new ArrayList<>();

    /**
     * The default constructor for the AuthoringEntities class. This is the super class of the authoring
     * entity types: individual author, Ad Hoc Team, and Writing Group
     */
    public AuthoringEntities() { }

    /**
     * The constructor for the AuthoringEntities class
     * @param name      The name of the author entity
     * @param email     The email of the author entity
     * @param authoringEntityType The type of the author entity; ie: Ad Hoc Team, Individual, Writing Group
     */
    public AuthoringEntities(String name, String email, String authoringEntityType) {
        this.name = name;
        this.email = email;
        this.authoringEntityType = authoringEntityType;
    }

    /**
     * This method adds books from a list of books that the author entity has authored
     * @param book     The book that the author entity has authored being added
     */
    public void addBooks(Books book) {
        books.add(book);
        book.setAuthoringEntities(this);
    }

    /**
     * This method removes books  from a list of books that the author entity has authored
     * @param book     The book that the author entity has authored being removed
     */
    public void removeBooks(Books book) {
        if (this.books != null) {
            books.remove(book);
            book.setAuthoringEntities(null);
        }
    }

    /**
     * This method get the books that the author has authored
     * @return An array list of the books that the author has authored
     */
    public ArrayList<Books> getBooks() { return books; }

    /**
     * This method sets the books that the author has authored
     * @param books An array list of the books that the author has authored
     */
    public void setBooks(ArrayList<Books> books) { this.books = books; }

    /**
     * This method gets the name of the authoring entity
     * @return the name of the authoring entity
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of the authoring entity
     * @param name the name of the authoring entity
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method gets the email of the authoring entity
     * return the email of the authoring entity
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method sets the email of the authoring entity
     * @param email the email of the authoring entity
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method gets the authoring type of the authoring entity
     * return the authoring type of the authoring entity
     */
    public String getAuthoringEntityType() {
        return authoringEntityType;
    }

    /**
     * This method sets the authoring type of the authoring entity
     * @param authoringEntityType the authoring type of the authoring entity
     */
    public void setAuthoringEntityType(String authoringEntityType) {
        this.authoringEntityType = authoringEntityType;
    }
}
