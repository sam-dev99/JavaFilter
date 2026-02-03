package filter;

import java.util.HashMap;
import java.util.Map;

import org.javatuples.Pair;

import Misc.Utils;

public class DefaultFilter extends Filter<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean state_of_filter = false;
	private HashMap<String, Pair<String, String>> key_operation_pair = null;

	public DefaultFilter() {
		super();
		if (this.key_operation_pair == null) {
			this.key_operation_pair = new HashMap<String, Pair<String, String>>();
		}

	}

	public DefaultFilter(String key, String operation, String value) {
		super();
		if (this.key_operation_pair == null) {
			this.key_operation_pair = new HashMap<String, Pair<String, String>>();
		}
		addFilterRule(key, operation, value);
	}

	public DefaultFilter(String key, String operation, String value, String regex) {
		this(key, operation, value);
		this.regex = regex;
	}

	@Override
	public String matches(Map<String, String> map) {
		for (String property : map.keySet()) {
			// First we check if the property matches a Regular expression
			if (this.getRegex() != null) {
				if (property.matches(this.getRegex())) {
					this.state_of_filter = true;
					return property + " " + map.get(property).toLowerCase();
				}
			}

			// if the key is in our set of rules, then we check the operation and value pair
			// and compute a conditional with the new value
			if (this.key_operation_pair.containsKey(property)) {
				Pair<String, String> operation = this.key_operation_pair.get(property);

				// check if value field is only numeric and cast to integer

				String op = operation.getValue0();
				String stored_value = operation.getValue1().toLowerCase();
				String input_value = map.get(property).toLowerCase();

				// values are numeric
				if (!(Utils.isAlphaNumeric(stored_value) && Utils.isAlphaNumeric(input_value))) {
					int stored_val = Integer.parseInt(stored_value);
					int input_val = Integer.parseInt(input_value);

					if (op == "=") {
						this.state_of_filter = (stored_val == input_val);
					} else if (op == ">") {
						this.state_of_filter = (input_val > stored_val);
					}

					else if (op == ">=") {
						this.state_of_filter = (input_val >= stored_val);
					}

					else if (op == "<") {
						this.state_of_filter = (input_val < stored_val);
					}

					else if (op == "<=") {
						this.state_of_filter = (input_val <= stored_val);
					}
				} else {
					this.state_of_filter = stored_value.compareTo(input_value) == 0 ? true : false;
				}

				return this.state_of_filter ? (property + " " + input_value) : "No matches Found";
			}
		}

		return "No matches Found";
	}

	@Override
	public Boolean getFilterState() {
		return this.state_of_filter;
	}

	public void addFilterRule(String property, String operation, String value) {
		this.key_operation_pair.put(property, new Pair<String, String>(operation, value));
	}

	public Boolean checkIfPropertyExists(String property) {
		return this.key_operation_pair.containsKey(property);
	}

	public HashMap<String, Pair<String, String>> getFilterRules() {
		return this.key_operation_pair;
	}
	
	public void setFilterState(Boolean state)
	{
		this.state_of_filter = state;
	}

//	@Override
//	public String toString()
//	{
//		
//		System.out.println("we are inside tostring");
////		return "{"
//		String className = getClass().getName();
//		try {
//			Constructor<?> classT = getClass().getConstructor();
//			Parameter[] parameters = classT.getParameters();
//			for (Parameter parameter : parameters)
//			{
//				System.out.println("name of parameter: " + parameter.getName());
//			}
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		return className;
//	}
}
