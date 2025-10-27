

package sait.driveMasters.manager;

import java.io.*;
import java.util.*;
import sait.driveMasters.problemdomain.*;

public class VehicleManager {
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    private Scanner input = new Scanner(System.in);
    private final String FILE_PATH = "res/vehicles.txt";

    // ----------------------------------------------
    // Constructor
    // ----------------------------------------------
    public VehicleManager() {
        loadVehicles();
    }

    // ----------------------------------------------
    // Load vehicle data from vehicles.txt
    // ----------------------------------------------
    private void loadVehicles() {
        try {
            File file = new File(FILE_PATH);
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine().trim();
                if (line.length() == 0)
                    continue;

                String[] data = line.split(";");
                // ✅ Clean up each value (remove spaces and carriage returns)
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].trim().replace("\r", "");
                }

                int carId = Integer.parseInt(data[0]);
                String type = data[1];
                String subtype = data[2];
                int speed = Integer.parseInt(data[3]);
                double fuel = Double.parseDouble(data[4]);
                int seats = Integer.parseInt(data[5]);
                int year = Integer.parseInt(data[6]);
                String drivetrain = data[7];
                double price = Double.parseDouble(data[8]);
                int quantity = Integer.parseInt(data[9]);

                if (carId / 100000000 == 1) { // Sedan
                    vehicles.add(new Sedan(carId, type, subtype, speed, fuel, seats, year,
                            drivetrain, price, quantity, data[10]));
                } else if (carId / 100000000 == 2) { // Hatchback
                    vehicles.add(new Hatchback(carId, type, subtype, speed, fuel, seats, year,
                            drivetrain, price, quantity, data[10]));
                } else if (carId / 100000000 == 3) { // SUV
                    vehicles.add(new SUV(carId, type, subtype, speed, fuel, seats, year,
                            drivetrain, price, quantity));
                } else if (carId / 100000000 == 4 || carId / 100000000 == 5) { // Hybrid
                    vehicles.add(new Hybrid(carId, type, subtype, speed, fuel, seats, year,
                            drivetrain, price, quantity, data[10],
                            Integer.parseInt(data[11])));
                } else if (carId / 100000000 == 6) { // Pickup Truck
                    vehicles.add(new PickupTruck(carId, type, subtype, speed, fuel, seats, year,
                            drivetrain, price, quantity, data[10],
                            Integer.parseInt(data[11])));
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: vehicles.txt not found.");
        } catch (Exception e) {
            System.out.println("Error reading vehicles file: " + e.getMessage());
        }
    }

    // ----------------------------------------------
    // Display the main menu
    // ----------------------------------------------
    public void displayMenu() {
        int option = 0;
        do {
            System.out.println("Welcome to DriveMasters");
            System.out.println("Please choose any option below");
            System.out.println("---------------------------------");
            System.out.println("1\tPurchase Vehicle");
            System.out.println("2\tDisplay Vehicles by Type");
            System.out.println("3\tDisplay Vehicles by Subtype");
            System.out.println("4\tProduce a Random List of Vehicles");
            System.out.println("5\tSave & Exit\n");
            System.out.print("Enter option: ");
            option = getIntInput();

            switch (option) {
                case 1:
                    purchaseVehicle();
                    break;
                case 2:
                    displayByType();
                    break;
                case 3:
                    displayBySubtype();
                    break;
                case 4:
                    displayRandom();
                    break;
                case 5:
                    saveToFile();
                    System.out.println("\nSaving Vehicles... Done!\n");
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.\n");
                    break;
            }

            if (option != 5)
                System.out.println();

        } while (option != 5);
    }

    // ----------------------------------------------
    // Option 1 - Purchase a vehicle
    // ----------------------------------------------
    private void purchaseVehicle() {
        System.out.print("Enter CarId: ");
        int id = getIntInput();
        boolean found = false;

        for (Vehicle v : vehicles) {
            if (v.getCarId() == id) {
                found = true;
                if (v.getQuantity() > 0) {
                    v.setQuantity(v.getQuantity() - 1);
                    System.out.println("\nThe Vehicle \"" + v.getVehicleType() + " " + v.getSubtype() + "\" has been checked out.\n");
                } else {
                    System.out.println("Sorry, that vehicle is not available.");
                }
                break;
            }
        }

        if (!found)
            System.out.println("No vehicle found with that CarId.\n");
    }

    // ----------------------------------------------
    // Option 2 - Display by vehicle type
    // ----------------------------------------------
    private void displayByType() {
        System.out.print("Enter vehicle type to search for: (Sedan, SUV, Hatchback, Pickup Truck and Hybrid car) ");
        String type = input.nextLine().trim();
        System.out.println("Matching vehicles:");
        for (Vehicle v : vehicles) {
            if (v.getVehicleType().equalsIgnoreCase(type)) {
                System.out.println(v.toString());
            }
        }
    }

    // ----------------------------------------------
    // Option 3 - Display by subtype
    // ----------------------------------------------
    private void displayBySubtype() {
        System.out.println("#\tSub Type");
        System.out.println("1\tSedan");
        System.out.println("2\tHatchBack");
        System.out.println("3\tSUV");
        System.out.println("4\tHybrid");
        System.out.println("5\tPickup Truck\n");

        System.out.print("Enter type of vehicle: ");
        int choice = getIntInput();

        if (choice == 1)
            handleSedanSubtype();
        else if (choice == 2)
            handleHatchbackSubtype();
        else if (choice == 3)
            handleSUVSubtype();
        else if (choice == 4)
            handleHybridSubtype();
        else if (choice == 5)
            handlePickupSubtype();
        else
            System.out.println("Invalid choice.");
    }

    // ----------------------------------------------
    // Subtype Handlers
    // ----------------------------------------------
    private void handleSedanSubtype() {
        System.out.print("Enter a format (L for Large/Spacious trunk, S for Small Trunk, or M for Moderate Trunk): ");
        String format = input.nextLine().trim().toUpperCase();
        System.out.println("\nMatching Vehicles:");
        for (Vehicle v : vehicles) {
            if (v instanceof Sedan) {
                Sedan s = (Sedan) v;
                String trunk = s.getTrunkSize().trim().toUpperCase();
                if (trunk.startsWith(format)) {
                    System.out.println(s.toString());
                }
            }
        }
    }

    private void handleHatchbackSubtype() {
        System.out.print("Enter HatchType (S for Standard Liftgate, T for Split Liftgate, P for Power Liftgate): ");
        String type = input.nextLine().trim().toUpperCase();
        System.out.println("\nMatching Vehicles:");
        for (Vehicle v : vehicles) {
            if (v instanceof Hatchback) {
                Hatchback h = (Hatchback) v;
                String hatch = h.getHatchType().trim().toUpperCase();
                if (hatch.startsWith(type)) {
                    System.out.println(h.toString());
                }
            }
        }
    }

    private void handleSUVSubtype() {
        System.out.print("Enter Drivetrain (AWD for All Wheel Drive, 4WD for Four Wheel Drive): ");
        String drive = input.nextLine().trim().toUpperCase();
        System.out.println("\nMatching Vehicles:");
        for (Vehicle v : vehicles) {
            if (v instanceof SUV) {
                SUV s = (SUV) v;
                if (s.getDrivetrain().trim().equalsIgnoreCase(drive))
                    System.out.println(s.toString());
            }
        }
    }

    private void handleHybridSubtype() {
        System.out.print("Enter a PowerTrain (E for Series Hybrid , A for Parallel Hybrid, PHEV for Plug-in Hybrid): ");
        String power = input.nextLine().trim().toUpperCase();
        System.out.println("\nMatching Vehicles:");
        boolean found = false;

        for (Vehicle v : vehicles) {
            if (v instanceof Hybrid) {
                Hybrid h = (Hybrid) v;
                String pt = h.getPowerTrain().trim().toUpperCase();
                if (pt.equalsIgnoreCase(power)) {
                    System.out.println(h.toString());
                    found = true;
                }
            }
        }

        if (!found)
            System.out.println("No matching hybrid vehicles found.");
    }

    private void handlePickupSubtype() {
        System.out.print("Enter CargoBeds (SB for Short Beds , EB for Extended Beds, DB for Dump Beds): ");
        String bed = input.nextLine().trim().toUpperCase();
        System.out.println("\nMatching Vehicles:");
        boolean found = false;

        for (Vehicle v : vehicles) {
            if (v instanceof PickupTruck) {
                PickupTruck p = (PickupTruck) v;
                String cb = p.getCargoBeds().trim().toUpperCase();
                if (cb.equalsIgnoreCase(bed)) {
                    System.out.println(p.toString());
                    found = true;
                }
            }
        }

        if (!found)
            System.out.println("No matching pickup trucks found.");
    }

    // ----------------------------------------------
    // Option 4 - Display random vehicles
    // ----------------------------------------------
    private void displayRandom() {
        System.out.print("Enter number of vehicles to display: ");
        int count = getIntInput();
        Random rand = new Random();

        System.out.println("\nRandom Vehicles:");
        for (int i = 0; i < count && !vehicles.isEmpty(); i++) {
            int index = rand.nextInt(vehicles.size());
            System.out.println(vehicles.get(index).toString());
            System.out.println("----------------------------------");
        }
    }

    // ----------------------------------------------
    // Option 5 - Save and exit
    // ----------------------------------------------
    private void saveToFile() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH));
            for (Vehicle v : vehicles) {
                if (v instanceof Sedan) {
                    Sedan s = (Sedan) v;
                    pw.println(s.getCarId() + ";" + s.getVehicleType() + ";" + s.getSubtype() + ";" + s.getSpeed() + ";" +
                            s.getFuel() + ";" + s.getSeats() + ";" + s.getYear() + ";" + s.getDrivetrain() + ";" +
                            s.getPrice() + ";" + s.getQuantity() + ";" + s.getTrunkSize().substring(0, 1));
                } else if (v instanceof Hatchback) {
                    Hatchback h = (Hatchback) v;
                    pw.println(h.getCarId() + ";" + h.getVehicleType() + ";" + h.getSubtype() + ";" + h.getSpeed() + ";" +
                            h.getFuel() + ";" + h.getSeats() + ";" + h.getYear() + ";" + h.getDrivetrain() + ";" +
                            h.getPrice() + ";" + h.getQuantity() + ";" + h.getHatchType().substring(0, 1));
                } else if (v instanceof SUV) {
                    SUV s = (SUV) v;
                    pw.println(s.getCarId() + ";" + s.getVehicleType() + ";" + s.getSubtype() + ";" + s.getSpeed() + ";" +
                            s.getFuel() + ";" + s.getSeats() + ";" + s.getYear() + ";" + s.getDrivetrain() + ";" +
                            s.getPrice() + ";" + s.getQuantity());
                } else if (v instanceof Hybrid) {
                    Hybrid h = (Hybrid) v;
                    pw.println(h.getCarId() + ";" + h.getVehicleType() + ";" + h.getSubtype() + ";" + h.getSpeed() + ";" +
                            h.getFuel() + ";" + h.getSeats() + ";" + h.getYear() + ";" + h.getDrivetrain() + ";" +
                            h.getPrice() + ";" + h.getQuantity() + ";" + h.getPowerTrain() + ";" + h.getElectricRange());
                } else if (v instanceof PickupTruck) {
                    PickupTruck p = (PickupTruck) v;
                    pw.println(p.getCarId() + ";" + p.getVehicleType() + ";" + p.getSubtype() + ";" + p.getSpeed() + ";" +
                            p.getFuel() + ";" + p.getSeats() + ";" + p.getYear() + ";" + p.getDrivetrain() + ";" +
                            p.getPrice() + ";" + p.getQuantity() + ";" + p.getCargoBeds() + ";" + p.getCargoCapacity());
                }
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // ----------------------------------------------
    // Helper - safely read integers
    // ----------------------------------------------
    private int getIntInput() {
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.print("Please enter a valid number: ");
        }
        int num = input.nextInt();
        input.nextLine();
        return num;
    }
}
