package sait.driveMasters.problemdomain;

public class Sedan extends Vehicle {
    private String trunkSize; // L, S, or M

    public Sedan(int carId, String vehicleType, String subtype, int speed, double fuel,
                 int seats, int year, String drivetrain, double price,
                 int quantity, String trunkSize) {
        super(carId, vehicleType, subtype, speed, fuel, seats, year, drivetrain, price, quantity);
        this.trunkSize = trunkSize;
    }
    public String getTrunkSize() {
        return trunkSize;
    }

    @Override
    public String toString() {
        String sizeDescription = "";
        switch (trunkSize.toUpperCase()) {
            case "L": sizeDescription = "Large/spacious Trunk"; break;
            case "S": sizeDescription = "Small Trunk"; break;
            case "M": sizeDescription = "Moderate Trunk"; break;
        }

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
               "Trunk Size:\t" + sizeDescription + "\n";
    }
}
