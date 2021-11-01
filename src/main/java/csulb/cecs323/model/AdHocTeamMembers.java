package csulb.cecs323.model;

import javax.persistence.*;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;

@Entity
public class AdHocTeamMembers {
    @Id
    @ManyToOne
    @JoinColumn(name = "individualAuthorEmail", referencedColumnName = "email", nullable = false)
    private IndividualAuthors authors;

    @Id
    @ManyToOne
    @JoinColumn(name = "adHocTeamEmail", referencedColumnName = "email", nullable = false)
    private AdHocTeams teams;

    public AdHocTeamMembers() { }

    public AdHocTeamMembers(IndividualAuthors authors, AdHocTeams teams) {
        this.authors = authors;
        this.teams = teams;
    }

    public IndividualAuthors getIndividualAuthors() {
        return authors;
    }

    public void setIndividualAuthors(IndividualAuthors authors) {
        this.authors = authors;
    }

    public AdHocTeams getAdHocTeams() {
        return teams;
    }

    public void setAdHocTeams(AdHocTeams teams) {
        this.teams = teams;
    }
}
