package com.jo.serializer;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JsonSerializerTest {
	
	private JsonSerializer jsonSerializer = null;
	
	@Before
	public void setup() {
		jsonSerializer = new JsonSerializer();
	}
	
	@Test
	public void mapTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("1", 1);
		map.put("2", 1);
		String json = jsonSerializer.toJsonString(map);
		Assert.assertTrue(json.equals("{\"1\":1, \"2\":1}"));
	}
	
	@Test
	public void listTest() {
		// TODO
	}

}
