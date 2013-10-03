package net.palmund.collections;

public interface Transformer<TIn, TOut> {
	public TOut transform(TIn object);
}