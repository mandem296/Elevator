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
    public int currentFloor;
    public boolean isMoving;
    public String elevatorId;

    public Elevator(String elevatorId) {
        this.elevatorId = elevatorId;
        currentFloor = 1; // Start on the first floor
        isMoving = false;
    }

    public void move(int destinationFloor) {
        if (destinationFloor == currentFloor) {
            System.out.println(elevatorId + " is already on floor " + currentFloor);
            return;
        }

        if (isMoving) {
            System.out.println(elevatorId + " is already in motion.");
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

            System.out.println(elevatorId + " is on floor " + currentFloor);

            try {
                Thread.sleep(1000); // Simulate time for moving between floors
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isMoving = false;
        System.out.println(elevatorId + " has reached floor " + currentFloor);
    }
}