package net.palmund.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NSCollection {
	public static <P, T extends P> Collection<T> filter(Collection<T> collection, Predicate<P> predicate) {
		if (collection == null) {
			return null;
		}
		Collection<T> filteredList = new ArrayList<T>();
		for (T item : collection) {
			if (predicate.evaluate(item)) {
				filteredList.add(item);
			}
		}
		return filteredList;
	}
	
	public static <T> T find(Collection<T> collection, Predicate<T> predicate) {
		if (collection == null) {
			return null;
		}
		for (T item : collection) {
			if (predicate.evaluate(item)) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Transforms each and every object in a {@link Collection} into another object.
	 * 
	 * @param collection the original collection of objects
	 * @param transformer a {@link Transformer}
	 * 
	 * @return a collection of transformed objects
	 */
	public static <O, TO, T3 extends O> Collection<TO> transformList(Collection<T3> collection, Transformer<O, TO> transformer) {
		if (collection == null) {
			return null;
		}
		Collection<TO> transformedList = new ArrayList<TO>();
		for (O object : collection) {
			transformedList.add(transformer.transform(object));
		}
		return transformedList;
	}
	
	/**
	 * 
	 * @param collection
	 * @param classifier
	 * @return
	 */
	public static <T1, T2, T3 extends Collection<T2>> Map<T1, T3> classify(Collection<T2> collection, Classifier<T1, T2> classifier) {
		Map<T1, T3> map = new HashMap<T1, T3>();
		for (T2 item : collection) {
			T1 classifiedItem = classifier.evaluate(item);
			if (map.containsKey(classifiedItem)) {
				map.get(classifiedItem).add(item);
			} else {
				@SuppressWarnings("unchecked")
				T3 list = (T3)new ArrayList<T2>();
				list.add(item);
				map.put(classifiedItem, list);
			}
		}
		return map;
	}

	public static <T1, T2> Map<T1, T2> toMap(KeyValuePair<T1, T2> ... pairs) {
		Map<T1, T2> map = new HashMap<T1, T2>();
		for (KeyValuePair<T1, T2> pair : pairs) {
			map.put(pair.getKey(), pair.getValue());
		}
		return map;
	}
}