package sait.driveMasters.problemdomain;

public class SUV extends Vehicle {
    public SUV(int carId, String vehicleType, String subtype, int speed, double fuel,
               int seats, int year, String drivetrain, double price, int quantity) {
        super(carId, vehicleType, subtype, speed, fuel, seats, year, drivetrain, price, quantity);
    }

    @Override
    public String toString() {
        return "Car ID:\t\t" + getCarId() + "\n" +
               "Vehicle Type:\t" + getVehicleType() + "\n" +
               "Sub Type:\t" + getSubtype() + "\n" +
               "Speed:\t\t" + getSpeed() + "\n" +
               "Fuel:\t\t" + getFuel() + "\n" +
               "Number of Seats:\t" + getSeats() + "\n" +
               "Year:\t\t" + getYear() + "\n" +
               "DriveTrain:\t\t" + getDrivetrain() + "\n" +
               "Price:\t\t" + getPrice() + "\n" +
               "Available:\t\t" + getQuantity() + "\n" +
               "DriveTrain:\t" + getDrivetrain() + "\n";
    }
}
