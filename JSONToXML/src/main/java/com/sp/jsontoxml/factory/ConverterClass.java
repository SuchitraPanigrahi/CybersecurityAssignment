package main.java.com.sp.jsontoxml.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class ConverterClass implements XMLJSONConverterI {

	@Override
	public void convertJSONtoXML(File jsonFile, File xmlFile) {
		StringBuilder sb = new StringBuilder();
        try (JsonReader jsonReaderObj = new JsonReader(new FileReader(jsonFile))) {
            sb.append(returnTag(jsonReaderObj.peek())).append(">"); //<object>
            while (true) {
                JsonToken token = jsonReaderObj.peek();
                switch (token) {
                    case BEGIN_OBJECT:
                        jsonReaderObj.beginObject();                
                        if (!JsonToken.NAME.equals(jsonReaderObj.peek())) {
                            sb.append(returnTag(jsonReaderObj.peek())).append(">");
                        }
                        break;
                    case END_OBJECT:
                        jsonReaderObj.endObject();
                        sb.append("</object>");
                        break;
                    case BEGIN_ARRAY:
                        jsonReaderObj.beginArray();
                        if (!JsonToken.NAME.equals(jsonReaderObj.peek())) {
                            sb.append(returnTag(jsonReaderObj.peek())).append(">");
                        }
                        break;
                    case END_ARRAY:
                        jsonReaderObj.endArray();
                        sb.append("</array>");
                        break;
                    case NAME:
                        String name = jsonReaderObj.nextName();
                        sb.append(returnTag(jsonReaderObj.peek())).append(" name=\"").append(name).append("\">");
                        break;
                    case STRING:
                        String s = jsonReaderObj.nextString();
                        sb.append(s).append("</string>");
                        break;
                    case NUMBER:                   	
                        String n = jsonReaderObj.nextString();          
                        sb.append(n).append("</number>");
                        break;
                    case BOOLEAN:
                        boolean b = jsonReaderObj.nextBoolean();
                        sb.append(b).append("</boolean>");
                        break;
                    case NULL:
                        jsonReaderObj.nextNull(); // or jsonReaderObj.skipValue();
                        sb.append("</null>");
                        break;
                    case END_DOCUMENT:
                        //System.out.println(sb.toString());
                        try (FileWriter fileWriter = new FileWriter(xmlFile);
                        		PrintWriter printWriter = new PrintWriter(fileWriter)) {
                        	printWriter.print(sb.toString());
                        } catch (Exception e) {
                			e.printStackTrace();
                		}
                        return;
                }                
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private String returnTag(JsonToken val) {
        switch (val) {
            case BEGIN_OBJECT:
                return "<object";
            case BEGIN_ARRAY:
                return "<array";
            case STRING:
                return "<string";
            case NUMBER:
                return "<number";
            case BOOLEAN:
                return "<boolean";
            case NULL:
                return "<null";
            default:
                return "";
        }
    }

}
