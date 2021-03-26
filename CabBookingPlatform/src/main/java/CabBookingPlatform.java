import exception.CabNotFoundException;
import model.*;
import repository.Repository;
import repository.RepositoryImpl;
import service.cab.CabService;
import service.cab.CabServiceImpl;
import service.matching.ClosestLocationMatchingStrategy;
import service.matching.MatchingService;
import service.matching.MatchingServiceImpl;
import service.matching.MatchingStrategy;
import service.rider.RiderService;
import service.rider.RiderServiceImpl;

import java.util.List;
import java.util.Scanner;

public class CabBookingPlatform {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Repository repository = new RepositoryImpl();
        MatchingStrategy strategy = new ClosestLocationMatchingStrategy(repository);
        CabService cabService = new CabServiceImpl(repository);
        RiderService riderService = new RiderServiceImpl(repository);
        MatchingService matchingService = new MatchingServiceImpl(strategy, repository);

        System.out.println("Cab Booking Platform");
        while(true) {
            printMenu();
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    registerDriver(cabService);
                    break;
                case 2:
                    registerCab(cabService);
                    break;
                case 3:
                    updateCabLocation(cabService);
                    break;
                case 4:
                    toggleCabAvailability(cabService);
                    break;
                case 5:
                    registerRider(riderService);
                    break;
                case 6:
                    getRiderHistory(riderService);
                    break;
                case 7:
                    bookCabForRider(matchingService);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Please enter a valid input");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Register Driver");
        System.out.println("2. Register Cab");
        System.out.println("3. Update Cab Location");
        System.out.println("4. Toggle Cab Availability");
        System.out.println("5. Register Rider");
        System.out.println("6. Get Rider History");
        System.out.println("7. Book cab for Rider");
        System.out.println("8. Exit");
    }

    private static void registerDriver(CabService cabService) {
        System.out.println("Enter driver name");
        String name = scanner.nextLine();
        System.out.println("Enter driver age");
        int age = Integer.parseInt(scanner.nextLine());
        Driver driver = new Driver(name, age);
        Driver returnedDriver = cabService.registerDriver(driver);
        System.out.println(returnedDriver);
    }

    private static void registerCab(CabService cabService) {
        System.out.println("Enter driver ID");
        int driverId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter x co-ordinate of Cab's location");
        int x = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter y co-ordinate of Cab's location");
        int y = Integer.parseInt(scanner.nextLine());
        Location location = new Location(x, y);
        Cab cab = new Cab(driverId, location, true);
        Cab returnedCab = cabService.registerCab(cab);
        System.out.println(returnedCab);
    }

    private static void updateCabLocation(CabService cabService) {
        System.out.println("Enter the cab ID");
        int cabId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter updated x co-ordinate of Cab's location");
        int x = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter updated y co-ordinate of Cab's location");
        int y = Integer.parseInt(scanner.nextLine());
        Location location = new Location(x, y);
        try {
            cabService.updateCabLocation(cabId, location);
        } catch (CabNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void toggleCabAvailability(CabService cabService) {
        System.out.println("Enter the cab ID");
        int cabId = Integer.parseInt(scanner.nextLine());
        try {
            cabService.toggleCabAvailability(cabId);
        } catch (CabNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void registerRider(RiderService riderService) {
        System.out.println("Enter rider name");
        String name = scanner.nextLine();
        System.out.println("Enter rider age");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter x co-ordinate of rider's location");
        int x = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter y co-ordinate of rider's location");
        int y = Integer.parseInt(scanner.nextLine());
        Location location = new Location(x, y);
        Rider rider = new Rider(name, age, location);
        Rider returnedRider = riderService.registerRider(rider);
        System.out.println(returnedRider);
    }

    private static void getRiderHistory(RiderService riderService) {
        System.out.println("Enter the rider ID");
        int riderId = Integer.parseInt(scanner.nextLine());
        List<RideDetail> rideDetails = riderService.getRiderHistory(riderId);
        rideDetails.stream().forEach(System.out::println);
    }

    private static void bookCabForRider(MatchingService matchingService) {
        System.out.println("Enter the rider ID");
        int riderId = Integer.parseInt(scanner.nextLine());
        RideDetail rideDetail = matchingService.matchRiderToCab(riderId);
        System.out.println(rideDetail);
    }
}
