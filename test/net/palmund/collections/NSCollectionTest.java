package net.palmund.collections;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

public class NSCollectionTest {

	@Ignore
	@Test
	public void testToMapKeyValuePairOfT1T2Array() {
		fail("Not yet implemented");
	}

	@Test
	public void testToMapObjectArray_Strings() {
		Map<String, String> map = NPCollections.toMap(String.class, String.class, "hej", "med", "dig", "!");
		assertEquals(2, map.size());
	}

	@Test
	public void testToMapObjectArray_Ints() {
		Map<Integer, Integer> map = NPCollections.toMap(Integer.class, Integer.class, 0, 1, 2, 3);
		assertEquals(2, map.size());
	}

	@Test
	public void testToMapObjectArray_MixedTypes() {
		try {
			@SuppressWarnings("unused")
			Map<Integer, Integer> map = NPCollections.toMap(Integer.class, Integer.class, 0, 1, "dig", "!");
		} catch (ClassCastException e) {
			assertTrue(true);
			return;
		}
		fail();
	}

	@Test
	public void testToUnsafeMapObjectArray_MixedTypes() {
		Map<Integer, Integer> map = NPCollections.toUnsafeMap(Integer.class, Integer.class, 0, 1, "dig", "!");
		assertEquals(1, map.size());
	}

	@Test
	public void toMapUsingTransformer() {
		@SuppressWarnings("serial")
		Collection<String> collection = new ArrayList<String>() {{
			add("key1:value1");
			add("key2:value2");
			add("key3:value3");
			add("key4:value4");
		}};
		Transformer<String, NPKeyValuePair<String, String>> transformer = new Transformer<String, NPKeyValuePair<String, String>>() {
			@Override
			public NPKeyValuePair<String, String> transform(String object) {
				String[] chunks = object.split(":");
				return new NPKeyValuePair<String, String>(chunks[0], chunks[1]);
			}
		};
		Map<String, String> transformedMap = NPCollections.toMap(collection, transformer);
		assertEquals(4, transformedMap.size());
		assertEquals(transformedMap.get("key1"), "value1");
		assertEquals(transformedMap.get("key2"), "value2");
		assertEquals(transformedMap.get("key3"), "value3");
		assertEquals(transformedMap.get("key4"), "value4");
	}
}