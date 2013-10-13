package net.palmund.collections;

public class KeyValuePair<T, V> {
	private T key;
	private V value;

	private KeyValuePair(T key, V value) {
		this.key = key;
		this.value = value;
	}

	public T getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
}