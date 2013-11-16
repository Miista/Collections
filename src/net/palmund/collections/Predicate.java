/*
 * Copyright 2013 (c) S¿ren Palmund
 * 
 * Licensed under the License described in LICENSE (the "License"); you may not
 * use this file except in compliance with the License.
 */

package net.palmund.collections;

/**
 * A predicate acts as a filter.
 * 
 * @author palmund
 * 
 * @param <T>
 *            the object type to operate on
 */
public interface Predicate<T> {
	/**
	 * Answers whether an object passes the evaluation of a certain condition.
	 * 
	 * @param object
	 *            the object the be tested
	 * @return <code>true</code> if the
	 */
	public boolean evaluate(T object);
}