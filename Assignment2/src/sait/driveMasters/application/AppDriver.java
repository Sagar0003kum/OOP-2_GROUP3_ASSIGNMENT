
package sait.driveMasters.application;

import sait.driveMasters.manager.VehicleManager;

public class AppDriver {
    public static void main(String[] args) {
        VehicleManager manager = new VehicleManager();
        manager.displayMenu();
    }
}
