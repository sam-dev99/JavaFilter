package filter;

import java.util.Map;

public class TrueFilter extends Filter<Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean state_of_filter = false;

	public TrueFilter() {
		super();
	}

	@Override
	public Boolean matches(Map<String, String> map) {
		this.state_of_filter = true;

		return true;
	}

	@Override
	public Boolean getFilterState() {
		return this.state_of_filter;
	}
	
	public void setFilterState(Boolean state)
	{
		this.state_of_filter = state;
	}

}
