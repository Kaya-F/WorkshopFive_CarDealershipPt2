package com.kf.dealership;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DealershipFileManager {
    String dealershipFileLocation = "./src/main/java/com/kf/dealership/inventory.txt";

    public Dealership getDealership() {

        try {
            FileInputStream dealershipFile = new FileInputStream(dealershipFileLocation);
            Scanner scanner = new Scanner(dealershipFile);

            String firstLine_dealershipData = scanner.nextLine();
            String[] dealershipDataArr = firstLine_dealershipData.split(Pattern.quote("|"));
            Dealership initialDealership = new Dealership(dealershipDataArr[0],dealershipDataArr[1],dealershipDataArr[2]);

            String vehicleData;
            while (scanner.hasNextLine()) {
                vehicleData = scanner.nextLine();
                String[] vehicleDataArr = vehicleData.split(Pattern.quote("|"));

                Vehicle currentVehicle = new Vehicle(
                        Integer.parseInt(vehicleDataArr[0]),
                        Integer.parseInt(vehicleDataArr[1]),
                        vehicleDataArr[2],
                        vehicleDataArr[3],
                        vehicleDataArr[4],
                        vehicleDataArr[5],
                        Integer.parseInt(vehicleDataArr[6]),
                        Double.parseDouble(vehicleDataArr[7])
                );

                initialDealership.addVehicle(currentVehicle);

            }

            scanner.close();
            return initialDealership;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveDealership(Dealership dealership) {

        try {
            FileWriter fileWriter = new FileWriter(dealershipFileLocation);

            fileWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone() + "\n");

            for (Vehicle vehicle: dealership.getAllVehicles()){
                fileWriter.write(
                        vehicle.getVin() + "|" +
                                vehicle.getYear() + "|" +
                                vehicle.getMake() + "|" +
                                vehicle.getModel() + "|" +
                                vehicle.getVehicleType() + "|" +
                                vehicle.getColor() + "|" +
                                vehicle.getOdometer() + "|" +
                                vehicle.getPrice() + "\n"
                );


            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Unsuccessful adding of new vehicle.");
            throw new RuntimeException(e);
        }

    }
}
