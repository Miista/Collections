/*
 * Copyright 2013 (c) S¿ren Palmund
 * 
 * Licensed under the License described in LICENSE (the "License"); you may not
 * use this file except in compliance with the License.
 */

package net.palmund.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NPCollections {
	/**
	 * Returns a {@link Collection} with the items that matched the
	 * <i>predicate</i>.
	 * 
	 * @param collection
	 * @param predicate
	 * @return
	 */
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

	/**
	 * Returns the first element in the <i>collection</i> that matches the
	 * <i>predicate</i>.
	 * 
	 * @param collection
	 * @param predicate
	 * @return
	 */
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
	 * Transforms each and every object in a {@link Collection} into another
	 * object.
	 * 
	 * @param collection
	 *            the original collection of objects
	 * @param transformer
	 *            a {@link Transformer}
	 * 
	 * @return a collection of transformed objects
	 */
	public static <O, TO, T3 extends O> Collection<TO> transformList(Collection<T3> collection, Transformer<O, TO> transformer) {
		/*
		 * O: Original value
		 * TO: Transformed Original value
		 * T3: Any type that extends the Original value
		 */
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
				T3 list = (T3) new ArrayList<T2>();
				list.add(item);
				map.put(classifiedItem, list);
			}
		}
		return map;
	}

	/**
	 * Returns a {@link Map} containing the values in the <i>collection</i> by
	 * transforming each of the elements using the supplied {@link Transformer}.
	 * 
	 * @param pairs
	 * @return
	 */
	public static <O, TKey, TValue> Map<TKey, TValue> toMap(Collection<O> collection, Transformer<O, KeyValuePair<TKey, TValue>> transformer) {
		Map<TKey, TValue> map = new HashMap<TKey, TValue>();
		for (O element : collection) {
			KeyValuePair<TKey, TValue> pair = transformer.transform(element);
			map.put(pair.getKey(), pair.getValue());
		}
		return map;
	}
	
	/**
	 * Returns a {@link Map} containing the values in <i>pairs</i>.
	 * 
	 * @param pairs
	 * @return
	 */
	public static <T1, T2> Map<T1, T2> toMap(KeyValuePair<T1, T2>... pairs) {
		Map<T1, T2> map = new HashMap<T1, T2>();
		for (KeyValuePair<T1, T2> pair : pairs) {
			map.put(pair.getKey(), pair.getValue());
		}
		return map;
	}

	/**
	 * Returns a {@link Map} that contains the <i>objects</i> as key-value
	 * pairs. If any of the keys or values does not match their designated class
	 * (<i>keyClass</i> and <i>valueClass</i> respectively) a
	 * {@link ClassCastException} is thrown.
	 * 
	 * @param keyClass
	 * @param valueClass
	 * @param objects
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T1, T2> Map<T1, T2> toMap(Class<T1> keyClass, Class<T2> valueClass, Object... objects) {
		Map<T1, T2> map = new HashMap<T1, T2>();
		T1 key = null;
		T2 value = null;
		for (Object object : objects) {
			if (key != null && value != null) {
				map.put(key, value);
				key = null;
				value = null;
			}
			if (key == null) {
				if (!keyClass.isInstance(object)) {
					throw new ClassCastException("Object cannot be cast to " + keyClass.getCanonicalName());
				}
				key = (T1) object;
				continue;
			}
			if (value == null) {
				if (!valueClass.isInstance(object)) {
					throw new ClassCastException("Object cannot be cast to " + valueClass.getCanonicalName());
				}
				value = (T2) object;
				continue;
			}
		}
		if (key != null && value != null) {
			map.put(key, value);
		}
		return map;
	}

	/**
	 * /** Returns a {@link Map} that contains the <i>objects</i> as key-value
	 * pairs. If a key does not match its designated class (<i>keyClass</i>) nor
	 * it or its value is added. The same goes for the value.
	 * 
	 * @param keyClass
	 * @param valueClass
	 * @param objects
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T1, T2> Map<T1, T2> toUnsafeMap(Class<T1> keyClass, Class<T2> valueClass, Object... objects) {
		Map<T1, T2> map = new HashMap<T1, T2>();
		T1 key = null;
		T2 value = null;
		for (Object object : objects) {
			if (key != null && value != null) {
				map.put(key, value);
				key = null;
				value = null;
			}
			if (key == null) {
				if (!keyClass.isInstance(object)) {
					key = null;
					value = null;
					continue;
				}
				key = (T1) object;
				continue;
			}
			if (value == null) {
				if (!valueClass.isInstance(object)) {
					key = null;
					value = null;
					continue;
				}
				value = (T2) object;
				continue;
			}
		}
		if (key != null && value != null) {
			map.put(key, value);
		}
		return map;
	}
}