package csulb.cecs323.model;

import javax.persistence.*;

@Entity
//@NamedNativeQuery(
//        name="ReturnBooks",
//        query = "SELECT * " +
//                "FROM   BOOKS " +
//                "WHERE  isbn = ? ",
//        resultClass = Publishers.class
//)
@Table (uniqueConstraints = {
        @UniqueConstraint(name = "UniqueTitleAndAuthoringEntityName", columnNames = { "title", "authoringEntityName" }),
        @UniqueConstraint(name = "UniqueTitleAndPublisherName", columnNames = { "title", "publisherName" })})
public class Books {
    @Id
    @Column(length = 17, nullable = false)
    // ISBN of book
    private int isbn;

    @Column(length = 80, nullable = false)
    // The title of the book
    private String title;

    @Column(nullable = false)
    // The year the book was published
    private int yearPublished;

    @ManyToOne
    @JoinColumn(name = "publisherName", referencedColumnName = "name", nullable = false)
    // Publisher of book
    private Publishers publishers;

    @ManyToOne
    @JoinColumn(name = "authoringEntityName", referencedColumnName = "name", nullable = false)
    // Author entity of book
    private AuthoringEntities entities;

    // Default constructor
    public Books() { }

    // Constructor creating book with a publisher, some type of author, title, and the year it was published
    public Books(Publishers publishers, AuthoringEntities entities, int isbn, String title, int yearPublished) {
        this.publishers = publishers;
        this.entities = entities;
        this.isbn = isbn;
        this.title = title;
        this.yearPublished = yearPublished;
    }

    public Publishers getPublishers() {
        return publishers;
    }

    public void setPublishers(Publishers publishers) {
        this.publishers = publishers;
    }

    public AuthoringEntities getAuthoringEntities() {
        return entities;
    }

    public void setAuthoringEntities(AuthoringEntities entities) {
        this.entities = entities;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    @Override
    public String toString() {
        return this.getPublishers() + ", " + this.getAuthoringEntities() + ", ISBN: " + this.isbn + " " +
                ", Title: " + this.title + ", Year Published: " + this.yearPublished + "\n";
    }
}