package net.palmund.collections;

import static org.junit.Assert.*;

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
		Map<String, String> map = NSCollection.toMap(String.class, String.class, "hej", "med", "dig", "!");
		assertEquals(2, map.size());
	}
	
	@Test
	public void testToMapObjectArray_Ints() {
		Map<Integer, Integer> map = NSCollection.toMap(Integer.class, Integer.class, 0, 1, 2, 3);
		assertEquals(2, map.size());
	}
	
	@Test
	public void testToMapObjectArray_MixedTypes() {
		try {
			Map<Integer, Integer> map = NSCollection.toMap(Integer.class, Integer.class, 0, 1, "dig", "!");
		} catch (ClassCastException e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void testToUnsafeMapObjectArray_MixedTypes() {
		Map<Integer, Integer> map = NSCollection.toUnsafeMap(Integer.class, Integer.class, 0, 1, "dig", "!");
		assertEquals(1, map.size());
	}
}