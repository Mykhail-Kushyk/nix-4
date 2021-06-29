package ua.com.alevel.racing;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {

    private final int distance;
    private final Map<Horse, Integer> horsesAndPlaces = new ConcurrentHashMap<>();
    private final Set<Horse> participants = ConcurrentHashMap.newKeySet();
    private final AtomicInteger counter = new AtomicInteger();
    private final Phaser phaser = new Phaser(1);

    Race(int distance) {
        this.distance = distance;
    }

    public synchronized void startRacing() {
        horsesAndPlaces.clear();
        counter.set(1);
        phaser.bulkRegister(participants.size());
        for (Horse horse : participants) {
            new Thread(horse, horse.name).start();
        }
        phaser.arriveAndAwaitAdvance();
        System.out.println("All horses Started the race!");
        phaser.arriveAndAwaitAdvance();
        System.out.println("All horses finished the race!");

    }

    public int getPlace(Horse horse) {
        return horsesAndPlaces.get(horse);
    }

    final static class Horse implements Runnable {

        private int distance;
        private final String name;
        private Race race;

        public Horse(String name) {
            this.name = name;
        }

        public void addToRace(Race race) {
            race.participants.remove(this);
            this.race = race;
            race.participants.add(this);
        }

        @Override
        public void run() {
            race.phaser.arriveAndAwaitAdvance();
            while (distance < race.distance) {
                move();
                delay();
            }
            int place = race.counter.getAndIncrement();
            race.horsesAndPlaces.put(this, place);
            race.phaser.arriveAndDeregister();
        }

        private void move() {
            distance += ThreadLocalRandom.current().nextInt(100, 200);
        }

        private void delay() {
            try {
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(400, 500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}