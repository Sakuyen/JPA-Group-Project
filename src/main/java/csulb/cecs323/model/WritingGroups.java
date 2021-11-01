package csulb.cecs323.model;

import javax.persistence.*;

@Entity
public class WritingGroups extends AuthoringEntities {
    @Column(length = 80, nullable = false)
    private String headWriter;

    @Column(nullable = false)
    private int yearFormed;

    public WritingGroups() { }

    public WritingGroups(String name, String email, String headWriter, int yearFormed) {
        super(name, email);
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
}
