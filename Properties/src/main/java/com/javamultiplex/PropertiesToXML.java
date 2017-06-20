package com.javamultiplex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class PropertiesToXML {

	public static void main(String[] args) throws IOException {
		
		Scanner input=null;
		try
		{
			input=new Scanner(System.in);
			File file=new File("src/main/resources/output.xml");
			FileOutputStream fos=new FileOutputStream(file);
			System.out.println("Enter your name : ");
			String name=input.nextLine();
			System.out.println("Enter your age : ");
			int age=input.nextInt();
			Properties properties=new Properties();
			properties.setProperty("name", name);
			properties.setProperty("age", String.valueOf(age));
			properties.storeToXML(fos, "My information", "UTF-8");
			System.out.println("Conversion has been completed successfull.");
		}finally{
			if(input!=null)
			{
				input.close();
			}
		}
				
	}
	
}
