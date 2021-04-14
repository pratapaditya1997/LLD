import enums.VehicleType;
import models.ParkingLot;
import models.Vehicle;
import repository.InMemoryRepository;
import repository.Repository;
import services.NearestSlotAssignStrategy;
import services.ParkingLotService;
import services.ParkingLotServiceImpl;
import services.SlotAssignStrategy;

import java.util.Scanner;

public class ParkingLotDriver {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter length of Parking Lot");
        Integer length = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter width of Parking Lot");
        Integer width = Integer.parseInt(scanner.nextLine());

        ParkingLot parkingLot = new ParkingLot(length, width);
        Repository repository = new InMemoryRepository();
        SlotAssignStrategy assignStrategy = new NearestSlotAssignStrategy(repository);
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(repository, parkingLot, assignStrategy);

        System.out.println("Parking Lot Service");
        while(true) {
            printMenu();
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    parkVehicle(parkingLotService, repository);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("enter a valid input");
                    break;
            }
        }
    }

    private static void parkVehicle(ParkingLotService service, Repository repository) {
        System.out.println("Enter registration number");
        String regNumber = scanner.nextLine();

        System.out.println("Enter vehicle type");
        String vehicleType = scanner.nextLine();

        System.out.println("Enter vehicle color");
        String color = scanner.nextLine();

        System.out.println("Enter entry point");
        Integer entryPoint = Integer.parseInt(scanner.nextLine());

        VehicleType vehicleType1 = getVehicleType(vehicleType);
        Vehicle vehicle = new Vehicle(vehicleType1, regNumber, color);
        repository.addVehicle(vehicle);

        service.getParkingSlot(regNumber, entryPoint);
    }

    private static VehicleType getVehicleType(String vehicleType) {
        switch (vehicleType) {
            case "CAR":
                return VehicleType.CAR;
            case "BIKE":
                return VehicleType.BIKE;
            case "TRUCK":
                return VehicleType.TRUCK;
            case "BUS":
                return VehicleType.BUS;
        }
        return null;
    }

    private static void printMenu() {
        System.out.println("1. Park Vehicle");
        System.out.println("2. Exit");
    }
}
