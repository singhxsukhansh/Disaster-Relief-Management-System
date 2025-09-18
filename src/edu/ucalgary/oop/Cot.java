package edu.ucalgary.oop;

public class Cot extends Supply {
    private String roomNumber;
    private String gridLocation;

    public Cot(int supplyID, String type, int quantity, String roomNumber, String gridLocation) {
        super(supplyID, type, quantity);
        this.setRoomNumber(roomNumber);
        this.setGridLocation(gridLocation);
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        if (roomNumber == null || roomNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Room number cannot be null or empty.");
        }
        this.roomNumber = roomNumber;
    }

    public String getGridLocation() {
        return gridLocation;
    }

    public void setGridLocation(String gridLocation) {
        if (gridLocation == null || gridLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("Grid location cannot be null or empty.");
        }
        this.gridLocation = gridLocation;
    }
}
