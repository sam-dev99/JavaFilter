package Misc;

public final class Utils {

	public static boolean isAlphaNumeric(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i))) {
				return true;
			}
		}

		return false;
	}

	public static boolean assertTrue(boolean condition) {
		return condition == true;
	}

	public static boolean assertFalse(boolean condition) {
		return condition == false;
	}

}
