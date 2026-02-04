package filter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultFilterTest {

	private DefaultFilter defaultFilter = null;

	@BeforeEach
	void init() {
		this.defaultFilter = new DefaultFilter();
	}

	@Test
	void testConstructor() {
		String testKey = "key";
		String testOperation = "op";
		String testValue = "value";
		this.defaultFilter = new DefaultFilter(testKey, testOperation, testValue);
		for (String key : this.defaultFilter.getFilterRules().keySet()) {
			assertEquals(key, testKey);
		}

	}

	@Test
	void testConstructorWithRegex() {
		String testKey = "key";
		String testOperation = "op";
		String testValue = "value";
		String testRegex = "*[]$";
		this.defaultFilter = new DefaultFilter(testKey, testOperation, testValue, testRegex);
		assertEquals(this.defaultFilter.regex, testRegex);

	}

	@Test
	void inputDoesNotMatcheAnyPropertyRule() {
		Map<String, String> user = new HashMap<>();
		user.put("name", "Joe");
		user.put("lastname", "Bloggs");
		user.put("role", "administrator");
		user.put("age", "35");

		// create rule that can accept the above custom input
		this.defaultFilter.addFilterRule("firstname", "=", "Joe");

		this.defaultFilter.matches(user);
		assertFalse(this.defaultFilter.matches(user));
	}

//	@Test
//	void inputMatchesAlphaNumericRule() {
//		Map<String, String> user = new HashMap<>();
//		user.put("firstname", "Joe");
//		user.put("lastname", "Bloggs");
//		user.put("role", "administrator");
//		user.put("age", "35");
//
//		// create rule that can accept the above custom input
//		this.defaultFilter.addFilterRule("firstname", "=", "Joe");
//
//		// test the custom input, should return "Joe Bloggs" because in case of
//		// alphanumeric property it returns
//		// key and value
//		assertEquals(this.defaultFilter.matches(user), "firstname Joe".toLowerCase());
//
//		// test alphaNumeric different value than filter
//		user.put("firstname", "not joe");
//		assertNotEquals(this.defaultFilter.matches(user), "firstname Joe".toLowerCase());
//	}

	@Test
	void inputMatchesNumericRule() {
		Map<String, String> user = new HashMap<>();
		user.put("firstname", "Joe");
		user.put("lastname", "Bloggs");
		user.put("role", "administrator");
		user.put("age", "35");

		// create rule that can accept the above custom input
		this.defaultFilter.addFilterRule("age", "=", "35");

		// test the custom input, should return "Joe Bloggs" because in case of
		// alphanumeric property it returns
		// key and value
//		this.defaultFilter.matches(user);
		assertTrue(this.defaultFilter.matches(user));
	}

	@Test
	void inputMatchesLessThanNumericRule() {
		Map<String, String> user = new HashMap<>();
		user.put("firstname", "Joe");
		user.put("lastname", "Bloggs");
		user.put("role", "administrator");
		user.put("age", "34");

		// create rule that can accept the above custom input
		this.defaultFilter.addFilterRule("age", "<", "35");

		// test the custom input, should return "Joe Bloggs" because in case of
		// alphanumeric property it returns
		// key and value
//		this.defaultFilter.matches(user);
		assertTrue(this.defaultFilter.matches(user));

		// test for > age
		user.put("age", "36");
//		this.defaultFilter.matches(user);
		assertFalse(this.defaultFilter.matches(user));
	}

	@Test
	void inputMatchesLessThanOrEqualNumericRule() {
		Map<String, String> user = new HashMap<>();
		user.put("firstname", "Joe");
		user.put("lastname", "Bloggs");
		user.put("role", "administrator");
		user.put("age", "34");

		// create rule that can accept the above custom input
		this.defaultFilter.addFilterRule("age", "<=", "35");

		// test the custom input, should return "Joe Bloggs" because in case of
		// alphanumeric property it returns
		// key and value
//		this.defaultFilter.matches(user);
		assertTrue(this.defaultFilter.matches(user));

		// test for > age
		user.put("age", "36");
//		this.defaultFilter.matches(user);
		assertFalse(this.defaultFilter.matches(user));
	}

	@Test
	void inputMatchesLessGreaterThanNumericRule() {
		Map<String, String> user = new HashMap<>();
		user.put("firstname", "Joe");
		user.put("lastname", "Bloggs");
		user.put("role", "administrator");
		user.put("age", "36");

		// create rule that can accept the above custom input
		this.defaultFilter.addFilterRule("age", ">", "35");

		// test the custom input, should return "Joe Bloggs" because in case of
		// alphanumeric property it returns
		// key and value
		this.defaultFilter.matches(user);
		assertTrue(this.defaultFilter.matches(user));

		// test for > age
		user.put("age", "34");
		this.defaultFilter.matches(user);
		assertFalse(this.defaultFilter.matches(user));
	}

	@Test
	void inputMatchesLessGreaterThanOrEqualNumericRule() {
		Map<String, String> user = new HashMap<>();
		user.put("firstname", "Joe");
		user.put("lastname", "Bloggs");
		user.put("role", "administrator");
		user.put("age", "35");

		// create rule that can accept the above custom input
		this.defaultFilter.addFilterRule("age", ">=", "35");

		// test the custom input, should return "Joe Bloggs" because in case of
		// alphanumeric property it returns
		// key and value
		this.defaultFilter.matches(user);
		assertTrue(this.defaultFilter.matches(user));

		// test for > age
		user.put("age", "34");
		this.defaultFilter.matches(user);
		assertFalse(this.defaultFilter.matches(user));
	}

	@Test
	void testInputMatchesRegex() {
		Map<String, String> user = new HashMap<>();
		user.put("firstname", "Joe");
		user.put("lastname", "Bloggs");
		user.put("role", "administrator");
		user.put("age", "35");

		// this regerular expression matches any string containing 'name' in it.
		String testRegex = "^.*[name]$";
//		String testName = "firstname joe";
		this.defaultFilter.setRegex(testRegex);

		// adding a generic rule. it doesn't matter in this case
		this.defaultFilter.addFilterRule("age", ">=", "35");

		// the regext should match and state of filter must be true.
		// also it should return in this case "firstname Joe";
//		String returnedProperty = this.defaultFilter.matches(user);

		assertTrue(this.defaultFilter.matches(user));
//		assertEquals(returnedProperty, testName);

	}

	@Test
	void testAddFilterRule() {
		String testProperty = "name";
		String testOperation = "=";
		String testValue = "25";

		this.defaultFilter.addFilterRule(testProperty, testOperation, testValue);
		assertTrue(this.defaultFilter.getFilterRules() != (null));
	}

	@Test
	void testCheckIfPropertyExists() {
		String testProperty = "name";
		String testOperation = "=";
		String testValue = "25";

		String testInputProperty = "name";

		this.defaultFilter.addFilterRule(testProperty, testOperation, testValue);

		assertTrue(this.defaultFilter.checkIfPropertyExists(testInputProperty) == true);
	}

}
