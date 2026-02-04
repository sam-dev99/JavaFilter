package filter;

import java.util.HashSet;
import java.util.Map;

public class LogicalFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashSet<Filter> filters;
	private String logic_op;
	private boolean state_of_current_filter = false;

	public LogicalFilter() {
		super();
		this.filters = null;
	}

	public LogicalFilter(HashSet<Filter> filters, String logicalOperation) {
		super();
		this.filters = filters;
		this.logic_op = logicalOperation;
	}

	@Override
	public Boolean matches(Map<String, String> map) {
		/**
		 * we need to initialize the state of the filter according to the given logical
		 * operation The reason is that if we have an "AND" operation and the original
		 * state is "false", then no matter what the state of the internal filters the
		 * result will always be "false". It is the opposite for the "or" operator.
		 **/

		// we initialize the state of filter to true with an "AND" operator, that way
		// "true and false"
		// will result to false and "true and true" results in true
		if (this.logic_op.equalsIgnoreCase("and")) {

			this.state_of_current_filter = true;
		}

		else if (this.logic_op.equalsIgnoreCase("or")) {
			this.state_of_current_filter = false;
		}

		for (Filter filter : this.filters) {
			if (this.logic_op.equalsIgnoreCase("or")) {
				boolean cur_filter_result = filter.matches(map);
				this.state_of_current_filter = this.state_of_current_filter || cur_filter_result;
			} else if (this.logic_op.equalsIgnoreCase("and")) {
				boolean cur_filter_result = filter.matches(map);
				this.state_of_current_filter = this.state_of_current_filter && cur_filter_result;

			}
		}

		return this.state_of_current_filter;
	}

//	@Override
//	public Boolean getFilterState() {
//		return this.state_of_filter;
//	}

	public void addFilter(HashSet<Filter> filters) {
		if (this.filters == null) {
			this.filters = filters;
		} else {
			for (Filter filter : filters) {
				this.filters.add(filter);
			}
		}
	}

	public void setLogicalOperation(String logicalOperation) {
		this.logic_op = logicalOperation;
	}

}
