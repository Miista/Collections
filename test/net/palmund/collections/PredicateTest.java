package net.palmund.collections;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PredicateTest {
	List<String> list;
	
	@Before
	public void setUp() {
		list = new ArrayList<String>();
		list.add("hej");
		list.add("med");
		list.add("dig");
	}
	
	@Test
	public void testFilterByPredicate() {
		Collection<String> filteredList = NSCollection.filter(list, new Predicate<String>() {
												@Override
												public boolean evaluate(String object) {
													return object.contains("e");
												}
											});
		assertEquals(2, filteredList.size());
	}
	
	@Test
	public void testFindByPredicate() {
		String string = NSCollection.find(list, new Predicate<String>() {
			@Override
			public boolean evaluate(String object) {
				return object.contains("m");
			}
		});
		assertEquals("med", string);
	}
}