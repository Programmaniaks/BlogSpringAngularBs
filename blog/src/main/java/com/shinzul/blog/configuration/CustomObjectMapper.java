package com.shinzul.blog.configuration;

import java.math.BigInteger;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

import com.shinzul.blog.jackson.BigIntegerAsStringSerializer;

public class CustomObjectMapper extends ObjectMapper {

	public CustomObjectMapper() {
		CustomSerializerFactory sf = new CustomSerializerFactory();
//		sf.addSpecificMapping(BigInteger.class,
//				new BigIntegerCustomSerializer());
		this.setSerializerFactory(sf);
	}

}
