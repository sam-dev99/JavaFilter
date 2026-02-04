package filter;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public abstract class Filter implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected final static String packageName = "filter";
	protected String regex = null;

	Filter() {

	}

	public abstract Boolean matches(Map<String, String> map);

//	public abstract Boolean getFilterState();

	public static Object parseFilter(String filterName) {
//		String className = getClass().getName();
//		System.out.println(filterName);
		try {
			Filter filter2 = (Filter) Class.forName(packageName + "." + filterName).getDeclaredConstructor()
					.newInstance();
			return filter2;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getRegex() {
		return this.regex;
	}

}
