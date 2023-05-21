package com.kf;

import com.kf.dealership.Dealership;
import com.kf.dealership.DealershipFileManager;
import com.kf.dealership.Vehicle;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    static Scanner scanner = new Scanner(System.in);
    private DealershipFileManager dealershipFileManager = new DealershipFileManager();

    private void init() {
        this.dealership = dealershipFileManager.getDealership();
    }

    public void display() {
        init();
        System.out.println(this.dealership);
        int userInput;

        do {
            System.out.println("| Welcome to Dealership Manager 2.0 |");
            System.out.println("\t[1] Search");
            System.out.println("\t[2] Add a Vehicle");
            System.out.println("\t[3] Remove a Vehicle");
            System.out.println("\t[4] Sell/Lease a Vehicle");
            System.out.println("\t[99] Exit");
            System.out.print("Command: ");
            userInput = scanner.nextInt();

            switch(userInput) {
                case 1:
                    displaySubMenu();
                    break;
                case 2:
                    processAddVehicleRequest();
                    break;
              case 3:
                    processRemoveVehicleRequest();
                    break;
                case 4:
//                  processSellOrLeaseRequest();
                    break;
                case 99:
                    System.out.println("Exiting Dealership 2.0...");
                    break;
                default:
                    System.out.println("Command not found, please try again. :)");
            }

        } while (userInput == 99);

    }
    public void processAddVehicleRequest(){
        System.out.println("Provide The Following Car Information:");

        System.out.print("VIN (Vehicle Identification Number): ");
        int vin  = scanner.nextInt();

        System.out.print("Year: ");
        int year = scanner.nextInt();

        System.out.print("Make: ");
        String make = scanner.next();

        System.out.print("Model: ");
        String model = scanner.next();

        System.out.print("Vehicle Type: ");
        String vehicleType = scanner.next();

        System.out.print("Color: ");
        String color = scanner.next();

        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();

        System.out.print("Price: ");
        double price = scanner.nextDouble();

        this.dealership.addVehicle(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
        dealershipFileManager.saveDealership(this.dealership);
        System.out.println("Vehicle Added: " + vin + "  " + year + "  " + make + "  " + model + "  " + vehicleType + "  " + color + "  " + odometer + "  " + price);

    }

    public void processRemoveVehicleRequest(){
        System.out.println("Please enter the vehicle number you want to remove");
        ArrayList<Vehicle> allVehicles = this.dealership.getAllVehicles();
        for(int i=0; i<allVehicles.size();i++){
            System.out.println((i+1) + " " + allVehicles.get(i).toString());
        }

        int chosenVehicle = scanner.nextInt();

        this.dealership.removeVehicle(chosenVehicle-1);
        dealershipFileManager.saveDealership(this.dealership);
        System.out.println("Removing Vehicle From Catalogue...");

    }

    public void displaySubMenu() {
        init();
        System.out.println(this.dealership);
        int subInput;

        do {
            System.out.println("| Search |");
            System.out.println("\t[1] Search All Vehicles");
            System.out.println("\t[2] Search Vehicles by Price");
            System.out.println("\t[3] Search Vehicles by Make/Model");
            System.out.println("\t[4] Search Vehicles by Year");
            System.out.println("\t[5] Search Vehicles by Color");
            System.out.println("\t[6] Search Vehicles by Mileage");
            System.out.println("\t[7] Search Vehicles by Type (Car, Truck, SUV, Van) ");
            System.out.println("\t[8] Return");

            System.out.print("Command: ");
            subInput = scanner.nextInt();

            switch(subInput) {
                case 1:
                    processGetAllVehiclesRequest();
                    break;
                case 2:
                    processGetVehiclesByPriceRequest();
                    break;
                case 3:
                    processGetVehiclesByMakeModelRequest();
                    break;
                case 4:
                    processGetVehiclesByYearRequest();
                    break;
                case 5:
                    processGetVehiclesByColorRequest();
                    break;
                case 6:
                    processGetVehiclesByMileageRequest();
                    break;
                case 7:
                    processGetVehiclesByTypeRequest();
                    break;
                case 8:
                   return;
                case 0:
                    System.out.println("Returning... ");
                    break;
                default:
                    System.out.println("Command not found, please try again. :) ");
            }

        } while (subInput == 0);

    }



    public void processGetAllVehiclesRequest() {
        ArrayList<Vehicle> allVehicles = this.dealership.getAllVehicles();
        for(Vehicle vehicle : allVehicles){
            System.out.println(vehicle);
        }
    }
    public void processGetVehiclesByPriceRequest() {
        System.out.println("Enter a Min Price");
        float minValue = scanner.nextFloat();
        System.out.println("Enter a Max Price");
        float maxValue = scanner.nextFloat();

        ArrayList<Vehicle> vehiclesByPrice = this.dealership.getVehiclesByPrice(minValue, maxValue);
        for (Vehicle vehicle: vehiclesByPrice){
            System.out.println(vehicle);
        }

        scanner.nextLine();
    }

    public void processGetVehiclesByMakeModelRequest() {

    }

    public void processGetVehiclesByYearRequest() {

    }

    public void processGetVehiclesByColorRequest() {

    }

    public void processGetVehiclesByMileageRequest() {

    }

    public void processGetVehiclesByTypeRequest() {

    }

}
