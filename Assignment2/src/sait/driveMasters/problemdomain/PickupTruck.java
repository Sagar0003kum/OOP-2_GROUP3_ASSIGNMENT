package sait.driveMasters.problemdomain;

/**
 * PickupTruck class extends Vehicle.
 * Represents pickup trucks with cargo bed type and capacity.
 */
public class PickupTruck extends Vehicle {
    private String cargoBeds;
    private int cargoCapacity;

    public PickupTruck(int carId, String vehicleType, String subtype, int speed, double fuel,
                       int seats, int year, String drivetrain, double price, int quantity,
                       String cargoBeds, int cargoCapacity) {
        super(carId, vehicleType, subtype, speed, fuel, seats, year, drivetrain, price, quantity);
        this.cargoBeds = cargoBeds.trim().toUpperCase();  // normalize immediately
        this.cargoCapacity = cargoCapacity;
    }

    public String getCargoBeds() {
        return cargoBeds;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
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
                "\nCargo Capacity:\t" + cargoCapacity +
                "\nCargo Beds:\t" + cargoBeds + "\n";
    }
}
