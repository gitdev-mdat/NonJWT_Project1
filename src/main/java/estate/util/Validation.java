package estate.util;

public class Validation {
	public static boolean isValid(String params) {
		return params != null && !params.trim().isEmpty();
	}
}
