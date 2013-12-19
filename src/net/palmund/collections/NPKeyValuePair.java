/*
 * Copyright 2013 (c) S&oslash;ren Palmund
 * 
 * Licensed under the License described in LICENSE (the "License"); you may not
 * use this file except in compliance with the License.
 */

package net.palmund.collections;

public class NPKeyValuePair<TKey, TValue> {
	private TKey key;
	private TValue value;

	public NPKeyValuePair(TKey key, TValue value) {
		this.key = key;
		this.value = value;
	}

	public TKey getKey() {
		return key;
	}

	public TValue getValue() {
		return value;
	}
}