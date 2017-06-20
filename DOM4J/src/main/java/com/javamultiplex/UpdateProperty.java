package com.javamultiplex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class UpdateProperty {

	public static void main(String[] args) throws DocumentException, IOException {
		
		Scanner input=null;
		try
		{
			input=new Scanner(System.in);
			File file = new File("src/main/resources/student.xml");
			SAXReader reader=new SAXReader();
			Document document=reader.read(file);
			System.out.println("Enter student roll number : ");
			int rollNumber=input.nextInt();
			System.out.println("Enter property name : ");
			String propertyName=input.next();
			System.out.println("Enter property value : ");
			String propertyValue=input.next();
			boolean result=updateProperty(document, rollNumber,propertyName,propertyValue);
			if(result)
			{
				FileOutputStream fos=new FileOutputStream(file);
				OutputFormat format=OutputFormat.createPrettyPrint();
				XMLWriter writer=new XMLWriter(fos,format);
				writer.write(document);
				System.out.println("Property has been updated successfully.");

			}else{
				System.out.println("Either student not found or property not exist.");
			}
		}finally{
			if(input!=null){
				input.close();
			}
		}
		
	}

	private static boolean updateProperty(Document document, int rollNumber,String propertyName, String propertyValue) {
	
		String expression="/class/student[@rollnumber="+rollNumber+"]/"+propertyName;
		Node node=document.selectSingleNode(expression);
		boolean result=false;
		if(node!=null)
		{
			Element element=(Element)node;
			element.setText(propertyValue);
			result=true;
		}
		return result;
	}
	
}
