import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;

public class ElevatorSystem {
    public static void main(String[] args) {
        Elevator elevator1 = new Elevator();
        Elevator elevator2 = new Elevator();

        // Create a thread pool for elevator tasks
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the floor for elevator 1 (or -1 to exit): ");
            int floor = scanner.nextInt();

            if (floor == -1) {
                break; // Exit the program
            }

            executorService.submit(() -> {
                elevator1.move(floor);
            });
        }

        scanner.close();



        // Shutdown the thread pool when done
        executorService.shutdown();
    }
}