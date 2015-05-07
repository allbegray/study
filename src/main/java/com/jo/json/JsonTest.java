package com.jo.json;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonTest {

	public static void main(String[] args) {
		
		String[] p = {"1", "2"};
//		HashMap<Integer, String> p = new HashMap<Integer, String>();
//		p.put(1, "1");
		
//		ArrayList<Integer> p = new ArrayList<Integer>();
//		p.add(1);
		
		returnJson(p);
	}
	
	private static Object returnJson(Object object) {
		// TODO : json �����
		if (object.equals(null)) {
			System.out.println("���� ���� null �Դϴ�.");
			return object;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();

		if (object instanceof Map) {

			map = (Map<String, Object>) object;

		} else if (object instanceof Collection) {
			Collection<Object> list = (Collection<Object>) object;

			for (Object obj : list) {
				map.put(obj.toString(), obj);
			}
		} else if (object instanceof Byte[] || object instanceof Character[] || object instanceof Short[] || object instanceof Integer[]
				|| object instanceof Long[] || object instanceof Boolean[] || object instanceof Float[] || object instanceof Double[]
				|| object instanceof String[]) {
			
			Object[] array = (Object[]) object;
			for (Object obj : array) {
				map.put(obj.toString(), obj);
			}
			
		} else if (object instanceof Byte || object instanceof Character || object instanceof Short || object instanceof Integer
				|| object instanceof Long || object instanceof Boolean || object instanceof Float || object instanceof Double
				|| object instanceof String) {
			
			map.put(object.toString(), object);
			
		} else {
			Method[] methods = object.getClass().getClassLoader() != null ? object.getClass().getMethods() : object.getClass().getDeclaredMethods();
			
			for (Method method : methods) {
				try {
					if (Modifier.isPublic(method.getModifiers())) {
						String name = method.getName();
						String key = "";
						if (name.startsWith("get")) {
							if ("getClass".equals(name) || "getDeclaringClass".equals(name)) {
								key = "";
							} else {
								key = name.substring(3);
							}
						} else if (name.startsWith("is")) {
							key = name.substring(2);
						}
						
						if (key.length() > 0 && Character.isUpperCase(key.charAt(0)) && method.getParameterTypes().length == 0) {
							if (key.length() == 1) {
								key = key.toLowerCase();
							} else if (Character.isLowerCase(key.charAt(1))) {
								key = key.substring(0, 1).toLowerCase() + key.substring(1);
							}
							
							Object result = method.invoke(object, null);
							if (result != null) {
								map.put(key, result);
							}
						}
					}
				} catch (Exception e) {
				}
				
			}
			
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		
		Iterator<String> keys = map.keySet().iterator();
		
		if (map.size() == 1) {
			String key = (String) keys.next();
			Object value = map.get(key);
			if (value instanceof String) {
				value = "\"" + value +"\"";
			}
			
			sb.append("\"" + key + "\"" + ":" + value);
		} else if (map.size() > 1) {
			
			while (keys.hasNext()) {
				String key = (String) keys.next();
				Object value = map.get(key);
				if (value instanceof String) {
					value = "\"" + value +"\"";
				}
				
				sb.append("\"" + key + "\"" + ":" + value);
				sb.append(", ");
			}
			
			sb.delete(sb.length()-2, sb.length());
		}
		
		sb.append("}");

		System.out.println(sb.toString());

		return map;
	}


}
