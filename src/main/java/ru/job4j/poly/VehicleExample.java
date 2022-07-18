package ru.job4j.poly;

public class VehicleExample {
    public static void main(String[] args) {
        Vehicle plane = new Plane();
        Vehicle train = new Train();
        Vehicle bus = new HighWayBus();
        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = plane;
        vehicles[1] = train;
        vehicles[2] = bus;
        for (Vehicle vehicle : vehicles) {
            vehicle.move();
        }
    }
}
