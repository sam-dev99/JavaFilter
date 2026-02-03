package filter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import Misc.Utils;

public class Main {

	public static void main(String[] args) {

		// Create user resource having various properties:
		Map<String, String> user = new HashMap<>();
		user.put("firstname", "Joey");
		user.put("lastname", "Bloggs");
		user.put("role", "administrator");
		user.put("age", "35");

		// EXAMPLE 1
		// Create a filter which matches all administrators older than 30:
		Filter filter = new DefaultFilter("age", ">", "30"); // Create a filter using your API.

		/**
		 * the following filter makes use of the regular expression *name*, this will
		 * return true whenever a property in the map has the word "name" in it.
		 */
		Filter regexFilter = new DefaultFilter("firstname", "=", "Joe", "^.*[name]+$"); // Create a filter using your
																						// API.
		System.out.println(regexFilter.matches(user));
		System.out.println("Regex filter state:" + Utils.assertTrue(regexFilter.getFilterState())); // Filter should
																									// match.

		// Default Filter returns {property + value}
		System.out.println(filter.matches(user));
		System.out.println("Default filter state: " + Utils.assertTrue(filter.getFilterState())); // Filter should
																									// match.

		user.put("age", "25");
		System.out.println(filter.matches(user));
		System.out.println("Default filter state: " + Utils.assertFalse(!filter.getFilterState())); // Filter should not
																									// match.

		// EXAMPLE 2
		// create boolean literals
		Filter trueFilter = new TrueFilter();
		trueFilter.matches(user);
		System.out.println("True Filter state: " + Utils.assertTrue(trueFilter.getFilterState())); // Filter should
																									// match.
//		
//		
//		// EXAMPLE 3
//		// create logical boolean operators 
		HashSet<Filter> filters = new HashSet<Filter>();
		filters.add(filter);
		filters.add(trueFilter);
		Filter orFilter = new LogicalFilter(filters, "or"); // Create a 'trueFilter OR filter'.

		// the orFilter must return true since trueFilter's state is true.
		orFilter.matches(user);
		System.out.println("Logical OR filter state: " + Utils.assertTrue(orFilter.getFilterState()));

		/**
		 * The following parses a new Filter object through the parseFilter static
		 * method. we just need to give it the name of a Custom Filter and it will
		 * return an instance of that class. We are using reflection here so use
		 * carefully.
		 */

		/**
		 * Since the following logical filter is an "AND" filter, the previously created
		 * filters like default filters and trueFilter will now return false, since
		 * defaultFilter's state is still false.
		 */

		LogicalFilter andFilter = (LogicalFilter) Filter.parseFilter("LogicalFilter");
		andFilter.addFilter(filters);
		andFilter.matches(user);
		System.out.println("Logical AND filter state: " + Utils.assertFalse(andFilter.getFilterState()));

	}

}
