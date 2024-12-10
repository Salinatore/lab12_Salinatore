package it.unibo.es3;

import java.util.Set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class LogicsImpl implements Logics{

    private final Set<Pair<Integer, Integer>> activePosition = new HashSet<>();
    private final int size;
    
    public LogicsImpl(int size) {
        final Random random = new Random();
        for(int i = 0; i < 3; i++) {
            activePosition.add(new Pair<Integer,Integer>(random.nextInt(size), random.nextInt(size)));
        }
        this.size = size;
    }

    @Override
    public Set<Pair<Integer, Integer>> activePointsSet() {
        return Collections.unmodifiableSet(activePosition);
    }
    
    @Override
    public void doIteration() {
        Set<Pair<Integer, Integer>> neighbor = new HashSet<>();
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                Pair<Integer, Integer> pair = new Pair<>(x, y);
                if (this.activePosition.stream().anyMatch(e -> this.areNeighbor(e, pair))) {
                    neighbor.add(pair);
                }
            }
        }
        activePosition.addAll(neighbor);
    }

    private Boolean areNeighbor(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
        return (Math.abs(p1.getX() - p2.getX()) <= 1) && (Math.abs(p1.getY() - p2.getY()) <= 1);
    }

    @Override
    public Boolean isFinished() {
        return activePosition.size() == (size * size);
    }

}
