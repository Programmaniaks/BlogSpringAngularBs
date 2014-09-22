package com.shinzul.blog.jackson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateAsStringSerializer extends JsonSerializer<Date>{
	
	private static final SimpleDateFormat DATE_FOMATTER = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");

	@Override
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jgen.writeString(DATE_FOMATTER.format(value));
		
	}

}
