class ElevatorRequest {
    public String user;
    public int floor;
    public long time;

    public ElevatorRequest(String user, int floor) {
        this.user = user;
        this.floor = floor;
        this.time = System.currentTimeMillis();
    }

    public String getUser() {
        return user;
    }

    public int getFloor() {
        return floor;
    }

    public long getTime() {
        return time;
    }
}
