package edu.ucalgary.oop;

public abstract class Supply {
    private int supplyID;
    private String type;
    private int quantity;

    public Supply(int supplyID, String type, int quantity) {
        this.supplyID = supplyID;
        this.setType(type);
        this.setQuantity(quantity);
    }

    public int getSupplyID() {
        return supplyID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Supply type cannot be null or empty.");
        }
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }
}
