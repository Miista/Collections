package net.palmund.collections;

public interface Classifier<TIn, TOut> {
	public TIn evaluate(TOut object);
}