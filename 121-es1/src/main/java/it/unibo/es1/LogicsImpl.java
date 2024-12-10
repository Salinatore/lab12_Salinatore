package it.unibo.es1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {
	private final List<Integer> values;

	public LogicsImpl(int size) {
		values = new ArrayList<>(Collections.nCopies(size,0));	
	}

	@Override
	public int size() {
		return this.values.size();
	}

	@Override
	public List<Integer> values() {
		return List.copyOf(this.values);
	}

	@Override
	public List<Boolean> enablings() {
		return this.values.stream()
				.map(value -> value < values.size())
				.toList();
	}

	@Override
	public int hit(int elem) {
		var afterHit = this.values.get(elem) + 1;
		this.values.set(elem, afterHit);
		return this.values.get(afterHit);
	}

	@Override
	public String result() {
		return this.values.stream()
				.map(value -> value.toString())
				.collect(Collectors.joining("|" , "<<", ">>"));
	}

	@Override
	public boolean toQuit() {
		return this.values.stream()
				.allMatch(value -> value.equals(this.values.get(0)));
	}
}
