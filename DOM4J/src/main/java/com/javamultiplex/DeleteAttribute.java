package com.javamultiplex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class DeleteAttribute {

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
			System.out.println("Enter attribute name : ");
			String attributeName=input.next();
			boolean result=deleteAttribute(document, rollNumber,attributeName);
			if(result)
			{
				FileOutputStream fos=new FileOutputStream(file);
				OutputFormat format=OutputFormat.createPrettyPrint();
				XMLWriter writer=new XMLWriter(fos,format);
				writer.write(document);
				System.out.println("Attribute has been deleted successfully.");

			}else{
				System.out.println("Either student not found or attribute not exist.");
			}
		}finally{
			if(input!=null){
				input.close();
			}
		}
		
	}

	private static boolean deleteAttribute(Document document, int rollNumber, String attributeName) {
	
		String expression="/class/student[@rollnumber="+rollNumber+"]";
		Node node=document.selectSingleNode(expression);
		boolean result=false;
		if(node!=null)
		{
			Element element=(Element)node;
			Attribute attr=element.attribute(attributeName);
			if(attr!=null){
				
				element.remove(attr);
				result=true;
			}
		}
		return result;
	}
	
}
