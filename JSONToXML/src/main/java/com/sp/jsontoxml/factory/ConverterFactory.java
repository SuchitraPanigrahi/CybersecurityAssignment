package main.java.com.sp.jsontoxml.factory;

public class ConverterFactory {
	public XMLJSONConverterI getConverter() {
		return new ConverterClass();
	}
}
