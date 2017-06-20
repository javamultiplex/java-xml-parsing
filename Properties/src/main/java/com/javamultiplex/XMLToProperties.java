package com.javamultiplex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class XMLToProperties {

	
	public static void main(String[] args) throws InvalidPropertiesFormatException, IOException {
		
		Properties properties=new Properties();
		File file=new File("src/main/resources/output.xml");
		FileInputStream fis=new FileInputStream(file);
		properties.loadFromXML(fis);
		String name=properties.getProperty("name");
		System.out.println("Name : "+name);
		int age=Integer.parseInt(properties.getProperty("age"));
		System.out.println("Age : "+age);
	}
	
	
}
