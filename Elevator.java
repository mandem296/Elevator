import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingQueue;


class Elevator {
    public int currentFloor;
    public boolean isMoving;
    public String elevatorId;

    
        

    public LinkedBlockingQueue<ElevatorRequest> requestQueue;

    public Elevator(String elevatorId) {
        this.elevatorId = elevatorId;
        currentFloor = 1; // Start on the first floor
        isMoving = false;

        // Initialize the request queue
        requestQueue = new LinkedBlockingQueue<>();
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


    public void addRequest(ElevatorRequest request) {
        requestQueue.offer(request);
    }

    public void processRequests() {
        while (true) {
            try {
                ElevatorRequest request = requestQueue.poll(1, TimeUnit.SECONDS);

                if (request != null) {
                    move(request.getFloor());
                    System.out.println(elevatorId + " picked up " + request.getUser() + " at floor " + request.getFloor());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}