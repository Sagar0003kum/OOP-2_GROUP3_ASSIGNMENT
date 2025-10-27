package sait.driveMasters.problemdomain;

public class Hatchback extends Vehicle {
    private String hatchType;

    public Hatchback(int carId, String vehicleType, String subtype, int speed, double fuel,
                     int seats, int year, String drivetrain, double price,
                     int quantity, String hatchType) {
        super(carId, vehicleType, subtype, speed, fuel, seats, year, drivetrain, price, quantity);
        this.hatchType = hatchType;
    }
    public String getHatchType() {
        return hatchType;
    }


    @Override
    public String toString() {
        String hatchDesc = "";
        switch (hatchType.toUpperCase()) {
            case "S": hatchDesc = "Standard Liftgate"; break;
            case "T": hatchDesc = "Split Liftgate"; break;
            case "P": hatchDesc = "Power Liftgate"; break;
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
               "Hatch Type:\t" + hatchDesc + "\n";
    }
}
