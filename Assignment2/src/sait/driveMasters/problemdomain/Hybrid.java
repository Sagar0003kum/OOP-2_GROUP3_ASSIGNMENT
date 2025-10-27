package sait.driveMasters.problemdomain;

/**
 * Hybrid class extends Vehicle.
 * Represents hybrid vehicles with powertrain type and electric range.
 */
public class Hybrid extends Vehicle {
    private String powerTrain;
    private int electricRange;

    public Hybrid(int carId, String vehicleType, String subtype, int speed, double fuel,
                  int seats, int year, String drivetrain, double price, int quantity,
                  String powerTrain, int electricRange) {
        super(carId, vehicleType, subtype, speed, fuel, seats, year, drivetrain, price, quantity);
        this.powerTrain = powerTrain.trim().toUpperCase();   // normalize immediately
        this.electricRange = electricRange;
    }

    public String getPowerTrain() {
        return powerTrain;
    }

    public int getElectricRange() {
        return electricRange;
    }

    @Override
    public String toString() {
        return "Car ID:\t\t" + getCarId() +
                "\nVehicle Type:\t" + getVehicleType() +
                "\nSub Type:\t" + getSubtype() +
                "\nSpeed:\t\t" + getSpeed() +
                "\nFuel:\t\t" + getFuel() +
                "\nNumber of Seats:\t" + getSeats() +
                "\nYear:\t\t" + getYear() +
                "\nDriveTrain:\t\t" + getDrivetrain() +
                "\nPrice:\t\t" + getPrice() +
                "\nAvailable:\t\t" + getQuantity() +
                "\nPower Train:\t" + powerTrain +
                "\nElectric Range:\t" + electricRange + "\n";
    }
}
