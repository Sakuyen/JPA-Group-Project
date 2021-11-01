package csulb.cecs323.model;

import javax.persistence.*;

@Entity
public class WritingGroups extends AuthoringEntities {
    @Column(length = 80)
    // Head writer of writing group
    private String headWriter;

    // The year the writing group was formed
    private int yearFormed;

    // Default constructor
    public WritingGroups() { }

    // Constructor that creates a writing group with a name, email, head writer, and the year formed
    public WritingGroups(String name, String email, String headWriter, int yearFormed) {
        super(name, email, "Writing Group");
        this.headWriter = headWriter;
        this.yearFormed = yearFormed;
    }

    public String getHeadWriter() {
        return headWriter;
    }

    public void setHeadWriter(String headWriter) {
        this.headWriter = headWriter;
    }

    public int getYearFormed() {
        return yearFormed;
    }

    public void setYearFormed(int yearFormed) {
        this.yearFormed = yearFormed;
    }

    @Override
    public String toString() {
        return "Author Name: " + this.getName() + ", Author Email: " + this.getEmail() + ", Head Writer: " + this.headWriter +
                ", Year Formed: " + this.yearFormed + "\n";
    }
}