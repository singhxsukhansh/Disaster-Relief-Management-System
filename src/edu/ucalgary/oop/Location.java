package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Location {
    private String name;
    private String address;
    private final List<DisasterVictim> occupants;
    private final List<Supply> supplies;

    public Location(String name, String address) {
        setName(name);
        setAddress(address);
        this.occupants = new ArrayList<>();
        this.supplies = new ArrayList<>();
    }

    // ---------------- Getters ----------------
    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public DisasterVictim[] getOccupants() {
        return occupants.toArray(new DisasterVictim[0]);
    }

    public Supply[] getSupplies() {
        return supplies.toArray(new Supply[0]);
    }

    // ---------------- Setters ----------------
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Location name cannot be null or empty.");
        }
        this.name = name;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Location address cannot be null or empty.");
        }
        this.address = address;
    }

    // ---------------- Occupants ----------------
    public void addOccupant(DisasterVictim victim) {
        if (victim == null) {
            throw new IllegalArgumentException("Victim cannot be null.");
        }
        if (occupants.contains(victim)) {
            throw new IllegalArgumentException("Victim is already an occupant.");
        }
        occupants.add(victim);
        victim.setLocation(this);
    }

    public void removeOccupant(DisasterVictim victim) {
        if (!occupants.remove(victim)) {
            throw new IllegalArgumentException("Victim not found in location.");
        }
        victim.setLocation(null);
    }

    // ---------------- Supplies ----------------
    public void addSupply(Supply supply) {
        if (supply == null) {
            throw new IllegalArgumentException("Supply cannot be null.");
        }
        if (supplies.contains(supply)) {
            throw new IllegalArgumentException("Supply already exists in location.");
        }
        supplies.add(supply);
    }

    public void removeSupply(Supply supply) {
        if (!supplies.remove(supply)) {
            throw new IllegalArgumentException("Supply not found in location.");
        }
    }
}
