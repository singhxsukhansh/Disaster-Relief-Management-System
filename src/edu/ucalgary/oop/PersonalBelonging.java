package edu.ucalgary.oop;

public class PersonalBelonging extends Supply {
    private String description;

    public PersonalBelonging(int supplyID, String type, int quantity, String description) {
        super(supplyID, type, quantity);
        this.setDescription(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.description = description;
    }
}
