package estate.util;

import java.util.Map;

public class MapUtil {
	public static <T> T getObject(Map<String,String> params, String key, Class<T> clazz) {
		Object obj = params.getOrDefault(key,null);
		if (obj != null) {
			if (clazz.getTypeName().equals("java.lang.Long")) {
				obj = obj != "" ? Long.valueOf(obj.toString()) : null;
			}
			if (clazz.getTypeName().equals("java.lang.Integer")) {
				obj = obj != "" ? Integer.valueOf(obj.toString()) : null;
			}
			if (clazz.getTypeName().equals("java.lang.String")) {
				obj = obj != "" ? String.valueOf(obj.toString()) : null;
			}
		} else return null;
		return clazz.cast(obj);
	}
}
