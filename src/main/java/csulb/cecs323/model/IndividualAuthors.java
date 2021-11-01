package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class IndividualAuthors extends AuthoringEntities {
    @OneToMany (mappedBy = "authors",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private ArrayList<AdHocTeamMembers> teamMembers;

    /**
     * The default constructor for the IndividualAuthors class. This is a single author and lists
     * their information
     */
    public IndividualAuthors() { }

    /**
     * The constructor for the IndividualAuthors class
     * @param name      The name of the individual authors
     * @param email     The email of the individual authors
     */
    public IndividualAuthors(String name, String email) {
        super(name, email, "Individual Author");
    }

    /**
     * Method that adds team member to Ad Hoc Team
     * @param teamMember      The name of the individual author who is being added as a member of the Ad Hoc Team
     */
    public void addTeamMembers(AdHocTeamMembers teamMember) {
        teamMembers.add(teamMember);
        teamMember.setIndividualAuthors(this);
    }

    /**
     * Method that removes team member from Ad Hoc Team
     * @param teamMember      The name of the individual author who is being removed as a member of the Ad Hoc Team
     */
    public void removeTeamMembers(AdHocTeamMembers teamMember) {
        if (this.teamMembers != null) {
            teamMembers.remove(teamMember);
            teamMember.setIndividualAuthors(null);
        }
    }

    /**
     * Method that gets a list of the names of the members of an Ad Hoc Team
     * @return      An array list of the names of the team members of an Ad Hoc Team
     */
    public ArrayList<AdHocTeamMembers> getTeamMembers() { return teamMembers; }

    public void setTeamMembers(ArrayList<AdHocTeamMembers> teamMembers) { this.teamMembers = teamMembers; }
}
