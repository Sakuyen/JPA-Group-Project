package csulb.cecs323.model;

import javax.persistence.*;

@Entity
@NamedNativeQuery(
        name="ReturnAdHocTeamMembers",
        query = "SELECT * " +
                "FROM   ADHOCTEAMMEMBERS " +
                "WHERE  authors = ? AND teams = ? ",
        resultClass = AdHocTeamMembers.class
)
public class AdHocTeamMembers {
    @Id
    @ManyToOne
    @JoinColumn(name = "individualAuthorEmail", referencedColumnName = "email", nullable = false)
    private IndividualAuthors authors;

    @Id
    @ManyToOne
    @JoinColumn(name = "adHocTeamEmail", referencedColumnName = "email", nullable = false)
    private AdHocTeams teams;

    /**
     * The default constructor for the AdHocTeamMembers class. This is an
     * assosiation class of AdHocTeam and IndividualAuthors
     */
    public AdHocTeamMembers() { }

    /**
     * The constructor for the AdHocTeamMembers class
     * @param authors References the individual author object that is an AdHocTeamMember
     * @param teams References the AdHocTeam object that the inidividual author is a member of
     */
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
