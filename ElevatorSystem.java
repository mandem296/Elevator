import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;

public class ElevatorSystem {
    public static void main(String[] args) {
        Elevator elevator1 = new Elevator("Elevator 1");
        Elevator elevator2 = new Elevator("Elevator 2");

        // Create a thread pool for elevator tasks
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the floor where you want to go (or -1 to exit): ");
            int destinationFloor = scanner.nextInt();

            if (destinationFloor == -1) {
                break; // Exit the program
            }

            // Determine which elevator is closer
            int distanceToElevator1 = Math.abs(destinationFloor - elevator1.currentFloor);
            int distanceToElevator2 = Math.abs(destinationFloor - elevator2.currentFloor);

            if (distanceToElevator1 <= distanceToElevator2) {
                executorService.submit(() -> {
                    elevator1.move(destinationFloor);
                });
            } else {
                executorService.submit(() -> {
                    elevator2.move(destinationFloor);
                });
            }
        }

        // Shutdown the thread pool when done
        executorService.shutdown();
    }
}