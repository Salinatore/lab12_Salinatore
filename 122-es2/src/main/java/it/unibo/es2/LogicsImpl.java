package it.unibo.es2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LogicsImpl implements Logics {
    private List<List<Boolean>> matrix = new ArrayList<>();

    public LogicsImpl(int size) {
        this.matrix = IntStream.range(0, size)
                .mapToObj(row -> IntStream.range(0, size)
                    .mapToObj(e -> false)
                    .collect(Collectors.toCollection(ArrayList::new)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Boolean hit(int row, int column) {
        Boolean currentValue = this.matrix.get(row).get(column);
        this.matrix.get(row).set(column, !currentValue);
        return !currentValue;
    }

    @Override
    public Boolean isFinished() {
        return this.isColumnFull() || this.isRowFull(); 
    }

    private Boolean isRowFull() {
        return matrix.stream()
                .anyMatch(row -> row.stream().allMatch(b -> b));
    }

    private Boolean isColumnFull() {
        return IntStream.range(0, matrix.size())
                .anyMatch(column -> this.matrix.stream().allMatch(e -> e.get(column)));
    }

}
