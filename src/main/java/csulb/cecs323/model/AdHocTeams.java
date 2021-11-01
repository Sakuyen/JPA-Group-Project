package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AdHocTeams extends AuthoringEntities {
    @OneToMany (mappedBy = "teams",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<AdHocTeamMembers> teamMembers;

    public AdHocTeams() { }

    public AdHocTeams(String name, String email) {
        super(name, email);
    }

    public void addTeamMembers(AdHocTeamMembers teamMember) {
        teamMembers.add(teamMember);
        teamMember.setAdHocTeams(this);
    }

    public void removeTeamMembers(AdHocTeamMembers teamMember) {
        if (this.teamMembers != null) {
            teamMembers.remove(teamMember);
            teamMember.setAdHocTeams(null);
        }
    }

    public List<AdHocTeamMembers> getTeamMembers() { return teamMembers; }

    public void setTeamMembers(List<AdHocTeamMembers> teamMembers) { this.teamMembers = teamMembers; }
}
