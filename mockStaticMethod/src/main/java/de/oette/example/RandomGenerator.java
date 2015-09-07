package de.oette.example;

public class RandomGenerator {

    public static int generateRandomFromZeroToHundred()
    {
        double random = Math.random();
        double scaledRandom = random * 100;
        return (int) scaledRandom;
    }

}
