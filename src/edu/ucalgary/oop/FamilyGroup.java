package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FamilyGroup {
    private final List<DisasterVictim> members;

    public FamilyGroup() {
        this.members = new ArrayList<>();
    }

    public void addMember(DisasterVictim victim) {
        if (victim == null) {
            throw new IllegalArgumentException("Victim cannot be null.");
        }
        if (members.contains(victim)) {
            throw new IllegalArgumentException("Victim is already a member of this family group.");
        }
        members.add(victim);
        victim.setFamilyGroup(this);
    }

    public void removeMember(DisasterVictim victim) {
        if (!members.remove(victim)) {
            throw new IllegalArgumentException("Victim is not a member of this family group.");
        }
        victim.setFamilyGroup(null);
    }

    public DisasterVictim[] getMembers() {
        return members.toArray(new DisasterVictim[0]);
    }
}
