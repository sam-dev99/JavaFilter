package filter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogicalFilterTest {

	private LogicalFilter logicalFilter = null;

	@BeforeEach
	void init() {
		this.logicalFilter = new LogicalFilter();
	}

	@Test
	void testConstructor() {
		HashSet<Filter> testFilters = new HashSet<Filter>();
		testFilters.add(new DefaultFilter());
		testFilters.add(new TrueFilter());
		String testLogicalOperation = "or";

		this.logicalFilter = new LogicalFilter(testFilters, testLogicalOperation);

	}

	@Test
	void testLogicalTrueORFilter() {

		this.logicalFilter.setLogicalOperation("or");
		HashSet<Filter> testFilters = new HashSet<Filter>();

		// create dummy filters of different types, we will test 'true or false'

		Map<String, String> user = new HashMap<>();
		user.put("firstname", "Joey");
		user.put("lastname", "Bloggs");
		user.put("role", "administrator");
		user.put("age", "35");

		DefaultFilter testDefaultFilter = new DefaultFilter("firstname", "=", "Joe");

		TrueFilter testTrueFilter = new TrueFilter();

		testFilters.add(testDefaultFilter);
		testFilters.add(testTrueFilter);

		this.logicalFilter.addFilter(testFilters);

//		Boolean returnedState = this.logicalFilter.matches(user);
		assertTrue(this.logicalFilter.matches(user));

	}

	@Test
	void testLogicalFalseORFilter() {
		this.logicalFilter.setLogicalOperation("or");
		HashSet<Filter> testFilters = new HashSet<Filter>();

		// create dummy filters of different types, we will test 'true or false'

		Map<String, String> user = new HashMap<>();
		user.put("firstname", "Joey");
		user.put("lastname", "Bloggs");
		user.put("role", "administrator");
		user.put("age", "35");

		DefaultFilter testDefaultFilter = new DefaultFilter("firstname", "=", "Joe");

		FalseFilter testFalseFilter = new FalseFilter();

		testFilters.add(testDefaultFilter);
		testFilters.add(testFalseFilter);

		this.logicalFilter.addFilter(testFilters);

//		Boolean returnedState = this.logicalFilter.matches(user);
		assertFalse(this.logicalFilter.matches(user));

	}

	@Test
	void testLogicalTrueANDFilter() {

		this.logicalFilter.setLogicalOperation("and");
		HashSet<Filter> testFilters = new HashSet<Filter>();

		// create dummy filters of different types, we will test 'true or false'

		Map<String, String> user = new HashMap<>();
		user.put("firstname", "Joe");
		user.put("lastname", "Bloggs");
		user.put("role", "administrator");
		user.put("age", "35");

		DefaultFilter testDefaultFilter = new DefaultFilter("firstname", "=", "Joe");

		TrueFilter testTrueFilter = new TrueFilter();

		testFilters.add(testDefaultFilter);
		testFilters.add(testTrueFilter);

		this.logicalFilter.addFilter(testFilters);

//		Boolean returnedState = this.logicalFilter.matches(user);
		assertTrue(this.logicalFilter.matches(user));

	}

	@Test
	void testLogicalFalseANDFilter() {
		this.logicalFilter.setLogicalOperation("and");
		HashSet<Filter> testFilters = new HashSet<Filter>();

		// create dummy filters of different types, we will test 'true or false'

		Map<String, String> user = new HashMap<>();
		user.put("firstname", "Joey");
		user.put("lastname", "Bloggs");
		user.put("role", "administrator");
		user.put("age", "35");

		DefaultFilter testDefaultFilter = new DefaultFilter("firstname", "=", "Joe");

		TrueFilter testTrueFilter = new TrueFilter();

		testFilters.add(testDefaultFilter);
		testFilters.add(testTrueFilter);

		this.logicalFilter.addFilter(testFilters);

//		Boolean returnedState = this.logicalFilter.matches(user);
		assertFalse(this.logicalFilter.matches(user));

	}

}
