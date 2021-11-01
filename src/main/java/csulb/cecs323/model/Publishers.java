package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
//@NamedNativeQuery(
//        name="ReturnPublishers",
//        query = "SELECT * " +
//                "FROM   PUBLISHERS " +
//                "WHERE  name = ? ",
//        resultClass = Publishers.class
//)
public class Publishers {
    @Id
    @Column(length = 80, nullable = false)
    // Name of publisher
    private String name;

    @Column(unique = true, length = 80, nullable = false)
    // Email of publisher
    private String email;

    @Column(unique = true, length = 24, nullable = false)
    // Phone number of publisher
    private String phone;

    @OneToMany (mappedBy = "publishers",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    // List of books
    private ArrayList<Books> books = new ArrayList<>();

    // Default constructor, creates publisher with no values
    public Publishers() { }

    // Constructor that creates  a publisher with a name, phone number, and email
    public Publishers(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Adds a book to a list of books
     * @param book The book being added to the list of books
     */
    public void addBooks(Books book) {
        books.add(book);
        book.setPublishers(this);
    }

    /**
     * Removes an existing book from a list of books
     * @param book The book being removed from the list of books
     */
    public void removeBooks(Books book) {
        if (this.books != null) {
            books.remove(book);
            book.setPublishers(null);
        }
    }

    public ArrayList<Books> getBooks() { return books; }

    public void setBooks(ArrayList<Books> books) { this.books = books; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Publisher Name: " + this.name + ", Publisher Email: " + this.email + ", Publisher Phone: " + this.phone + "\n";
    }
}