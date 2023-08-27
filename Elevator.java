import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import com.example.ElevatorSystem.ElevatorSystem;
import java.util.Scanner;


class Elevator {
    private int currentFloor;
    private boolean isMoving;

    public Elevator() {
        currentFloor = 1; // Start on the first floor
        isMoving = false;
    }

    public void move(int destinationFloor) {
        if (destinationFloor == currentFloor) {
            System.out.println("Elevator is already on floor " + currentFloor);
            return;
        }

        if (isMoving) {
            System.out.println("Elevator is already in motion.");
            return;
        }

        isMoving = true;

        // Simulate elevator movement
        while (currentFloor != destinationFloor) {
            if (currentFloor < destinationFloor) {
                currentFloor++;
            } else {
                currentFloor--;
            }

            System.out.println("Elevator is on floor " + currentFloor);

            try {
                Thread.sleep(1000); // Simulate time for moving between floors
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isMoving = false;
        System.out.println("Elevator has reached floor " + currentFloor);
    }
}