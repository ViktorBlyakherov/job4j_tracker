package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("I'm drive");
    }

    @Override
    public void passengers(int countPassengers) {
        System.out.println("Driving with " + countPassengers + " passengers");
    }

    @Override
    public float fill(int volumeToFill) {
        float fuelPrice = 52.40f;
        System.out.println("I'm filling");
        return volumeToFill * fuelPrice;
    }
}
