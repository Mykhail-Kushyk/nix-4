package ua.com.alevel.racing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        Race race = new Race(1000);
        Race.Horse[] horses = {
                new Race.Horse("First horse"),
                new Race.Horse("Second horse"),
                new Race.Horse("Third horse"),
                new Race.Horse("Fourth horse"),
                new Race.Horse("Fifth horse")
        };
        for (Race.Horse horse : horses) {
            horse.addToRace(race);
        }
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Please, choose horse you bet (1-5): ");
            int chosenPlace = Integer.parseInt(reader.readLine());
            race.startRacing();
            int place = race.getPlace(horses[chosenPlace - 1]);
            System.out.println("Your horse has taken " + place + " of " + horses.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}