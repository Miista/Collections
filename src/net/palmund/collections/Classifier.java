/*
 * Copyright 2013 (c) S&oslash;ren Palmund
 * 
 * Licensed under the License described in LICENSE (the "License"); you may not
 * use this file except in compliance with the License.
 */

package net.palmund.collections;

public interface Classifier<TIn, TOut> {
	public TIn evaluate(TOut object);
}