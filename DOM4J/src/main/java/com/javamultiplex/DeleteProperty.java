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

public class DeleteProperty {

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
			boolean result=deleteProperty(document, rollNumber,propertyName);
			if(result)
			{
				FileOutputStream fos=new FileOutputStream(file);
				OutputFormat format=OutputFormat.createPrettyPrint();
				XMLWriter writer=new XMLWriter(fos,format);
				writer.write(document);
				System.out.println("Property has been deleted successfully.");

			}else{
				System.out.println("Either student not found or property not exist.");
			}
		}finally{
			if(input!=null){
				input.close();
			}
		}
		
	}

	private static boolean deleteProperty(Document document, int rollNumber,String propertyName) {
	
		String expression1="/class/student[@rollnumber="+rollNumber+"]/"+propertyName;
		Node node1=document.selectSingleNode(expression1);
		String expression2="/class/student[@rollnumber="+rollNumber+"]";
		Node node2=document.selectSingleNode(expression2);
		boolean result=false;
		if(node1!=null)
		{
			Element element1=(Element)node1;
			Element element2=(Element)node2;
			element2.remove(element1);
			result=true;
		}
		return result;
	}
	
}
