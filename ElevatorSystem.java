import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;


public class ElevatorSystem {
    public static void main(String[] args) {
        
        Elevator elevator1 = new Elevator("Elevator 1");
        Elevator elevator2 = new Elevator("Elevator 2");
        Elevator elevator3 = new Elevator("Elevator 3");
        Elevator elevator4 = new Elevator("Elevator 4");
        Elevator elevator5 = new Elevator("Elevator 5");

        // Create a thread pool for elevator tasks
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the floor where you want to go (or -1 to exit): ");
            int destinationFloor = scanner.nextInt();

            if (destinationFloor == -1) {
                break; // Exit the program
            }

            // Determine which elevator is closer
            int[] distances = {
                Math.abs(destinationFloor - elevator1.currentFloor),
                Math.abs(destinationFloor - elevator2.currentFloor),
                Math.abs(destinationFloor - elevator3.currentFloor),
                Math.abs(destinationFloor - elevator4.currentFloor),
                Math.abs(destinationFloor - elevator5.currentFloor)
            };

            int closestElevatorIndex = 0;
            int closestDistance = distances[0];

            for (int i = 1; i < distances.length; i++) {
                if (distances[i] < closestDistance) {
                    closestElevatorIndex = i;
                    closestDistance = distances[i];
                }
            }

            final int selectedElevatorIndex = closestElevatorIndex;

            executorService.submit(() -> {
                switch (selectedElevatorIndex) {
                    case 0:
                        elevator1.move(destinationFloor);
                        break;
                    case 1:
                        elevator2.move(destinationFloor);
                        break;
                    case 2:
                        elevator3.move(destinationFloor);
                        break;
                    case 3:
                        elevator4.move(destinationFloor);
                        break;
                    case 4:
                        elevator5.move(destinationFloor);
                        break;
                }
            });
        }

        // Shutdown the thread pool when done
        executorService.shutdown();

        while (true) {
            System.out.print("Enter your name (or type 'exit' to exit): ");
            String user = scanner.nextLine();

            if ("exit".equalsIgnoreCase(user)) {
                break; // Exit the program
            }

            System.out.print("Enter the floor where you want to go: ");
            int destinationFloor = scanner.nextInt();

            if (destinationFloor == -1) {
                break; // Exit the program
            }

            // Determine which elevator is closer
            int[] distances = {
                Math.abs(destinationFloor - elevator1.currentFloor),
                Math.abs(destinationFloor - elevator2.currentFloor),
                Math.abs(destinationFloor - elevator3.currentFloor),
                Math.abs(destinationFloor - elevator4.currentFloor),
                Math.abs(destinationFloor - elevator5.currentFloor)
            };

            int closestElevatorIndex = 0;
            int closestDistance = distances[0];

            for (int i = 1; i < distances.length; i++) {
                if (distances[i] < closestDistance) {
                    closestElevatorIndex = i;
                    closestDistance = distances[i];
                }
            }

            final int selectedElevatorIndex = closestElevatorIndex;

            ElevatorRequest request = new ElevatorRequest(user, destinationFloor);
            switch (selectedElevatorIndex) {
                case 0:
                    elevator1.addRequest(request);
                    break;
                case 1:
                    elevator2.addRequest(request);
                    break;
                case 2:
                    elevator3.addRequest(request);
                    break;
                case 3:
                    elevator4.addRequest(request);
                    break;
                case 4:
                    elevator5.addRequest(request);
                    break;
            }
        }

        executorService.shutdown();
    }
}