package main.java.com.sp.jsontoxml.app;

import java.io.File;

import main.java.com.sp.jsontoxml.factory.ConverterFactory;
import main.java.com.sp.jsontoxml.factory.XMLJSONConverterI;

public class ConverterApp {

	public static void main(String[] args) {
//		ConverterClass converter = new ConverterClass();
//		converter.convertJSONtoXML(new File(args[0]), new File(args[1]));
		
		XMLJSONConverterI converter = new ConverterFactory().getConverter();
		converter.convertJSONtoXML(new File(args[0]), new File(args[1]));

	}

}
