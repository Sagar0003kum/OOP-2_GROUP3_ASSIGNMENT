
package sait.driveMasters.problemdomain;

public abstract class Vehicle {
    private int carId;
    private String vehicleType;
    private String subtype;
    private int speed;
    private double fuel;
    private int seats;
    private int year;
    private String drivetrain;
    private double price;
    private int quantity;

    public Vehicle(int carId, String vehicleType, String subtype, int speed, double fuel,
                   int seats, int year, String drivetrain, double price, int quantity) {
        this.carId = carId;
        this.vehicleType = vehicleType;
        this.subtype = subtype;
        this.speed = speed;
        this.fuel = fuel;
        this.seats = seats;
        this.year = year;
        this.drivetrain = drivetrain;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters
    public int getCarId() { return carId; }
    public String getVehicleType() { return vehicleType; }
    public String getSubtype() { return subtype; }
    public int getSpeed() { return speed; }
    public double getFuel() { return fuel; }
    public int getSeats() { return seats; }
    public int getYear() { return year; }
    public String getDrivetrain() { return drivetrain; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    // Abstract method for printing details
    public abstract String toString();
}
