package filter;

import java.util.Map;

public class FalseFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private boolean state_of_filter = false;

	public FalseFilter() {
		super();
	}

	@Override
	public Boolean matches(Map<String, String> map) {
		return false;
	}

//	@Override
//	public Boolean getFilterState() {
//		return this.state_of_filter;
//	}
//
//	public void setFilterState(Boolean state) {
//		this.state_of_filter = state;
//	}

}
