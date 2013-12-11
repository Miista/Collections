/*
 * Copyright 2013 (c) S&oslash;ren Palmund
 * 
 * Licensed under the License described in LICENSE (the "License"); you may not
 * use this file except in compliance with the License.
 */

package net.palmund.collections;

public class NPKeyValuePair<T, V> {
	private T key;
	private V value;

	public NPKeyValuePair(T key, V value) {
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