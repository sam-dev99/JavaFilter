package filter;

import java.util.HashSet;
import java.util.Map;

public class LogicalFilter extends Filter<Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashSet<Filter> filters;
	private String logic_op;
	private boolean state_of_filter = false;

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
		if (this.logic_op == "and") {

			this.state_of_filter = true;
		}

		else if (this.logic_op == "or") {
			this.state_of_filter = false;
		}

		for (Filter filter : this.filters) {
			if (this.logic_op == "or") {
				filter.matches(map);
				this.state_of_filter = this.state_of_filter || filter.getFilterState();
			} else if (this.logic_op == "and") {
				filter.matches(map);
				this.state_of_filter = this.state_of_filter && filter.getFilterState();

			}
		}

		return this.getFilterState();
	}

	@Override
	public Boolean getFilterState() {
		return this.state_of_filter;
	}

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
